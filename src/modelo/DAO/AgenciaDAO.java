
package modelo.DAO;

import Controlador.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Agencia;

/**
 *
 * @author _pant
 */
public class AgenciaDAO {
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    int r;
    
    public Agencia BuscarAgencia(){
        Agencia agen = new Agencia();
        
        String sql = "SELECT * FROM agencia";
        
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()){
                agen.setId(rs.getInt("id"));
                agen.setRut(rs.getString("rut"));
                agen.setNombre(rs.getString("nombre"));
                agen.setTelefono(rs.getInt("telefono"));
                agen.setDireccion(rs.getString("direccion"));
            }
        }catch(SQLException e){
             System.out.print(e.toString());
        }
        return agen;
    }
    public boolean ModificarAgencia(Agencia agen){
         String sql = "UPDATE agencia SET rut=?, nombre=?, telefono=?, direccion=? WHERE id=?";
         
             try {
            ps = con.prepareStatement(sql);
            ps.setString(1, agen.getRut());
            ps.setString(2, agen.getNombre());
            ps.setInt(3, agen.getTelefono());
            ps.setString(4, agen.getDireccion());
            ps.setInt(5, agen.getId());
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
