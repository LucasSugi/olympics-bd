package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kelvi
 */
public class DB_OracleConnection {
    
    private String host;
    private String user;
    private String password;
        
    public Connection connection;
    private boolean isConnected;
    
    public DB_OracleConnection(String host, String user, String password){
        this.host = host;        
        this.user = user;
        this.password = password;
        this.isConnected = false;
    }
    
    public boolean connect(){        
        
        if(this.isConnected == true) return true;
        
        String serverName = this.host;
        String portNumber = "15215";
        String service = "orcl";
        String userName = this.user;
        String passwordName = this.password;
        
        String strConnection = "jdbc:oracle:thin:@" + serverName + ":" + portNumber + ":" + service;
    
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
            this.connection = DriverManager.getConnection(strConnection, userName, passwordName);
            this.isConnected = true;
        } catch(ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e){
            System.out.println(e.getMessage() + " " + e.initCause(e));
            this.isConnected = false;
        }
        
        return this.isConnected;
    }
     
    public boolean disconnect() {
        
        if(this.isConnected == false) return true;
        
        try {            
            this.connection.close();
            this.isConnected = false;            
        } catch(SQLException e){
            System.out.println(e.getMessage());
            this.isConnected = true;
        }
        
        return !(this.isConnected);
    }         
}
