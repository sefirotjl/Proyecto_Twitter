/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Juancho
 */
public class Usuario {
    private String nombre;
    private int ID;
    private int[] sigID;
    private String sigNombre;
    private String[] mensajes;

    public Usuario() {
    }

    public Usuario(String nombre, int ID) {
        this.nombre = nombre;
        this.ID = ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int[] getSigID() {
        return sigID;
    }

    public void setSigID(int[] sigID) {
        this.sigID = sigID;
    }

    public String getSigNombre() {
        return sigNombre;
    }

    public void setSigNombre(String sigNombre) {
        this.sigNombre = sigNombre;
    }

    public String[] getMensajes() {
        return mensajes;
    }

    public void setMensajes(String[] mensajes) {
        this.mensajes = mensajes;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.ID;
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
        if (this.ID != other.ID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Usuario{" + "nombre=" + nombre + ", ID=" + ID + '}';
    }
    
    
}
