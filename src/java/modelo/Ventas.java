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
public class Ventas {

    public Ventas(int id_Venta, int no_factura, char serie, Date fecha_factura, int id_Cliente, int id_empleado, Date fecha_ingreso) {
        this.id_Venta = id_Venta;
        this.no_factura = no_factura;
        this.serie = serie;
        this.fecha_factura = fecha_factura;
        this.id_Cliente = id_Cliente;
        this.id_empleado = id_empleado;
        this.fecha_ingreso = fecha_ingreso;
       
    }

    public Ventas() {
    }
    
    private int id_Venta;
    private int no_factura;
    private char serie;
    private Date fecha_factura;
    private int id_Cliente;
    private int id_empleado;
    private Date fecha_ingreso;
    Conexion cn;

    public int getId_Venta() {
        return id_Venta;
    }

    public void setId_Venta(int id_Venta) {
        this.id_Venta = id_Venta;
    }

    public int getNo_factura() {
        return no_factura;
    }

    public void setNo_factura(int no_factura) {
        this.no_factura = no_factura;
    }

    public char getSerie() {
        return serie;
    }

    public void setSerie(char serie) {
        this.serie = serie;
    }

    public Date getFecha_factura() {
        return fecha_factura;
    }

    public void setFecha_factura(Date fecha_factura) {
        this.fecha_factura = fecha_factura;
    }

    public int getId_Cliente() {
        return id_Cliente;
    }

    public void setId_Cliente(int id_Cliente) {
        this.id_Cliente = id_Cliente;
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
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
            String query = "SELECT * FROM ventas;";
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            String encabezado[] = {"fecha_factura", "fecha_ingreso","id_cliente","id_empleado","id_venta","no_factura","serie"};
            tabla.setColumnIdentifiers(encabezado);
            String datos[] = new String[8];
            while (consulta.next()) {
                datos[0] = consulta.getString("apellidos");
                datos[1] = consulta.getString("correo_electronico");
                datos[2] = consulta.getString("fecha_ingreso");
                datos[3] = consulta.getString("genero");
                datos[4] = consulta.getString("id_cliente");
                datos[5] = consulta.getString("nit");
                datos[6] = consulta.getString("nombres");
                datos[7] = consulta.getString("telefono");
                
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
            String query = "insert into clientes (apellidos, correo_electronico,fecha_ingreso,genero,id_cliente,nit,nombres,telefono) values(?,?,?,?,?,?,?,?);";
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setString(1, getApellidos()); 
            parametro.setString(2, getCorreo_electronico());
            parametro.setDate(3, getFecha_ingreso());
            parametro.setBoolean(4, getGenero());
            parametro.setInt(5, getId_Cliente());
            parametro.setString(6, getNIT());
            parametro.setString(7, getNombres());
            parametro.setString(8, getTelefono());
           
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
            String query = "update clientes set apellidos= ?, correo_electronico= ?,fecha_ingreso= ?,genero= ?,id_cliente= ?,nit= ?,nombres,telefono= ?  where id_cliente = ?;";
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setString(1, getApellidos()); 
            parametro.setString(2, getCorreo_electronico());
            parametro.setDate(3, getFecha_ingreso());
            parametro.setBoolean(4, getGenero());
            parametro.setInt(5, getId_Cliente());
            parametro.setString(6, getNIT());
            parametro.setString(7, getNombres());
            parametro.setString(8, getTelefono());
            
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
            String query = "delete from clientes  where id_clientes = ?;";
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setInt(1, getId_Cliente());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return retorno;
    }
}

