/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Juancho
 */
public class Consultas {

    public Consultas() {
    }

    public Comment[] getCommentsByUser(String name) {
        return null;
    }

    public Comment[] getCommentsByHash(String hash) {
        return null;
    }

    public boolean siguiendo(String yo, String otro) {
        return true;
    }
    
    public String getUserByEmail(String email){
        return null;
    }
    
    public Usuario[] getUsersByName(String nombre){
        return null;
    }
    public String[] getTrendingTopics(){
        return null;
    }
    
    public String crearUsuario(String email, String nombre, String password){
        //regresas un string para que me reportes que paso al crear el usuario
        //si hay un error me dices si es porque ya existe el usuario
        //o porque puede que este muy lagor el nombre o cosas de esas
        return null;   
    } 
}
