package com.analisisii.g3.cobros.all;

import com.analisisii.g3.parqueo.dao.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author gian_
 */
public class FacturaDAO {
    private static final String SQL_INSERT_FACTURA = "INSERT INTO factura (fecha_hora_emision, nombre_cliente_factura, direccion_cliente_factura, nit_cliente_factura, total_factura, id_registro_parqueo, id_kiosko_pago)\n" +
"VALUES (?, ?, ?, ?, ?, ?, ?); ";
    private static final String SQL_INSERT_DETALLE = "INSERT INTO detalle_factura (id_factura, id_tarifa, cantidad, monto, descuento, subtotal)  VALUES  (?, ?, ?, ?, ?, ?);";
    
    public int insertarFactura(Factura factura){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int rows = 0;
        try {
            conn = Conexion.conectar();
            stmt = conn.prepareStatement(SQL_INSERT_FACTURA, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, factura.getFechaHoraEmision());
            stmt.setString(2, factura.getNombreCliente());
            stmt.setString(3, factura.getDireccionCliente());
            stmt.setString(4, factura.getNitCliente());
            stmt.setDouble(5, factura.getTotalFactura());
            stmt.setInt(6, factura.getIdRegistroParqueo());
            stmt.setInt(7, factura.getIdKioskoPago());
            //stmt.setInt(8, factura.getIdCliente());
            //stmt.setInt(9, factura.getIdUsuario());

            rows = stmt.executeUpdate();
            
            rs = stmt.getGeneratedKeys();
            while(rs.next()) {
                factura.setIdFactura(rs.getInt(1));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                Conexion.desconectar(stmt);
                Conexion.desconectar(conn);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return rows;
    }
    
    public int insertarDetalleFactura(DetalleFactura detalle){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int rows = 0;
        try {
            conn = Conexion.conectar();
            stmt = conn.prepareStatement(SQL_INSERT_DETALLE);
            stmt.setInt(1, detalle.getIdFactura());
            stmt.setInt(2, detalle.getIdTarifa());
            stmt.setInt(3, detalle.getCantidad());
            stmt.setDouble(4, detalle.getDescuento());
            stmt.setDouble(5, detalle.getMonto());

            rows = stmt.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                Conexion.desconectar(stmt);
                Conexion.desconectar(conn);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return rows;
    }
}
