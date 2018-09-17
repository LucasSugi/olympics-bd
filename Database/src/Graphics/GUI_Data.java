/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kelvi
 */
public class GUI_Data {

    public ArrayList<String> passaporte;
    public ArrayList<String> nome;
    public ArrayList<String> nacao;
    public ArrayList<String> datanasc;
    public ArrayList<Integer> altura;
    public ArrayList<Integer> peso;
    public ArrayList<String> sexo;

    public Connection c;
    public int nRegisters;

    public GUI_Data(Connection c) {
        this.sexo = new ArrayList<String>();
        this.peso = new ArrayList<Integer>();
        this.altura = new ArrayList<Integer>();
        this.datanasc = new ArrayList<String>();
        this.nacao = new ArrayList<String>();
        this.nome = new ArrayList<String>();
        this.passaporte = new ArrayList<String>();
        this.c = c;
        this.update();
        this.nRegisters = this.passaporte.size();
    }

    public void update() {

        ArrayList<String> passaporte = new ArrayList<String>();
        ArrayList<String> nome = new ArrayList<String>();
        ArrayList<String> nacao = new ArrayList<String>();
        ArrayList<String> datanasc = new ArrayList<String>();
        ArrayList<Integer> altura = new ArrayList<Integer>();
        ArrayList<Integer> peso = new ArrayList<Integer>();
        ArrayList<String> sexo = new ArrayList<String>();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Statement stmt;
        ResultSet rs;
        
        try {
            stmt = this.c.createStatement();
            rs = stmt.executeQuery("SELECT * FROM ATLETA");
        } catch (SQLException ex) {
            Logger.getLogger(GUI_Start.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }        
        
        int p, a;
        
        try {            
            while (rs.next()) {                
                passaporte.add(rs.getString("NUMERO_PASSAPORTE"));
                nome.add(rs.getString("NOME"));
                datanasc.add(df.format(rs.getDate("DATA_NASCIMENTO")));
                a = rs.getInt("ALTURA");
                if(a == 0) a = -1;
                altura.add(a);
                p = rs.getInt("PESO");
                if(p == 0) p = -1;
                peso.add(p);
                sexo.add(rs.getString("SEXO"));
                nacao.add(rs.getString("NACAO"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GUI_Start.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.passaporte = passaporte;
        this.nome = nome;
        this.datanasc = datanasc;
        this.altura = altura;
        this.peso = peso;
        this.sexo = sexo;
        this.nacao = nacao;     
        this.nRegisters = this.passaporte.size();
    }
}
