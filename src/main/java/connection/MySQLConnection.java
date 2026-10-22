package connection;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author sesi2dib
 */
public class MySQLConnection {
    
    // Define Strings de conexao com o banco
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://172.16.0.30:3306/leonardo_dukemarket";
    
    private static final String USER = "leonardo";
    private static final String PASS = "2diSenai";
    
    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);
        }
        catch (SQLException ex) {
            throw new RuntimeException("Erro de conexao com o BD. verifique!", ex);
        }
        catch (ClassNotFoundException ex) {
            throw new RuntimeException("Falha na carga do Driver", ex);
        }
    }
        
        public static void closeConnection(Connection conn) {
         try {
             if (conn != null) {
                 conn.close();
             }
         }
         catch (SQLException ex) {
             Logger.getLogger(
                     MySQLConnection.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
        
        public static void closeConnection(Connection conn, PreparedStatement stmt) {
            closeConnection(conn);
            
            try {
                if (stmt != null) {
                    stmt.close();
                }
            }
            catch (SQLException ex) {
                Logger.getLogger(
                        MySQLConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        public static void closeConnection(Connection conn,
                                            PreparedStatement stmt,
                                            ResultSet rs) {
            closeConnection(conn, stmt);
            
            try {
                if (rs != null) {
                    rs.close();
                }
            }
            catch (SQLException ex) {
                Logger.getLogger(
                        MySQLConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
                
    }
}
