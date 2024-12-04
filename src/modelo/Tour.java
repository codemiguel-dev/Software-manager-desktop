
package modelo;

/**
 *
 * @author _pant
 */
public class Tour {
    private int id;
    private int id_tour;
    private int codigo;
    private String nombre;
    private int preciogeneral;
    private String horasalida;
    private String horallegada;
    private String descripcionitinerario;

    public Tour() {
    }

    public Tour(int id, int id_tour, int codigo, String nombre, int preciogeneral, String horasalida, String horallegada, String descripcionitinerario) {
        this.id = id;
        this.id_tour = id_tour;
        this.codigo = codigo;
        this.nombre = nombre;
        this.preciogeneral = preciogeneral;
        this.horasalida = horasalida;
        this.horallegada = horallegada;
        this.descripcionitinerario = descripcionitinerario;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_tour() {
        return id_tour;
    }

    public void setId_tour(int id_tour) {
        this.id_tour = id_tour;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPreciogeneral() {
        return preciogeneral;
    }

    public void setPreciogeneral(int preciogeneral) {
        this.preciogeneral = preciogeneral;
    }

    public String getHorasalida() {
        return horasalida;
    }

    public void setHorasalida(String horasalida) {
        this.horasalida = horasalida;
    }

    public String getHorallegada() {
        return horallegada;
    }

    public void setHorallegada(String horallegada) {
        this.horallegada = horallegada;
    }

    public String getDescripcionitinerario() {
        return descripcionitinerario;
    }

    public void setDescripcionitinerario(String descripcionitinerario) {
        this.descripcionitinerario = descripcionitinerario;
    }


 
 
   
}
