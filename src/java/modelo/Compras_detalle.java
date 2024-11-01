package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author keila
 */
public class Compras_detalle {

    public Compras_detalle(int id_compra_detalle, int id_compra, int id_producto, int cantidad, double precio_costo_unitario) {
        this.id_compra_detalle = id_compra_detalle;
        this.id_compra = id_compra;
        this.id_producto = id_producto;
        this.cantidad = cantidad;
        this.precio_costo_unitario = precio_costo_unitario;
       
    }

    public Compras_detalle() {
    }
    
    private int id_compra_detalle;
    private int id_compra;
    private int id_producto;
    private int cantidad;
    private double precio_costo_unitario;
    Conexion cn;

    public int getId_compra_detalle() {
        return id_compra_detalle;
    }

    public void setId_compra_detalle(int id_compra_detalle) {
        this.id_compra_detalle = id_compra_detalle;
    }

    public int getId_compra() {
        return id_compra;
    }

    public void setId_compra(int id_compra) {
        this.id_compra = id_compra;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio_costo_unitario() {
        return precio_costo_unitario;
    }

    public void setPrecio_costo_unitario(double precio_costo_unitario) {
        this.precio_costo_unitario = precio_costo_unitario;
    }
    public DefaultTableModel leer() {
        DefaultTableModel tabla = new DefaultTableModel();
        try {
            cn = new Conexion();
            cn.abrir_conexion();
            String query = "SELECT * FROM compras_detalle;";
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            String encabezado[] = {"id_compra_detalle","id_compra","id_producto","cantidad","precio_costo_unitario"};
            tabla.setColumnIdentifiers(encabezado);
            String datos[] = new String[2];
            while (consulta.next()) {
                datos[0] = consulta.getString("id_compra_detalle");
                datos[1] = consulta.getString("id_compra");
                datos[2] = consulta.getString("id_producto");
                datos[3] = consulta.getString("cantidad");
                datos[4] = consulta.getString("precio_costo_unitario");
                
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
            String query = "insert into compras_detalle(Id_compra, Id_producto,Cantidad,Precio_costo_unitario) values(?,?,?,?,?);";
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setInt(1, getId_compra_detalle());
            parametro.setInt(2, getId_compra());
            parametro.setInt(3, getId_producto());
            parametro.setInt(4, getCantidad());
            parametro.setDouble(5, getPrecio_costo_unitario());
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
            String query = "update compras_detallle set cantidad= ?, id_compra= ? , id_compra_detalle= ?,id_producto= ?,precio_costo_unitario = ?  where id_compra_detalle = ?;";
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setInt(1, getCantidad());
            parametro.setInt(2,  getId_compra());
            parametro.setInt(3,  getId_compra_detalle());
            parametro.setInt(4,  getId_producto());
            parametro.setDouble(5,  getPrecio_costo_unitario());
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
            String query = "delete compras_detalle from   where id_compora_detalle = ?;";
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setInt(1, getId_compra_detalle());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return retorno;
    }
}



