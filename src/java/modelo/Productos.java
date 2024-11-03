 package modelo;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author keila
 */
public class Productos {

    public Productos() {
    }

    public Productos(int id_Producto, String producto, int id_Marca, String Descripcion, String Imagen, double precio_costo, double precio_venta, int existencia, Date fecha_ingreso) {
        this.id_Producto = id_Producto;
        this.producto = producto;
        this.id_Marca = id_Marca;
        this.Descripcion = Descripcion;
        this.Imagen = Imagen;
        this.precio_costo = precio_costo;
        this.precio_venta = precio_venta;
        this.existencia = existencia;
        this.fecha_ingreso = fecha_ingreso;
    }
    private int id_Producto;
    private String producto;
    private int id_Marca;
    private String Descripcion;
    private String Imagen;
    private double precio_costo;
    private double precio_venta;
    private int existencia;
    private Date fecha_ingreso;
    Conexion cn;

    public int getId_Producto() {
        return id_Producto;
    }

    public void setId_Producto(int id_Producto) {
        this.id_Producto = id_Producto;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public int getId_Marca() {
        return id_Marca;
    }

    public void setId_Marca(int id_Marca) {
        this.id_Marca = id_Marca;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public String getImagen() {
        return Imagen;
    }

    public void setImagen(String Imagen) {
        this.Imagen = Imagen;
    }

    public double getPrecio_costo() {
        return precio_costo;
    }

    public void setPrecio_costo(double precio_costo) {
        this.precio_costo = precio_costo;
    }

    public double getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(double precio_venta) {
        this.precio_venta = precio_venta;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

    public Date getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(Date fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }
   public DefaultTableModel leer() {
        DefaultTableModel tabla = new DefaultTableModel();
        try {
            cn = new Conexion();
            cn.abrir_conexion();
            String query = "SELECT * FROM productos;";
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            String encabezado[] = {"descripcion,existencia,fecha_ingreso,id_marca,id_producto,imagen,precio_costo,precio_venta,producto",};
            tabla.setColumnIdentifiers(encabezado);
            String datos[] = new String[9];
            while (consulta.next()) {
                datos[0] = consulta.getString("descripcion");
                datos[1] = consulta.getString("existencia");
                datos[2] = consulta.getString("fecha_ingreso");
                datos[3] = consulta.getString("id_marca");
                datos[4] = consulta.getString("id_producto");
                datos[5] = consulta.getString("imagen");
                datos[6] = consulta.getString("precio_costo");
                datos[7] = consulta.getString("precio_venta");
                datos[7] = consulta.getString("producto");
                 
                tabla.addRow(datos);
            }

            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return tabla;
    }

    public int agregar() {
        int retorno = 0;
        try {
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "insert into productos (descripcion,existencia,fecha_ingreso,id_marca,id_producto,imagen,precio_costo,precio_venta,producto) values(?,?,?,?,?,?,?,?,?);";
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setString(1, getDescripcion());
            parametro.setInt(2, getExistencia());
            parametro.setDate(3, getFecha_ingreso());
            parametro.setInt(4, getId_Marca());
            parametro.setInt(5, getId_Producto());
            parametro.setString(6, getImagen());
            parametro.setDouble(7, getPrecio_costo());
            parametro.setDouble(8, getPrecio_venta());
            parametro.setString(9, getProducto());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return retorno;
    }

    public int modificar() {
        int retorno = 0;
        try {
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "update productos set descripcion = ?,existencia = ?,fecha_ingreso = ?,id_marca = ?,id_producto= ?,imagen = ?,precio_costo = ?,precio_venta = ?,producto = ?  where id_producto = ?;";
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setString(1, getDescripcion());
            parametro.setInt(2, getExistencia());
            parametro.setDate(3, getFecha_ingreso());
            parametro.setInt(4, getId_Marca());
            parametro.setInt(5, getId_Producto());
            parametro.setString(6, getImagen());
            parametro.setDouble(7, getPrecio_costo());
            parametro.setDouble(8, getPrecio_venta());
            parametro.setString(9, getProducto());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return retorno;
    }

    public int eliminar() {
        int retorno = 0;
        try {
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "delete from productos  where id_producto = ?;";
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setInt(1, getId_Producto());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return retorno;
    }
}



