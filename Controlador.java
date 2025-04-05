/** 
 * Paquete que contiene las clase de presentación
 */
package BibliotecaVIU_Presentacion;
 
import java.util.ArrayList;

//Importamos la el paquete que contiene las clases de negocio
import BibliotecaVIU_Negocio.*;

/**
 * Clase controladora del catálogo, la cual importa el paquete que contiene las clases de negocio
 */
 public class Controlador 
 {
	 Catalogo catalogo;
	 
	 /**
	 * Constructor público
	 */
	public Controlador()
	 {
		 catalogo = new Catalogo();
	 }
	 
	 /**
	  * Permite agregar un libro al catálogo
	 * @param libro Libro para agregar
	 */
	public void agregarLibro(Libro libro) 
	 {
	     // Obtener datos del libro desde la vista y agregarlo al catálogo
		 catalogo.agregarLibro(libro);
	 }
	
	 /**
	  * Permite agregar un usuario 
	 * @param libro Usuario para agregar
	 */
	public void agregarUsuario(String usuario) 
	 {
	     // Agregar un usuario
		 //catalogo.agregarUsuario(usuario);
	 }
		 
	 /**
	  * Permite editar un libro del catálogo
	 * @param libro Lo¡ibro para editar
	 */
	public void editarLibro(Libro libro) 
	 {
	     // Obtener datos del libro actualizado desde la vista y actualizarlo en el catálogo
		 catalogo.editarLibro(libro.getIsbn(),libro.getAutor(), libro.getTitulo());
	 }
	
	 /**
	  * Permite eliminar un libro del catálogo
	 * @param isbn Isbn de entrada
	 */
	public void eliminarLibro(String isbn) 
	 {
	     // Obtener ISBN del libro desde la vista y eliminarlo del catálogo
		 catalogo.eliminarLibro(isbn);
	 }
	
	 /**
	  * Permite buscar un libro
	 * @param isbn Isbn de entrada
	 * @return Libro buscado
	 */
	public Libro buscarLibro(String isbn) 
	 {
		 return catalogo.buscarLibrosPorISBN(isbn);
	 }
	
	 /**
	  * Permite consultar los libros no prestados o disponibles
	 * @return Libros no prestados o disponibles
	 */
	public ArrayList<String> consultarLibrosCatalogoNoPrestados() 
	 {
		 return catalogo.devolverCatalogo();
	 }
	
	 /**
	  * Permite consultar los libros prestados o no disponibles
	 * @return Libros prestados
	 */
	 public ArrayList<String> consultarLibrosCatalogoPrestados() 
	 {
	     // Lógica para consultar libros prestados del catálogo
		 return catalogo.devolverCatalogoPrestados();
	 }
	 
	 /**
	  * Permite registrar un prestamo
	 * @param isbn Parámetro de entrada
	 * @param user Parámetro de entrada
	 * @param tipoAccion Parámetro de entrada
	 */
	public void registrarPrestamosDevoluciones(String isbn, String user, boolean tipoAccion) 
	 {
	     // Lógica para registrar préstamos y devoluciones
	     if (tipoAccion)
	     {
	    	 //Gestionamos prestamo
	    	 catalogo.prestarLibro(isbn, user);
	     }
	     else
	     {
	    	 //Gestionamos devolución
	    	 catalogo.devolverLibro(isbn, user);
	     }
	 }
}

 
 
