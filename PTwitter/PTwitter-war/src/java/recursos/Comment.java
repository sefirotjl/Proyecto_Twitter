/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Juancho
 */
public class Comment {
    private String email;
    private String nombre;
    private String comment;
    private String[] hashes;
    private String[] referencias;

    public Comment(String email, String nombre, String comment) {
        this.email = email;
        this.nombre = nombre;
        this.comment = comment;
    }

    public Comment(String email, String nombre, String comment, String[] hashes) {
        this.email = email;
        this.nombre = nombre;
        this.comment = comment;
        this.hashes = hashes;
    }

    public Comment(String email, String nombre, String comment, String[] hashes, String[] referencias) {
        this.email = email;
        this.nombre = nombre;
        this.comment = comment;
        this.hashes = hashes;
        this.referencias = referencias;
    }

    public Comment() {
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String[] getHashes() {
        return hashes;
    }

    public void setHashes(String[] hashes) {
        this.hashes = hashes;
    }

    public String[] getReferencias() {
        return referencias;
    }

    public void setReferencias(String[] referencias) {
        this.referencias = referencias;
    }

    @Override
    public String toString() {
        return "Comment{" + "email=" + email + ", nombre=" + nombre + ", comment=" + comment + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + (this.email != null ? this.email.hashCode() : 0);
        hash = 23 * hash + (this.comment != null ? this.comment.hashCode() : 0);
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
        final Comment other = (Comment) obj;
        if ((this.email == null) ? (other.email != null) : !this.email.equals(other.email)) {
            return false;
        }
        if ((this.comment == null) ? (other.comment != null) : !this.comment.equals(other.comment)) {
            return false;
        }
        return true;
    }
    
    
    
}
