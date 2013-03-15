/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Juancho
 */
public class pruebasIndividuales {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            Consultas con = new Consultas();
            String mail = "jorge@renteria.com.mx";
            String password = "jorge";
            String nombre = "Jorge Perez";
            //System.out.println(con.login(mail, password));
            //System.out.println(con.getNombreByCorreo(mail));
            //Validaciones v = new Validaciones();
            con.publicaComment("@suarez_manuel@hotmail.com @hola @pureba@mal @jorge@renteria.com #bien mal# # @mal ", "suarez_manuel@hotmail.com");
            //System.out.println(v.validaMencion("@suarez@hotmail.com @hola @pureba@mal #bien mal# # @mal "));
            //System.out.println(con.getIdByCorreo("suarez@hotmail.com"));
            //System.out.println(con.registraUsuario(mail, password, nombre));
        } catch (SQLException ex) {
            Logger.getLogger(pruebasIndividuales.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(pruebasIndividuales.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(pruebasIndividuales.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(pruebasIndividuales.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
