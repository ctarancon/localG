/**
 * Paquete que contiene las clases de negocio
 */
package BibliotecaVIU_Datos;

/**
 * Clase para almacenar las sentencias SQL de la biblioteca VIU de tipo consulta
 */
public class Queries {
	public static final String buscarLibro = "SELECT FROM CATALOGO WHERE TITULO = ";
	public static final String consultarPrestamos = "SELECT * FROM PRESTAMOS";
	public static final String consultarCatalogo = "SELECT * FROM CATALOGO";
}

