
package Vista;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Agencia;
import modelo.DAO.AgenciaDAO;
import modelo.Login_dueno;
import modelo.Pasajero;
import modelo.DAO.PasajeroDAO;
import modelo.Proveedor;
import modelo.DAO.ProveedorDAO;
import modelo.Tour;
import modelo.DAO.TourDAO;
import modelo.Vendedor;
import modelo.DAO.VendedorDAO;
import modelo.Venta;
import modelo.DAO.VentaDAO;

/**
 *
 * @author _pant
 */
public class Admindueno extends javax.swing.JFrame {

    /**
     * Creates new form Admindueno
     */
    /*variable model para las tablas*/
      DefaultTableModel modelo = new DefaultTableModel();
     /*clases necesarias para el sistema*/
         Agencia clase_agencia = new Agencia(); 
         AgenciaDAO agencia_dao = new AgenciaDAO();
         
         Vendedor clase_vendedor = new Vendedor();
         VendedorDAO vendedor_dao = new VendedorDAO();
         
         Proveedor clase_proveedor = new Proveedor();
         ProveedorDAO proveedordao = new ProveedorDAO();
         
         Tour clase_tour = new Tour();
         TourDAO tourdao = new TourDAO();
         
         Venta clase_venta = new Venta();
         VentaDAO ventadao = new VentaDAO();
         
         Pasajero clase_pasajero = new Pasajero();
         PasajeroDAO pasajerodao = new PasajeroDAO();
         
    public Admindueno() {
        initComponents();
    }
     public Admindueno(Login_dueno priv) {
        initComponents();
        nombre_vendedor.setText(priv.getNombre());
        //ocultar campos de id
        txtidempresa.setVisible(false);
        txtidvendedor.setVisible(false);
        txtidproveedor.setVisible(false);
        txtidpasajero.setVisible(false);
        txtidtour.setVisible(false);
        txtidventa.setVisible(false);
        //imagen para para el escritotio
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/logo_passarinho_actualizado.png")));
           //funciones de listar
        ListarAgencia(); 
    }
     //funciones de listar
     public void ListarAgencia(){
        clase_agencia = agencia_dao.BuscarAgencia();
        txtidempresa.setText(""+clase_agencia.getId());
        txtrutempresa.setText(""+clase_agencia.getRut());
        txtnombreempresa.setText(""+clase_agencia.getNombre());
        txttelefonoempresa.setText(""+clase_agencia.getTelefono());
        txtdireccionempresa.setText(""+clase_agencia.getDireccion());
     }
     
    public void ListarVendedor() {
        List<Vendedor> ListarVe = vendedor_dao.ListarVendedor();
        modelo = (DefaultTableModel) tablavendedor.getModel();
        Object[] ob = new Object[4];
        for (int i = 0; i < ListarVe.size(); i++) {
            ob[0] = ListarVe.get(i).getId();
            ob[1] = ListarVe.get(i).getNombre();
            ob[2] = ListarVe.get(i).getCorreo();
            ob[3] = ListarVe.get(i).getClave();
            modelo.addRow(ob);
        }
        tablavendedor.setModel(modelo);
    }
    
      public void ListarProveedor(){
        List<Proveedor> Listarpr = proveedordao.ListarProveedor();
        modelo = (DefaultTableModel) tablaproveedor.getModel();
        Object[] ob = new Object[6];
             for (int i = 0; i < Listarpr.size(); i++) {
            ob[0] = Listarpr.get(i).getId();
            ob[1] = Listarpr.get(i).getNombre();
            ob[2] = Listarpr.get(i).getCorreo();
            ob[3] = Listarpr.get(i).getTelefono();
            ob[4] = Listarpr.get(i).getDireccion();
            ob[5] = Listarpr.get(i).getTipo();
            modelo.addRow(ob);
        }
             tablaproveedor.setModel(modelo);
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
        Object[] ob = new Object[5];
        for (int i = 0; i < Listarve.size(); i++) {
            ob[0] = Listarve.get(i).getId();
            ob[1] = Listarve.get(i).getPasajero();
            ob[2] = Listarve.get(i).getVendedor();
            ob[3] = Listarve.get(i).getTotal();
            ob[4] = Listarve.get(i).getFecha();
            modelo.addRow(ob);
        }
        tablaventa.setModel(modelo);
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
       public void LimpiarTabla() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
       
       
    }
    public void LimpiarCamposVendedor() {
        txtnombrevendedor.setText("");
        txtcorreovendedor.setText("");
        txtclavevendedor.setText("");
    }
    
    public void LimpiarCamposProveedor(){
        txtnombreproveedor.setText("");
        txtcorreoproveedor.setText("");
        txttelefonoproveedor.setText("");
        txtdireccionproveedor.setText("");
        txttipoproveedor.setText("");
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
    public void LimpiarCamposTour(){
        txtcodigotour.setText("");
        txtnombretour.setText("");
        txtpreciogeneral.setText("");
        txthorasalida.setText("");
        txthorallegada.setText("");
        txtitinerario.setText("");
    }
        public void LimpiarCamposVenta(){
        txtidventa.setText("");
        txtpasajeroventa.setText("");
        txtvendedorventa.setText("");
        txttotalventa.setText("");
        txtfechaventa.setText("");
    }
     private void desplegarpasajeroexcel(){
         pasajerodao.excel();
     }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        nombre_vendedor = new javax.swing.JLabel();
        buttondatosagencia1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        buttondatoproveedor = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        buttondatovendedor = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        buttondatotour = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        buttondatoventa = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        tablas = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtrutempresa = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtnombreempresa = new javax.swing.JTextField();
        txttelefonoempresa = new javax.swing.JTextField();
        txtdireccionempresa = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        txtidempresa = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablavendedor = new javax.swing.JTable();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        txtidvendedor = new javax.swing.JTextField();
        txtnombrevendedor = new javax.swing.JTextField();
        txtcorreovendedor = new javax.swing.JTextField();
        icono_excel_vendedor = new javax.swing.JLabel();
        buttonagrregarvendedor = new javax.swing.JButton();
        buttoneditarvendedor = new javax.swing.JButton();
        buttoneliminarvendedor = new javax.swing.JButton();
        buttonnuevovendedor = new javax.swing.JButton();
        txtclavevendedor = new javax.swing.JPasswordField();
        jPanel11 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txtidproveedor = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaproveedor = new javax.swing.JTable();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        txtnombreproveedor = new javax.swing.JTextField();
        txtcorreoproveedor = new javax.swing.JTextField();
        txtdireccionproveedor = new javax.swing.JTextField();
        txttipoproveedor = new javax.swing.JTextField();
        txttelefonoproveedor = new javax.swing.JTextField();
        buttonagregarproveedor = new javax.swing.JButton();
        buttoneditarproveedor = new javax.swing.JButton();
        buttoneliminarproveedor = new javax.swing.JButton();
        buttonnuevoproveedor = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablatour = new javax.swing.JTable();
        buttonagregartour = new javax.swing.JButton();
        buttoneliminartour = new javax.swing.JButton();
        buttoneditartour = new javax.swing.JButton();
        buttonnurvotour = new javax.swing.JButton();
        txtcodigotour = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        txtpreciogeneral = new javax.swing.JTextField();
        txtnombretour = new javax.swing.JTextField();
        txthorasalida = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtitinerario = new javax.swing.JTextArea();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        txtidtour = new javax.swing.JTextField();
        txthorallegada = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablapasajero = new javax.swing.JTable();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
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
        buttonagregarpasajero = new javax.swing.JButton();
        buttoneditarpasajero = new javax.swing.JButton();
        buttoneliminarpasajero = new javax.swing.JButton();
        buttonnuevopasajero = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tablaventa = new javax.swing.JTable();
        txtidventa = new javax.swing.JTextField();
        txtvendedorventa = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        txtpasajeroventa = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        txtfechaventa = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        txttotalventa = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        buttoneliminarpasajero1 = new javax.swing.JButton();

        setExtendedState(6);

        jPanel1.setBackground(new java.awt.Color(130, 56, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(65, 53, 87));

        nombre_vendedor.setBackground(new java.awt.Color(255, 255, 255));
        nombre_vendedor.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        nombre_vendedor.setForeground(new java.awt.Color(255, 255, 255));
        nombre_vendedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/login.png"))); // NOI18N
        nombre_vendedor.setText("dueno");

        buttondatosagencia1.setBackground(new java.awt.Color(130, 56, 102));
        buttondatosagencia1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttondatosagencia1.setPreferredSize(new java.awt.Dimension(203, 45));
        buttondatosagencia1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttondatosagencia1MouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Datos de la agencia");

        javax.swing.GroupLayout buttondatosagencia1Layout = new javax.swing.GroupLayout(buttondatosagencia1);
        buttondatosagencia1.setLayout(buttondatosagencia1Layout);
        buttondatosagencia1Layout.setHorizontalGroup(
            buttondatosagencia1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buttondatosagencia1Layout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(30, 30, 30))
        );
        buttondatosagencia1Layout.setVerticalGroup(
            buttondatosagencia1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttondatosagencia1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        buttondatoproveedor.setBackground(new java.awt.Color(130, 56, 102));
        buttondatoproveedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttondatoproveedor.setPreferredSize(new java.awt.Dimension(203, 45));
        buttondatoproveedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttondatoproveedorMouseClicked(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Proveedor");

        javax.swing.GroupLayout buttondatoproveedorLayout = new javax.swing.GroupLayout(buttondatoproveedor);
        buttondatoproveedor.setLayout(buttondatoproveedorLayout);
        buttondatoproveedorLayout.setHorizontalGroup(
            buttondatoproveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buttondatoproveedorLayout.createSequentialGroup()
                .addContainerGap(62, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(66, 66, 66))
        );
        buttondatoproveedorLayout.setVerticalGroup(
            buttondatoproveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttondatoproveedorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        buttondatovendedor.setBackground(new java.awt.Color(130, 56, 102));
        buttondatovendedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttondatovendedor.setPreferredSize(new java.awt.Dimension(203, 45));
        buttondatovendedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttondatovendedorMouseClicked(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Vendedor");

        javax.swing.GroupLayout buttondatovendedorLayout = new javax.swing.GroupLayout(buttondatovendedor);
        buttondatovendedor.setLayout(buttondatovendedorLayout);
        buttondatovendedorLayout.setHorizontalGroup(
            buttondatovendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buttondatovendedorLayout.createSequentialGroup()
                .addContainerGap(68, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(66, 66, 66))
        );
        buttondatovendedorLayout.setVerticalGroup(
            buttondatovendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttondatovendedorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        buttondatotour.setBackground(new java.awt.Color(130, 56, 102));
        buttondatotour.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttondatotour.setPreferredSize(new java.awt.Dimension(203, 45));
        buttondatotour.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttondatotourMouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Tour");

        javax.swing.GroupLayout buttondatotourLayout = new javax.swing.GroupLayout(buttondatotour);
        buttondatotour.setLayout(buttondatotourLayout);
        buttondatotourLayout.setHorizontalGroup(
            buttondatotourLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buttondatotourLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(90, 90, 90))
        );
        buttondatotourLayout.setVerticalGroup(
            buttondatotourLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttondatotourLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        buttondatoventa.setBackground(new java.awt.Color(130, 56, 102));
        buttondatoventa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttondatoventa.setPreferredSize(new java.awt.Dimension(203, 45));
        buttondatoventa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttondatoventaMouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Venta");

        javax.swing.GroupLayout buttondatoventaLayout = new javax.swing.GroupLayout(buttondatoventa);
        buttondatoventa.setLayout(buttondatoventaLayout);
        buttondatoventaLayout.setHorizontalGroup(
            buttondatoventaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buttondatoventaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(79, 79, 79))
        );
        buttondatoventaLayout.setVerticalGroup(
            buttondatoventaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buttondatoventaLayout.createSequentialGroup()
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

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Pasajero");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(68, 68, 68))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(nombre_vendedor, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                    .addComponent(buttondatotour, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttondatoproveedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttondatovendedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttondatosagencia1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttondatoventa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(nombre_vendedor)
                .addGap(43, 43, 43)
                .addComponent(buttondatosagencia1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(buttondatovendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(buttondatoproveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(buttondatotour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(buttondatoventa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 250, 610));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logo_admin_amplio.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, 110));

        jPanel5.setBackground(new java.awt.Color(130, 56, 102));

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Datos de la agencia");

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Rut");

        txtrutempresa.setBackground(new java.awt.Color(130, 56, 102));
        txtrutempresa.setForeground(new java.awt.Color(255, 255, 255));
        txtrutempresa.setBorder(null);

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Nombre");

        jLabel15.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Teléfono");

        jLabel16.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Dirección");

        txtnombreempresa.setBackground(new java.awt.Color(130, 56, 102));
        txtnombreempresa.setForeground(new java.awt.Color(255, 255, 255));
        txtnombreempresa.setBorder(null);
        txtnombreempresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnombreempresaActionPerformed(evt);
            }
        });

        txttelefonoempresa.setBackground(new java.awt.Color(130, 56, 102));
        txttelefonoempresa.setForeground(new java.awt.Color(255, 255, 255));
        txttelefonoempresa.setBorder(null);

        txtdireccionempresa.setBackground(new java.awt.Color(130, 56, 102));
        txtdireccionempresa.setForeground(new java.awt.Color(255, 255, 255));
        txtdireccionempresa.setBorder(null);

        jSeparator1.setBackground(new java.awt.Color(65, 53, 87));
        jSeparator1.setForeground(new java.awt.Color(65, 53, 87));

        jSeparator2.setBackground(new java.awt.Color(65, 53, 87));
        jSeparator2.setForeground(new java.awt.Color(65, 53, 87));

        jSeparator3.setBackground(new java.awt.Color(65, 53, 87));
        jSeparator3.setForeground(new java.awt.Color(65, 53, 87));

        jSeparator4.setBackground(new java.awt.Color(65, 53, 87));
        jSeparator4.setForeground(new java.awt.Color(65, 53, 87));

        txtidempresa.setEditable(false);
        txtidempresa.setBackground(new java.awt.Color(130, 56, 102));
        txtidempresa.setBorder(null);

        jButton1.setBackground(new java.awt.Color(130, 56, 102));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Actualizar Datos");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel8))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(530, 530, 530)
                                .addComponent(jLabel13)
                                .addGap(30, 30, 30))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addGap(495, 495, 495)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addComponent(txtrutempresa, javax.swing.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)
                            .addComponent(jSeparator2)
                            .addComponent(txtnombreempresa, javax.swing.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)
                            .addComponent(jSeparator3)
                            .addComponent(jSeparator4)
                            .addComponent(txttelefonoempresa, javax.swing.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)
                            .addComponent(txtdireccionempresa, javax.swing.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGap(116, 116, 116)
                                        .addComponent(txtidempresa, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jButton1))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(106, 106, 106))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel8)
                .addGap(17, 17, 17)
                .addComponent(txtidempresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtrutempresa, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtnombreempresa, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txttelefonoempresa, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtdireccionempresa, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addGap(7, 7, 7)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66)
                .addComponent(jButton1)
                .addContainerGap(113, Short.MAX_VALUE))
        );

        tablas.addTab("tab1", jPanel5);

        jPanel6.setBackground(new java.awt.Color(130, 56, 102));

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Vendedor");

        tablavendedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Nombre", "Correo", "Clave"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablavendedor.setEditingColumn(0);
        tablavendedor.setEditingRow(0);
        tablavendedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablavendedorMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablavendedor);

        jLabel45.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(255, 255, 255));
        jLabel45.setText("Nombre");

        jLabel46.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(255, 255, 255));
        jLabel46.setText("Correo");

        jLabel47.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(255, 255, 255));
        jLabel47.setText("Clave");

        txtnombrevendedor.setBackground(new java.awt.Color(255, 255, 255));
        txtnombrevendedor.setForeground(new java.awt.Color(51, 51, 51));
        txtnombrevendedor.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtcorreovendedor.setBackground(new java.awt.Color(255, 255, 255));
        txtcorreovendedor.setForeground(new java.awt.Color(51, 51, 51));
        txtcorreovendedor.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        icono_excel_vendedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        icono_excel_vendedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                icono_excel_vendedorMouseClicked(evt);
            }
        });

        buttonagrregarvendedor.setBackground(new java.awt.Color(130, 56, 102));
        buttonagrregarvendedor.setForeground(new java.awt.Color(255, 255, 255));
        buttonagrregarvendedor.setText("Agregar");
        buttonagrregarvendedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonagrregarvendedor.setPreferredSize(new java.awt.Dimension(75, 25));
        buttonagrregarvendedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonagrregarvendedorMouseClicked(evt);
            }
        });

        buttoneditarvendedor.setBackground(new java.awt.Color(130, 56, 102));
        buttoneditarvendedor.setForeground(new java.awt.Color(255, 255, 255));
        buttoneditarvendedor.setText("Editar");
        buttoneditarvendedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttoneditarvendedor.setMaximumSize(new java.awt.Dimension(75, 25));
        buttoneditarvendedor.setPreferredSize(new java.awt.Dimension(75, 25));
        buttoneditarvendedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttoneditarvendedorMouseClicked(evt);
            }
        });

        buttoneliminarvendedor.setBackground(new java.awt.Color(130, 56, 102));
        buttoneliminarvendedor.setForeground(new java.awt.Color(255, 255, 255));
        buttoneliminarvendedor.setText("Eliminar");
        buttoneliminarvendedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttoneliminarvendedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttoneliminarvendedorMouseClicked(evt);
            }
        });

        buttonnuevovendedor.setBackground(new java.awt.Color(130, 56, 102));
        buttonnuevovendedor.setForeground(new java.awt.Color(255, 255, 255));
        buttonnuevovendedor.setText("Nuevo");
        buttonnuevovendedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonnuevovendedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonnuevovendedorMouseClicked(evt);
            }
        });

        txtclavevendedor.setBackground(new java.awt.Color(255, 255, 255));
        txtclavevendedor.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(buttonagrregarvendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(buttoneditarvendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(buttoneliminarvendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(buttonnuevovendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(194, 194, 194)
                        .addComponent(icono_excel_vendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 974, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtnombrevendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel45))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtidvendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(182, 182, 182)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel46)
                                    .addComponent(txtcorreovendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(37, 37, 37)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel47)
                                    .addComponent(txtclavevendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(121, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtidvendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(jLabel46)
                    .addComponent(jLabel47))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtclavevendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtnombrevendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtcorreovendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(39, 39, 39)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buttonagrregarvendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttoneditarvendedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(icono_excel_vendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buttoneliminarvendedor)
                            .addComponent(buttonnuevovendedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(42, 42, 42))))
        );

        tablas.addTab("tab2", jPanel6);

        jPanel11.setBackground(new java.awt.Color(130, 56, 102));

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Proveedor");

        tablaproveedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre", "Correo", "Teléfono", "Dirección", "Tipo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaproveedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaproveedorMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaproveedor);

        jLabel36.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setText("Nombre");

        jLabel37.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setText("Correo");

        jLabel38.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setText("Teléfono");

        jLabel39.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setText("Dirección");

        jLabel40.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setText("Tipo");

        txtnombreproveedor.setBackground(new java.awt.Color(255, 255, 255));
        txtnombreproveedor.setForeground(new java.awt.Color(51, 51, 51));
        txtnombreproveedor.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtnombreproveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnombreproveedorKeyTyped(evt);
            }
        });

        txtcorreoproveedor.setBackground(new java.awt.Color(255, 255, 255));
        txtcorreoproveedor.setForeground(new java.awt.Color(51, 51, 51));
        txtcorreoproveedor.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtcorreoproveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcorreoproveedorKeyTyped(evt);
            }
        });

        txtdireccionproveedor.setBackground(new java.awt.Color(255, 255, 255));
        txtdireccionproveedor.setForeground(new java.awt.Color(51, 51, 51));
        txtdireccionproveedor.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtdireccionproveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtdireccionproveedorKeyTyped(evt);
            }
        });

        txttipoproveedor.setBackground(new java.awt.Color(255, 255, 255));
        txttipoproveedor.setForeground(new java.awt.Color(51, 51, 51));
        txttipoproveedor.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txttipoproveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txttipoproveedorKeyTyped(evt);
            }
        });

        txttelefonoproveedor.setBackground(new java.awt.Color(255, 255, 255));
        txttelefonoproveedor.setForeground(new java.awt.Color(51, 51, 51));
        txttelefonoproveedor.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txttelefonoproveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txttelefonoproveedorKeyTyped(evt);
            }
        });

        buttonagregarproveedor.setBackground(new java.awt.Color(130, 56, 102));
        buttonagregarproveedor.setForeground(new java.awt.Color(255, 255, 255));
        buttonagregarproveedor.setText("Agregar");
        buttonagregarproveedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonagregarproveedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonagregarproveedorMouseClicked(evt);
            }
        });

        buttoneditarproveedor.setBackground(new java.awt.Color(130, 56, 102));
        buttoneditarproveedor.setForeground(new java.awt.Color(255, 255, 255));
        buttoneditarproveedor.setText("Editar");
        buttoneditarproveedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttoneditarproveedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttoneditarproveedorMouseClicked(evt);
            }
        });

        buttoneliminarproveedor.setBackground(new java.awt.Color(130, 56, 102));
        buttoneliminarproveedor.setForeground(new java.awt.Color(255, 255, 255));
        buttoneliminarproveedor.setText("Eliminar");
        buttoneliminarproveedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttoneliminarproveedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttoneliminarproveedorMouseClicked(evt);
            }
        });

        buttonnuevoproveedor.setBackground(new java.awt.Color(130, 56, 102));
        buttonnuevoproveedor.setForeground(new java.awt.Color(255, 255, 255));
        buttonnuevoproveedor.setText("Nuevo");
        buttonnuevoproveedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonnuevoproveedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonnuevoproveedorMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(txttipoproveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(buttonagregarproveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createSequentialGroup()
                                        .addGap(348, 348, 348)
                                        .addComponent(jLabel38))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createSequentialGroup()
                                        .addGap(30, 30, 30)
                                        .addComponent(buttoneditarproveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(50, 50, 50)
                                        .addComponent(buttoneliminarproveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(50, 50, 50)
                                        .addComponent(buttonnuevoproveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtidproveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtnombreproveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel40)
                                            .addComponent(jLabel36))
                                        .addGap(59, 59, 59)
                                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel37)
                                            .addComponent(txtcorreoproveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(66, 66, 66)
                                        .addComponent(txttelefonoproveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(74, 74, 74)
                                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtdireccionproveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel39))))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtidproveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(jLabel37)
                    .addComponent(jLabel38)
                    .addComponent(jLabel39))
                .addGap(8, 8, 8)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtnombreproveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtcorreoproveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txttelefonoproveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtdireccionproveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel40)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txttipoproveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonagregarproveedor)
                    .addComponent(buttoneditarproveedor)
                    .addComponent(buttoneliminarproveedor)
                    .addComponent(buttonnuevoproveedor))
                .addGap(0, 86, Short.MAX_VALUE))
        );

        tablas.addTab("tab7", jPanel11);

        jPanel12.setBackground(new java.awt.Color(130, 56, 102));

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Tour");

        tablatour.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "código", "Nombre", "Precio General", "Hora de salida", "Hora de llegada", "Descripción de itinerario"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
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

        buttonagregartour.setBackground(new java.awt.Color(130, 56, 102));
        buttonagregartour.setForeground(new java.awt.Color(255, 255, 255));
        buttonagregartour.setText("Agregar");
        buttonagregartour.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonagregartour.setPreferredSize(new java.awt.Dimension(75, 25));
        buttonagregartour.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonagregartourMouseClicked(evt);
            }
        });

        buttoneliminartour.setBackground(new java.awt.Color(130, 56, 102));
        buttoneliminartour.setForeground(new java.awt.Color(255, 255, 255));
        buttoneliminartour.setText("Eliminar");
        buttoneliminartour.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttoneliminartour.setPreferredSize(new java.awt.Dimension(75, 25));
        buttoneliminartour.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttoneliminartourMouseClicked(evt);
            }
        });

        buttoneditartour.setBackground(new java.awt.Color(130, 56, 102));
        buttoneditartour.setForeground(new java.awt.Color(255, 255, 255));
        buttoneditartour.setText("Editar");
        buttoneditartour.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttoneditartour.setPreferredSize(new java.awt.Dimension(75, 25));
        buttoneditartour.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttoneditartourMouseClicked(evt);
            }
        });

        buttonnurvotour.setBackground(new java.awt.Color(130, 56, 102));
        buttonnurvotour.setForeground(new java.awt.Color(255, 255, 255));
        buttonnurvotour.setText("Nuevo");
        buttonnurvotour.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonnurvotour.setPreferredSize(new java.awt.Dimension(75, 25));
        buttonnurvotour.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonnurvotourMouseClicked(evt);
            }
        });

        txtcodigotour.setBackground(new java.awt.Color(255, 255, 255));
        txtcodigotour.setForeground(new java.awt.Color(51, 51, 51));
        txtcodigotour.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtcodigotour.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcodigotourKeyTyped(evt);
            }
        });

        jLabel41.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(255, 255, 255));
        jLabel41.setText("Código");

        txtpreciogeneral.setBackground(new java.awt.Color(255, 255, 255));
        txtpreciogeneral.setForeground(new java.awt.Color(51, 51, 51));
        txtpreciogeneral.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtpreciogeneral.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtpreciogeneralKeyTyped(evt);
            }
        });

        txtnombretour.setBackground(new java.awt.Color(255, 255, 255));
        txtnombretour.setForeground(new java.awt.Color(51, 51, 51));
        txtnombretour.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtnombretour.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnombretourKeyTyped(evt);
            }
        });

        txthorasalida.setBackground(new java.awt.Color(255, 255, 255));
        txthorasalida.setForeground(new java.awt.Color(51, 51, 51));
        txthorasalida.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txthorasalida.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txthorasalidaKeyTyped(evt);
            }
        });

        txtitinerario.setBackground(new java.awt.Color(255, 255, 255));
        txtitinerario.setColumns(20);
        txtitinerario.setRows(5);
        txtitinerario.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jScrollPane6.setViewportView(txtitinerario);

        jLabel42.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(255, 255, 255));
        jLabel42.setText("Precio General");

        jLabel43.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(255, 255, 255));
        jLabel43.setText("Nombre");

        jLabel44.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(255, 255, 255));
        jLabel44.setText("Hora de llegada");

        jLabel49.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(255, 255, 255));
        jLabel49.setText("Itinerario");

        txthorallegada.setBackground(new java.awt.Color(255, 255, 255));
        txthorallegada.setForeground(new java.awt.Color(51, 51, 51));
        txthorallegada.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txthorallegada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txthorallegadaKeyTyped(evt);
            }
        });

        jLabel50.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(255, 255, 255));
        jLabel50.setText("Hora de salida");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel12Layout.createSequentialGroup()
                                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel42)
                                            .addComponent(jLabel41)
                                            .addGroup(jPanel12Layout.createSequentialGroup()
                                                .addComponent(jLabel12)
                                                .addGap(21, 21, 21)
                                                .addComponent(txtidtour, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(347, 347, 347)
                                        .addComponent(jLabel44)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtcodigotour, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel12Layout.createSequentialGroup()
                                        .addComponent(txtpreciogeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(48, 48, 48)
                                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel50)
                                            .addComponent(txthorasalida, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txthorallegada, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel43)
                                    .addComponent(txtnombretour, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(122, 122, 122)))
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel49))
                        .addGap(27, 27, 27))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(buttonagregartour, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(buttoneditartour, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(buttoneliminartour, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(buttonnurvotour, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12)
                            .addComponent(txtidtour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addComponent(jLabel41)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel43)
                            .addComponent(jLabel49))
                        .addGap(15, 15, 15)))
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtcodigotour, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtnombretour, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel42)
                            .addComponent(jLabel44)
                            .addComponent(jLabel50))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtpreciogeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txthorasalida, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txthorallegada, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonagregartour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttoneditartour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttoneliminartour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonnurvotour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(54, Short.MAX_VALUE))
        );

        tablas.addTab("tab4", jPanel12);

        jPanel10.setBackground(new java.awt.Color(130, 56, 102));

        jLabel18.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Pasajero");

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
        tablapasajero.setGridColor(new java.awt.Color(255, 255, 255));
        tablapasajero.setSelectionForeground(new java.awt.Color(0, 0, 0));
        tablapasajero.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablapasajeroMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tablapasajero);

        jLabel21.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Rut");

        jLabel22.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Nombre");

        jLabel23.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Apellidos");

        jLabel24.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Teléfono");

        jLabel26.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Correo");

        jLabel27.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Direccion");

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

        buttonagregarpasajero.setBackground(new java.awt.Color(130, 56, 102));
        buttonagregarpasajero.setForeground(new java.awt.Color(255, 255, 255));
        buttonagregarpasajero.setText("Agregar");
        buttonagregarpasajero.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonagregarpasajero.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonagregarpasajeroMouseClicked(evt);
            }
        });

        buttoneditarpasajero.setBackground(new java.awt.Color(130, 56, 102));
        buttoneditarpasajero.setForeground(new java.awt.Color(255, 255, 255));
        buttoneditarpasajero.setText("Editar");
        buttoneditarpasajero.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttoneditarpasajero.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttoneditarpasajeroMouseClicked(evt);
            }
        });

        buttoneliminarpasajero.setBackground(new java.awt.Color(130, 56, 102));
        buttoneliminarpasajero.setForeground(new java.awt.Color(255, 255, 255));
        buttoneliminarpasajero.setText("Eliminar");
        buttoneliminarpasajero.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttoneliminarpasajero.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttoneliminarpasajeroMouseClicked(evt);
            }
        });

        buttonnuevopasajero.setBackground(new java.awt.Color(130, 56, 102));
        buttonnuevopasajero.setForeground(new java.awt.Color(255, 255, 255));
        buttonnuevopasajero.setText("Nuevo");
        buttonnuevopasajero.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonnuevopasajero.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonnuevopasajeroMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtrutpasajero, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                                        .addComponent(jLabel21)
                                        .addComponent(txtcorreopasajero))
                                    .addComponent(jLabel26))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel22)
                                    .addComponent(txtnombrepasajero, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtdireccionpasajero, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel27))
                                .addGap(37, 37, 37)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel28)
                                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel23)
                                        .addComponent(txtapellidospasajero, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txttipopasajero, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel24)
                                    .addComponent(jLabel29)
                                    .addComponent(txttelefonopasajero, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                                    .addComponent(txtnacionalidadpasajero))))
                        .addGap(32, 32, 32))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(buttonagregarpasajero, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(buttoneditarpasajero, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(buttoneliminarpasajero, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(buttonnuevopasajero, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addGap(31, 31, 31)
                                .addComponent(txtidpasajero, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(545, 545, Short.MAX_VALUE))))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(10, 10, 10))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addComponent(txtidpasajero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel21)
                        .addComponent(jLabel22)
                        .addComponent(jLabel24)))
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
                            .addComponent(jLabel27)
                            .addComponent(jLabel26)
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
                .addGap(34, 34, 34)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(buttoneliminarpasajero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonnuevopasajero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(buttoneditarpasajero, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonagregarpasajero, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(80, 80, 80))
        );

        tablas.addTab("tab2", jPanel10);

        jPanel13.setBackground(new java.awt.Color(130, 56, 102));
        jPanel13.setPreferredSize(new java.awt.Dimension(1400, 613));

        jLabel32.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("Venta");

        tablaventa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Pasajero", "Vendedor", "Total", "Fecha"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, false, false
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

        txtidventa.setBackground(new java.awt.Color(255, 255, 255));
        txtidventa.setForeground(new java.awt.Color(51, 51, 51));
        txtidventa.setBorder(null);
        txtidventa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtidventaKeyTyped(evt);
            }
        });

        txtvendedorventa.setBackground(new java.awt.Color(255, 255, 255));
        txtvendedorventa.setForeground(new java.awt.Color(51, 51, 51));
        txtvendedorventa.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtvendedorventa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtvendedorventaKeyTyped(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Pasajero");

        jLabel30.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("Vendedor");

        txtpasajeroventa.setBackground(new java.awt.Color(255, 255, 255));
        txtpasajeroventa.setForeground(new java.awt.Color(51, 51, 51));
        txtpasajeroventa.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtpasajeroventa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtpasajeroventaKeyTyped(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("Total");

        txtfechaventa.setBackground(new java.awt.Color(255, 255, 255));
        txtfechaventa.setForeground(new java.awt.Color(51, 51, 51));
        txtfechaventa.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtfechaventa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtfechaventaKeyTyped(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("Fecha (dd/mm/aa)");

        txttotalventa.setBackground(new java.awt.Color(255, 255, 255));
        txttotalventa.setForeground(new java.awt.Color(51, 51, 51));
        txttotalventa.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txttotalventa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txttotalventaKeyTyped(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(130, 56, 102));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pdf.png"))); // NOI18N
        jButton2.setText("PDF");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        buttoneliminarpasajero1.setBackground(new java.awt.Color(130, 56, 102));
        buttoneliminarpasajero1.setForeground(new java.awt.Color(255, 255, 255));
        buttoneliminarpasajero1.setText("Eliminar");
        buttoneliminarpasajero1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttoneliminarpasajero1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttoneliminarpasajero1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel32)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtidventa, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(39, 39, 39)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25)
                            .addComponent(txtpasajeroventa, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel30)
                            .addComponent(txtvendedorventa, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel31)
                            .addComponent(txttotalventa, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel33)
                            .addComponent(txtfechaventa, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 1036, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(60, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttoneliminarpasajero1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(159, 159, 159))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel32)
                    .addComponent(txtidventa, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(jLabel30)
                            .addComponent(jLabel31)
                            .addComponent(jLabel33))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtvendedorventa, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtpasajeroventa, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtfechaventa, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txttotalventa, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jButton2)))
                .addGap(39, 39, 39)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttoneliminarpasajero1)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        tablas.addTab("tab5", jPanel13);

        jPanel1.add(tablas, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 70, 1140, 640));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttondatosagencia1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttondatosagencia1MouseClicked
       tablas.setSelectedIndex(0);
       ListarAgencia();
    }//GEN-LAST:event_buttondatosagencia1MouseClicked

    private void buttondatoproveedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttondatoproveedorMouseClicked
       tablas.setSelectedIndex(2);
       LimpiarTabla();
       ListarProveedor();
    }//GEN-LAST:event_buttondatoproveedorMouseClicked

    private void txtnombreempresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnombreempresaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnombreempresaActionPerformed

    private void icono_excel_vendedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icono_excel_vendedorMouseClicked
       
    }//GEN-LAST:event_icono_excel_vendedorMouseClicked

    private void tablavendedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablavendedorMouseClicked
          //creo variable
        int fila = tablavendedor.rowAtPoint(evt.getPoint());
          //los datos son llevados hacia los campos
        txtidvendedor.setText(tablavendedor.getValueAt(fila, 0).toString());
        txtnombrevendedor.setText(tablavendedor.getValueAt(fila, 1).toString());
        txtcorreovendedor.setText(tablavendedor.getValueAt(fila, 2).toString());
        txtclavevendedor.setText(tablavendedor.getValueAt(fila, 3).toString());
    }//GEN-LAST:event_tablavendedorMouseClicked

    private void tablaproveedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaproveedorMouseClicked
        //crear la variable
        int fila = tablaproveedor.rowAtPoint(evt.getPoint());
        //datos llevados hacia los campos
        txtidproveedor.setText(tablaproveedor.getValueAt(fila, 0).toString());
        txtnombreproveedor.setText(tablaproveedor.getValueAt(fila, 1).toString());
        txtcorreoproveedor.setText(tablaproveedor.getValueAt(fila, 2).toString());
        txttelefonoproveedor.setText(tablaproveedor.getValueAt(fila, 3).toString());
        txtdireccionproveedor.setText(tablaproveedor.getValueAt(fila, 4).toString());
        txttipoproveedor.setText(tablaproveedor.getValueAt(fila, 5).toString());
    }//GEN-LAST:event_tablaproveedorMouseClicked

    private void txtnombreproveedorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombreproveedorKeyTyped
        char c = evt.getKeyChar();
        if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c < 'ñ' || c > 'Ñ') && (c < 'á' || c > 'ú') && (c < ' ' || c > ' ')) {
            evt.consume();
        }
    }//GEN-LAST:event_txtnombreproveedorKeyTyped

    private void txtcorreoproveedorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcorreoproveedorKeyTyped

    }//GEN-LAST:event_txtcorreoproveedorKeyTyped

    private void txtdireccionproveedorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdireccionproveedorKeyTyped
        char c = evt.getKeyChar();
        if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c < 'ñ' || c > 'Ñ') && (c < 'á' || c > 'ú') && (c == '#') || (c == '.') && (c < 0 || c > 9)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtdireccionproveedorKeyTyped

    private void txttipoproveedorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttipoproveedorKeyTyped
        char c = evt.getKeyChar();
        if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c < 'ñ' || c > 'Ñ') && (c < 'á' || c > 'ú') && (c < ' ' || c > ' ')) {
            evt.consume();
        }
    }//GEN-LAST:event_txttipoproveedorKeyTyped

    private void txttelefonoproveedorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttelefonoproveedorKeyTyped
        char c = evt.getKeyChar();
        if ((c < '0' || c > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_txttelefonoproveedorKeyTyped

    private void buttondatovendedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttondatovendedorMouseClicked
       tablas.setSelectedIndex(1);
       LimpiarTabla();
       ListarVendedor();
    }//GEN-LAST:event_buttondatovendedorMouseClicked

    private void buttondatotourMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttondatotourMouseClicked
        tablas.setSelectedIndex(3);
        LimpiarTabla();
        ListarTour();
    }//GEN-LAST:event_buttondatotourMouseClicked

    private void buttondatoventaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttondatoventaMouseClicked
        tablas.setSelectedIndex(5);
        LimpiarTabla();
        ListarVenta();
    }//GEN-LAST:event_buttondatoventaMouseClicked

    private void tablatourMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablatourMouseClicked
                  //creo variable
        int fila = tablatour.rowAtPoint(evt.getPoint());
          //los datos son llevados hacia los campos
        txtidtour.setText(tablatour.getValueAt(fila, 0).toString());
        txtcodigotour.setText(tablatour.getValueAt(fila, 1).toString());
        txtnombretour.setText(tablatour.getValueAt(fila, 2).toString());
        txtpreciogeneral.setText(tablatour.getValueAt(fila, 3).toString());
        txthorasalida.setText(tablatour.getValueAt(fila, 4).toString());
        txthorallegada.setText(tablatour.getValueAt(fila, 5).toString());
        txtitinerario.setText(tablatour.getValueAt(fila, 6).toString());
    }//GEN-LAST:event_tablatourMouseClicked

    private void jPanel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseClicked
        tablas.setSelectedIndex(4);
        LimpiarTabla();
        ListarPasajero();

    }//GEN-LAST:event_jPanel4MouseClicked

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

    private void txtrutpasajeroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrutpasajeroKeyTyped
        char c = evt.getKeyChar();
        if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c < '0' || c > '9') && (c < '-' || c > '.')) {
            evt.consume();
        }
    }//GEN-LAST:event_txtrutpasajeroKeyTyped

    private void txtnombrepasajeroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombrepasajeroKeyTyped
        char c = evt.getKeyChar();
        if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c < 'ñ' || c > 'Ñ')&&(c < 'á' || c > 'ú') &&(c < ' ' || c > ' ')) {
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
        if ((c < '1' || c > '9') && (c < 'a' || c > 'z') && (c < 'A' || c > 'Z')  && (c < 'á' || c > 'ú')  && (c < '.' || c > '@') && (c < ' ' || c > ' ')) {
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
        if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z')  && (c < 'á' || c > 'ú') && (c < ' ' || c > ' ')) {
            evt.consume();
        }
    }//GEN-LAST:event_txttipopasajeroKeyTyped

    private void txtnacionalidadpasajeroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnacionalidadpasajeroKeyTyped
        char c = evt.getKeyChar();
        if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z')  && (c < 'á' || c > 'ú') && (c < ' ' || c > ' ')) {
            evt.consume();
        }
    }//GEN-LAST:event_txtnacionalidadpasajeroKeyTyped

    private void tablaventaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaventaMouseClicked
        int fila = tablaventa.rowAtPoint(evt.getPoint());
        //los datos son llevados hacia los campos
        txtidventa.setText(tablaventa.getValueAt(fila, 0).toString());
        txtpasajeroventa.setText(tablaventa.getValueAt(fila, 1).toString());  
        txtvendedorventa.setText(tablaventa.getValueAt(fila, 2).toString()); 
        txttotalventa.setText(tablaventa.getValueAt(fila, 3).toString()); 
        txtfechaventa.setText(tablaventa.getValueAt(fila, 4).toString()); 
    }//GEN-LAST:event_tablaventaMouseClicked

    private void txtidventaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtidventaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidventaKeyTyped

    private void buttonagregarproveedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonagregarproveedorMouseClicked
             //botón de guardar
        //validación de los campos
        if (!"".equals(txtnombreproveedor.getText()) || !"".equals(txtcorreoproveedor.getText())
            || !"".equals(txttelefonoproveedor.getText()) || !"".equals(txtdireccionproveedor.getText())
            || !"".equals(txttipoproveedor.getText())) {
            //setea los campos con la llamada de la clase
            clase_proveedor.setNombre(txtnombreproveedor.getText());
            clase_proveedor.setCorreo(txtcorreoproveedor.getText());
            clase_proveedor.setTelefono(txttelefonoproveedor.getText());
            clase_proveedor.setDireccion(txtdireccionproveedor.getText());
            clase_proveedor.setTipo(txttipoproveedor.getText());
            proveedordao.RegistrarProveedor(clase_proveedor);
            //mensaje de cliente registrado
            JOptionPane.showMessageDialog(null, "Proveedor registrado");
            //metodo para limpiar los campos
            LimpiarTabla();
            ListarProveedor();
            LimpiarCamposProveedor();

        } else {
            JOptionPane.showMessageDialog(null, "Los campos estan vacios");
        }
    }//GEN-LAST:event_buttonagregarproveedorMouseClicked

    private void buttoneditarproveedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttoneditarproveedorMouseClicked
             if(" ".equals(txtidproveedor.getText())){
            JOptionPane.showMessageDialog(null, "seleccione una fila");
        }else{
            if(!"".equals(txtnombreproveedor.getText()) || !"".equals(txtcorreoproveedor.getText()) 
                    || !"".equals(txttelefonoproveedor.getText()) || !"".equals(txtdireccionproveedor.getText())
                    || !"".equals(txttipoproveedor.getText())
                    ){
                clase_proveedor.setNombre(txtnombreproveedor.getText());
                clase_proveedor.setCorreo(txtcorreoproveedor.getText());
                clase_proveedor.setTelefono(txttelefonoproveedor.getText());
                clase_proveedor.setDireccion(txtdireccionproveedor.getText());
                clase_proveedor.setTipo(txttipoproveedor.getText());
                clase_proveedor.setId(Integer.parseInt(txtidproveedor.getText()));
                proveedordao.ModificarProveedor(clase_proveedor);
                LimpiarTabla();
                LimpiarCamposProveedor();
                ListarProveedor();
                JOptionPane.showMessageDialog(null, "Datos Actualizados");
            }else{
                JOptionPane.showMessageDialog(null, "Los campos estan vacios");
            }
        }
    }//GEN-LAST:event_buttoneditarproveedorMouseClicked

    private void buttoneliminarproveedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttoneliminarproveedorMouseClicked
        if(!"".equals(txtidproveedor.getText())){
            int pregunta = JOptionPane.showConfirmDialog(null, "estas seguro de eliminar");
            if(pregunta == 0){
                int id = Integer.parseInt(txtidproveedor.getText());
                proveedordao.EliminarProveedor(id);
                LimpiarTabla();
                ListarProveedor();
                LimpiarCamposProveedor();
            }
        }else{
                JOptionPane.showMessageDialog(null, "seleccione fila");
            }
    }//GEN-LAST:event_buttoneliminarproveedorMouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
           if (!"".equals(txtrutempresa.getText()) || !"".equals(txtnombreempresa.getText()) || !"".equals(txttelefonoempresa.getText())
            || !"".equals(txtdireccionempresa.getText())) {
            clase_agencia.setRut(txtrutempresa.getText());
            clase_agencia.setNombre(txtnombreempresa.getText());
            clase_agencia.setTelefono(Integer.parseInt(txttelefonoempresa.getText()));
            clase_agencia.setDireccion(txtdireccionempresa.getText());
            clase_agencia.setId(Integer.parseInt(txtidempresa.getText()));
            agencia_dao.ModificarAgencia(clase_agencia);
            JOptionPane.showMessageDialog(null, "Datos de la empresa modificados");
          

        } else {
            JOptionPane.showMessageDialog(null, "Los campos estan vacios");
        }
    }//GEN-LAST:event_jButton1MouseClicked

    private void buttonagrregarvendedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonagrregarvendedorMouseClicked
       //botón de guardar
        //validación de los campos
        if (!"".equals(txtnombrevendedor.getText()) || !"".equals(txtcorreovendedor.getText())
            || !"".equals(txtclavevendedor.getText())
        ) {
            //setea los campos con la llamada de la clase
            clase_vendedor.setNombre(txtnombrevendedor.getText());
            clase_vendedor.setCorreo(txtcorreovendedor.getText());
            clase_vendedor.setClave(txtclavevendedor.getText());
            vendedor_dao.RegistrarVendedor(clase_vendedor);
            //mensaje de cliente registrado
            JOptionPane.showMessageDialog(null, "Vendedor registrado");
            //llamados de los metodos
            LimpiarTabla();
            ListarVendedor();
            LimpiarCamposVendedor();
            
        } else {
            JOptionPane.showMessageDialog(null, "Los campos estan vacios");
        }
    }//GEN-LAST:event_buttonagrregarvendedorMouseClicked

    private void buttoneditarvendedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttoneditarvendedorMouseClicked
        if("".equals(txtidvendedor.getText())){
            JOptionPane.showMessageDialog(null, "seleccione una fila");
        }else{
            if(!"".equals(txtnombrevendedor.getText()) || !"".equals(txtcorreovendedor.getText()) || !"".equals(txtclavevendedor.getText())){
                clase_vendedor.setNombre(txtnombrevendedor.getText());
                clase_vendedor.setCorreo(txtcorreovendedor.getText());
                clase_vendedor.setClave(txtclavevendedor.getText());
                clase_vendedor.setId(Integer.parseInt(txtidvendedor.getText()));
                vendedor_dao.ModificarVendedor(clase_vendedor);
                LimpiarTabla();
                LimpiarCamposVendedor();
                ListarVendedor();
                JOptionPane.showMessageDialog(null, "Datos Actualizados");
            }else{
                JOptionPane.showMessageDialog(null, "Los campos estan vacios");
            }
        }
    }//GEN-LAST:event_buttoneditarvendedorMouseClicked

    private void buttoneliminarvendedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttoneliminarvendedorMouseClicked
         if(!"".equals(txtidvendedor.getText())){
            int pregunta = JOptionPane.showConfirmDialog(null, "estas seguro de eliminar");
            if(pregunta == 0){
                int id = Integer.parseInt(txtidvendedor.getText());
                vendedor_dao.EliminarVendedor(id);
                LimpiarTabla();
                ListarVendedor();
                LimpiarCamposVendedor();
            }
        }else{
                JOptionPane.showMessageDialog(null, "seleccione fila");
            }
    }//GEN-LAST:event_buttoneliminarvendedorMouseClicked

    private void buttonnuevovendedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonnuevovendedorMouseClicked
        LimpiarCamposVendedor();
    }//GEN-LAST:event_buttonnuevovendedorMouseClicked

    private void buttonnuevoproveedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonnuevoproveedorMouseClicked
          LimpiarCamposProveedor();
    }//GEN-LAST:event_buttonnuevoproveedorMouseClicked

    private void buttonagregarpasajeroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonagregarpasajeroMouseClicked
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
    }//GEN-LAST:event_buttonagregarpasajeroMouseClicked

    private void buttoneditarpasajeroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttoneditarpasajeroMouseClicked
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
    }//GEN-LAST:event_buttoneditarpasajeroMouseClicked

    private void buttoneliminarpasajeroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttoneliminarpasajeroMouseClicked
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
    }//GEN-LAST:event_buttoneliminarpasajeroMouseClicked

    private void buttonnuevopasajeroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonnuevopasajeroMouseClicked
        LimpiarCamposPasajero();
    }//GEN-LAST:event_buttonnuevopasajeroMouseClicked

    private void txtcodigotourKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcodigotourKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcodigotourKeyTyped

    private void txtpreciogeneralKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpreciogeneralKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpreciogeneralKeyTyped

    private void txtnombretourKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombretourKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnombretourKeyTyped

    private void txthorasalidaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txthorasalidaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txthorasalidaKeyTyped

    private void txthorallegadaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txthorallegadaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txthorallegadaKeyTyped

    private void buttoneditartourMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttoneditartourMouseClicked
                     if ("".equals(txtidtour.getText())) {
            JOptionPane.showMessageDialog(null, "seleccione una fila");
        } else {
            if (!"".equals(txtcodigotour.getText()) || !"".equals(txtnombretour.getText())
                    || !"".equals(txtpreciogeneral.getText()) || !"".equals(txthorasalida.getText())
                    || !"".equals(txthorallegada.getText())
                    || !"".equals(txtitinerario.getText())
                    ) {
                clase_tour.setId(Integer.parseInt(txtidtour.getText()));
                clase_tour.setCodigo(Integer.parseInt(txtcodigotour.getText()));
                clase_tour.setNombre(txtnombretour.getText());
                clase_tour.setPreciogeneral(Integer.parseInt(txtpreciogeneral.getText()));
                clase_tour.setHorasalida(txthorasalida.getText());
                clase_tour.setHorallegada(txthorallegada.getText());
                clase_tour.setDescripcionitinerario(txtitinerario.getText());
                tourdao.ModificarTour(clase_tour);
                LimpiarTabla();
                LimpiarCamposTour();
                ListarTour();
                JOptionPane.showMessageDialog(null, "Datos actualizados");
            }else{
                  JOptionPane.showMessageDialog(null, "Los campos estan vacios");
            }
        }
    }//GEN-LAST:event_buttoneditartourMouseClicked

    private void buttonagregartourMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonagregartourMouseClicked
        if (!"".equals(txtcodigotour.getText()) || !"".equals(txtnombretour.getText())
                || !"".equals(txtpreciogeneral.getText()) || !"".equals(txthorasalida.getText())
                || !"".equals(txthorallegada.getText()) || !"".equals(txtitinerario.getText())
                    ) {
            //setea los campos con la llamada de la clase
            clase_tour.setCodigo(Integer.parseInt(txtcodigotour.getText()));
            clase_tour.setNombre(txtnombretour.getText());
            clase_tour.setPreciogeneral(Integer.parseInt(txtpreciogeneral.getText()));
            clase_tour.setHorasalida(txthorasalida.getText());
            clase_tour.setHorallegada(txthorallegada.getText());
            clase_tour.setDescripcionitinerario(txtitinerario.getText());
            tourdao.RegistrarTour(clase_tour);
            //mensaje de cliente registrado
            JOptionPane.showMessageDialog(null, "tour registrado");
            LimpiarTabla();
            ListarTour();
            LimpiarCamposTour();
      
        } else {
            JOptionPane.showMessageDialog(null, "Los campos estan vacios");
        }
    }//GEN-LAST:event_buttonagregartourMouseClicked

    private void buttonnurvotourMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonnurvotourMouseClicked
        LimpiarCamposTour();
    }//GEN-LAST:event_buttonnurvotourMouseClicked

    private void buttoneliminartourMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttoneliminartourMouseClicked
                 if (!"".equals(txtidtour.getText())) {
            int pregunta = JOptionPane.showConfirmDialog(null, "estas seguro de eliminar");
            if (pregunta == 0) {
                int id = Integer.parseInt(txtidtour.getText());
                tourdao.EliminarTour(id);
                LimpiarTabla();
                ListarTour();
                //metodo limpia los campos    
                LimpiarCamposTour();
            }
        }else{
                JOptionPane.showMessageDialog(null, "seleccione fila");
            }
    }//GEN-LAST:event_buttoneliminartourMouseClicked

    private void txtvendedorventaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtvendedorventaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtvendedorventaKeyTyped

    private void txtpasajeroventaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpasajeroventaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpasajeroventaKeyTyped

    private void txtfechaventaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfechaventaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtfechaventaKeyTyped

    private void txttotalventaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttotalventaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txttotalventaKeyTyped

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(txtidventa.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Selecciona una fila");
        }else{
            clase_venta = ventadao.BuscarVenta(Integer.parseInt(txtidventa.getText()));
            ventadao.pdfV(clase_venta.getId(), clase_venta.getCliente(), clase_venta.getVendedor(), clase_venta.getTotal());
        } 
    }//GEN-LAST:event_jButton2ActionPerformed

    private void buttoneliminarpasajero1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttoneliminarpasajero1MouseClicked
            if (!"".equals(txtidventa.getText())) {
            int pregunta = JOptionPane.showConfirmDialog(null, "estas seguro de eliminar");
            if (pregunta == 0) {
                int id = Integer.parseInt(txtidventa.getText());
                ventadao.EliminarVenta(id);
                LimpiarTabla();
                ListarVenta();
                //metodo limpia los campos
                LimpiarCamposVenta();
            }
        }else{
                JOptionPane.showMessageDialog(null, "seleccione fila");
            }
    }//GEN-LAST:event_buttoneliminarpasajero1MouseClicked

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
            java.util.logging.Logger.getLogger(Admindueno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Admindueno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Admindueno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Admindueno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Admindueno().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonagregarpasajero;
    private javax.swing.JButton buttonagregarproveedor;
    private javax.swing.JButton buttonagregartour;
    private javax.swing.JButton buttonagrregarvendedor;
    private javax.swing.JPanel buttondatoproveedor;
    private javax.swing.JPanel buttondatosagencia1;
    private javax.swing.JPanel buttondatotour;
    private javax.swing.JPanel buttondatovendedor;
    private javax.swing.JPanel buttondatoventa;
    private javax.swing.JButton buttoneditarpasajero;
    private javax.swing.JButton buttoneditarproveedor;
    private javax.swing.JButton buttoneditartour;
    private javax.swing.JButton buttoneditarvendedor;
    private javax.swing.JButton buttoneliminarpasajero;
    private javax.swing.JButton buttoneliminarpasajero1;
    private javax.swing.JButton buttoneliminarproveedor;
    private javax.swing.JButton buttoneliminartour;
    private javax.swing.JButton buttoneliminarvendedor;
    private javax.swing.JButton buttonnuevopasajero;
    private javax.swing.JButton buttonnuevoproveedor;
    private javax.swing.JButton buttonnuevovendedor;
    private javax.swing.JButton buttonnurvotour;
    private javax.swing.JLabel icono_excel_vendedor;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel nombre_vendedor;
    private javax.swing.JTable tablapasajero;
    private javax.swing.JTable tablaproveedor;
    private javax.swing.JTabbedPane tablas;
    private javax.swing.JTable tablatour;
    private javax.swing.JTable tablavendedor;
    private javax.swing.JTable tablaventa;
    private javax.swing.JTextField txtapellidospasajero;
    private javax.swing.JPasswordField txtclavevendedor;
    private javax.swing.JTextField txtcodigotour;
    private javax.swing.JTextField txtcorreopasajero;
    private javax.swing.JTextField txtcorreoproveedor;
    private javax.swing.JTextField txtcorreovendedor;
    private javax.swing.JTextField txtdireccionempresa;
    private javax.swing.JTextField txtdireccionpasajero;
    private javax.swing.JTextField txtdireccionproveedor;
    private javax.swing.JTextField txtfechaventa;
    private javax.swing.JTextField txthorallegada;
    private javax.swing.JTextField txthorasalida;
    private javax.swing.JTextField txtidempresa;
    private javax.swing.JTextField txtidpasajero;
    private javax.swing.JTextField txtidproveedor;
    private javax.swing.JTextField txtidtour;
    private javax.swing.JTextField txtidvendedor;
    private javax.swing.JTextField txtidventa;
    private javax.swing.JTextArea txtitinerario;
    private javax.swing.JTextField txtnacionalidadpasajero;
    private javax.swing.JTextField txtnombreempresa;
    private javax.swing.JTextField txtnombrepasajero;
    private javax.swing.JTextField txtnombreproveedor;
    private javax.swing.JTextField txtnombretour;
    private javax.swing.JTextField txtnombrevendedor;
    private javax.swing.JTextField txtpasajeroventa;
    private javax.swing.JTextField txtpreciogeneral;
    private javax.swing.JTextField txtrutempresa;
    private javax.swing.JTextField txtrutpasajero;
    private javax.swing.JTextField txttelefonoempresa;
    private javax.swing.JTextField txttelefonopasajero;
    private javax.swing.JTextField txttelefonoproveedor;
    private javax.swing.JTextField txttipopasajero;
    private javax.swing.JTextField txttipoproveedor;
    private javax.swing.JTextField txttotalventa;
    private javax.swing.JTextField txtvendedorventa;
    // End of variables declaration//GEN-END:variables
}
