
package modelo.DAO;

import Controlador.Conexion;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.filechooser.FileSystemView;
import modelo.Tour;
import modelo.Venta;

/**
 *
 * @author _pant
 */
public class VentaDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;
      public int IdVenta(){
        int id = 0;
        String sql = "SELECT MAX(id) FROM venta";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return id;
    }
        public int RegistrarVenta(Venta v){
        String sql = "INSERT INTO venta (pasajero, vendedor, total, fecha) VALUES (?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, v.getCliente());
            ps.setString(2, v.getVendedor());
            ps.setDouble(3, v.getTotal());
            ps.setString(4, v.getFecha());
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
        return r;
    }
    public List ListarVenta() {
        List<Venta> ListaVe = new ArrayList();
        String sql = "SELECT p.id as id_cli, p.nombre, v.* FROM pasajero p INNER JOIN venta v ON p.id = v.pasajero";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Venta ve = new Venta();
                ve.setId(rs.getInt("id"));
                ve.setPasajero(rs.getString("nombre"));
                ve.setVendedor(rs.getString("vendedor"));
                ve.setTotal(rs.getInt("total"));
                ve.setFecha(rs.getString("fecha"));
                ListaVe.add(ve);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return ListaVe;
    }
    public boolean ModificarVenta(Venta v) {

        String sql = "UPDATE venta SET pasajero=?, vendedor=?, total=?, fecha=? WHERE id=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, v.getPasajero());
            ps.setString(2, v.getVendedor());
            ps.setDouble(3, v.getTotal());
            ps.setString(4, v.getFecha());
            ps.setInt(5, v.getId());
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
    
         public boolean EliminarVenta(int id) {
        String sql = "DELETE FROM venta WHERE id=?";
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
       public List Listarventas(){
       List<Venta> ListaVenta = new ArrayList();
       String sql = "SELECT p.id AS id_cli, p.nombre, v.* FROM pasajero p INNER JOIN venta v ON c.id = v.pasajero";
       try {
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           rs = ps.executeQuery();
           while (rs.next()) {               
               Venta vent = new Venta();
               vent.setId(rs.getInt("id"));
               vent.setPasajero(rs.getString("pasajero"));
               vent.setVendedor(rs.getString("vendedor"));
               vent.setTotal(rs.getDouble("total"));
               ListaVenta.add(vent);
           }
       } catch (SQLException e) {
           System.out.println(e.toString());
       }
       return ListaVenta;
   }
   public Venta BuscarVenta(int id){
        Venta cl = new Venta();
        String sql = "SELECT * FROM venta WHERE id = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                cl.setId(rs.getInt("id"));
                cl.setCliente(rs.getInt("pasajero"));
                cl.setTotal(rs.getDouble("total"));
                cl.setVendedor(rs.getString("vendedor"));
                cl.setFecha(rs.getString("fecha"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return cl;
    }
     public void pdfV(int idventa, int cliente, String vendedor, double total) {
        try {
            Date date = new Date();
            FileOutputStream archivo;
            String url = FileSystemView.getFileSystemView().getDefaultDirectory().getPath();
            File salida = new File(url + "venta.pdf");
            archivo = new FileOutputStream(salida);
            Document doc = new Document();
            PdfWriter.getInstance(doc, archivo);
            doc.open();
            Image img = Image.getInstance(getClass().getResource("/img/logo_passarinho_paint_actualizado.png"));
            img.setWidthPercentage(150);
            //Fecha
            Paragraph fecha = new Paragraph();
            Font negrita = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK);
            fecha.add(Chunk.NEWLINE);
            fecha.add("Vendedor: " + vendedor + "\nFolio: " + idventa + "\nFecha: "
                    + new SimpleDateFormat("dd/MM/yyyy").format(date) + "\n\n");
            PdfPTable Encabezado = new PdfPTable(4);
            Encabezado.setWidthPercentage(90);
            Encabezado.getDefaultCell().setBorder(0);
            float[] columnWidthsEncabezado = new float[]{20f, 30f, 70f, 40f};
            Encabezado.setWidths(columnWidthsEncabezado);
            Encabezado.setHorizontalAlignment(Element.ALIGN_LEFT);
            Encabezado.addCell(img);
            Encabezado.addCell("");
            //info empresa
            String agencia = "SELECT * FROM agencia";
            //String mensaje = "";
            try {
                con = cn.getConnection();
                ps = con.prepareStatement(agencia);
                rs = ps.executeQuery();
                if (rs.next()) {
                   // mensaje = rs.getString("mensaje");
                    Encabezado.addCell("Rut:   " + rs.getString("rut") + "\nNombre: " + rs.getString("nombre") + "\nTeléfono: " + rs.getString("telefono") + "\nDirección: " + rs.getString("direccion") + "\n\n");
                }
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
            //
            Encabezado.addCell(fecha);
            doc.add(Encabezado);
            //cliente
            Paragraph cli = new Paragraph();
            cli.add(Chunk.NEWLINE);
            cli.add("DATOS DEL PASAJERO" + "\n\n");
            doc.add(cli);

            PdfPTable proveedor = new PdfPTable(4);
            proveedor.setWidthPercentage(100);
            proveedor.getDefaultCell().setBorder(0);
            float[] columnWidthsCliente = new float[]{25f, 25f, 25f, 25f};
            proveedor.setWidths(columnWidthsCliente);
            proveedor.setHorizontalAlignment(Element.ALIGN_LEFT);
            PdfPCell cliNom = new PdfPCell(new Phrase("Nombre", negrita));
            PdfPCell cliApe = new PdfPCell(new Phrase("Apellidos", negrita));
            PdfPCell cliTel = new PdfPCell(new Phrase("Télefono", negrita));
            PdfPCell cliDir = new PdfPCell(new Phrase("Dirección", negrita));
            cliNom.setBorder(Rectangle.NO_BORDER);
            cliApe.setBorder(Rectangle.NO_BORDER);
            cliTel.setBorder(Rectangle.NO_BORDER);
            cliDir.setBorder(Rectangle.NO_BORDER);
            proveedor.addCell(cliNom);
            proveedor.addCell(cliApe);
            proveedor.addCell(cliTel);
            proveedor.addCell(cliDir);
            String prove = "SELECT * FROM pasajero WHERE id = ?";
            try {
                ps = con.prepareStatement(prove);
                ps.setInt(1, cliente);
                rs = ps.executeQuery();
                if (rs.next()) {
                    proveedor.addCell(rs.getString("nombre"));
                    proveedor.addCell(rs.getString("apellidos"));
                    proveedor.addCell(rs.getString("telefono"));
                    proveedor.addCell(rs.getString("direccion") + "\n\n");
                } else {
                    proveedor.addCell("Publico en General");
                    proveedor.addCell("S/N");
                    proveedor.addCell("S/N" + "\n\n");
                }

            } catch (SQLException e) {
                System.out.println(e.toString());
            }
            doc.add(proveedor);

            PdfPTable tabla = new PdfPTable(3);
            tabla.setWidthPercentage(100);
            tabla.getDefaultCell().setBorder(0);
            float[] columnWidths = new float[]{25f, 30f, 25f};
            tabla.setWidths(columnWidths);
            tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
            
            // descripcion tour
            PdfPCell c1 = new PdfPCell(new Phrase("Tour.", negrita));
            PdfPCell c2 = new PdfPCell(new Phrase("Precio Adulto.", negrita));
            PdfPCell c3 = new PdfPCell(new Phrase("Precio Niño.", negrita));
        
            c1.setBorder(Rectangle.NO_BORDER);
            c2.setBorder(Rectangle.NO_BORDER);
            c3.setBorder(Rectangle.NO_BORDER);
       
          
         
            tabla.addCell(c1);
            tabla.addCell(c2);
            tabla.addCell(c3);
    
            //Viña del mar y valparaíso.
            PdfPCell t1 = new PdfPCell(new Phrase("Viña del mar y valparaíso."));
            PdfPCell p1 = new PdfPCell(new Phrase("25.000"));
            PdfPCell pp1 = new PdfPCell(new Phrase("15.000"));
            t1.setBorder(Rectangle.NO_BORDER);
            p1.setBorder(Rectangle.NO_BORDER);
            pp1.setBorder(Rectangle.NO_BORDER);
           // t1.setBackgroundColor(BaseColor.LIGHT_GRAY);
           // p1.setBackgroundColor(BaseColor.LIGHT_GRAY);
           // pp1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            tabla.addCell(t1);
            tabla.addCell(p1);
            tabla.addCell(pp1);
            //Portillo y laguna inca.
            PdfPCell t2 = new PdfPCell(new Phrase("Portillo y laguna inca."));
            PdfPCell p2 = new PdfPCell(new Phrase("35.000"));
            PdfPCell pp2 = new PdfPCell(new Phrase("20.000"));
            t2.setBorder(Rectangle.NO_BORDER);
            p2.setBorder(Rectangle.NO_BORDER);
            pp2.setBorder(Rectangle.NO_BORDER);
           /* t2.setBackgroundColor(BaseColor.LIGHT_GRAY);
            p2.setBackgroundColor(BaseColor.LIGHT_GRAY);
            pp2.setBackgroundColor(BaseColor.LIGHT_GRAY);*/
            tabla.addCell(t2);
            tabla.addCell(p2);
            tabla.addCell(pp2);
         
            //Farellones y valle nevado.
            PdfPCell t3 = new PdfPCell(new Phrase("Farellones y valle nevado."));
            PdfPCell p3 = new PdfPCell(new Phrase("25.000"));
            PdfPCell pp3 = new PdfPCell(new Phrase("15.000"));
            t3.setBorder(Rectangle.NO_BORDER);
            p3.setBorder(Rectangle.NO_BORDER);
            pp3.setBorder(Rectangle.NO_BORDER);
           /* t3.setBackgroundColor(BaseColor.LIGHT_GRAY);
            p3.setBackgroundColor(BaseColor.LIGHT_GRAY);
            pp3.setBackgroundColor(BaseColor.LIGHT_GRAY);*/
            tabla.addCell(t3);
            tabla.addCell(p3);
            tabla.addCell(pp3);
               
            //Cajon del maipo y termas de Colina.
            PdfPCell t4 = new PdfPCell(new Phrase("Cajon del maipo y termas de Colina."));
            PdfPCell p4 = new PdfPCell(new Phrase("45.000"));
            PdfPCell pp4 = new PdfPCell(new Phrase("35.000"));
            t4.setBorder(Rectangle.NO_BORDER);
            p4.setBorder(Rectangle.NO_BORDER);
            pp4.setBorder(Rectangle.NO_BORDER);
            /*t4.setBackgroundColor(BaseColor.LIGHT_GRAY);
            p4.setBackgroundColor(BaseColor.LIGHT_GRAY);
            pp4.setBackgroundColor(BaseColor.LIGHT_GRAY);*/
            tabla.addCell(t4);
            tabla.addCell(p4);
            tabla.addCell(pp4);
            
            //Cajon del maipo y embalse del yeso.
            PdfPCell t5 = new PdfPCell(new Phrase("Cajon del maipo y embalse del yeso."));
            PdfPCell p5 = new PdfPCell(new Phrase("25.000"));
            PdfPCell pp5 = new PdfPCell(new Phrase("20.000"));
            t5.setBorder(Rectangle.NO_BORDER);
            p5.setBorder(Rectangle.NO_BORDER);
            pp5.setBorder(Rectangle.NO_BORDER);
            /*t5.setBackgroundColor(BaseColor.LIGHT_GRAY);
            p5.setBackgroundColor(BaseColor.LIGHT_GRAY);
            pp5.setBackgroundColor(BaseColor.LIGHT_GRAY);*/
            tabla.addCell(t5);
            tabla.addCell(p5);
            tabla.addCell(pp5);
            
            //Vinicola concha y toro.
            PdfPCell t6 = new PdfPCell(new Phrase("Vinicola concha y toro."));
            PdfPCell p6 = new PdfPCell(new Phrase("30.000"));
            PdfPCell pp6 = new PdfPCell(new Phrase("15.000"));
            t6.setBorder(Rectangle.NO_BORDER);
            p6.setBorder(Rectangle.NO_BORDER);
            pp6.setBorder(Rectangle.NO_BORDER);
            /*t6.setBackgroundColor(BaseColor.LIGHT_GRAY);
            p6.setBackgroundColor(BaseColor.LIGHT_GRAY);
            pp6.setBackgroundColor(BaseColor.LIGHT_GRAY);*/
            tabla.addCell(t6);
            tabla.addCell(p6);
            tabla.addCell(pp6);
            
            //Vinicola santa rita.
            PdfPCell t7 = new PdfPCell(new Phrase("Vinicola santa rita."));
            PdfPCell p7 = new PdfPCell(new Phrase("30.000"));
            PdfPCell pp7 = new PdfPCell(new Phrase("15.000"));
            t7.setBorder(Rectangle.NO_BORDER);
            p7.setBorder(Rectangle.NO_BORDER);
            pp7.setBorder(Rectangle.NO_BORDER);
            /*t7.setBackgroundColor(BaseColor.LIGHT_GRAY);
            p7.setBackgroundColor(BaseColor.LIGHT_GRAY);
            pp7.setBackgroundColor(BaseColor.LIGHT_GRAY);*/
            tabla.addCell(t7);
            tabla.addCell(p7);
            tabla.addCell(pp7);
            
            //Vinicola Undurraga.
            PdfPCell t8 = new PdfPCell(new Phrase("Vinicola Undurraga."));
            PdfPCell p8 = new PdfPCell(new Phrase("30.000"));
            PdfPCell pp8 = new PdfPCell(new Phrase("15.000"));
            t8.setBorder(Rectangle.NO_BORDER);
            p8.setBorder(Rectangle.NO_BORDER);
            pp8.setBorder(Rectangle.NO_BORDER);
            /*t8.setBackgroundColor(BaseColor.LIGHT_GRAY);
            p8.setBackgroundColor(BaseColor.LIGHT_GRAY);
            pp8.setBackgroundColor(BaseColor.LIGHT_GRAY);*/
            tabla.addCell(t8);
            tabla.addCell(p8);
            tabla.addCell(pp8);
            
            //Vinicola Alayan.
            PdfPCell t9 = new PdfPCell(new Phrase("Vinicola Alayan."));
            PdfPCell p9 = new PdfPCell(new Phrase("50.000"));
            PdfPCell pp9 = new PdfPCell(new Phrase(""));
            t9.setBorder(Rectangle.NO_BORDER);
            p9.setBorder(Rectangle.NO_BORDER);
            pp9.setBorder(Rectangle.NO_BORDER);
            /*t9.setBackgroundColor(BaseColor.LIGHT_GRAY);
            p9.setBackgroundColor(BaseColor.LIGHT_GRAY);
            pp9.setBackgroundColor(BaseColor.LIGHT_GRAY);*/
            tabla.addCell(t9);
            tabla.addCell(p9);
            tabla.addCell(pp9);
            
            //Vinicola city walking tour + fotos + agua + sorbete las rosas.
            PdfPCell t10 = new PdfPCell(new Phrase("Vinicola city walking tour + fotos + agua + sorbete las rosas."));
            PdfPCell p10 = new PdfPCell(new Phrase("20.000"));
            PdfPCell pp10 = new PdfPCell(new Phrase("15.000"));
            t10.setBorder(Rectangle.NO_BORDER);
            p10.setBorder(Rectangle.NO_BORDER);
            pp10.setBorder(Rectangle.NO_BORDER);
            /*t10.setBackgroundColor(BaseColor.LIGHT_GRAY);
            p10.setBackgroundColor(BaseColor.LIGHT_GRAY);
            pp10.setBackgroundColor(BaseColor.LIGHT_GRAY);*/
            tabla.addCell(t10);
            tabla.addCell(p10);
            tabla.addCell(pp10);
            
            
            /*String tour= "SELECT * FROM tour WHERE id = ?";
            try {
                ps = con.prepareStatement(tour);
                ps.setInt(1, idventa);
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabla.addCell(rs.getString("nombre"));
                    tabla.addCell(rs.getString("preciogeneral"));
                    tabla.addCell(rs.getString("horasalida"));
                    tabla.addCell(rs.getString("horallegada"));
                }

            } catch (SQLException e) {
                System.out.println(e.toString());
            }*/
            doc.add(tabla);
            Paragraph info = new Paragraph();
            info.add(Chunk.NEWLINE);
            info.add("Total S/: " + total);
            info.setAlignment(Element.ALIGN_RIGHT);
            doc.add(info);
            Paragraph gr = new Paragraph();
            gr.add(Chunk.NEWLINE);
            //gr.add(mensaje);
            gr.setAlignment(Element.ALIGN_CENTER);
            doc.add(gr);
            doc.close();
            archivo.close();
            Desktop.getDesktop().open(salida);
        } catch (DocumentException | IOException e) {
            System.out.println(e.toString());
        }
    }
}
