package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author keila
 */
public class Ventas_detalle {

    public Ventas_detalle() {
    }

    public Ventas_detalle(int id_venta_detalle, int id_Venta, int id_Producto, int cantidad, double precio_unitario) {
        this.id_venta_detalle = id_venta_detalle;
        this.id_Venta = id_Venta;
        this.id_Producto = id_Producto;
        this.cantidad = cantidad;
        this.precio_unitario = precio_unitario;
    }
  private int id_venta_detalle;
  private int id_Venta;
  private int id_Producto;
  private int cantidad;
  private double precio_unitario;
  Conexion cn;

    public int getId_venta_detalle() {
        return id_venta_detalle;
    }

    public void setId_venta_detalle(int id_venta_detalle) {
        this.id_venta_detalle = id_venta_detalle;
    }

    public int getId_Venta() {
        return id_Venta;
    }

    public void setId_Venta(int id_Venta) {
        this.id_Venta = id_Venta;
    }

    public int getId_Producto() {
        return id_Producto;
    }

    public void setId_Producto(int id_Producto) {
        this.id_Producto = id_Producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(double precio_unitario) {
        this.precio_unitario = precio_unitario;
    }
    public DefaultTableModel leer() {
        DefaultTableModel tabla = new DefaultTableModel();
        try {
            cn = new Conexion();
            cn.abrir_conexion();
            String query = "SELECT * FROM ventas_detalle;";
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            String encabezado[] = {"cantidad","id_producto","id_venta","id_venta_detalle","precio_unitario"};
            tabla.setColumnIdentifiers(encabezado);
            String datos[] = new String[5];
            while (consulta.next()) {
                datos[0] = consulta.getString("cantidad");
                datos[1] = consulta.getString("id_producto");
                datos[2] = consulta.getString("id_venta");
                datos[3] = consulta.getString("id_venta_detalle");
                datos[4] = consulta.getString("precio_unitario");
           
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
            String query = "insert into ventas_detalle (cantidad,id_producto,id_venta,id_venta_detalle,precio_unitario)values(?,?,?,?,?);";
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setInt(1, getCantidad()); 
            parametro.setInt(2, getId_Producto());
            parametro.setInt(3, getId_Venta());
            parametro.setInt(4, getId_venta_detalle());
            parametro.setDouble(5, getPrecio_unitario());
            
           
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
            String query = "update ventas_detalle set cantidad = ?,id_producto = ?,id_venta = ?,id_venta_detalle = ?,precio_unitario = ?   where id_venta_detalle = ?;";
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setInt(1, getCantidad()); 
            parametro.setInt(2, getId_Producto());
            parametro.setInt(3, getId_Venta());
            parametro.setInt(4, getId_venta_detalle());
            parametro.setDouble(5, getPrecio_unitario());
            
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
            String query = "delete from ventas_detalle where id_venta_detalle = ?;";
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setInt(1, getId_venta_detalle());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return retorno;
    }
}


  
    

