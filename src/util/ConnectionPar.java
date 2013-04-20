/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author snowman
 */
public class ConnectionPar {
    //"jdbc:oracle:thin:@localhost:1521:orcl","sys as SYSDBA","admin");
    private String server="localhost";
    private String port="1521";
    private String sid="orcl";
    private String username="sys as SYSDBA";
    private String password ="admin";
    
 
       public Connection getConn(){
        Connection con = null;
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
        }
        catch(ClassNotFoundException e){
            System.out.println("class not found");
             
        }
        
        try {
            String constr = "jdbc:oracle:thin:@" + server + ":" + port + ":" + sid;
            con = DriverManager.getConnection(constr,username,password);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        
        return con;
    }
}
