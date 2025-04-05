/**
 * Paquete que contiene las clases de negocio
 */
package BibliotecaVIU_Negocio;

/**
 * Clase DTO para manejar al usuario
 */
public class Usuario {
    private String nombre;
    private String idUsuario;

    // Constructor
    public Usuario(String nombre, String idUsuario) {
        this.nombre = nombre;
        this.idUsuario = idUsuario;
    }

    // Métodos getter para acceder a las variables 
    public String getNombre() {
        return nombre;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    // Métodos setter para acceder a las variables 
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }
}

