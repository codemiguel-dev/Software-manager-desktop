
package modelo.DAO;

import Controlador.Conexion;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Pasajero;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import reporte.ExcelPasajero;

/**
 *
 * @author _pant
 */
public class PasajeroDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
     
        
        public boolean RegistrarPasajero(Pasajero pas){
         String sql = "INSERT INTO pasajero (rut, nombre, apellidos, telefono, correo, direccion, tipo, nacionalidad) VALUES(?,?,?,?,?,?,?,?)";
     
            try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, pas.getRut());
            ps.setString(2, pas.getNombre());
            ps.setString(3, pas.getApellidos());
            ps.setString(4, pas.getTelefono());
            ps.setString(5, pas.getCorreo());
            ps.setString(6, pas.getDireccion());
            ps.setString(7, pas.getTipo());
            ps.setString(8, pas.getNacionalidad());
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
        
        public List ListarPasajero(){
        List<Pasajero> ListaPa = new ArrayList();
        String sql ="SELECT * FROM pasajero";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Pasajero pa = new Pasajero();
                pa.setId(rs.getInt("id"));
                pa.setRut(rs.getString("rut"));
                pa.setNombre(rs.getString("nombre"));
                pa.setApellidos(rs.getString("apellidos"));
                pa.setTelefono(rs.getString("telefono"));
                pa.setCorreo(rs.getString("correo"));
                pa.setDireccion(rs.getString("direccion"));
                pa.setTipo(rs.getString("tipo"));
                pa.setNacionalidad(rs.getString("nacionalidad"));
                ListaPa.add(pa);
            }
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return ListaPa;
    }
         public boolean EliminarPasajero(int id) {
        String sql = "DELETE FROM pasajero WHERE id=?";
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
    
    public boolean ModificarPasajero(Pasajero pa){
        String sql = "UPDATE pasajero SET rut=?, nombre=?, apellidos=?, telefono=?, correo=?,  direccion=?, tipo=?, nacionalidad=? WHERE id=?";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, pa.getRut());
            ps.setString(2, pa.getNombre());
            ps.setString(3, pa.getApellidos());
            ps.setString(4, pa.getTelefono());
            ps.setString(5, pa.getCorreo());
            ps.setString(6, pa.getDireccion());
            ps.setString(7, pa.getTipo());
            ps.setString(8, pa.getNacionalidad());
            ps.setInt(9, pa.getId());
            ps.execute();
            return true;
        }catch (SQLException e){
            System.out.println(e.toString());
            return false;
        }finally{
            try{
                con.close();
            }catch (SQLException e){
                System.out.println(e.toString());
            }
        }
    }
       
   public Pasajero Buscarpasajero(String rut){
       Pasajero pa = new Pasajero();
       String sql = "SELECT * FROM pasajero WHERE rut = ?";
       try {
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           ps.setString(1, rut);
           rs = ps.executeQuery();
           if (rs.next()) {
               pa.setId(rs.getInt("id"));
               pa.setNombre(rs.getString("nombre"));
               pa.setApellidos(rs.getString("apellidos"));
               pa.setTelefono(rs.getString("telefono"));
           }
       } catch (SQLException e) {
           System.out.println(e.toString());
       }
       return pa;
   }
   public void excel(){
         Workbook book = new XSSFWorkbook();
        Sheet sheet = book.createSheet("Pasajero");
       try {
           int imgIndex;
             try (InputStream is = new FileInputStream("src/img/logo_passarinho1.png")) {
                 byte[] bytes = IOUtils.toByteArray(is);
                 imgIndex = book.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
             }
 
            CreationHelper help = book.getCreationHelper();
            Drawing draw = sheet.createDrawingPatriarch();
 
            ClientAnchor anchor = help.createClientAnchor();
            anchor.setCol1(0);
            anchor.setRow1(1);
            Picture pict = draw.createPicture(anchor, imgIndex);
            pict.resize(1, 3);
 
            CellStyle tituloEstilo = book.createCellStyle();
            tituloEstilo.setAlignment(HorizontalAlignment.CENTER);
            tituloEstilo.setVerticalAlignment(VerticalAlignment.CENTER);
            Font fuenteTitulo = book.createFont();
            fuenteTitulo.setFontName("Arial");
            fuenteTitulo.setBold(true);
            fuenteTitulo.setFontHeightInPoints((short) 14);
            tituloEstilo.setFont(fuenteTitulo);
 
            Row filaTitulo = sheet.createRow(1);
            Cell celdaTitulo = filaTitulo.createCell(1);
            celdaTitulo.setCellStyle(tituloEstilo);
            celdaTitulo.setCellValue("Reporte de Pasajero");
 
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 1, 3));
 
            String[] cabecera = new String[]{"rut", "Nombre", "Apellidos", "Teléfono", "Correo", "Dirección", "Tipo", "Nacionalidad"};
 
            CellStyle headerStyle = book.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.VIOLET.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderRight(BorderStyle.THIN);
            headerStyle.setBorderBottom(BorderStyle.THIN);
 
            Font font = book.createFont();
            font.setFontName("Arial");
            font.setBold(true);
            font.setColor(IndexedColors.WHITE.getIndex());
            font.setFontHeightInPoints((short) 12);
            headerStyle.setFont(font);
 
            Row filaEncabezados = sheet.createRow(4);
 
            for (int i = 0; i < cabecera.length; i++) {
                Cell celdaEnzabezado = filaEncabezados.createCell(i);
                celdaEnzabezado.setCellStyle(headerStyle);
                celdaEnzabezado.setCellValue(cabecera[i]);
            }
 
            Conexion con = new Conexion();
            PreparedStatement ps;
            ResultSet rs;
            Connection conn = con.getConnection();
 
            int numFilaDatos = 5;
 
            CellStyle datosEstilo = book.createCellStyle();
            datosEstilo.setBorderBottom(BorderStyle.THIN);
            datosEstilo.setBorderLeft(BorderStyle.THIN);
            datosEstilo.setBorderRight(BorderStyle.THIN);
            datosEstilo.setBorderBottom(BorderStyle.THIN);
 
            ps = conn.prepareStatement("SELECT rut, nombre, apellidos, telefono, correo, direccion, tipo, nacionalidad FROM pasajero");
            rs = ps.executeQuery();
 
            int numCol = rs.getMetaData().getColumnCount();
 
            while (rs.next()) {
                Row filaDatos = sheet.createRow(numFilaDatos);
 
                for (int a = 0; a < numCol; a++) {
 
                    Cell CeldaDatos = filaDatos.createCell(a);
                    CeldaDatos.setCellStyle(datosEstilo);
                    CeldaDatos.setCellValue(rs.getString(a + 1));
                }
 
 
                numFilaDatos++;
            }
            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);
            sheet.autoSizeColumn(4);
            
            sheet.setZoom(150);
            String fileName = "pasajero";
            String home = System.getProperty("user.home");
            File file = new File(home + "/Downloads/" + fileName + ".xlsx");
            FileOutputStream fileOut = new FileOutputStream(file);
            book.write(fileOut);
            fileOut.close();
            Desktop.getDesktop().open(file);
            JOptionPane.showMessageDialog(null, "Reporte Generado");
 
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExcelPasajero.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | SQLException ex) {
            Logger.getLogger(ExcelPasajero.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
}
