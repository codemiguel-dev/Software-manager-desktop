
package modelo;

/**
 *
 * @author _pant
 */
public class Venta {
    private int id;
    private int cliente;
    private String pasajero;
    private String vendedor;
    private double total;
    private String fecha;

    public Venta() {
    }

    public Venta(int id, int cliente, String pasajero, String vendedor, double total, String fecha) {
        this.id = id;
        this.cliente = cliente;
        this.pasajero = pasajero;
        this.vendedor = vendedor;
        this.total = total;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCliente() {
        return cliente;
    }

    public void setCliente(int cliente) {
        this.cliente = cliente;
    }

    public String getPasajero() {
        return pasajero;
    }

    public void setPasajero(String pasajero) {
        this.pasajero = pasajero;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

     
}
