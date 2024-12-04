
package modelo.DAO;

import Controlador.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Vendedor;

/**
 *
 * @author _pant
 */
public class VendedorDAO {
     Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public boolean RegistrarVendedor(Vendedor ve){
        String sql = "INSERT INTO usuario (nombre, correo, clave) VALUES(?,?,?)";
            try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, ve.getNombre());
            ps.setString(2, ve.getCorreo());
            ps.setString(3, ve.getClave());
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }

        }
    }
    
    public List ListarVendedor(){
        List<Vendedor> Listave = new ArrayList();
        String sql = "SELECT * FROM usuario";
         try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Vendedor ve = new Vendedor();
                ve.setId(rs.getInt("id"));
                ve.setNombre(rs.getString("nombre"));
                ve.setCorreo(rs.getString("correo"));
                ve.setClave(rs.getString("clave"));
                Listave.add(ve);
            }
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return Listave;
    }
    
    public boolean EliminarVendedor(int id){
        String sql = "DELETE FROM usuario WHERE id=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.print(ex.toString());
            }
        }
    }
    
    public boolean ModificarVendedor(Vendedor ve){
        String sql = "UPDATE usuario SET nombre=?, correo=?, clave=? WHERE id=?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, ve.getNombre());
            ps.setString(2, ve.getCorreo());
            ps.setString(3, ve.getClave());
            ps.setInt(4, ve.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
}
