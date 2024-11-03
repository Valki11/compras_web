/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Conexion;

@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Conectar a la base de datos MySQL y verificar las credenciales
        boolean isValidUser = validateUser(username, password);

        if(isValidUser) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            response.sendRedirect("welcome.jsp");
        } else {
            response.sendRedirect("login.jsp?error=true");
        }
    }

    private boolean validateUser(String username, String password) {
        boolean isValidUser = false;

        try {
            Conexion cn = new Conexion();
            cn.abrir_conexion();
            String sql = "SELECT * FROM usuarios WHERE nombre_usuario = ? AND contrasena = ?";
            PreparedStatement parametro = (PreparedStatement) cn.conexionBD.prepareStatement(sql);
            parametro.setString(1, username);
            parametro.setString(2, password);
            ResultSet resultSet = parametro.executeQuery();

            if(resultSet.next()) {
                isValidUser = true;
            }

            resultSet.close();
            parametro.close();
            cn.cerrar_conexion();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isValidUser;
    }
}
