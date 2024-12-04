
package Vista;



import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Agencia;
import modelo.DAO.AgenciaDAO;
import modelo.Eventos;
import modelo.Pasajero;
import modelo.DAO.PasajeroDAO;
import modelo.Tour;
import modelo.DAO.TourDAO;
import modelo.Venta;
import modelo.DAO.VentaDAO;
import modelo.login;
import reporte.Grafico;


/**
 *
 * @author _pant
 */
public class Sistema extends javax.swing.JFrame {
    //variable de fechas
    Date fechaVenta = new Date();
    String fechaActual = new SimpleDateFormat("dd/MM/yyyy").format(fechaVenta);
    /*variable model para las tablas*/
      DefaultTableModel modelo = new DefaultTableModel();
      DefaultTableModel tmp = new DefaultTableModel();
     /*clases necesarias para el sistema*/
         Agencia clase_agencia = new Agencia(); 
         AgenciaDAO agencia_dao = new AgenciaDAO();
         
         Pasajero clase_pasajero = new Pasajero();
         PasajeroDAO pasajerodao = new PasajeroDAO();
         
         Tour clase_tour = new Tour();
         TourDAO tourdao = new TourDAO();
         
         Venta clase_venta = new Venta();
         VentaDAO ventadao = new VentaDAO();
         
         Eventos event = new Eventos();
       //variables de inicio
        int item;
        double Totalpagar;
     public Sistema() {
        initComponents();
        
    }
    public Sistema(login priv) {
       
        initComponents();
        /**/
        nombre_vendedor.setText(priv.getNombre());
        /*para el campo no se muestre*/
        txtidpasajero.setVisible(false);
        txtidtourventa.setVisible(false);
        txtidpasajeroventa.setVisible(false);
        txttelefonopasajeroventa.setVisible(false);
        txtidventa.setVisible(false);
        logo_pdf.setVisible(false);
        //función para desplegar la fecha actual
        DesplegarFecha();
        //imagen para para el escritotio
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/logo_passarinho_actualizado.png")));
        ListarAgencia();
    }
    //funciones para limpiar los campos
    public void LimpiarCamposPasajero(){
        txtrutpasajero.setText("");
        txtnombrepasajero.setText("");
        txtapellidospasajero.setText("");
        txttelefonopasajero.setText("");
        txtcorreopasajero.setText("");
        txtdireccionpasajero.setText("");
        txttipopasajero.setText("");
        txtnacionalidadpasajero.setText("");
    }
    

  
     
       public void LimpiarCamposTourventa(){
        txtcodigotourventa.setText("");
        txtnombretourventa.setText("");
        txtpreciogeneralventa.setText("");
        txthorasalidaventa.setText("");
        txthorallegadaventa.setText("");
        txtitinerariotourventa.setText("");
    }
    private void Limpiartourventa() {
        txtcodigotourventa.setText("");
        txtnombretourventa.setText("");
        txtpreciogeneralventa.setText("");
        txthorasalidaventa.setText("");
        txthorallegadaventa.setText("");
        txtitinerariotourventa.setText("");
    }
        private void LimpiarClienteventa() {
        txtrutpasajeroventa.setText("");
        txtnombrepasajeroventa.setText("");
        txtapellidospasajeroventa.setText("");
        txttelefonopasajeroventa.setText("");
    }
        private void LimpiarCamposNuevaVenta(){
            txtcodigotourventa.setText("");
            txtnombretourventa.setText("");
            txtitinerariotourventa.setText("");
            txtpreciogeneralventa.setText("");
            txthorasalidaventa.setText("");
            txthorallegadaventa.setText("");
        }
        private void LimpiarCamposPasajeroVenta(){
            txtidpasajeroventa.setText("");
            txtrutpasajeroventa.setText("");
            txtnombrepasajeroventa.setText("");
            txtapellidospasajeroventa.setText("");
            txttelefonopasajeroventa.setText("");
        }
        private void DesplegarFecha(){
            Date date = new Date();
            new SimpleDateFormat("dd/MM/yyyy").format(date);
            Midate.setDate(date);
        }
        
        
         private void LimpiarTableVenta() {
        tmp = (DefaultTableModel) TableVenta.getModel();
        int fila = TableVenta.getRowCount();
        for (int i = 0; i < fila; i++) {
            tmp.removeRow(0);
        }
    }
    //funcion para limpiar las tablas
      public void LimpiarTabla() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }
    private void TotalPagar(){
            Totalpagar = 0;
        int numFila = TableVenta.getRowCount();
        for (int i = 0; i < numFila; i++) {
            double cal = Double.parseDouble(String.valueOf(TableVenta.getModel().getValueAt(i, 3)));
            Totalpagar = Totalpagar + cal;
        }
        LabelTotal.setText(String.format("%.2f", Totalpagar));
    }
    
      private void RegistrarVenta() {
        
        int cliente = Integer.parseInt(txtidpasajeroventa.getText());
        String vendedor = nombre_vendedor.getText();
        double monto = Totalpagar;
        clase_venta.setCliente(cliente);
        clase_venta.setVendedor(vendedor);
        clase_venta.setTotal(monto);
        clase_venta.setFecha(fechaActual);
        ventadao.RegistrarVenta(clase_venta);
      
    }
   private void desplegartourboleta() {
        
      
        int id = ventadao.IdVenta();
        for (int i = 0; i < TableVenta.getRowCount(); i++) {
            int id_tour = Integer.parseInt(TableVenta.getValueAt(i, 0).toString());
            String nombre = TableVenta.getValueAt(i, 2).toString();
            int precio = Integer.parseInt(TableVenta.getValueAt(i, 3).toString());
            String horasalida =  TableVenta.getValueAt(i, 4).toString();
            String horallegada = TableVenta.getValueAt(i, 5).toString();
            clase_tour.setId_tour(id_tour);
            clase_tour.setNombre(nombre);
            clase_tour.setPreciogeneral(precio);
            clase_tour.setHorasalida(horasalida);
            clase_tour.setHorallegada(horallegada);
            clase_tour.setId(id);
        }
          int cliente = Integer.parseInt(txtidpasajeroventa.getText());
          ventadao.pdfV(id, cliente, nombre_vendedor.getText() , Totalpagar);
    }
     //funciones para listar
    public void ListarPasajero(){
          List<Pasajero> ListarPa = pasajerodao.ListarPasajero();
        modelo = (DefaultTableModel) tablapasajero.getModel();
        Object[] ob = new Object[9];
        for (int i = 0; i < ListarPa.size(); i++) {
            ob[0] = ListarPa.get(i).getId();
            ob[1] = ListarPa.get(i).getRut();
            ob[2] = ListarPa.get(i).getNombre();
            ob[3] = ListarPa.get(i).getApellidos();
            ob[4] = ListarPa.get(i).getTelefono();
            ob[5] = ListarPa.get(i).getCorreo();
            ob[6] = ListarPa.get(i).getDireccion();
            ob[7] = ListarPa.get(i).getTipo();
            ob[8] = ListarPa.get(i).getNacionalidad();
            modelo.addRow(ob);
        }
        tablapasajero.setModel(modelo);
    }
  
     public void ListarTour(){
        List<Tour> Listarto = tourdao.ListarTour();
        modelo = (DefaultTableModel) tablatour.getModel();
        Object[] ob = new Object[7];
             for (int i = 0; i < Listarto.size(); i++) {
            ob[0] = Listarto.get(i).getId();
            ob[1] = Listarto.get(i).getCodigo();
            ob[2] = Listarto.get(i).getNombre();
            ob[3] = Listarto.get(i).getPreciogeneral();
            ob[4] = Listarto.get(i).getHorasalida();
            ob[5] = Listarto.get(i).getHorallegada();
            ob[6] = Listarto.get(i).getDescripcionitinerario();
            modelo.addRow(ob);
        }
             tablatour.setModel(modelo);
    }
    public void ListarVenta() {
        List<Venta> Listarve = ventadao.ListarVenta();
        modelo = (DefaultTableModel) tablaventa.getModel();
        Object[] ob = new Object[4];
        for (int i = 0; i < Listarve.size(); i++) {
            ob[0] = Listarve.get(i).getId();
            ob[1] = Listarve.get(i).getPasajero();
            ob[2] = Listarve.get(i).getVendedor();
            ob[3] = Listarve.get(i).getTotal();
            modelo.addRow(ob);
        }
        tablaventa.setModel(modelo);
    }
      public void ListarAgencia(){
        clase_agencia = agencia_dao.BuscarAgencia();
        
       Labelrutagencia.setText(""+clase_agencia.getRut());
        Labelnombreagencia.setText(""+clase_agencia.getNombre());
        Labeltelefonoagencia.setText(""+clase_agencia.getTelefono());
        Labeldireccionagencia.setText(""+clase_agencia.getDireccion());
     }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jPanel1 = new javax.swing.JPanel();
        icono_admin = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        nombre_vendedor = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        tablas = new javax.swing.JTabbedPane();
        jPanel9 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtnombretourventa = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        txtcodigotourventa = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtitinerariotourventa = new javax.swing.JTextArea();
        txtpreciogeneralventa = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        txthorallegadaventa = new javax.swing.JTextField();
        txthorasalidaventa = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        TableVenta = new javax.swing.JTable();
        Midate = new com.toedter.calendar.JDateChooser();
        btnGraficar = new javax.swing.JLabel();
        txtidtourventa = new javax.swing.JTextField();
        btngenrarventa = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablapasajero = new javax.swing.JTable();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        txtrutpasajero = new javax.swing.JTextField();
        txtnombrepasajero = new javax.swing.JTextField();
        txtapellidospasajero = new javax.swing.JTextField();
        txttelefonopasajero = new javax.swing.JTextField();
        txtcorreopasajero = new javax.swing.JTextField();
        txtdireccionpasajero = new javax.swing.JTextField();
        txttipopasajero = new javax.swing.JTextField();
        txtnacionalidadpasajero = new javax.swing.JTextField();
        txtidpasajero = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablatour = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtitinerario = new javax.swing.JTextArea();
        jLabel25 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tablaventa = new javax.swing.JTable();
        logo_pdf = new javax.swing.JLabel();
        txtidventa = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        Labelrutagencia = new javax.swing.JLabel();
        Labelnombreagencia = new javax.swing.JLabel();
        Labeltelefonoagencia = new javax.swing.JLabel();
        Labeldireccionagencia = new javax.swing.JLabel();
        txtidpasajeroventa = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        txtnombrepasajeroventa = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        txttelefonopasajeroventa = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        txtrutpasajeroventa = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        LabelTotal = new javax.swing.JLabel();
        txtapellidospasajeroventa = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        buttonLimpiar = new javax.swing.JButton();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setExtendedState(6);

        jPanel1.setBackground(new java.awt.Color(130, 56, 102));
        jPanel1.setPreferredSize(new java.awt.Dimension(1370, 720));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        icono_admin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logo_admin_amplio.png"))); // NOI18N
        jPanel1.add(icono_admin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 1370, 70));

        jPanel2.setBackground(new java.awt.Color(65, 53, 87));

        nombre_vendedor.setBackground(new java.awt.Color(255, 255, 255));
        nombre_vendedor.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        nombre_vendedor.setForeground(new java.awt.Color(255, 255, 255));
        nombre_vendedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/login.png"))); // NOI18N
        nombre_vendedor.setText(" vendedor");

        jPanel7.setBackground(new java.awt.Color(130, 56, 102));
        jPanel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel7MouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Nueva venta");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(60, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(54, 54, 54))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(130, 56, 102));
        jPanel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel3.setPreferredSize(new java.awt.Dimension(203, 45));
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel3MouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Pasajero");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(68, 68, 68))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(130, 56, 102));
        jPanel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel5.setPreferredSize(new java.awt.Dimension(203, 45));
        jPanel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel5MouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Tour");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(82, 82, 82))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(130, 56, 102));
        jPanel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel6.setPreferredSize(new java.awt.Dimension(203, 45));
        jPanel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel6MouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Venta");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(79, 79, 79))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(130, 56, 102));
        jPanel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel4.setPreferredSize(new java.awt.Dimension(203, 45));
        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel4MouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Agencia");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(77, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(70, 70, 70))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(nombre_vendedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(nombre_vendedor)
                .addGap(36, 36, 36)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(130, 130, 130))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 260, 630));

        jPanel9.setBackground(new java.awt.Color(130, 56, 102));

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Nueva Venta");

        txtnombretourventa.setEditable(false);
        txtnombretourventa.setBackground(new java.awt.Color(255, 255, 255));
        txtnombretourventa.setForeground(new java.awt.Color(51, 51, 51));
        txtnombretourventa.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtnombretourventa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnombretourventaKeyTyped(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("Código");

        txtcodigotourventa.setBackground(new java.awt.Color(255, 255, 255));
        txtcodigotourventa.setForeground(new java.awt.Color(51, 51, 51));
        txtcodigotourventa.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtcodigotourventa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtcodigotourventaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcodigotourventaKeyTyped(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("Itinerario");

        jLabel52.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(255, 255, 255));
        jLabel52.setText("Nombre de tour");

        txtitinerariotourventa.setEditable(false);
        txtitinerariotourventa.setBackground(new java.awt.Color(255, 255, 255));
        txtitinerariotourventa.setColumns(20);
        txtitinerariotourventa.setRows(5);
        txtitinerariotourventa.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtitinerariotourventa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtitinerariotourventaKeyTyped(evt);
            }
        });
        jScrollPane6.setViewportView(txtitinerariotourventa);

        txtpreciogeneralventa.setEditable(false);
        txtpreciogeneralventa.setBackground(new java.awt.Color(255, 255, 255));
        txtpreciogeneralventa.setForeground(new java.awt.Color(51, 51, 51));
        txtpreciogeneralventa.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtpreciogeneralventa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtpreciogeneralventaKeyTyped(evt);
            }
        });

        jLabel55.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(255, 255, 255));
        jLabel55.setText("Precio General");

        jLabel56.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(255, 255, 255));
        jLabel56.setText("Hora llegada");

        jLabel57.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(255, 255, 255));
        jLabel57.setText("Hora de salida");

        txthorallegadaventa.setEditable(false);
        txthorallegadaventa.setBackground(new java.awt.Color(255, 255, 255));
        txthorallegadaventa.setForeground(new java.awt.Color(51, 51, 51));
        txthorallegadaventa.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txthorallegadaventa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txthorallegadaventaKeyTyped(evt);
            }
        });

        txthorasalidaventa.setEditable(false);
        txthorasalidaventa.setBackground(new java.awt.Color(255, 255, 255));
        txthorasalidaventa.setForeground(new java.awt.Color(51, 51, 51));
        txthorasalidaventa.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txthorasalidaventa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txthorasalidaventaKeyTyped(evt);
            }
        });

        TableVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Código", "Nombre", "Precio general", "Hora Salida", "Hora Lllegada", "Itinerario"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TableVenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableVentaMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(TableVenta);
        if (TableVenta.getColumnModel().getColumnCount() > 0) {
            TableVenta.getColumnModel().getColumn(0).setResizable(false);
        }

        btnGraficar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/torta.png"))); // NOI18N
        btnGraficar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGraficar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnGraficarMouseClicked(evt);
            }
        });

        txtidtourventa.setBackground(new java.awt.Color(255, 255, 255));
        txtidtourventa.setForeground(new java.awt.Color(51, 51, 51));
        txtidtourventa.setBorder(null);
        txtidtourventa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtidtourventaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtidtourventaKeyTyped(evt);
            }
        });

        btngenrarventa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/print.png"))); // NOI18N
        btngenrarventa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btngenrarventa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btngenrarventaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btngenrarventaMouseEntered(evt);
            }
        });

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/eliminar.png"))); // NOI18N
        jLabel13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(130, 56, 102));
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Limpiar campos");
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton5MouseClicked(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(130, 56, 102));
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Buscar Tour");
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton6MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtidtourventa, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(178, 178, 178))
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel9Layout.createSequentialGroup()
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtcodigotourventa, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel31)
                                .addComponent(txtpreciogeneralventa, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel55)
                                .addComponent(jLabel57)
                                .addComponent(txthorasalidaventa, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(75, 75, 75)
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel9Layout.createSequentialGroup()
                                    .addComponent(btnGraficar)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(Midate, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE))
                                .addComponent(jLabel52)
                                .addComponent(jLabel56)
                                .addComponent(txthorallegadaventa)
                                .addComponent(txtnombretourventa))
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btngenrarventa)
                                    .addGap(155, 155, 155))
                                .addGroup(jPanel9Layout.createSequentialGroup()
                                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel9Layout.createSequentialGroup()
                                            .addGap(71, 71, 71)
                                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                                                .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addGroup(jPanel9Layout.createSequentialGroup()
                                            .addGap(121, 121, 121)
                                            .addComponent(jLabel13)))
                                    .addGap(107, 107, 107))))
                        .addGroup(jPanel9Layout.createSequentialGroup()
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 785, Short.MAX_VALUE)
                            .addGap(18, 18, 18))))
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel32)
                        .addGap(120, 120, 120))))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtidtourventa, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel52)
                    .addComponent(jLabel32)
                    .addComponent(jLabel31))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtnombretourventa, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtcodigotourventa, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel9Layout.createSequentialGroup()
                                                .addComponent(jLabel55)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtpreciogeneralventa, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(19, 19, 19))
                                            .addGroup(jPanel9Layout.createSequentialGroup()
                                                .addComponent(Midate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(28, 28, 28)))
                                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel56)
                                            .addComponent(jLabel57)))
                                    .addComponent(btngenrarventa))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txthorasalidaventa, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                                        .addComponent(txthorallegadaventa))))
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnGraficar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel9Layout.createSequentialGroup()
                                    .addComponent(jButton5)
                                    .addGap(37, 37, 37))))
                        .addGap(31, 31, 31)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE))
                    .addComponent(jScrollPane6))
                .addContainerGap())
        );

        tablas.addTab("tab1", jPanel9);
        jPanel9.getAccessibleContext().setAccessibleDescription("");

        jPanel10.setBackground(new java.awt.Color(130, 56, 102));

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Pasajero");

        tablapasajero.setBackground(new java.awt.Color(255, 255, 255));
        tablapasajero.setForeground(new java.awt.Color(65, 53, 87));
        tablapasajero.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Rut", "Nombre", "Apellidos", "Teléfono", "Correo", "Dirección", "tipo", "Nacionalidad"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablapasajero.setEditingColumn(0);
        tablapasajero.setEditingRow(0);
        tablapasajero.setGridColor(new java.awt.Color(255, 255, 255));
        tablapasajero.setSelectionForeground(new java.awt.Color(0, 0, 0));
        tablapasajero.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablapasajeroMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablapasajero);

        jLabel20.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Rut");

        jLabel21.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Nombre");

        jLabel22.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Apellidos");

        jLabel23.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Teléfono");

        jLabel24.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Correo");

        jLabel26.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Direccion");

        jLabel28.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Tipo");

        jLabel29.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("Nacionalidad");

        txtrutpasajero.setBackground(new java.awt.Color(255, 255, 255));
        txtrutpasajero.setForeground(new java.awt.Color(51, 51, 51));
        txtrutpasajero.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtrutpasajero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtrutpasajeroKeyTyped(evt);
            }
        });

        txtnombrepasajero.setBackground(new java.awt.Color(255, 255, 255));
        txtnombrepasajero.setForeground(new java.awt.Color(51, 51, 51));
        txtnombrepasajero.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtnombrepasajero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnombrepasajeroKeyTyped(evt);
            }
        });

        txtapellidospasajero.setBackground(new java.awt.Color(255, 255, 255));
        txtapellidospasajero.setForeground(new java.awt.Color(51, 51, 51));
        txtapellidospasajero.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtapellidospasajero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtapellidospasajeroKeyTyped(evt);
            }
        });

        txttelefonopasajero.setBackground(new java.awt.Color(255, 255, 255));
        txttelefonopasajero.setForeground(new java.awt.Color(51, 51, 51));
        txttelefonopasajero.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txttelefonopasajero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txttelefonopasajeroKeyTyped(evt);
            }
        });

        txtcorreopasajero.setBackground(new java.awt.Color(255, 255, 255));
        txtcorreopasajero.setForeground(new java.awt.Color(51, 51, 51));
        txtcorreopasajero.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtcorreopasajero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcorreopasajeroKeyTyped(evt);
            }
        });

        txtdireccionpasajero.setBackground(new java.awt.Color(255, 255, 255));
        txtdireccionpasajero.setForeground(new java.awt.Color(51, 51, 51));
        txtdireccionpasajero.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtdireccionpasajero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtdireccionpasajeroKeyTyped(evt);
            }
        });

        txttipopasajero.setBackground(new java.awt.Color(255, 255, 255));
        txttipopasajero.setForeground(new java.awt.Color(51, 51, 51));
        txttipopasajero.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txttipopasajero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txttipopasajeroKeyTyped(evt);
            }
        });

        txtnacionalidadpasajero.setBackground(new java.awt.Color(255, 255, 255));
        txtnacionalidadpasajero.setForeground(new java.awt.Color(51, 51, 51));
        txtnacionalidadpasajero.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtnacionalidadpasajero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnacionalidadpasajeroKeyTyped(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(130, 56, 102));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Agregar");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setPreferredSize(new java.awt.Dimension(94, 25));
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(130, 56, 102));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Editar");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.setPreferredSize(new java.awt.Dimension(94, 25));
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(130, 56, 102));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Eliminar");
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(130, 56, 102));
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Nuevo");
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtrutpasajero, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                                        .addComponent(jLabel20)
                                        .addComponent(txtcorreopasajero))
                                    .addComponent(jLabel24))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel21)
                                    .addComponent(txtnombrepasajero, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtdireccionpasajero, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel26))
                                .addGap(55, 55, 55)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel28)
                                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel22)
                                        .addComponent(txtapellidospasajero, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txttipopasajero, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel23)
                                    .addComponent(jLabel29)
                                    .addComponent(txttelefonopasajero, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                                    .addComponent(txtnacionalidadpasajero))))
                        .addGap(32, 32, 32))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(31, 31, 31)
                                .addComponent(txtidpasajero, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(591, 591, Short.MAX_VALUE))))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(10, 10, 10))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addComponent(txtidpasajero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel20)
                        .addComponent(jLabel21)
                        .addComponent(jLabel23)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtrutpasajero, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtapellidospasajero, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txttelefonopasajero, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtnombrepasajero, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26)
                            .addComponent(jLabel24)
                            .addComponent(jLabel28))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtdireccionpasajero, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtcorreopasajero, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txttipopasajero, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtnacionalidadpasajero, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(40, 40, 40)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(121, 121, 121))
        );

        tablas.addTab("tab2", jPanel10);

        jPanel12.setBackground(new java.awt.Color(130, 56, 102));

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Tour");

        tablatour.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "código", "Nombre", "Precio General", "Hora de salida", "Hora de llegada", "Descripción de itinerario"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablatour.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablatourMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tablatour);

        txtitinerario.setEditable(false);
        txtitinerario.setBackground(new java.awt.Color(255, 255, 255));
        txtitinerario.setColumns(20);
        txtitinerario.setRows(5);
        txtitinerario.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jScrollPane2.setViewportView(txtitinerario);

        jLabel25.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Itinerario");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(47, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1041, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 1041, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                            .addComponent(jLabel10)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel25)
                            .addGap(518, 518, 518))))
                .addGap(37, 37, 37))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel25))
                .addGap(2, 2, 2)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        tablas.addTab("tab4", jPanel12);

        jPanel13.setBackground(new java.awt.Color(130, 56, 102));
        jPanel13.setPreferredSize(new java.awt.Dimension(1400, 613));

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Venta");

        tablaventa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Pasajero", "Vendedor", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaventa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaventaMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tablaventa);

        logo_pdf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pdf.png"))); // NOI18N
        logo_pdf.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logo_pdfMouseClicked(evt);
            }
        });

        txtidventa.setBackground(new java.awt.Color(255, 255, 255));
        txtidventa.setForeground(new java.awt.Color(51, 51, 51));
        txtidventa.setBorder(null);
        txtidventa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtidventaKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(logo_pdf)
                        .addGap(35, 35, 35)
                        .addComponent(txtidventa, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel11)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 992, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(95, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(logo_pdf, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtidventa, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tablas.addTab("tab5", jPanel13);

        jPanel8.setBackground(new java.awt.Color(130, 56, 102));

        Labelrutagencia.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        Labelrutagencia.setForeground(new java.awt.Color(255, 255, 255));
        Labelrutagencia.setText("rut");

        Labelnombreagencia.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        Labelnombreagencia.setForeground(new java.awt.Color(255, 255, 255));
        Labelnombreagencia.setText("nombre");

        Labeltelefonoagencia.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        Labeltelefonoagencia.setForeground(new java.awt.Color(255, 255, 255));
        Labeltelefonoagencia.setText("telefono");

        Labeldireccionagencia.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        Labeldireccionagencia.setForeground(new java.awt.Color(255, 255, 255));
        Labeldireccionagencia.setText("direccion");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Labeldireccionagencia)
                    .addComponent(Labeltelefonoagencia)
                    .addComponent(Labelnombreagencia)
                    .addComponent(Labelrutagencia))
                .addContainerGap(966, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(102, 102, 102)
                .addComponent(Labelrutagencia)
                .addGap(28, 28, 28)
                .addComponent(Labelnombreagencia)
                .addGap(33, 33, 33)
                .addComponent(Labeltelefonoagencia)
                .addGap(45, 45, 45)
                .addComponent(Labeldireccionagencia)
                .addContainerGap(211, Short.MAX_VALUE))
        );

        tablas.addTab("tab5", jPanel8);

        jPanel1.add(tablas, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 70, 1130, 550));

        txtidpasajeroventa.setBackground(new java.awt.Color(255, 255, 255));
        txtidpasajeroventa.setForeground(new java.awt.Color(51, 51, 51));
        txtidpasajeroventa.setBorder(null);
        txtidpasajeroventa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtidpasajeroventaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtidpasajeroventaKeyTyped(evt);
            }
        });
        jPanel1.add(txtidpasajeroventa, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 630, 10, 25));

        jLabel53.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(255, 255, 255));
        jLabel53.setText("Rut");
        jPanel1.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 630, -1, -1));

        txtnombrepasajeroventa.setEditable(false);
        txtnombrepasajeroventa.setBackground(new java.awt.Color(255, 255, 255));
        txtnombrepasajeroventa.setForeground(new java.awt.Color(51, 51, 51));
        txtnombrepasajeroventa.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtnombrepasajeroventa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtnombrepasajeroventaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnombrepasajeroventaKeyTyped(evt);
            }
        });
        jPanel1.add(txtnombrepasajeroventa, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 660, 197, 25));

        jLabel54.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(255, 255, 255));
        jLabel54.setText("Nombre");
        jPanel1.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 630, -1, -1));

        txttelefonopasajeroventa.setBackground(new java.awt.Color(255, 255, 255));
        txttelefonopasajeroventa.setForeground(new java.awt.Color(51, 51, 51));
        txttelefonopasajeroventa.setBorder(null);
        txttelefonopasajeroventa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttelefonopasajeroventaActionPerformed(evt);
            }
        });
        txttelefonopasajeroventa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txttelefonopasajeroventaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txttelefonopasajeroventaKeyTyped(evt);
            }
        });
        jPanel1.add(txttelefonopasajeroventa, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 690, 20, 25));

        jLabel49.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(255, 255, 255));
        jLabel49.setText("Apellidos");
        jPanel1.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 630, -1, -1));

        txtrutpasajeroventa.setBackground(new java.awt.Color(255, 255, 255));
        txtrutpasajeroventa.setForeground(new java.awt.Color(51, 51, 51));
        txtrutpasajeroventa.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtrutpasajeroventa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtrutpasajeroventaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtrutpasajeroventaKeyTyped(evt);
            }
        });
        jPanel1.add(txtrutpasajeroventa, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 660, 197, 25));

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/money.png"))); // NOI18N
        jLabel12.setText("Total a pagar");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 640, -1, -1));

        LabelTotal.setForeground(new java.awt.Color(255, 255, 255));
        LabelTotal.setText("----------");
        jPanel1.add(LabelTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 660, -1, 50));

        txtapellidospasajeroventa.setEditable(false);
        txtapellidospasajeroventa.setBackground(new java.awt.Color(255, 255, 255));
        txtapellidospasajeroventa.setForeground(new java.awt.Color(51, 51, 51));
        txtapellidospasajeroventa.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtapellidospasajeroventa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtapellidospasajeroventaActionPerformed(evt);
            }
        });
        txtapellidospasajeroventa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtapellidospasajeroventaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtapellidospasajeroventaKeyTyped(evt);
            }
        });
        jPanel1.add(txtapellidospasajeroventa, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 660, 197, 25));

        jButton7.setBackground(new java.awt.Color(130, 56, 102));
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("Buscar Rut");
        jButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton7.setPreferredSize(new java.awt.Dimension(109, 25));
        jButton7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton7MouseClicked(evt);
            }
        });
        jPanel1.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 630, 120, -1));

        buttonLimpiar.setBackground(new java.awt.Color(130, 56, 102));
        buttonLimpiar.setForeground(new java.awt.Color(255, 255, 255));
        buttonLimpiar.setText("Limpiar campos");
        buttonLimpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonLimpiar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonLimpiarMouseClicked(evt);
            }
        });
        jPanel1.add(buttonLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 660, 120, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 744, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel7MouseClicked
        tablas.setSelectedIndex(0);
        DesplegarFecha();
    }//GEN-LAST:event_jPanel7MouseClicked

    private void jPanel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseClicked
        tablas.setSelectedIndex(1);
        LimpiarTabla();
        ListarPasajero();

          
    }//GEN-LAST:event_jPanel3MouseClicked

    private void jPanel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseClicked
          tablas.setSelectedIndex(2);
          LimpiarTabla();
          ListarTour();
    }//GEN-LAST:event_jPanel5MouseClicked

    private void jPanel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseClicked
         tablas.setSelectedIndex(3);
         LimpiarTabla();
         ListarVenta();
    }//GEN-LAST:event_jPanel6MouseClicked

    private void tablapasajeroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablapasajeroMouseClicked
       int fila = tablapasajero.rowAtPoint(evt.getPoint());
       txtidpasajero.setText(tablapasajero.getValueAt(fila, 0).toString());
        txtrutpasajero.setText(tablapasajero.getValueAt(fila, 1).toString());
        txtnombrepasajero.setText(tablapasajero.getValueAt(fila, 2).toString());
        txtapellidospasajero.setText(tablapasajero.getValueAt(fila, 3).toString());
        txttelefonopasajero.setText(tablapasajero.getValueAt(fila, 4).toString());
        txtcorreopasajero.setText(tablapasajero.getValueAt(fila, 5).toString());
        txtdireccionpasajero.setText(tablapasajero.getValueAt(fila, 6).toString());
        txttipopasajero.setText(tablapasajero.getValueAt(fila, 7).toString());
        txtnacionalidadpasajero.setText(tablapasajero.getValueAt(fila, 8).toString());
        
    }//GEN-LAST:event_tablapasajeroMouseClicked

    private void tablatourMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablatourMouseClicked
        int fila = tablatour.rowAtPoint(evt.getPoint());
        txtitinerario.setText(tablatour.getValueAt(fila, 6).toString());
    }//GEN-LAST:event_tablatourMouseClicked

    private void txtcodigotourventaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcodigotourventaKeyPressed
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtcodigotourventa.getText())) {
                String cod = txtcodigotourventa.getText();
                clase_tour = tourdao.BuscarTour(cod);
                if (clase_tour.getNombre() != null) {
                    txtidtourventa.setText("" + clase_tour.getId());
                    txtnombretourventa.setText("" + clase_tour.getNombre());
                    txtpreciogeneralventa.setText("" + clase_tour.getPreciogeneral());
                    txthorasalidaventa.setText("" + clase_tour.getHorasalida());
                    txthorallegadaventa.setText("" + clase_tour.getHorallegada());
                    txtitinerariotourventa.setText("" + clase_tour.getDescripcionitinerario());
                    int id = Integer.parseInt(txtidtourventa.getText());
                    String codigo = txtcodigotourventa.getText();
                    String nombre = txtnombretourventa.getText();
                    int preciogeneral = Integer.parseInt(txtpreciogeneralventa.getText());
                    String horasalida = txthorasalidaventa.getText();
                    String horallegada = txthorallegadaventa.getText();
                    String itinerario = txtitinerariotourventa.getText();
                    item = item + 1;
                    tmp = (DefaultTableModel) TableVenta.getModel();
                    for (int i = 0; i < TableVenta.getRowCount(); i++) {
                        if (TableVenta.getValueAt(i, 1).equals(txtnombretourventa.getText())) {
                            JOptionPane.showMessageDialog(null, "El tour ya esta registrado");
                            return;
                        }
                    }
                    ArrayList lista = new ArrayList();
                    lista.add(item);
                    lista.add(id);
                    lista.add(codigo);
                    lista.add(nombre);
                    lista.add(preciogeneral);
                    lista.add(horasalida);
                    lista.add(horallegada);
                    lista.add(itinerario);
                    Object[] O = new Object[7];
                    O[0] = lista.get(1);
                    O[1] = lista.get(2);
                    O[2] = lista.get(3);
                    O[3] = lista.get(4);
                    O[4] = lista.get(5);
                    O[5] = lista.get(6);
                    O[6] = lista.get(7);
                    tmp.addRow(O);
                    TableVenta.setModel(tmp);
                    TotalPagar();
                    txtcodigotourventa.requestFocus(); 
                } else {
                    LimpiarCamposTourventa();
                    txtcodigotourventa.requestFocus();
                    JOptionPane.showMessageDialog(null, "Tour incorrecto");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese el codigo del tour");
                txtcodigotourventa.requestFocus();
            }
        }
    }//GEN-LAST:event_txtcodigotourventaKeyPressed

    private void txtcodigotourventaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcodigotourventaKeyTyped
   char c = evt.getKeyChar();
        if ((c < '0' || c > '9')) {
            evt.consume();
        }
  
    }//GEN-LAST:event_txtcodigotourventaKeyTyped

    private void txtnombretourventaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombretourventaKeyTyped
         char c = evt.getKeyChar();
        if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c < ' ' || c > ' ') && (c < 'ñ' || c > 'Ñ')&&(c < 'á' || c > 'ú')) {
            evt.consume();
        }
    }//GEN-LAST:event_txtnombretourventaKeyTyped

    private void txtidpasajeroventaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtidpasajeroventaKeyPressed
         
    }//GEN-LAST:event_txtidpasajeroventaKeyPressed

    private void txtidpasajeroventaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtidpasajeroventaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidpasajeroventaKeyTyped

    private void txtnombrepasajeroventaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombrepasajeroventaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnombrepasajeroventaKeyPressed

    private void txtnombrepasajeroventaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombrepasajeroventaKeyTyped
           char c = evt.getKeyChar();
        if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c < 'ñ' || c > 'Ñ')&&(c < 'á' || c > 'ú')) {
            evt.consume();
        }
    }//GEN-LAST:event_txtnombrepasajeroventaKeyTyped

    private void txttelefonopasajeroventaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttelefonopasajeroventaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttelefonopasajeroventaKeyPressed

    private void txttelefonopasajeroventaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttelefonopasajeroventaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txttelefonopasajeroventaKeyTyped

    private void txttelefonopasajeroventaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttelefonopasajeroventaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttelefonopasajeroventaActionPerformed

    private void txtidtourventaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtidtourventaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidtourventaKeyPressed

    private void txtidtourventaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtidtourventaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidtourventaKeyTyped

    private void txtrutpasajeroventaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrutpasajeroventaKeyPressed
        //acción de busqueda del pasajero
           if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtrutpasajeroventa.getText())) {
                String rut = txtrutpasajeroventa.getText();
                clase_pasajero = pasajerodao.Buscarpasajero(rut);
                if (clase_pasajero.getNombre() != null) {
                    txtnombrepasajeroventa.setText("" + clase_pasajero.getNombre());
                    txtapellidospasajeroventa.setText("" + clase_pasajero.getApellidos());
                    txttelefonopasajeroventa.setText("" + clase_pasajero.getTelefono());
                    txtidpasajeroventa.setText("" + clase_pasajero.getId());
                } else {
                    txtrutpasajeroventa.setText("");
                    JOptionPane.showMessageDialog(null, "El pasajero no existe");
                }
            }
        }
    }//GEN-LAST:event_txtrutpasajeroventaKeyPressed

    private void txtrutpasajeroventaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrutpasajeroventaKeyTyped
          char c = evt.getKeyChar();
        if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c < '0' || c > '9') && (c < '-' || c > '.')) {
            evt.consume();
        }
    }//GEN-LAST:event_txtrutpasajeroventaKeyTyped

    private void btnGraficarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGraficarMouseClicked
        
        if (!"".equals(Midate.getDate())){
            String fechaReporte = new SimpleDateFormat("dd/MM/yyyy").format(Midate.getDate());
            Grafico.Graficar(fechaReporte);}
        else{
         JOptionPane.showMessageDialog(null, "seleccione calendario");
        }
    }//GEN-LAST:event_btnGraficarMouseClicked

    private void btngenrarventaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btngenrarventaMouseClicked
        if (TableVenta.getRowCount() > 0) {
            if (!"".equals(txtnombretourventa.getText())) {
                RegistrarVenta();
                desplegartourboleta();
                LabelTotal.setText("-------");
                LimpiarTableVenta();
                Limpiartourventa();
                LimpiarClienteventa();
            } else {
                JOptionPane.showMessageDialog(null, "Debes buscar un pasajero");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No hay tour");
        }
    }//GEN-LAST:event_btngenrarventaMouseClicked

    private void txtapellidospasajeroventaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtapellidospasajeroventaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtapellidospasajeroventaActionPerformed

    private void txtapellidospasajeroventaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtapellidospasajeroventaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtapellidospasajeroventaKeyPressed

    private void txtapellidospasajeroventaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtapellidospasajeroventaKeyTyped
           char c = evt.getKeyChar();
        if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c < ' ' || c > ' ') && (c < 'ñ' || c > 'Ñ')&&(c < 'á' || c > 'ú')) {
            evt.consume();
        }
    }//GEN-LAST:event_txtapellidospasajeroventaKeyTyped

    private void btngenrarventaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btngenrarventaMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btngenrarventaMouseEntered

    private void txtpreciogeneralventaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpreciogeneralventaKeyTyped
   char c = evt.getKeyChar();
        if ((c < '0' || c > '9')) {
            evt.consume();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txtpreciogeneralventaKeyTyped

    private void txtrutpasajeroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrutpasajeroKeyTyped
          char c = evt.getKeyChar();
        if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c < '0' || c > '9') && (c < '-' || c > '.')) {
            evt.consume();
        }
    }//GEN-LAST:event_txtrutpasajeroKeyTyped

    private void txtnombrepasajeroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombrepasajeroKeyTyped
      char c = evt.getKeyChar();
        if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c < 'ñ' || c > 'Ñ')&&(c < 'á' || c > 'ú')) {
            evt.consume();
        }
    }//GEN-LAST:event_txtnombrepasajeroKeyTyped

    private void txtapellidospasajeroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtapellidospasajeroKeyTyped
         char c = evt.getKeyChar();
        if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c < 'ñ' || c > 'Ñ') && (c < 'á' || c > 'ú') && (c < ' ' || c > ' ') ) {
            evt.consume();
        }
    }//GEN-LAST:event_txtapellidospasajeroKeyTyped

    private void txttelefonopasajeroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttelefonopasajeroKeyTyped
        char c = evt.getKeyChar();
        if ((c < '1' || c > '9') && (c=='+')) {
            evt.consume();
        }
    }//GEN-LAST:event_txttelefonopasajeroKeyTyped

    private void txtcorreopasajeroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcorreopasajeroKeyTyped
        char c = evt.getKeyChar();
        if ((c < '1' || c > '9') && (c < 'a' || c > 'z') && (c < 'A' || c > 'Z')  && (c < 'á' || c > 'ú')  && (c < '.' || c > '@')) {
            evt.consume();
        }
    }//GEN-LAST:event_txtcorreopasajeroKeyTyped

    private void txtdireccionpasajeroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdireccionpasajeroKeyTyped
      char c = evt.getKeyChar();
        if ((c < '1' || c > '9') && (c < 'a' || c > 'z') && (c < 'A' || c > 'Z')  && (c < 'á' || c > 'ú')  && (c < '.' || c > '@') && (c == '#')) {
            evt.consume();
        }
    }//GEN-LAST:event_txtdireccionpasajeroKeyTyped

    private void txttipopasajeroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttipopasajeroKeyTyped
        char c = evt.getKeyChar();
        if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z')  && (c < 'á' || c > 'ú')) {
            evt.consume();
        }
    }//GEN-LAST:event_txttipopasajeroKeyTyped

    private void txtnacionalidadpasajeroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnacionalidadpasajeroKeyTyped
        char c = evt.getKeyChar();
        if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z')  && (c < 'á' || c > 'ú')) {
            evt.consume();
        }
    }//GEN-LAST:event_txtnacionalidadpasajeroKeyTyped

    private void txthorasalidaventaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txthorasalidaventaKeyTyped
      
    }//GEN-LAST:event_txthorasalidaventaKeyTyped

    private void txthorallegadaventaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txthorallegadaventaKeyTyped
        
    }//GEN-LAST:event_txthorallegadaventaKeyTyped

    private void txtitinerariotourventaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtitinerariotourventaKeyTyped
       
    }//GEN-LAST:event_txtitinerariotourventaKeyTyped

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        modelo = (DefaultTableModel) TableVenta.getModel();
        modelo.removeRow(TableVenta.getSelectedRow());
        TotalPagar();
        txtcodigotourventa.requestFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel13MouseClicked

    private void txtidventaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtidventaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidventaKeyTyped

    private void tablaventaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaventaMouseClicked
        int fila = tablaventa.rowAtPoint(evt.getPoint());
        //los datos son llevados hacia los campos
        txtidventa.setText(tablaventa.getValueAt(fila, 0).toString());        // TODO add your handling code here:
    }//GEN-LAST:event_tablaventaMouseClicked

    private void logo_pdfMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logo_pdfMouseClicked
    /*if(txtidventa.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Selecciona una fila");
        }else{
            clase_venta = ventadao.BuscarVenta(Integer.parseInt(txtidventa.getText()));
            ventadao.pdfV(clase_venta.getId(), clase_venta.getCliente(), clase_venta.getTotal(), clase_venta.getVendedor());
        }*/        // TODO add your handling code here:
    }//GEN-LAST:event_logo_pdfMouseClicked

    private void TableVentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableVentaMouseClicked
        int fila = TableVenta.rowAtPoint(evt.getPoint());
        txtidtourventa.setText(TableVenta.getValueAt(fila, 0).toString());
        txtcodigotourventa.setText(TableVenta.getValueAt(fila, 1).toString());
        txtnombretourventa.setText(TableVenta.getValueAt(fila, 2).toString());
        txtpreciogeneralventa.setText(TableVenta.getValueAt(fila, 3).toString());
        txthorasalidaventa.setText(TableVenta.getValueAt(fila, 4).toString());
        txthorallegadaventa.setText(TableVenta.getValueAt(fila, 5).toString());
        txtitinerariotourventa.setText(TableVenta.getValueAt(fila, 6).toString());
    }//GEN-LAST:event_TableVentaMouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
       if (!"".equals(txtrutpasajero.getText()) || !"".equals(txtnombrepasajero.getText())
                || !"".equals(txtapellidospasajero.getText()) || !"".equals(txttelefonopasajero.getText())
                || !"".equals(txtcorreopasajero.getText()) || !"".equals(txtdireccionpasajero.getText())
                    || !"".equals(txttipopasajero.getText()) || !"".equals(txtnacionalidadpasajero.getText())) {
            //setea los campos con la llamada de la clase
            clase_pasajero.setRut(txtrutpasajero.getText());
            clase_pasajero.setNombre(txtnombrepasajero.getText());
            clase_pasajero.setApellidos(txtapellidospasajero.getText());
            clase_pasajero.setTelefono(txttelefonopasajero.getText());
            clase_pasajero.setCorreo(txtcorreopasajero.getText());
            clase_pasajero.setDireccion(txtdireccionpasajero.getText());
            clase_pasajero.setTipo(txttipopasajero.getText());
            clase_pasajero.setNacionalidad(txtnacionalidadpasajero.getText());
            pasajerodao.RegistrarPasajero(clase_pasajero);
            //mensaje de cliente registrado
            JOptionPane.showMessageDialog(null, "Pasajero registrado");
            LimpiarTabla();
            ListarPasajero();
            LimpiarCamposPasajero();
      
        } else {
            JOptionPane.showMessageDialog(null, "Los campos estan vacios");
        }
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
               if ("".equals(txtidpasajero.getText())) {
            JOptionPane.showMessageDialog(null, "seleccione una fila");
        } else {
            if (!"".equals(txtrutpasajero.getText()) || !"".equals(txtnombrepasajero.getText())
                    || !"".equals(txtapellidospasajero.getText()) || !"".equals(txttelefonopasajero.getText())
                    || !"".equals(txtcorreopasajero.getText())
                    || !"".equals(txtdireccionpasajero.getText())
                    || !"".equals(txttipopasajero.getText())
                    || !"".equals(txtnacionalidadpasajero.getText())
                    ) {
                clase_pasajero.setRut(txtrutpasajero.getText());
                clase_pasajero.setNombre(txtnombrepasajero.getText());
                clase_pasajero.setApellidos(txtapellidospasajero.getText());
                clase_pasajero.setTelefono(txttelefonopasajero.getText());
                clase_pasajero.setCorreo(txtcorreopasajero.getText());
                clase_pasajero.setDireccion(txtdireccionpasajero.getText());
                clase_pasajero.setTipo(txttipopasajero.getText());
                clase_pasajero.setNacionalidad(txtnacionalidadpasajero.getText());
                clase_pasajero.setId(Integer.parseInt(txtidpasajero.getText()));
                pasajerodao.ModificarPasajero(clase_pasajero);
                LimpiarTabla();
                LimpiarCamposPasajero();
                ListarPasajero();
                JOptionPane.showMessageDialog(null, "Datos actualizados");
            }else{
                  JOptionPane.showMessageDialog(null, "Los campos estan vacios");
            }
        }
    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
             if (!"".equals(txtidpasajero.getText())) {
            int pregunta = JOptionPane.showConfirmDialog(null, "estas seguro de eliminar");
            if (pregunta == 0) {
                int id = Integer.parseInt(txtidpasajero.getText());
                pasajerodao.EliminarPasajero(id);
                LimpiarTabla();
                ListarPasajero();
                //metodo limpia los campos    
                LimpiarCamposPasajero();
            }
        }else{
                JOptionPane.showMessageDialog(null, "seleccione fila");
            }
    }//GEN-LAST:event_jButton3MouseClicked

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
        LimpiarCamposPasajero();
    }//GEN-LAST:event_jButton4MouseClicked

    private void jPanel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseClicked
         tablas.setSelectedIndex(4);
    }//GEN-LAST:event_jPanel4MouseClicked

    private void jButton5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseClicked
        //función para limpiar los campos de nueva venta
        LimpiarCamposNuevaVenta();
    }//GEN-LAST:event_jButton5MouseClicked

    private void jButton6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseClicked
        if (!"".equals(txtcodigotourventa.getText())) {
                String cod = txtcodigotourventa.getText();
                clase_tour = tourdao.BuscarTour(cod);
                if (clase_tour.getNombre() != null) {
                    txtidtourventa.setText("" + clase_tour.getId());
                    txtnombretourventa.setText("" + clase_tour.getNombre());
                    txtpreciogeneralventa.setText("" + clase_tour.getPreciogeneral());
                    txthorasalidaventa.setText("" + clase_tour.getHorasalida());
                    txthorallegadaventa.setText("" + clase_tour.getHorallegada());
                    txtitinerariotourventa.setText("" + clase_tour.getDescripcionitinerario());
                    int id = Integer.parseInt(txtidtourventa.getText());
                    String codigo = txtcodigotourventa.getText();
                    String nombre = txtnombretourventa.getText();
                    int preciogeneral = Integer.parseInt(txtpreciogeneralventa.getText());
                    String horasalida = txthorasalidaventa.getText();
                    String horallegada = txthorallegadaventa.getText();
                    String itinerario = txtitinerariotourventa.getText();
                    item = item + 1;
                    tmp = (DefaultTableModel) TableVenta.getModel();
                    for (int i = 0; i < TableVenta.getRowCount(); i++) {
                        if (TableVenta.getValueAt(i, 1).equals(txtnombretourventa.getText())) {
                            JOptionPane.showMessageDialog(null, "El tour ya esta registrado");
                            return;
                        }
                    }
                    ArrayList lista = new ArrayList();
                    lista.add(item);
                    lista.add(id);
                    lista.add(codigo);
                    lista.add(nombre);
                    lista.add(preciogeneral);
                    lista.add(horasalida);
                    lista.add(horallegada);
                    lista.add(itinerario);
                    Object[] O = new Object[7];
                    O[0] = lista.get(1);
                    O[1] = lista.get(2);
                    O[2] = lista.get(3);
                    O[3] = lista.get(4);
                    O[4] = lista.get(5);
                    O[5] = lista.get(6);
                    O[6] = lista.get(7);
                    tmp.addRow(O);
                    TableVenta.setModel(tmp);
                    TotalPagar();
                    txtcodigotourventa.requestFocus(); 
                } else {
                    LimpiarCamposTourventa();
                    txtcodigotourventa.requestFocus();
                    JOptionPane.showMessageDialog(null, "Tour incorrecto");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese el codigo del tour");
                txtcodigotourventa.requestFocus();
            }
    }//GEN-LAST:event_jButton6MouseClicked

    private void jButton7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseClicked
        if (!"".equals(txtrutpasajeroventa.getText())) {
            String rut = txtrutpasajeroventa.getText();
            clase_pasajero = pasajerodao.Buscarpasajero(rut);
            if (clase_pasajero.getNombre() != null) {
                txtnombrepasajeroventa.setText("" + clase_pasajero.getNombre());
                txtapellidospasajeroventa.setText("" + clase_pasajero.getApellidos());
                txttelefonopasajeroventa.setText("" + clase_pasajero.getTelefono());
                txtidpasajeroventa.setText("" + clase_pasajero.getId());
            } else {
                txtrutpasajeroventa.setText("");
                JOptionPane.showMessageDialog(null, "El pasajero no existe");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe buscar un pasajero");
        }
    }//GEN-LAST:event_jButton7MouseClicked

    private void buttonLimpiarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonLimpiarMouseClicked
        LimpiarCamposPasajeroVenta();
    }//GEN-LAST:event_buttonLimpiarMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Sistema().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LabelTotal;
    private javax.swing.JLabel Labeldireccionagencia;
    private javax.swing.JLabel Labelnombreagencia;
    private javax.swing.JLabel Labelrutagencia;
    private javax.swing.JLabel Labeltelefonoagencia;
    private com.toedter.calendar.JDateChooser Midate;
    private javax.swing.JTable TableVenta;
    private javax.swing.JLabel btnGraficar;
    private javax.swing.JLabel btngenrarventa;
    private javax.swing.JButton buttonLimpiar;
    private javax.swing.JLabel icono_admin;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JLabel logo_pdf;
    private javax.swing.JLabel nombre_vendedor;
    private javax.swing.JTable tablapasajero;
    private javax.swing.JTabbedPane tablas;
    private javax.swing.JTable tablatour;
    private javax.swing.JTable tablaventa;
    private javax.swing.JTextField txtapellidospasajero;
    private javax.swing.JTextField txtapellidospasajeroventa;
    private javax.swing.JTextField txtcodigotourventa;
    private javax.swing.JTextField txtcorreopasajero;
    private javax.swing.JTextField txtdireccionpasajero;
    private javax.swing.JTextField txthorallegadaventa;
    private javax.swing.JTextField txthorasalidaventa;
    private javax.swing.JTextField txtidpasajero;
    private javax.swing.JTextField txtidpasajeroventa;
    private javax.swing.JTextField txtidtourventa;
    private javax.swing.JTextField txtidventa;
    private javax.swing.JTextArea txtitinerario;
    private javax.swing.JTextArea txtitinerariotourventa;
    private javax.swing.JTextField txtnacionalidadpasajero;
    private javax.swing.JTextField txtnombrepasajero;
    private javax.swing.JTextField txtnombrepasajeroventa;
    private javax.swing.JTextField txtnombretourventa;
    private javax.swing.JTextField txtpreciogeneralventa;
    private javax.swing.JTextField txtrutpasajero;
    private javax.swing.JTextField txtrutpasajeroventa;
    private javax.swing.JTextField txttelefonopasajero;
    private javax.swing.JTextField txttelefonopasajeroventa;
    private javax.swing.JTextField txttipopasajero;
    // End of variables declaration//GEN-END:variables
}
