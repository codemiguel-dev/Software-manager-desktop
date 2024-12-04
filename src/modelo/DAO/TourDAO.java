
package modelo.DAO;

import Controlador.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Tour;

/**
 *
 * @author _pant
 */
public class TourDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public boolean RegistrarTour(Tour to) {

        String sql = "INSERT INTO tour (codigo, nombre, preciogeneral, horasalida, horallegada, descripcionitinerario) VALUES(?,?,?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, to.getCodigo());
            ps.setString(2, to.getNombre());
            ps.setInt(3, to.getPreciogeneral());
            ps.setString(4, to.getHorasalida());
            ps.setString(5, to.getHorallegada());
            ps.setString(6, to.getDescripcionitinerario());
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

    public List ListarTour() {
        List<Tour> ListaTo = new ArrayList();
        String sql = "SELECT * FROM tour";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Tour to = new Tour();
                to.setId(rs.getInt("id"));
                to.setCodigo(rs.getInt("codigo"));
                to.setNombre(rs.getString("nombre"));
                to.setPreciogeneral(rs.getInt("preciogeneral"));
                to.setHorasalida(rs.getString("horasalida"));
                to.setHorallegada(rs.getString("horallegada"));
                to.setDescripcionitinerario(rs.getString("descripcionitinerario"));
                ListaTo.add(to);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return ListaTo;
    }

    public boolean EliminarTour(int id) {
        String sql = "DELETE FROM tour WHERE id=?";

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

    public boolean ModificarTour(Tour to) {

        String sql = "UPDATE tour SET codigo=?, nombre=?, preciogeneral=?, horasalida=?, horallegada=?, descripcionitinerario=? WHERE id=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, to.getCodigo());
            ps.setString(2, to.getNombre());
            ps.setDouble(3, to.getPreciogeneral());
            ps.setString(4, to.getHorasalida());
            ps.setString(5, to.getHorallegada());
            ps.setString(6, to.getDescripcionitinerario());
            ps.setInt(7, to.getId());
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
        public Tour BuscarTour(String cod){
        Tour tour = new Tour();
        String sql = "SELECT * FROM tour WHERE codigo = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, cod);
            rs = ps.executeQuery();
            if (rs.next()) {
                tour.setId(rs.getInt("id"));
                tour.setCodigo(rs.getInt("codigo"));
                tour.setNombre(rs.getString("nombre"));
                tour.setPreciogeneral(rs.getInt("preciogeneral"));
                tour.setHorasalida(rs.getString("horasalida"));
                tour.setHorallegada(rs.getString("horallegada"));
                tour.setDescripcionitinerario(rs.getString("descripcionitinerario"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return tour;
    }
}
