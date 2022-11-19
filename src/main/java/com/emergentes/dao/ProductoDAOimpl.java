package com.emergentes.dao;
import com.emergentes.model.Producto;
import com.emergentes.utils.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAOimpl extends ConexionDB implements ProductoDAO {

    @Override
    public void insert(Producto producto) throws Exception {
        String sql = "INSERT INTO productos (producto, precio, cantidad) VALUES ( ?, ?, ?);";
        this.conectar();
        PreparedStatement ps = this.conn.prepareStatement(sql);
        ps.setString(1, producto.getProducto());
        ps.setFloat(2, producto.getPrecio());
        ps.setInt(3, producto.getCantidad());

        ps.executeUpdate();

        this.desconectar();
    }

    @Override
    public void update(Producto producto) throws Exception {
        String sql = "UPDATE productos SET producto = ?, precio = ?, cantidad = ? WHERE id = ?;";
        this.conectar();
        PreparedStatement ps = this.conn.prepareStatement(sql);
        ps.setString(1, producto.getProducto());
        ps.setFloat(2, producto.getPrecio());
        ps.setInt(3, producto.getCantidad());
        ps.setInt(4, producto.getId());
        ps.executeUpdate();

        this.desconectar();
    }

    @Override
    public void delete(int id) throws Exception {
        String sql = "DELETE FROM productos WHERE id = ?;";
        this.conectar();
        PreparedStatement ps = this.conn.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();

        this.desconectar();
    }

    @Override
    public List<Producto> getAll() throws Exception {
        String sql = "SELECT * FROM productos;";
        this.conectar();
        PreparedStatement ps = this.conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        List<Producto> lista = new ArrayList<Producto>();
        while (rs.next()) {
            Producto producto = new Producto();

            producto.setId(rs.getInt("id"));
            producto.setProducto(rs.getString("producto"));
            producto.setPrecio(rs.getFloat("precio"));
            producto.setCantidad(rs.getInt("cantidad"));

            lista.add(producto);
        }

        this.desconectar();
        return lista;
    }

    @Override
    public Producto getById(int id) throws Exception {
        Producto producto = new Producto();
        try {
            String sql = "SELECT * FROM productos WHERE id = ?;";
            this.conectar();

            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                producto.setId(rs.getInt("id"));
                producto.setProducto(rs.getString("producto"));
                producto.setPrecio(rs.getFloat("precio"));
                producto.setCantidad(rs.getInt("cantidad"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return producto;
    }

}
