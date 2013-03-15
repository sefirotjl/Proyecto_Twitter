package recursos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author msuarezl
 */
public class Consultas {

    Connection conexion;
    Validaciones v;

    public Consultas() {
    }

    public String[] trendingTopics() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String[] tt = new String[20];
        int i = 0;
        conexion = new ConectBD().open();
        Class.forName("com.mysql.jdbc.Driver").newInstance();


        PreparedStatement res = conexion.prepareStatement("select h.mensaje from hash h group by mensaje order by count(*) desc");
        ResultSet resultSet = res.executeQuery();

        while (resultSet.next()) {
            if (i < tt.length) {
                tt[i] = resultSet.getString(1);
                i++;
            }
        }

        return tt;
    }

    public String seguir(String seguidor, String seguido) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        int idSeguidor = getIdByCorreo(seguidor);
        int idSeguido = getIdByCorreo(seguido);

        if (idSeguidor == 0 || idSeguido == 0) {
            return "Algun mail no es válido";
        }
        System.out.println(idSeguidor);
        System.out.println(idSeguido);
        String query = "insert into siguiendo (IDPERSONA , IDSIGUE) VALUES('" + idSeguidor + "','" + idSeguido + "')";
        PreparedStatement res = conexion.prepareStatement(query);
        res.executeUpdate();
        return "OK";

    }

    public int getIdByCorreo(String correo) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        conexion = new ConectBD().open();
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        int id = 0;
        String query = "SELECT P.IDPERSONA FROM PERSONA P WHERE P.CORREO='" + correo + "'";

        PreparedStatement res = conexion.prepareStatement(query);
        ResultSet resultSet = res.executeQuery();
        while (resultSet.next()) {
            id = resultSet.getInt(1);
        }

        return id;
    }

    public String getNombreByCorreo(String correo) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        conexion = new ConectBD().open();
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        String resp = "";
        String query = "SELECT P.NOMBRE FROM PERSONA P WHERE P.CORREO='" + correo + "'";

        PreparedStatement res = conexion.prepareStatement(query);
        ResultSet resultSet = res.executeQuery();
        if (resultSet.next()) {

            resp = resultSet.getString(1);
        }
        conexion.close();
        return resp;
    }

    public Comment[] getCommentsByUser(String correo) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        conexion = new ConectBD().open();
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Comment[] arr = new Comment[20];
        int i = 0;
        int id = 0;
        id = getIdByCorreo(correo);
        if (id == 0) {
            conexion.close();
            return null;
        }

        PreparedStatement res = conexion.prepareStatement("SELECT P.NOMBRE, M.MENSAJE, P.CORREO FROM MENSAJE M, SIGUIENDO S, PERSONA P WHERE S.IDPERSONA=" + id + " AND S.IDSIGUE=M.IDPERSONA AND M.IDPERSONA=P.IDPERSONA UNION SELECT P.NOMBRE, M.MENSAJE, P.CORREO FROM MENSAJE M, SIGUIENDO S, PERSONA_EN_MENSAJE PE, PERSONA P WHERE PE.IDPERSONA=" + id + " AND PE.IDMENSAJE=M.IDMENSAJE AND M.IDPERSONA=P.IDPERSONA");
        ResultSet resultSet = res.executeQuery();

        while (resultSet.next()) {
            if (i < arr.length) {
                arr[i] = new Comment(resultSet.getString(3), resultSet.getString(1), resultSet.getString(2));
                i++;
            }
        }
        if (i == 0) {
            conexion.close();
            return null;
        }

        conexion.close();
        return arr;
    }

    public Usuario[] getUsersByName(String nombre) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Usuario[] arr = new Usuario[20];
        conexion = new ConectBD().open();
        int i = 0;
        int sigId;
        String sig;
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        PreparedStatement res = conexion.prepareStatement("SELECT P.CORREO FROM PERSONA P WHERE P.NOMBRE LIKE '%" + nombre + "%'");
        ResultSet resultSet = res.executeQuery();

        while (resultSet.next()) {
            if (i < arr.length) {
                sig = resultSet.getString(1);
                arr[i] = new Usuario(sig, getNombreByCorreo(sig));
                i++;
            }
        }
        if (i == 0) {
            conexion.close();
            return null;
        }
        conexion.close();
        return arr;
    }

    public Usuario[] getUsersByExactName(String nombre) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Usuario[] arr = new Usuario[20];
        conexion = new ConectBD().open();
        int i = 0;
        int[] sigId;
        String sig;
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        PreparedStatement res = conexion.prepareStatement("SELECT P.CORREO FROM PERSONA P WHERE P.NOMBRE='" + nombre + "'");
        ResultSet resultSet = res.executeQuery();

        while (resultSet.next()) {
            if (i < arr.length) {
                sig = resultSet.getString(1);
                sigId = arrDeSeguidores(getIdByCorreo(sig));
                arr[i] = new Usuario(sig, nombre, sigId, getCommentsByUser(sig));
                i++;
            }
        }
        if (i == 0) {
            conexion.close();
            return null;
        }
        conexion.close();
        return arr;
    }

    public int[] arrDeSeguidores(int idpersona) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        conexion = new ConectBD().open();
        int i = 0;
        int sigue = 0;
        int lon = 0;

        Class.forName("com.mysql.jdbc.Driver").newInstance();
        PreparedStatement res = conexion.prepareStatement("SELECT S.IDSIGUE FROM SIGUIENDO S WHERE S.IDPERSONA=" + idpersona);
        ResultSet resultSet = res.executeQuery();
        resultSet.last();
        lon = resultSet.getRow();
        resultSet.beforeFirst();
        int arr[] = new int[lon];
        while (resultSet.next()) {
            if (i < arr.length) {
                arr[i] = resultSet.getInt(1);
                i++;
            }
        }
        conexion.close();
        return arr;

    }

    public String registraUsuario(String correo, String pass, String nombre) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        conexion = new ConectBD().open();
        String resu = "OK";
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        v = new Validaciones();
        if (v.validaLongitud(correo) == false) {
            return "Correo muy largo";
        }
        if (v.validaLongitud(pass) == false) {
            return "Contraseña muy larga";
        }
        if (v.validaLongitud(nombre) == false) {
            return "Nombre muy largo";
        }

        if (getIdByCorreo(correo) != 0) {
            conexion.close();
            return "El correo ya está registrado";
        }
        String query = "insert into persona (CORREO, PASSWORD, NOMBRE) VALUES('" + correo + "','" + pass + "','" + nombre + "')";
        PreparedStatement res = conexion.prepareStatement(query);
        res.executeUpdate();
        conexion.close();
        return resu;
    }

    public String login(String correo, String pass) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        //No es case sensitive
        String ms = "OK";
        int id = 0;
        conexion = new ConectBD().open();
        Class.forName("com.mysql.jdbc.Driver").newInstance();

        if (getIdByCorreo(correo) == 0) {
            conexion.close();
            return "Correo electrónico inválido";
        }
        String query = "SELECT P.IDPERSONA FROM PERSONA P WHERE P.PASSWORD='" + pass + "' AND P.CORREO='" + correo + "'";
        PreparedStatement res = conexion.prepareStatement(query);
        ResultSet resultSet = res.executeQuery();
        while (resultSet.next()) {
            id = resultSet.getInt(1);
        }
        if (id == 0) {
            ms = "Contraseña incorrecta";
        }
        conexion.close();
        return ms;
    }

    public boolean siguiendo(String seguidor, String seguido) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        int idSeguidor = getIdByCorreo(seguidor);
        int idSeguido = getIdByCorreo(seguido);
        boolean i = true;
        conexion = new ConectBD().open();
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        int id = 0;
        String query = "SELECT S.IDPERSONA FROM SIGUIENDO S WHERE S.IDPERSONA='" + idSeguidor + "' AND S.IDSIGUE='" + idSeguido + "'";

        PreparedStatement res = conexion.prepareStatement(query);
        ResultSet resultSet = res.executeQuery();
        while (resultSet.next()) {
            id = resultSet.getInt(1);
        }
        if (id == 0) {
            i = false;
        }
        conexion.close();
        return i;
    }

    public Comment[] buscaMensajesPorHash(String hash) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        conexion = new ConectBD().open();
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Comment[] arr = new Comment[20];
        int i = 0;


        PreparedStatement res = conexion.prepareStatement("select p.nombre, m.mensaje, p.correo from mensaje m, persona p, hash h where h.mensaje LIKE '%" + hash + "%' and h.idmensaje=m.idmensaje and m.idpersona=p.idpersona");
        ResultSet resultSet = res.executeQuery();

        while (resultSet.next()) {
            if (i < arr.length) {
                arr[i] = new Comment(resultSet.getString(3), resultSet.getString(1), resultSet.getString(2));
                i++;
            }
        }
        if (i == 0) {
            conexion.close();
            return null;
        }

        conexion.close();
        return arr;
    }

    public Comment[] buscaMensajesPorHashExacto(String hash) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        conexion = new ConectBD().open();
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Comment[] arr = new Comment[20];
        int i = 0;


        PreparedStatement res = conexion.prepareStatement("select p.nombre, m.mensaje, p.correo from mensaje m, persona p, hash h where h.mensaje='" + hash + "' and h.idmensaje=m.idmensaje and m.idpersona=p.idpersona");
        ResultSet resultSet = res.executeQuery();

        while (resultSet.next()) {
            if (i < arr.length) {
                arr[i] = new Comment(resultSet.getString(3), resultSet.getString(1), resultSet.getString(2));
                i++;
            }
        }
        if (i == 0) {
            conexion.close();
            return null;
        }

        conexion.close();
        return arr;
    }

    public String publicaComment(String comment, String correo) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        comment = comment.trim();
        //Comment tuit = new Comment(correo,getNombreByCorreo(correo),comment);

        v = new Validaciones();
        conexion = new ConectBD().open();
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        int id = getIdByCorreo(correo);
        if (id == 0) {
            return "El correo no está registrado";
        }
        if (v.validaLongitudMensaje(comment) == false) {
            return "Mensaje muy largo";
        }
        String query = "insert into mensaje (IDPERSONA, MENSAJE) VALUES(" + id + ",'" + comment + "')";
        PreparedStatement res = conexion.prepareStatement(query);
        res.executeUpdate();

        query = "select mensaje.idmensaje from mensaje where mensaje.mensaje='" + comment + "'";
        res = conexion.prepareStatement(query);
        ResultSet resultSet = res.executeQuery();
        int idM = 0;
        while (resultSet.next()) {
            idM = resultSet.getInt(1);
        }

        v.cuentaHash(comment, idM);
        v.cuentaMenciones(comment, idM);

        return "OK";
    }
}
