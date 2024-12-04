
package modelo.DAO;

import Controlador.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Login_dueno;

/**
 *
 * @author _pant
 */
public class Login_dueno_DAO {
   Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();
    
    public Login_dueno logi(String correo, String clave){
        Login_dueno ld = new Login_dueno();
        String sql = "SELECT * FROM administrador WHERE correo = ? AND clave = ?";
        
           try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, correo);
            ps.setString(2, clave);
            rs = ps.executeQuery();
            if (rs.next()) {
                ld.setId(rs.getInt("id"));
                ld.setNombre(rs.getString("nombre"));
                ld.setCorreo(rs.getString("correo"));
                ld.setClave(rs.getString("clave"));
             
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return ld;
    }
}
