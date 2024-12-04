
package Controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author _pant
 */
public class Conexion {
    Connection con;
    public Connection getConnection(){
        try{
            String myBD = "jdbc:mysql://localhost:3306/passarinho_chile?serverTimezone=UTC";
            con = DriverManager.getConnection(myBD, "root", "");
           
            return con;
    }catch (SQLException e){
        System.out.println(e.toString());
        JOptionPane.showMessageDialog(null, "Error al conectar, revise si tiene conexión a internet");
    }
        return null;
    }
}
