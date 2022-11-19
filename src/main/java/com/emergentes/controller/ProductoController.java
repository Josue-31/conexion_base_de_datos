package com.emergentes.controller;
import com.emergentes.dao.ProductoDAO;
import com.emergentes.dao.ProductoDAOimpl;
import com.emergentes.model.Producto;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ProductoController", urlPatterns = {"/ProductoController"})
public class ProductoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductoDAO dao = new ProductoDAOimpl();

        Producto producto = new Producto();
        int id;

        String action = request.getParameter("action") != null ? request.getParameter("action") : "view";

        switch (action) {
            case "add":
                request.setAttribute("producto", producto);
                request.getRequestDispatcher("frmproducto.jsp").forward(request, response);
                break;
            case "edit":
                id = Integer.parseInt(request.getParameter("id"));

                try {
                    producto = dao.getById(id);
                } catch (Exception e) {
                    System.out.println("Error al obtener el registro" + e.getMessage());
                }
                request.setAttribute("producto", producto);
                request.getRequestDispatcher("frmproducto.jsp").forward(request, response);
                break;
            case "delete":
                id = Integer.parseInt(request.getParameter("id"));

                try {
                    dao.delete(id);
                } catch (Exception ex) {
                    System.out.println("Error al eliminar: " + ex.getMessage());
                }

                response.sendRedirect("ProductoController");
                break;

            case "view":
                List<Producto> lista = new ArrayList<Producto>();
                try {
                    lista = dao.getAll();
                } catch (Exception ex) {
                    System.out.println("Error al listar " + ex.getMessage());
                }
                request.setAttribute("productos", lista);
                request.getRequestDispatcher("productos.jsp").forward(request, response);
                break;
            default:
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Producto productod = new Producto();

        int id = Integer.parseInt(request.getParameter("id"));
        String producto = request.getParameter("producto");
        float precio = Float.parseFloat(request.getParameter("precio"));
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));

        productod.setId(id);
        productod.setProducto(producto);
        productod.setPrecio(precio);
        productod.setCantidad(cantidad);

        ProductoDAO dao = new ProductoDAOimpl();

        if (id == 0) {
            try {
                dao.insert(productod);
            } catch (Exception ex) {
                System.out.println("Error al insertar " + ex.getMessage());
            }
        } else {
            try {
                dao.update(productod);
            } catch (Exception ex) {
                System.out.println("Error al actualizar " + ex.getMessage());
            }
        }
        response.sendRedirect("ProductoController");
    }

}
