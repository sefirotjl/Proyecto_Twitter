/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Juancho
 */
public class Usuario {
    private String email;
    private String nombre;
    private int[] sigID;
    private Comment[] comments;

    public Usuario(String email, String nombre) {
        this.email = email;
        this.nombre = nombre;
    }

    public Usuario(String email, String nombre, Comment[] comments) {
        this.email = email;
        this.nombre = nombre;
        this.comments = comments;
    }

    public Usuario() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Comment[] getComments() {
        return comments;
    }

    public void setComments(Comment[] comments) {
        this.comments = comments;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + (this.email != null ? this.email.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if ((this.email == null) ? (other.email != null) : !this.email.equals(other.email)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Usuario{" + "email=" + email + ", nombre=" + nombre + '}';
    }
    
    

}
