<%-- 
    Document   : ListaProductos
    Created on : 9 nov. 2021, 23:06:27
    Author     : kai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head> 
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style type="text/css">
            .cabecera{
                font-size: 1.2em;
                font-weight: bold;
                color: #ffffff;
                background-color: #003366;
            }
            .filas{
                text-align: center;
                background-color: #3399ff;
            }
            table{
                float: left;
            }
            #contenedorBoton{
                margin-left: 600px;
            }
        </style>
    </head>
   
    <body>
        <table>
            <tr>
                <th class="cabecera">Codigo</th>
                <th class="cabecera">Nom. Articulo</th>
                <th class="cabecera">Categoria</th>
                <th class="cabecera">Descripcion</th>
                <th class="cabecera">Precio</th>
                <th class="cabecera">Fecha</th>
                <th class="cabecera">Accion</th>
            </tr>
            <c:forEach var="tempArti" items="${MISPRODUCTOS}">
            
            <%--Link para Actualizar cada pruducto con su campo clave --%>   
            
            <c:url var="linkTemp" value="ControladorProductos">
               
                <c:param name="instruccion" value="cargar"></c:param> 
                <c:param name="Carticulo" value="${tempArti.coArt}"></c:param>
            
            </c:url>  
            
            <%--Link para Eliminar cada articulo --%> 
            
            <c:url var="linkTempEliminar" value="ControladorProductos">
               
                <c:param name="instruccion" value="eliminar"></c:param> 
                <c:param name="Carticulo" value="${tempArti.coArt}"></c:param>
            
            </c:url> 
            <tr>
                <td class="filas">${tempArti.coArt}</td>
                <td class="filas">${tempArti.noArt}</td>
                <td class="filas">${tempArti.catArt}</td>
                <td class="filas">${tempArti.desArt}</td>
                <td class="filas">${tempArti.preArt}</td>
                <td class="filas">${tempArti.fecArt}</td>
                <td class="filas"><a href="${linkTemp}">Actualizar</a>&nbsp;&nbsp;<a href="${linkTempEliminar}">Eliminar</a></td>
            </tr>
            </c:forEach>
        </table>
        <div id="contenedorBoton">
            <input type="submit" name="insertar_registro" value="Insertar Registro" onclick="window.location.href='Insertar_producto.jsp'" />
        </div>
    </body>
</html>
