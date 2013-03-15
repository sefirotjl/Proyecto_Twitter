package recursos;



import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sdist
 */
public class ConectBD {
    String servidor;
    String puerto;
    String bd;
    String userName;
    String password;
    String URLbaseDeDatos;
    Connection c;
    
    public ConectBD(String serv, String puerto, String bd, String userName, String pass){
        this.servidor=serv;
        this.puerto=puerto;
        this.bd=bd;
        this.userName=userName;
        this.password=pass;
        URLbaseDeDatos="jdbc:mysql://"+servidor+":"+this.puerto+"/"+this.bd;
    }
    
    public ConectBD(){
        servidor="localhost";
        puerto="3306";
        bd="tuiter";
        password="chaparro";
        userName="root";
        URLbaseDeDatos="jdbc:mysql://localhost:3306/tuiter";
    }
    
    public Connection open(){
        
        try{
         Class.forName("com.mysql.jdbc.Driver").newInstance();
        c = DriverManager.getConnection(URLbaseDeDatos,userName,password);
        }
        catch(Exception e){
            System.out.println("No se pudo abrir la base de datos");
            System.out.println(e.getMessage());
        }
        
        return c;
    }
    
    public void close(){
        if(c!=null){
            try{
                c.close();
            }
            catch(Exception e){
                System.out.println("No se pudo cerrar la conexion");
                System.out.println(e.getMessage());
            }
        }
    }
}
