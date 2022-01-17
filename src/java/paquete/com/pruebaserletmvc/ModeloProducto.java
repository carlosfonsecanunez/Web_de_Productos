/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paquete.com.pruebaserletmvc;

//import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kai
 */
public class ModeloProducto {
    
    private DataSource OrigenDatos;
    
    public ModeloProducto(DataSource origen){
        
        this.OrigenDatos=origen;
    
    }
    
    public List<Productos> getProductos()throws Exception{
        List<Productos> productos = new ArrayList<>();
        //Class.forName("com.mysql.jdbc.Driver");                  
        Connection miConnection = null;
        Statement miStatement = null;
        ResultSet miResultset = null;
        try{
        miConnection = OrigenDatos.getConnection();

        String sentenciaSql="SELECT * FROM PRODUCTO";
        miStatement = miConnection.createStatement();

        miResultset = miStatement.executeQuery(sentenciaSql);
            
            while(miResultset.next()){
                String id = miResultset.getString(1);
                String nombre = miResultset.getString(2);
                String categoria = miResultset.getString(3);
                String descripcion = miResultset.getString(4);
                double precio = miResultset.getDouble(5);
                Date fecha = miResultset.getDate(6);
                
                Productos temProducto = new Productos(id,nombre,categoria,descripcion,precio,fecha);
                productos.add(temProducto);
            }
        }finally{
          miStatement.close();
          miConnection.close();  
        }   
            
       
        return productos;
    }

    public void agregarElNuevoProducto(Productos nuevoProducto)throws Exception {
        
        Connection miConnection = null;
        PreparedStatement miStatement = null;
        
        try {
            //Obtener la Conexcion
            miConnection = OrigenDatos.getConnection();
            //Crear instruccion SQL que inserte el producto
            String instruccionSql = "INSERT INTO PRODUCTO (ID, ARTICULO, CATEGORIA , DESCRIPCION, PRECIO, fecha)"+
                    "VALUES(?,?,?,?,?,?)";
            miStatement = miConnection.prepareCall(instruccionSql);
            //establecer parametros para el producto
            
            miStatement.setString(1, nuevoProducto.getCoArt());
            miStatement.setString(2, nuevoProducto.getNoArt());
            miStatement.setString(3, nuevoProducto.getCatArt());
            miStatement.setString(4, nuevoProducto.getDesArt());
            miStatement.setDouble(5, nuevoProducto.getPreArt());
            
            java.util.Date utilDate = nuevoProducto.getFecArt();
            long fechaEnlong = utilDate.getTime();
            java.sql.Date fechaConvertida;
            fechaConvertida = new java.sql.Date(fechaEnlong);
            
            //fechaConvertida = (java.sql.Date) utilDate;
            
            miStatement.setDate(6, fechaConvertida);
            //ejecutar la instruccion SQL
            miStatement.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(ModeloProducto.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
          miStatement.close();
          miConnection.close();  
        }
    }

    Productos getProducto(String codigoarticulo)throws Exception {
        Productos elProducto = null;
        Connection miConnection = null;
        PreparedStatement miStatement = null;
        ResultSet miResultset = null;
        String cArticulo = codigoarticulo;
        
        try{
        //Establecer la conexion con la BBDD
       
        miConnection = OrigenDatos.getConnection();
        
        //Crear sql que busque el producto
        
        String sql = "SELECT * FROM PRODUCTO WHERE ID = ?";  
        
        //Crear la consulta preparada
        
        miStatement = miConnection.prepareStatement(sql);
        
        //establecer los paprametros
        
        miStatement.setString(1, cArticulo);
        
        //ejecutar la consulta
        
        miResultset = miStatement.executeQuery();
       
        //obtener los datos de respuesta
        
        if(miResultset.next()){
                String id = miResultset.getString(1);
                String nombre = miResultset.getString(2);
                String categoria = miResultset.getString(3);
                String descripcion = miResultset.getString(4);
                double precio = miResultset.getDouble(5);
                Date fecha = miResultset.getDate(6);
                
                    elProducto = new Productos(id,nombre,categoria,descripcion,precio,fecha);
                
        }else{
            throw new Exception("No hemos encontrado el producto con codigo: "+cArticulo);
        }
        
        }catch(Exception e){
            e.printStackTrace();
        }finally{
          miStatement.close();
          miConnection.close();  
        }
        return elProducto;
        
    }

    void actualizarProducto(Productos productoActualizado) throws Exception {
        Connection miConnection = null;
        PreparedStatement miStatement = null;
        
        try{
        //Establecer la conexion 
        miConnection = OrigenDatos.getConnection();
        
        //Crear la sentencia Sql
        String sql = "UPDATE PRODUCTO SET ARTICULO=?, CATEGORIA=?, DESCRIPCION=?, PRECIO=?, fecha=?"+
                "WHERE ID=?";
        //Crar la consulta preparada
        miStatement = miConnection.prepareStatement(sql);
        //Establecer los parametros
        miStatement.setString(1, productoActualizado.getNoArt());
        miStatement.setString(2, productoActualizado.getCatArt());
        miStatement.setString(3, productoActualizado.getDesArt());
        miStatement.setDouble(4, productoActualizado.getPreArt());
         java.util.Date utilDate = productoActualizado.getFecArt();
            long fechaEnlong = utilDate.getTime();
            java.sql.Date fechaConvertida;
            fechaConvertida = new java.sql.Date(fechaEnlong);
            
            //fechaConvertida = (java.sql.Date) utilDate;
            
            miStatement.setDate(5, fechaConvertida);
        miStatement.setString(6, productoActualizado.getCoArt());
        //Ejecutar la instruccion sql
        miStatement.execute();
        }finally{
            miStatement.close();
            miConnection.close();
        }
    }

    void eliminarProducto(String codarti)throws Exception {
        Connection miConnection = null;
        PreparedStatement miStatement = null; 
        try{
        //obtener conexion con BBDD
        miConnection = OrigenDatos.getConnection();
        //crear instrucion sql de eliminacion
        String sql = "DELETE FROM PRODUCTO WHERE ID =?";
        //preparar la consulta
        miStatement= miConnection.prepareStatement(sql);
        //establecer parametros de consulta
        miStatement.setString(1,codarti );
        //Ejecutar sentencia sql
        miStatement.execute();
        }finally{
          miStatement.close();
          miConnection.close();  
        }
    }
    
}
