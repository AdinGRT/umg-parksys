package com.analisisii.g3.parqueo.dao;

import com.analisisii.g3.parqueo.constantes.EstadoRegistroDeParqueo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.analisisii.g3.parqueo.modelo.RegistroDeParqueo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gian_
 */
public class RegistroDeParqueoDAO {

    private static final String SQL_SELECT_REGISTROS_POR_ESTADO = "SELECT "
            + "id_registro_parqueo, fecha_hora_entrada, fecha_hora_salida "
            + "FROM registro_parqueo WHERE id_status_reg_parqueo = ?;";

    private static final String SQL_SELECT_POR_MATRICULA_ESTADO = "SELECT id_registro_parqueo, fecha_hora_entrada, fecha_hora_salida, matricula_vehiculo, descripcion_status_reg_parqueo\n"
            + "FROM registro_parqueo\n"
            + "INNER JOIN vehiculo ON registro_parqueo.id_vehiculo = vehiculo.id_vehiculo\n"
            + "INNER JOIN status_reg_parqueo ON registro_parqueo.id_status_reg_parqueo = status_reg_parqueo.id_status_reg_parqueo\n"
            + "WHERE registro_parqueo.id_status_reg_parqueo = ?;";

    private static final String SQL_INSERT_INGRESO = "INSERT INTO registro_parqueo"
            + "(fecha_hora_entrada, fecha_hora_salida, id_vehiculo, id_status_reg_parqueo) "
            + "VALUES (?, ?, ?, ?)";

    private static final String SQL_UPDATE_SALIDA = "UPDATE ticket "
            + "SET fecha_hora_salida = ? WHERE id_status_reg_parqueo = ?";

    private static final String SQL_UPDATE_ESTADO = "UPDATE ticket "
            + "SET id_status_reg_parqueo = ? WHERE id_registro_parqueo = ?";

    private static final String SQL_SELECT_ESTADO_POR_ID = "SELECT id_status_reg_parqueo "
            + "FROM registro_parqueo WHERE id_registro_parqueo = ?";

    private static final String COUNT_ESPACIOS_OCUPADOS = "SELECT COUNT(*) FROM registro_parqueo\n"
            + "INNER JOIN vehiculo ON registro_parqueo.id_vehiculo = vehiculo.id_vehiculo\n"
            + "INNER JOIN tipo_vehiculo ON vehiculo.id_tipo_vehiculo = tipo_vehiculo.id_tipo_vehiculo\n"
            + "WHERE id_status_reg_parqueo = 1 AND tipo_vehiculo.id_tipo_vehiculo =  ?;";

    private static final String COUNT_REGISTROS = "SELECT count(*) FROM registro_parqueo;";
    
    public List<RegistroDeParqueo> listarRegistrosPorEstado(int idEstado) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        RegistroDeParqueo registro = null;

        List<RegistroDeParqueo> registros = new ArrayList<>();

        conn = Conexion.conectar();
        stmt = conn.prepareStatement(SQL_SELECT_REGISTROS_POR_ESTADO);
        stmt.setInt(1, idEstado);
        rs = stmt.executeQuery();

        while (rs.next()) {
            registro = new RegistroDeParqueo();
            registro.setIdRegistroParqueo(rs.getInt(1));
            registro.setFechaHoraEntrada(rs.getString(2));
            registro.setFechaHoraSalida(rs.getString(3));
            registro.setEstado(EstadoRegistroDeParqueo.valueOf(String.valueOf(idEstado)));
            registros.add(registro);
        }
        return registros;
    }

    public Map<String, RegistroDeParqueo> listarRegistrosPorMatriculaEstado(int idEstado) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        RegistroDeParqueo registro = null;
        String matricula;

        Map<String, RegistroDeParqueo> registros = new HashMap<>();

        conn = Conexion.conectar();
        stmt = conn.prepareStatement(SQL_SELECT_POR_MATRICULA_ESTADO);
        stmt.setInt(1, idEstado);
        rs = stmt.executeQuery();

        while (rs.next()) {
            registro = new RegistroDeParqueo();
            registro.setIdRegistroParqueo(rs.getInt(1));
            registro.setFechaHoraEntrada(rs.getString(2));
            registro.setFechaHoraSalida(rs.getString(3));
            matricula = rs.getString(4);
            registro.setEstado(EstadoRegistroDeParqueo.values()[idEstado]);
            registros.put(matricula, registro);
        }
        return registros;
    }

    public int contarEspaciosOcupados(int idTipoVehiculo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int cantidadEspaciosOcupados = 0;
        try {
            conn = Conexion.conectar();
            stmt = conn.prepareStatement(COUNT_ESPACIOS_OCUPADOS);
            stmt.setInt(1, idTipoVehiculo);
            rs = stmt.executeQuery();
            while (rs.next()) {
                cantidadEspaciosOcupados = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegistroDeParqueoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                Conexion.desconectar(rs);
                Conexion.desconectar(stmt);
                Conexion.desconectar(conn);
            } catch (SQLException ex) {
                Logger.getLogger(RegistroDeParqueoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return cantidadEspaciosOcupados;
    }
    
    public int contarRegistros() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int cantidadRegistros = 0;
        try {
            conn = Conexion.conectar();
            stmt = conn.prepareStatement(COUNT_REGISTROS);
            rs = stmt.executeQuery();
            while (rs.next()) {
                cantidadRegistros = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegistroDeParqueoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                Conexion.desconectar(rs);
                Conexion.desconectar(stmt);
                Conexion.desconectar(conn);
            } catch (SQLException ex) {
                Logger.getLogger(RegistroDeParqueoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return cantidadRegistros;
    }


    /*PASAR A OTRA CLASE
    private static final String SQL_SELECT_ULTIMO_TICKET_VEHICULO = "SELECT * FROM ticket "
            + "WHERE id_vehiculo = ? ORDER BY id_ticket DESC LIMIT 1";
    
    
    private static final String SQL_COUNT_TICKETSTATUS = "SELECT COUNT(*) \n" +
            "FROM ticket\n" +
            "INNER JOIN vehiculo ON ticket.id_vehiculo = vehiculo.id_vehiculo\n" +
            "INNER JOIN tipo_vehiculo ON vehiculo.id_tipo_vehiculo = tipo_vehiculo.id_tipo_vehiculo\n" +
            "WHERE vehiculo.id_tipo_vehiculo = ? AND ticket.id_ticket_status = ?";
    
    public int contarVehiculos(int idTipoVehiculo, int idTicketStatus) {
        int conteo = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.conectar();
            stmt = conn.prepareStatement(SQL_COUNT_TICKETSTATUS);
            stmt.setInt(1, idTipoVehiculo);
            stmt.setInt(2, idTicketStatus);
            rs = stmt.executeQuery();
            while(rs.next()) {
                conteo = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                Conexion.desconectar(stmt);
                Conexion.desconectar(conn);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        
        return conteo;
    }
     */
    public int updateTicketStatus(int idTicketStatus, int idTicket) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.conectar();
            stmt = conn.prepareStatement(SQL_UPDATE_ESTADO);

            stmt.setInt(1, idTicketStatus);
            stmt.setInt(2, idTicket);

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

    public int getTicketStatus(int idTicket) {
        int status = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.conectar();
            stmt = conn.prepareStatement(SQL_SELECT_ESTADO_POR_ID);
            stmt.setInt(1, idTicket);
            rs = stmt.executeQuery();
            while (rs.next()) {
                status = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                Conexion.desconectar(stmt);
                Conexion.desconectar(conn);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return status;
    }

    /*
    public RegistroDeParqueo verUltimoTicket(int idVehiculo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        RegistroDeParqueo ticket = new RegistroDeParqueo();
        try {
            conn = Conexion.conectar();
            stmt = conn.prepareStatement(SQL_SELECT_ULTIMO_TICKET_VEHICULO);
            stmt.setInt(1, idVehiculo);
            rs = stmt.executeQuery();
            while(rs.next()) {
                ticket.setIdTicket(rs.getInt(1));
                ticket.setHorarioEntrada(rs.getString(2));
                ticket.setHorarioSalida(rs.getString(3));
                ticket.setMontoTicket(rs.getDouble(4));
                ticket.setIdVehiculo(rs.getInt(5));
                ticket.setIdUsuario(rs.getInt(6));
                ticket.setIdTicketStatus(rs.getInt(7));
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                Conexion.desconectar(stmt);
                Conexion.desconectar(conn);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        
        
        return ticket;
    }
     
    public int insertar(RegistroDeParqueo ticket) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.conectar();
            stmt = conn.prepareStatement(SQL_INSERT_INGRESO);
            stmt.setString(1, ticket.getHorarioEntrada());
            stmt.setInt(2, ticket.getIdUsuario());
            stmt.setInt(3, ticket.getIdVehiculo());
            stmt.setInt(4, ticket.getIdTicketStatus());

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

    public int actualizar(RegistroDeParqueo ticket) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.conectar();
            stmt = conn.prepareStatement(SQL_UPDATE_SALIDA);

            stmt.setString(1, ticket.getHorarioSalida());
            stmt.setInt(2, ticket.getIdTicket());

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
     */
}
