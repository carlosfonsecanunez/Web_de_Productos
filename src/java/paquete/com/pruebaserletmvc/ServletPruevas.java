/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete.com.pruebaserletmvc;

//import com.mysql.cj.xdevapi.Statement;
import java.sql.*;
import jakarta.annotation.Resource;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 *
 * @author Carlos Fonseca
 */
public class ServletPruevas extends HttpServlet {

    
    
    @Resource(name="jdbc/Productos")
    private DataSource miPool;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException,SQLException {
        response.setContentType("text/html;charset=UTF-8");
        
        Connection miConnection = null;
        Statement miStatement = null;
        ResultSet rs = null; 
        try ( PrintWriter out = response.getWriter()) {
          
            //Creando el Pool de conexiones
            miConnection = miPool.getConnection();
            String Sql = "SELECT * FROM PRODUCTO";
            miStatement = miConnection.createStatement();
            rs = miStatement.executeQuery(Sql);
            
            while(rs.next()){
                String articulo = rs.getString(2);
                String codigo = rs.getString(1);
                String categoria = rs.getString(3);
                String descripcion = rs.getString(4);
                double precio = rs.getDouble(5);
                out.println(articulo+" "+codigo+" "+descripcion+precio+" "+categoria+"<br>");
            }
        }
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ServletPruevas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ServletPruevas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
