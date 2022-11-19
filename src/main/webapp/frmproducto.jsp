<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.emergentes.model.Producto"%>
<%
    Producto producto = (Producto)request.getAttribute("producto");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Nuevo Producto</h1>
        <form action="ProductoController" method="post">
            <input type="hidden" name="id" value="${producto.id}">
            <table>
                <tr>
                    <td>Producto</td>
                    <td> <input type="text" name="producto" value="${producto.producto}"> </td>
                </tr>
                <tr>
                    <td>Precio</td>
                    <td> <input type="text" name="precio" value="${producto.precio}"> </td>
                </tr>
                <tr>
                    <td>Cantidad</td>
                    <td> <input type="text" name="cantidad" value="${producto.cantidad}"> </td>
                </tr>
                <tr>
                    <td></td>
                    <td> <input type="submit" value="Enviar"> </td>
                </tr>
            </table>
        </form>
    </body>
</html>
