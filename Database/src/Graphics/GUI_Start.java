/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics;

import Database.DB_OracleConnection;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author kelvi
 */
public class GUI_Start extends JFrame implements ActionListener {
    
    private int Width;
    private int Height;
    private DB_OracleConnection db;
    private Connection c;
    private GUI_Data data;    
    private int current;
    private boolean editing;
    private boolean creating;
    
    // GUI
    private JPanel jp;
    private JLabel jl_description;    
    private JButton jb_add;
    private JButton jb_edit;
    private JButton jb_next;
    private JButton jb_previous;
    private JButton jb_delete;
    private JButton jb_report;
    private JButton jb_confirm;
    
    private JLabel jl_nacao;
    private JLabel jl_passaporte;
    private JLabel jl_nome;
    private JLabel jl_datanasc;
    private JLabel jl_altura;
    private JLabel jl_peso;
    private JLabel jl_sexo;
        
    private JComboBox jtf_nacao;
    private JTextField jtf_passaporte;
    private JTextField jtf_nome;
    private JTextField jtf_datanasc;
    private JTextField jtf_altura;
    private JTextField jtf_peso;
    private JComboBox jtf_sexo;

    public GUI_Start(DB_OracleConnection db){
        super("Start");
        
        this.data = new GUI_Data(db.connection);
        if(this.data.nRegisters == 0) this.current = -1;        
        else this.current = 0;
        this.db = db;
        this.c = db.connection;        
        this.editing = false;
        this.creating = false;
        this.createElements();
        this.configWindow();
        this.configElements();
        this.addElements();
        this.fillTable();
    }
    
    /**
     * Cria/Inicializa os elementos que serao exibidos no Frame.
     */
    private void createElements(){                
        this.Width = (int) 1024;
        this.Height = (int) 550;        
        this.jp = (JPanel) this.getContentPane();
        this.jl_description = new JLabel("CADASTRO DE ATLETAS");
        this.jb_add = new JButton(new ImageIcon("pictures/add.png"));
        this.jb_edit = new JButton(new ImageIcon("pictures/edit.png"));
        this.jb_delete = new JButton(new ImageIcon("pictures/delete.png"));
        this.jb_next = new JButton(new ImageIcon("pictures/next.png"));
        this.jb_previous = new JButton(new ImageIcon("pictures/previous.png"));
        this.jb_report = new JButton(new ImageIcon("pictures/report.png"));
        this.jb_confirm = new JButton(new ImageIcon("pictures/confirm.png"));
        this.jl_nacao = new JLabel("Nação");
        this.jl_passaporte = new JLabel("Passaporte");
        this.jl_nome = new JLabel("Nome");
        this.jl_datanasc = new JLabel("Data de Nascimento");
        this.jl_altura = new JLabel("Altura");
        this.jl_peso = new JLabel("Peso");
        this.jl_sexo = new JLabel("Sexo");
        
        this.jp.setBackground(new Color(192, 192, 192));
        
        this.jtf_nacao = new JComboBox();
        this.jtf_passaporte = new JTextField();
        this.jtf_nome = new JTextField();
        this.jtf_datanasc = new JTextField();
        this.jtf_altura = new JTextField();
        this.jtf_sexo = new JComboBox();
        this.jtf_peso = new JTextField();                
    }
    
    /**
     * Configura os parametros da Janela.
     */
    private void configWindow(){
        //Titulo da Janela
        this.setTitle("Cadastro de Atletas");
        //Layout da Janela
        this.setLayout(null);
        //Tamanho da Janela        
        this.setSize(this.Width, this.Height);        
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter(){            
            @Override
            public void windowClosing(WindowEvent evt){
                closeWindow();                
            }
        });
        //Localizacao da Janela (Centro)
        this.setLocationRelativeTo(null);        
    }
    
    public void closeWindow(){
        try {
            this.c.close();
            this.db.disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(GUI_Start.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.dispose();
    }
    
    /**
     * Configura os elementos da que serao exibidos na Janela.
     */
    private void configElements(){                       
        
        //Label "CADASTRO DE ATLETAS"
        this.jl_description.setForeground(Color.WHITE);
        this.jl_description.setBounds(0, 20, this.Width, 80);
        this.jl_description.setHorizontalAlignment(JLabel.CENTER);
        this.jl_description.setFont(new Font("Dialog", Font.BOLD, 45));
        this.jl_description.setOpaque(true);
        this.jl_description.setBackground(new Color(0, 0, 0, 150));                
        
        //Botao "Add"       
        this.jb_add.setBounds(396, 120, 64, 64);
        this.jb_add.setHorizontalAlignment(JLabel.CENTER);        
        this.jb_add.setToolTipText("Adicionar");
        this.jb_add.setOpaque(false);
        this.jb_add.setContentAreaFilled(false);        
        this.jb_add.setBorderPainted(false);                
        this.jb_add.addActionListener(this);
        this.jb_add.setActionCommand("add");
        
        //Botao "Edit"       
        this.jb_edit.setBounds(480, 120, 64, 64);
        this.jb_edit.setHorizontalAlignment(JLabel.CENTER);        
        this.jb_edit.setToolTipText("Editar");
        this.jb_edit.setOpaque(false);
        this.jb_edit.setContentAreaFilled(false);        
        this.jb_edit.setBorderPainted(false);                
        this.jb_edit.addActionListener(this);
        this.jb_edit.setActionCommand("edit");
        
        //Botao "Delete"       
        this.jb_delete.setBounds(564, 120, 64, 64);
        this.jb_delete.setHorizontalAlignment(JLabel.CENTER);        
        this.jb_delete.setToolTipText("Remover");
        this.jb_delete.setOpaque(false);
        this.jb_delete.setContentAreaFilled(false);        
        this.jb_delete.setBorderPainted(false);               
        this.jb_delete.addActionListener(this);
        this.jb_delete.setActionCommand("delete");
        
        //Botao "Next"       
        this.jb_next.setBounds(522, 425, 64, 64);
        this.jb_next.setHorizontalAlignment(JLabel.CENTER);        
        this.jb_next.setToolTipText("Proximo");
        this.jb_next.setOpaque(false);
        this.jb_next.setContentAreaFilled(false);        
        this.jb_next.setBorderPainted(false);                
        this.jb_next.addActionListener(this);
        this.jb_next.setActionCommand("next");
        
        //Botao "Previous"       
        this.jb_previous.setBounds(438, 425, 64, 64);
        this.jb_previous.setHorizontalAlignment(JLabel.CENTER);        
        this.jb_previous.setToolTipText("Anterior");
        this.jb_previous.setOpaque(false);
        this.jb_previous.setContentAreaFilled(false);        
        this.jb_previous.setBorderPainted(false);                
        this.jb_previous.addActionListener(this);
        this.jb_previous.setActionCommand("previous");
        
        //Botao "Report"       
        this.jb_report.setBounds(900, 120, 64, 64);
        this.jb_report.setHorizontalAlignment(JLabel.CENTER);        
        this.jb_report.setToolTipText("Relatório");
        this.jb_report.setOpaque(false);
        this.jb_report.setContentAreaFilled(false);        
        this.jb_report.setBorderPainted(false);                
        this.jb_report.addActionListener(this);
        this.jb_report.setActionCommand("report");
        
        //Botao "Confirm"       
        this.jb_confirm.setBounds(900, 425, 64, 64);
        this.jb_confirm.setHorizontalAlignment(JLabel.CENTER);        
        this.jb_confirm.setToolTipText("Confirmar");
        this.jb_confirm.setOpaque(false);
        this.jb_confirm.setContentAreaFilled(false);        
        this.jb_confirm.setBorderPainted(false);                
        this.jb_confirm.addActionListener(this);
        this.jb_confirm.setActionCommand("confirm");
        
        //Label "Nacao"
        this.jl_nacao.setForeground(Color.BLACK);
        this.jl_nacao.setBounds(32, 210, 70, 50);
        this.jl_nacao.setHorizontalAlignment(JLabel.CENTER);
        this.jl_nacao.setFont(new Font("Dialog", Font.BOLD, 20));                                
        this.jl_nacao.setBackground(Color.WHITE);
                
        // Text Field "Nacao"        
        this.jtf_nacao.setBounds(112, 210, 460, 50);
        this.jtf_nacao.setFont(new Font("Dialog", Font.BOLD, 20));                
        this.fillNacao();
        
        //Label "Passaporte"
        this.jl_passaporte.setForeground(Color.BLACK);
        this.jl_passaporte.setBounds(592, 210, 120, 50);
        this.jl_passaporte.setHorizontalAlignment(JLabel.CENTER);
        this.jl_passaporte.setFont(new Font("Dialog", Font.BOLD, 20));                
        
        // Text Field "Passaporte"
        this.jtf_passaporte.setBounds(722, 210, 245, 50);
        this.jtf_passaporte.setFont(new Font("Dialog", Font.BOLD, 20));
        this.jtf_passaporte.setHorizontalAlignment(JTextField.CENTER);
        
        //Label "Nome"
        this.jl_nome.setForeground(Color.BLACK);
        this.jl_nome.setBounds(30, 280, 70, 50);
        this.jl_nome.setHorizontalAlignment(JLabel.CENTER);
        this.jl_nome.setFont(new Font("Dialog", Font.BOLD, 20));                                        
        
        // Text Field "Nome"
        this.jtf_nome.setBounds(112, 280, 855, 50);
        this.jtf_nome.setFont(new Font("Dialog", Font.BOLD, 20));
        this.jtf_nome.setHorizontalAlignment(JTextField.CENTER);
        
        //Label "Datanasc"
        this.jl_datanasc.setForeground(Color.BLACK);
        this.jl_datanasc.setBounds(32, 350, 200, 50);
        this.jl_datanasc.setHorizontalAlignment(JLabel.CENTER);
        this.jl_datanasc.setFont(new Font("Dialog", Font.BOLD, 20));                                        
        
        // Text Field "Datanasc"
        this.jtf_datanasc.setBounds(242, 350, 200, 50);
        this.jtf_datanasc.setFont(new Font("Dialog", Font.BOLD, 20));
        this.jtf_datanasc.setHorizontalAlignment(JTextField.CENTER);
        
        //Label "Altura"
        this.jl_altura.setForeground(Color.BLACK);
        this.jl_altura.setBounds(462, 350, 70, 50);
        this.jl_altura.setHorizontalAlignment(JLabel.CENTER);
        this.jl_altura.setFont(new Font("Dialog", Font.BOLD, 20));                                        
        
        // Text Field "Altura"
        this.jtf_altura.setBounds(552, 350, 70, 50);
        this.jtf_altura.setFont(new Font("Dialog", Font.BOLD, 20));
        this.jtf_altura.setHorizontalAlignment(JTextField.CENTER);
        
        //Label "Peso"
        this.jl_peso.setForeground(Color.BLACK);
        this.jl_peso.setBounds(642, 350, 70, 50);
        this.jl_peso.setHorizontalAlignment(JLabel.CENTER);
        this.jl_peso.setFont(new Font("Dialog", Font.BOLD, 20));                                        
        
        // Text Field "Peso"
        this.jtf_peso.setBounds(722, 350, 70, 50);
        this.jtf_peso.setFont(new Font("Dialog", Font.BOLD, 20));
        this.jtf_peso.setHorizontalAlignment(JTextField.CENTER);
        
        //Label "Sexo"
        this.jl_sexo.setForeground(Color.BLACK);
        this.jl_sexo.setBounds(812, 350, 70, 50);
        this.jl_sexo.setHorizontalAlignment(JLabel.CENTER);
        this.jl_sexo.setFont(new Font("Dialog", Font.BOLD, 20));                                                                                                
        
        // Text Field "Sexo"
        this.jtf_sexo.setBounds(892, 350, 75, 50);
        this.jtf_sexo.setFont(new Font("Dialog", Font.BOLD, 20));
        this.jtf_sexo.addItem("M");
        this.jtf_sexo.addItem("F");        
    }
    
    private void fillNacao(){
        
        String query = "SELECT NOME FROM NACAO";
        
        Statement stmt;
        ResultSet rs;
        
        try {
            stmt = this.c.createStatement();
            rs = stmt.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(GUI_Start.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }        
                
        
        try {            
            while (rs.next()) {                
                this.jtf_nacao.addItem(rs.getString("NOME"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GUI_Start.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /**
     * Adiciona os elementos principais no 'JPanel' principal da janela.
     */
    private void addElements(){        
        this.jp.add(this.jl_description);
        this.jp.add(this.jb_add);
        this.jp.add(this.jb_edit);
        this.jp.add(this.jb_report);
        this.jp.add(this.jb_confirm);
        this.jp.add(this.jb_next);
        this.jp.add(this.jb_previous);
        this.jp.add(this.jb_delete);        
        this.jp.add(this.jl_nacao);
        this.jp.add(this.jl_passaporte);        
        this.jp.add(this.jl_nome);        
        this.jp.add(this.jl_datanasc);        
        this.jp.add(this.jl_altura);        
        this.jp.add(this.jl_peso);        
        this.jp.add(this.jl_sexo);               
        this.jp.add(this.jtf_nacao);               
        this.jp.add(this.jtf_passaporte);        
        this.jp.add(this.jtf_nome);        
        this.jp.add(this.jtf_datanasc);        
        this.jp.add(this.jtf_altura);        
        this.jp.add(this.jtf_peso);        
        this.jp.add(this.jtf_sexo);
    }
    
    public void insertValues() throws ParseException{
               
        String nacao, passaporte, nome, datanasc, altura, peso, sexo;
        boolean h = true, w = true;
        
        nacao = (String) this.jtf_nacao.getSelectedItem();
        passaporte = this.jtf_passaporte.getText();
        nome = this.jtf_nome.getText();
        datanasc = this.jtf_datanasc.getText();
        altura = this.jtf_altura.getText();
        peso = this.jtf_peso.getText();
        sexo = (String) this.jtf_sexo.getSelectedItem();               
        
        if(this.jtf_nacao.getSelectedIndex() == -1){
            JOptionPane.showMessageDialog(this, "Escolha uma Nação", "Faltando dados...", JOptionPane.ERROR_MESSAGE);
            return;
        }        
        
        if(passaporte.isEmpty()){
            JOptionPane.showMessageDialog(this, "Preencha o campo Passaporte", "Faltando dados...", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(nome.isEmpty()){
            JOptionPane.showMessageDialog(this, "Preencha o campo Nome", "Faltando dados...", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(datanasc.isEmpty()){
            JOptionPane.showMessageDialog(this, "Preencha o campo Data de Nascimento", "Faltando dados...", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(altura.isEmpty()){
            h = false;
        }
        
        if(peso.isEmpty()){
            w = false;
        }
        
        if(this.jtf_sexo.getSelectedIndex() == -1){ 
            JOptionPane.showMessageDialog(this, "Escolha um Sexo", "Faltando dados...", JOptionPane.ERROR_MESSAGE);
            return;
        }                       
        
        datanasc = "TO_DATE('" + datanasc + "', 'DD/MM/RRRR')";
                
        String values, columns;
        if(h && w){
            values = "'" + passaporte + "', '" + nome + "', " + datanasc + ", " + altura + ", " + peso + ", '" + sexo + "', '" + nacao + "'";
            columns = "Numero_passaporte, Nome, Data_nascimento, Altura, Peso, Sexo, Nacao";
        }
        else if(h){
            values = "'" + passaporte + "', '" + nome + "', " + datanasc + ", " + peso + ", '" + sexo + "', '" + nacao + "'";
            columns = "Numero_passaporte, Nome, Data_nascimento, Peso, Sexo, Nacao";
        }
        else if(w){
            values = "'" + passaporte + "', '" + nome + "', " + datanasc + ", " + altura + ", '" + sexo + "', '" + nacao + "'";
            columns = "Numero_passaporte, Nome, Data_nascimento, Altura, Sexo, Nacao";
        }
        else{
            values = "'" + passaporte + "', '" + nome + "', " + datanasc + ", '" + sexo + "', '" + nacao + "'";
            columns = "Numero_passaporte, Nome, Data_nascimento, Sexo, Nacao";
        }                
                        
        PreparedStatement st;
        String query = "INSERT INTO ATLETA("+columns+") VALUES(" + values + ")";
        
        System.out.println(query);
        
        try {
            st = this.c.prepareStatement(query);
            int result = st.executeUpdate();
            System.out.println(result);
            if(result > 0){
                this.data.update();
                this.creating = false;
                this.current = this.data.nRegisters - 1;
                this.fillTable();
            }
            st.close();
        } catch ( SQLException e ) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(this, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void alterTable(){
        String nacao, passaporte, nome, datanasc, altura, peso, sexo;
        boolean h = true, w = true;
        
        nacao = (String) this.jtf_nacao.getSelectedItem();        
        passaporte = this.jtf_passaporte.getText();
        nome = this.jtf_nome.getText();
        datanasc = this.jtf_datanasc.getText();
        altura = this.jtf_altura.getText();
        peso = this.jtf_peso.getText();        
        sexo = (String) this.jtf_sexo.getSelectedItem();
                
        if(this.jtf_nacao.getSelectedIndex() == -1){
            JOptionPane.showMessageDialog(this, "Escolha uma Nação", "Faltando dados...", JOptionPane.ERROR_MESSAGE);
            return;
        }                                
        
        if(passaporte.isEmpty()){
            JOptionPane.showMessageDialog(this, "Preencha o campo Passaporte", "Faltando dados...", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(nome.isEmpty()){
            JOptionPane.showMessageDialog(this, "Preencha o campo Nome", "Faltando dados...", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(datanasc.isEmpty()){
            JOptionPane.showMessageDialog(this, "Preencha o campo Data de Nascimento", "Faltando dados...", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(altura.isEmpty()){
            h = false;
        }
        
        if(peso.isEmpty()){
            w = false;
        }
        
        if(this.jtf_sexo.getSelectedIndex() == -1){
            JOptionPane.showMessageDialog(this, "Escolha um Sexo", "Faltando dados...", JOptionPane.ERROR_MESSAGE);
            return;
        }                       
        
        datanasc = "TO_DATE('" + datanasc + "', 'DD/MM/RRRR')";
        
        String query = "UPDATE Atleta SET ";
            
        query += "Nome = '" + nome + "', Data_nascimento = " + datanasc + ", Nacao = '" + nacao + "', Sexo = '" + sexo + "'";
        
        if(!h) altura = "null";
        query += ", Altura = " + altura;       
        if(!w) peso = "null";
        query += ", Peso = " + peso;
        
        query += " WHERE Numero_passaporte = '" + passaporte + "'";
        
        PreparedStatement st;                
        System.out.println(query);
        
        try {
            st = this.c.prepareStatement(query);
            int result = st.executeUpdate();
            System.out.println(result);
            if(result > 0){
                this.data.update();
                this.fillTable();
                this.editing = false;
            }
            st.close();
        } catch ( SQLException e ) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(this, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void fillTable(){                
        
        if(this.current == -1 || this.data.nRegisters == 0){
            this.jtf_nacao.setSelectedItem(-1);        
            this.jtf_passaporte.setText("");        
            this.jtf_nome.setText("");        
            this.jtf_datanasc.setText("");        
            this.jtf_altura.setText("");
            this.jtf_peso.setText("");
            this.jtf_sexo.setSelectedItem(-1);
        } else {        
            int p, a;        
            this.jtf_nacao.setSelectedItem(data.nacao.get(this.current));        
            this.jtf_passaporte.setText(data.passaporte.get(this.current));        
            this.jtf_nome.setText(data.nome.get(this.current));        
            this.jtf_datanasc.setText(data.datanasc.get(this.current));        
            a = data.altura.get(this.current);
            if(a != -1) this.jtf_altura.setText(data.altura.get(this.current).toString());
            else this.jtf_altura.setText("");        
            p = data.peso.get(this.current);        
            if(p != -1) this.jtf_peso.setText(data.peso.get(this.current).toString());
            else this.jtf_peso.setText("");        
            this.jtf_sexo.setSelectedItem(data.sexo.get(this.current));
        }
        
        this.jtf_nacao.setEditable(false);
        this.jtf_nacao.setEnabled(false);
        this.jtf_passaporte.setEditable(false);
        this.jtf_nome.setEditable(false);
        this.jtf_datanasc.setEditable(false);
        this.jtf_altura.setEditable(false);
        this.jtf_peso.setEditable(false);
        this.jtf_sexo.setEditable(false);       
        this.jtf_sexo.setEnabled(false);
    }
    
    void deleteData(){
        
        if(this.data.nRegisters == 0) return;
        
        String passaporte = this.jtf_passaporte.getText();        
        String query = "DELETE FROM ATLETA WHERE NUMERO_PASSAPORTE = '" + passaporte + "'";
        
        PreparedStatement st;                
        System.out.println(query);
        
        try {
            st = this.c.prepareStatement(query);
            int result = st.executeUpdate();
            System.out.println(result);
            if(result > 0){
                if(this.current == this.data.nRegisters - 1) this.current--;
                this.data.update();                
                this.fillTable();                
            }
            st.close();
        } catch ( SQLException e ) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(this, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    void createBlankRegister(){
        this.jtf_nacao.setSelectedIndex(-1);
        this.jtf_nacao.setEnabled(true);
        this.jtf_altura.setText("");
        this.jtf_altura.setEditable(true);
        this.jtf_datanasc.setText("");
        this.jtf_datanasc.setEditable(true);
        this.jtf_nome.setText("");
        this.jtf_nome.setEditable(true);
        this.jtf_passaporte.setText("");
        this.jtf_passaporte.setEditable(true);
        this.jtf_peso.setText("");
        this.jtf_peso.setEditable(true);
        this.jtf_sexo.setSelectedIndex(-1);
        this.jtf_sexo.setEnabled(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {        
        if("add".equals(e.getActionCommand())){
            if(this.editing) this.editing = false;            
            this.createBlankRegister();
            this.creating = true;     
        } else if("edit".equals(e.getActionCommand())){
            if(!this.creating && this.current >= 0){
                this.jtf_altura.setEditable(true);            
                this.jtf_nome.setEditable(true);
                this.jtf_nacao.setEnabled(true);
                this.jtf_sexo.setEnabled(true);                
                this.jtf_peso.setEditable(true);
                this.jtf_datanasc.setEditable(true);            
                this.editing = true;
            }
        } else if("delete".equals(e.getActionCommand())){
            if(this.editing){
                this.editing = false;
                this.fillTable();
            }
            else if(this.creating){
                this.creating = false;
                this.current = 0;
                this.fillTable();
            } else if(this.data.nRegisters > 0) this.deleteData();            
        } else if("next".equals(e.getActionCommand())){
            if(this.editing) this.editing = false;
            if(this.creating){
                this.creating = false;
                this.current = -1;
            }
            if(this.current < this.data.nRegisters - 1){
                this.current++;
                this.fillTable();                
            }            
        } else if("previous".equals(e.getActionCommand())){
            if(this.editing) this.editing = false;
            if(this.creating){
                this.creating = false;
                this.current = 1;
            }
            if(this.current > 0){
                this.current--;
                this.fillTable();
            }            
        } else if("confirm".equals(e.getActionCommand())){
            if(this.editing){               
                this.alterTable();
            }
            else if(this.creating){
                try {
                    this.insertValues();
                } catch (ParseException ex) {
                    Logger.getLogger(GUI_Start.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
                }
            }                      
        } else if("report".equals(e.getActionCommand())){            
            if(this.editing) this.editing = false;            
            if(this.creating){
                this.creating = false;
                this.current = 0;
                this.fillTable();
            }
            GUI_Report rep = new GUI_Report();
            rep.setConnection(db);
            rep.setVisible(true);
        }
    }
    
    public static void main(String args[]){
       
        DB_OracleConnection db = new DB_OracleConnection("grad.icmc.usp.br", "9293178", "a");
        db.connect();                                               
                        
        GUI_Start frame = new GUI_Start(db);
        frame.setVisible(true);                
    }
    
}
