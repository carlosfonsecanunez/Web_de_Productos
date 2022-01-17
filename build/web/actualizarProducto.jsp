<%-- 
    Document   : Insertar_producto
    Created on : 11 nov. 2021, 18:11:35
    Author     : kai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>I P</title>
    </head>
    <h1>Actualizar Producto</h1>
    <body>
        <form name="form1" method="get" action="ControladorProductos">
        <input type="hidden" name="instruccion" value="actualizarBBDD">
        <input type="hidden" name="codigo_articulo" value="${ProductoActualizar.coArt}">
            <table>
                <!--
                <tr>
                    <td><label for="codigo_articulo">Codigo del Aticulo</label></td>
                    <td><input type="text" name="codigo_articulo" id="codigo_articulo"></td>
                </tr>
                <tr>
                -->
                    <td><label for="nombre_articulo">Nombre del Aticulo</label></td>
                    <td><input type="text" name="nombre_articulo" id="nombre_articulo" value="${ProductoActualizar.noArt}"></td>
                </tr>
                <tr>
                    <td><label for="categoria_articulo">Categoria del Aticulo</label></td>
                    <td><input type="text" name="categoria_articulo" id="categoria_articulo" value="${ProductoActualizar.catArt}"></td>
                </tr>
                <tr>
                    <td><label for="descripcion_articulo">Descripcion del Aticulo</label></td>
                    <td><input type="text" name="descripcion_articulo" id="descripcion_articulo" value="${ProductoActualizar.desArt}"></td>
                </tr>
                <tr>
                    <td><label for="precio_articulo">Precio del Aticulo</label></td>
                    <td><input type="text" name="precio_articulo" id="precio_articulo" value="${ProductoActualizar.preArt}"></td>
                </tr>
                <tr>
                    <td><label for="fecha_articulo">Fecha del Aticulo</label></td>
                    <td><input type="text" name="fecha_articulo" id="fecha_articulo" value="${ProductoActualizar.fecArt}"></td>
                </tr>
                <tr>
                    <td><input type="submit" name="boton_enviar" value="Enviar"></td>
                    <td><input type="submit" name="boton_limpiar" value="Limpiar"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
