/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package paquete.com.pruebaserletmvc;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
//import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;


public class ControladorProductos extends HttpServlet {

    private ModeloProducto modeloProducto;
    
    @Resource(name="jdbc/Productos")
    private DataSource miPool;
    
    
    @Override
    public void init()throws ServletException{
        super.init();
        
        try{
        modeloProducto = new ModeloProducto(miPool);
        }catch(Exception e){
            throw new ServletException (e);
        }
    }
    
    
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String elcomando = request.getParameter("instruccion");
        
        if(elcomando==null) elcomando="listar";
        
        switch(elcomando){
            case "insertarBBDD" : insertarProductos(request,response);
                break;
            
            case "listar" : obtenerProductos(request,response);
                break;
                
            case "cargar" : cargaProductos(request, response);
                break;
            
            case "actualizarBBDD" : actualizaPrducto(request, response);
                break;
                
            case "eliminar" : {
                try {
                    eliminarProducto(request, response);
                } catch (Exception ex) {
                    Logger.getLogger(ControladorProductos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                break;

            
            default : obtenerProductos(request,response);
        }
        
        
        
        
        
        
    }

    private void obtenerProductos(HttpServletRequest request, HttpServletResponse response) {
      
        List<Productos> productos;
            
        try  {       
            
            productos = modeloProducto.getProductos();
            
            request.setAttribute("MISPRODUCTOS", productos);
            
            RequestDispatcher miDispatcher = request.getRequestDispatcher("/ListaProductos.jsp");
            
            miDispatcher.forward(request, response);
            
        }catch(Exception e){
            
            e.printStackTrace();
            
        }
    }

    private void insertarProductos(HttpServletRequest request, HttpServletResponse response) {
        
        String codarti = request.getParameter("codigo_articulo");
        String nomarti = request.getParameter("nombre_articulo");
        String catarti = request.getParameter("categoria_articulo");
        String desarti = request.getParameter("descripcion_articulo");
        double prearti = Double.parseDouble(request.getParameter("precio_articulo"));
        // Poner guines(-) en el SimpleFormat!!!!
        SimpleDateFormat formatofecha = new SimpleDateFormat("yyyy-MM-dd");
        
        Date fecarti = null;
        
        try {
            fecarti =formatofecha.parse(request.getParameter("fecha_articulo"));
        } catch (ParseException ex) {
            Logger.getLogger(ControladorProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
        //nuevo objeto producto
        Productos nuevoProducto = new Productos(codarti,nomarti,catarti,desarti,prearti,fecarti);
        try {
            //Enviar el objeto al Modelo y despues insertar el objeto producto en la BBDD
            modeloProducto.agregarElNuevoProducto(nuevoProducto);
        } catch (Exception ex) {
            Logger.getLogger(ControladorProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Volver a listar los productos
        obtenerProductos(request,response);
        
        
    }

    
    private void cargaProductos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        
        //Leer el codigoArticulo que viene del listado
        
        String codigoarticulo = request.getParameter("Carticulo");
        
        //Enviar c.Articulo a Modelo
        
        Productos elProducto;
        try {
            elProducto = modeloProducto.getProducto(codigoarticulo);
        
        
        //colocar Atributo correspondiente al c.Articulo
        
        request.setAttribute("ProductoActualizar", elProducto);
        
        //enviar Producto al formulario de acualizar(jsp)
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/actualizarProducto.jsp");
        
        dispatcher.forward(request, response);
        
        } catch (Exception ex) {
            Logger.getLogger(ControladorProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private void actualizaPrducto(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        
        //Leer los datos que le vienen del formulario actualizar
        
        String codarti = request.getParameter("codigo_articulo");
        String nomarti = request.getParameter("nombre_articulo");
        String catarti = request.getParameter("categoria_articulo");
        String desarti = request.getParameter("descripcion_articulo");
        double prearti = Double.parseDouble(request.getParameter("precio_articulo"));
        // Poner guiones(-) en el SimpleFormat!!!!
        SimpleDateFormat formatofecha = new SimpleDateFormat("yyyy-MM-dd");
        
        Date fecarti = null;
        
        try {
            fecarti =formatofecha.parse(request.getParameter("fecha_articulo"));
        } catch (ParseException ex) {
            Logger.getLogger(ControladorProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Crear un objeto de tipo producto con la informacion del formulario
        Productos productoActualizado = new Productos(codarti,nomarti,catarti,desarti,prearti,fecarti);
       
        try {
            //Actualizar la BBDD con la info del objt Producto
            modeloProducto.actualizarProducto(productoActualizado);
        } catch (Exception ex) {
            Logger.getLogger(ControladorProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //volver al listado con la info actualizada
        obtenerProductos(request,response);
    }

    private void eliminarProducto(HttpServletRequest request, HttpServletResponse response)throws Exception {
        //Capturar codigo Articulo
        String codarti = request.getParameter("Carticulo");
        //borrar producto de la BBDD
        modeloProducto.eliminarProducto(codarti);
        //volver a la lista de producto
        obtenerProductos(request,response);

    }
    

    
    

}
