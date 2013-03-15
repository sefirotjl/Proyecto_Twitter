package recursos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Usuario
 */
public class Validaciones {

    Consultas c;
    Connection conexion;

    public Validaciones() {
        c =  new Consultas();
        conexion = new ConectBD().open();
    }

    public boolean validaLongitud(String mensaje) {
        boolean a = false;
        if (mensaje.length() < 30) {
            a = true;
        }
        return a;
    }

    public boolean validaLongitudMensaje(String mensaje) {
        boolean a = false;
        if (mensaje.length() < 130) {
            a = true;
        }
        return a;

    }

    public boolean tieneEspeciales(String mensaje) {
        boolean res = true;
        String alphaAndDigits = mensaje.replaceAll("[^a-zA-Z0-9]+", "");
        if (alphaAndDigits.equals(mensaje)) {
            res = false;
        }
        return res;
    }

    public String cuentaHash(String msg, int idMensaje) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        //Debe ser del tipo comment el param de entrada
        conexion = new ConectBD().open();
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        String hash = "#";
        //String mensaje = comment.getComment();

        while (msg.indexOf(hash) != -1) {
            int ind = validaHash(msg);
            if (ind != -1) {
                //Crea el substring a partir del hash
                int fin = msg.indexOf(" ", ind);
                String algo;
                if (fin == -1) {
                    algo = msg.substring(ind);
                } else {
                    algo = msg.substring(ind, fin);
                }
                //Se eliminan los caracteres especiales del hash tag
                algo = quitaEspeciales(algo);


                String query = "insert into hash (IDMENSAJE , MENSAJE) VALUES('" + idMensaje + "','" + algo + "')";
                PreparedStatement res = conexion.prepareStatement(query);
                res.executeUpdate();
            }
            msg = msg.replaceFirst("#", "");
        }
        return "OK";
    }

    private int validaHash(String hash) {
        String hashT = "#";
        int ind = hash.indexOf(hashT);
        int lst = hash.lastIndexOf(hashT);
        int lstr = hash.length() - 1;

        if (lstr == -1) {
            return -1;
        }
        if (ind == -1) {
            return -1;
        } else if (lst == lstr || hash.charAt(ind + 1) == ' ') {
            return -1;
        } else if (ind > 0) {
            if (hash.charAt(ind - 1) != ' ') {
                return -1;
            }
        }
        return ind;
    }
   
    
    //---------------------------------------------------------------
    public int validaMencion(String st) {
        String hashT = "@";
        int ind = st.indexOf(hashT);
        int lst = st.lastIndexOf(hashT);
        int lstr = st.length() - 1;
        if (lstr == -1) {
            return -1;
        }
        if (ind == -1) {
            return -1;
        } else if (lst == lstr || st.charAt(ind + 1) == ' ') {
            return -1;
        } else if (ind > 0) {
            if (st.charAt(ind - 1) != ' ') {
                return -1;
            }
        }
        String aux = st.replaceFirst("@", "");
        int fin = aux.indexOf(" ", ind);
        String mail;
        if (fin == -1) {
            mail = aux.substring(ind);
        } else {
            mail = aux.substring(ind, fin);
        }
        if (verificaMail(mail).equals("OK")) {
            return 0;
        }
        return -1;
    }
    public String quitaEspeciales(String mensaje) {
        return mensaje.replaceAll("[^a-zA-Z0-9]+", "");
    }
    public String verificaMail(String str) {
        String at = "@";
        String dot = ".";
        int lat = str.indexOf(at);
        int lstr = str.length();
        int ldot = str.indexOf(dot);
        int lastIndexOfDot = str.lastIndexOf(dot);
        if (lastIndexOfDot == lstr - 1) {
            return "Invalid E-mail ID";
        }
        // check if '@' is at the first position or at last position or absent in given email
        if (str.indexOf(at) == -1 || str.indexOf(at) == 0
                || str.indexOf(at) == lstr) {
            return "Invalid E-mail ID";
        }
        // check if '.' is at the first position or at last position or absent in given email
        if (str.indexOf(dot) == -1 || str.indexOf(dot) == 0
                || str.indexOf(dot) == lstr) {
            return "Invalid E-mail ID";
        }
        // check if '@' is used more than one times in given email
        if (str.indexOf(at, (lat + 1)) != -1) {
            return "Invalid E-mail ID";
        }
        // check for the position of '.'
        if (str.substring(lat - 1, lat) == dot
                || str.substring(lat + 1, lat + 2) == dot) {
            return "Invalid E-mail ID";
        }
        // check if '.' is present after two characters from location of '@'
        if (str.indexOf(dot, (lat + 2)) == -1) {
            return "Invalid E-mail ID";
        }
        // check for blank spaces in given email
        if (str.indexOf(" ") != -1) {
            return "Invalid E-mail ID";
        }
        //verifica si hay dos puntos juntos
        String aux = str;
        while (aux.indexOf(dot) != -1) {
            int i = aux.indexOf(dot);
            aux = aux.replaceFirst(".", "");
            int j = aux.indexOf(dot);
            if ((i - j) == 0) {
                return "Invalid E-mail ID";
            }
        }
        return "OK";
    }
    public String cuentaMenciones(String mensaje, int idMensaje) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        String at = "@";
        int fin;
        mensaje = mensaje.trim();
        //String mensaje = comment.getComment();
        while (mensaje.indexOf(at) != -1) {
            while (mensaje.indexOf(" ") == 0) {
                mensaje = mensaje.replaceFirst(" ", "");
            }
            int ind = validaMencion(mensaje);
            if (ind != -1) {
                //ind me dice si la primera mencion del mensaje es valida
                //Crea el substring de mail a partir de la arroba
                fin = mensaje.indexOf(" ", ind);
                String mencion;
                if (fin == -1) {
                    mencion = mensaje.substring(ind);
                } else {
                    mencion = mensaje.substring(ind, fin);
                }
                //Se quita la primera arroba
                String correo = mencion.replaceFirst("@", "");
                System.out.println(correo);
                int idCorreo = c.getIdByCorreo(correo);
                System.out.println(idCorreo);
                if (idCorreo != 0) {
                    String query = "insert into PERSONA_EN_MENSAJE (IDPERSONA , IDMENSAJE) VALUES(" + idCorreo + "," + idMensaje + ")";
                    System.out.println(query);
                    PreparedStatement res = conexion.prepareStatement(query);
                res.executeUpdate();
                }
            }
            fin = mensaje.indexOf(" ", ind);
            if (fin != -1) {
                mensaje = mensaje.substring(fin, mensaje.length());
            } else {
                return "OK";
            }
        }
        return "OK";
    }
}