/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package expensetracker2;
import java.sql.*;
import java.util.logging.*;


/**
 *
 * @author Acer
 */
public class DBConnection {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/financetracker?zeroDateTimeBehavior=CONVERT_TO_NULL";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private Connection conn; 
    
    public DBConnection(){
        conn = getconn();
    }
    public Connection getconn(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn=DriverManager.getConnection(JDBC_URL,USERNAME ,"");
            System.out.println("Successfull");
        }
        catch(SQLException ex){
            System.out.println(ex);
        }
        catch(ClassNotFoundException ex){
            System.out.println(ex);
        }
     return conn;  
    }
    
    public boolean excute(String query){
        boolean status=false;
        
        try{
            Statement stmt = (Statement) conn.createStatement();
            stmt.executeUpdate(query);
            status = true;
        }
        catch(SQLException ex){
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE,null,ex);
        }
        return status;
    }
    public static void main(String[] args) {
        DBConnection d1 = new DBConnection();
    }
}
