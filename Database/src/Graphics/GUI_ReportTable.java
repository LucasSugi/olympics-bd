/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics;

import Database.DB_OracleConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lgome
 */
public class GUI_ReportTable extends javax.swing.JFrame {
    private DB_OracleConnection db;
    /**
     * Creates new form GUI_ReportTable
     */
    public GUI_ReportTable() {
        initComponents();
    }
    
    public void setConnection(DB_OracleConnection connection) {
        db = connection;
    }
    
    public void setDescription(String desc) {
        labelDescricao.setText(desc);
    }
    
    public boolean loadReport1(String modalidade, String medico, String treinador) {
        String query = "SELECT DISTINCT ATLETA.NOME, ATLETA.NUMERO_PASSAPORTE, ATLETA.NACAO, ATLETA.DATA_NASCIMENTO "
                + "FROM ATLETA, DISPUTA, CONSULTA, REALIZA, MEDICO, ROTINA_TREINO, PREPARADOR, MODALIDADE "
                + "WHERE "
                + "MODALIDADE.NOME = '" + modalidade + "' AND "
                + "ATLETA.NUMERO_PASSAPORTE = DISPUTA.ATLETA AND "
                + "DISPUTA.MODALIDADE = MODALIDADE.CODIGO AND "
                + "MEDICO.NOME = '" + medico + "' AND "
                + "(CONSULTA.MEDICO = MEDICO.ID OR REALIZA.MEDICO = MEDICO.ID) AND "
                + "ROTINA_TREINO.ATLETA = ATLETA.NUMERO_PASSAPORTE AND "
                + "ROTINA_TREINO.PREPARADOR = PREPARADOR.CODIGO AND "
                + "PREPARADOR.NOME = '" + treinador + "'";
        
        try {
            PreparedStatement st = db.connection.prepareStatement(query);
            ResultSet res = st.executeQuery();
            String[] column_names = {"Nome", "Passaporte", "Nação", "Data de Nascimento"};
            Object[] row = new Object[4];
            
            DefaultTableModel model = new DefaultTableModel(column_names, 0);            
            while (res.next()) {
                row[0] = res.getString("NOME");
                row[1] = res.getString("NUMERO_PASSAPORTE");
                row[2] = res.getString("NACAO");
                row[3] = res.getDate("DATA_NASCIMENTO");
                
                model.addRow(row);
            }
            
            tableReportData.setModel(model);
        } catch(Exception e) {
            JOptionPane.showMessageDialog(this, e.toString(), "Erro!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    
    public boolean loadReport2(int nAtletas, String nacao) {
        String query = "SELECT MEDICO.NOME, COUNT(ATLETA.NUMERO_PASSAPORTE) AS TOTAL, ATLETA.NACAO "
                + "FROM MEDICO, ATLETA, CONSULTA "
                + "WHERE CONSULTA.MEDICO = MEDICO.ID AND CONSULTA.ATLETA = ATLETA.NUMERO_PASSAPORTE AND "
                + "ATLETA.NACAO = '" + nacao + "' "
                + "GROUP BY ATLETA.NACAO, MEDICO.NOME "
                + "HAVING COUNT(ATLETA.NUMERO_PASSAPORTE) >= " + nAtletas;
        
        try {
            PreparedStatement st = db.connection.prepareStatement(query);
            ResultSet res = st.executeQuery();
            
            String[] cols = {"Nome", "Total"};
            DefaultTableModel model = new DefaultTableModel(cols, 0);
            Object[] row = new Object[2];
            
            while (res.next()) {
                row[1] = res.getInt("TOTAL");
                row[0] = res.getString("NOME");
                
                model.addRow(row);
            }
            
            tableReportData.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.toString(), "Erro!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return false;
    }
    
    public boolean loadReport4() {
        String query = "";
        return false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tableReportData = new javax.swing.JTable();
        btnGerarPDF = new javax.swing.JButton();
        labelDescricao = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tableReportData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tableReportData);

        btnGerarPDF.setText("Gerar PDF");
        btnGerarPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGerarPDFActionPerformed(evt);
            }
        });

        labelDescricao.setText("<Descrição do Relatório>");
        labelDescricao.setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGerarPDF)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelDescricao)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelDescricao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGerarPDF)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGerarPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGerarPDFActionPerformed
        JFileChooser fc = new JFileChooser();
        int rVal = fc.showSaveDialog(this);
        if (rVal == JFileChooser.APPROVE_OPTION) {
            String pdffile = fc.getSelectedFile().getAbsolutePath();
            
            try {
                makePDF(pdffile);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.toString(), "Erro!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnGerarPDFActionPerformed

    private void makePDF(String where) throws Exception {
        // muitas dependências.
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI_ReportTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI_ReportTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI_ReportTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI_ReportTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI_ReportTable().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGerarPDF;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelDescricao;
    private javax.swing.JTable tableReportData;
    // End of variables declaration//GEN-END:variables
}