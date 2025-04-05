
/**  
 * Paquete que contiene las clases de negocio
 */
package BibliotecaVIU_Negocio;

import BibliotecaVIU_Datos.GestionBBDD;
import BibliotecaVIU_Datos.Queries;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Clase que maneja el catálogo de libros
 */
public class Catalogo {

	GestionBBDD gestionBBDD;
	ResultSet resultados;
	
	/**
	 * Agrega un libro a la biblioteca y comprueba con una regla de negocio el formato del ISBN
	 * @param libro Libro a agregar.
	 */
	public void agregarLibro(Libro libro) 
	{
		try 
		{
			if (ReglasNegocio.formatoIsbnCorrecto(libro.getIsbn())) {
				//Llamamos a la clase de gestión de la BBDD y le pasamos la consulta a ejecutar
				int filasAfectadas = gestionBBDD
						.ejecutarUpdateInsertSQL("INSERT INTO CATALOGO (isbn, autor, titulo) VALUES (" + libro.getIsbn()
								+ "," + libro.getAutor() + "," + libro.getTitulo() + ")");
				if (filasAfectadas == 0)
					throw new Exception("No se pudo realizar la inserción el libro en el catálogo.");
			}
			else
			{
				throw new Exception("ISBN formato incorrecto.");
			}
		} 
		catch (Exception e) 
		{
			this.gestionarExcepcion(e);
		}
	}
	
	/**
	 * Edita un libro a la biblioteca
	 * @param libro Libro a editar.
	 */
	public void editarLibro(String isbn, String titulo, String autor) 
	{
		try 
		{
			//Llamamos a la clase de gestión de la BBDD y le pasamos la consulta a ejecutar
			int filasAfectadas = gestionBBDD.ejecutarUpdateInsertSQL("UPDATE CATALOGO SET titulo = " + titulo + ", autor = " + autor + " WHERE isbn = "+ isbn);
			if (filasAfectadas == 0)
				throw new Exception("No se pudo realizar la actualización del libro del catálogo.");		
		} 
		catch (Exception e) 
		{
			this.gestionarExcepcion(e);
		}
	}
	
	/**
	 * Elimna un libro de la biblioteca
	 * @param isbn ISBN del libro a eliminar.
	 */
	public void eliminarLibro(String isbn) 
	{
		try 
		{
			//Llamamos a la clase de gestión de la BBDD y le pasamos la consulta a ejecutar
			int filasAfectadas = gestionBBDD.ejecutarUpdateInsertSQL("DELETE FROM CATALOGO WHERE isbn =" + isbn);
			if (filasAfectadas == 0)
				throw new Exception("No se pudo realizar la eliminación del libro del catálogo.");		
		} 
		catch (Exception e) 
		{
			this.gestionarExcepcion(e);
		}
	}
	
	 /**
	 * Consulta del título de todos los libros  del catálogo
	 * @return Listado con los libros del catálogo
	 */
	 public ArrayList<String> devolverCatalogo() 
	 {
		//Llamamos a la clase de gestión de la BBDD y le pasamos la consulta a ejecutar
		resultados = gestionBBDD.obtenerResultadoConsultaSQL(Queries.consultarCatalogo);
		ArrayList<String> libros = new ArrayList<>();
		try {
			// Procesar los resultados 
			while (resultados.next()) 
			{ 
				libros.add(resultados.getString("titulo")); 
			}
		} catch (SQLException e) {
			this.gestionarExcepcion(e);
		}
	    return libros;
	 }
	 
	 /**
	 * Consulta del título de todos los libros prestados del catálogo
	 * @return Listado con los libros prestados
	 */
	public ArrayList<String> devolverCatalogoPrestados() 
	  {
		//Llamamos a la clase de gestión de la BBDD y le pasamos la consulta a ejecutar
		resultados = gestionBBDD.obtenerResultadoConsultaSQL(Queries.consultarPrestamos);
		ArrayList<String> libros = new ArrayList<>();
		try {
			// Procesar los resultados 
			while (resultados.next()) 
			{ 
				libros.add(resultados.getString("titulo")); 
			}
		} catch (SQLException e) {
			this.gestionarExcepcion(e);
		}
	    return libros;
	  }
    
	/**
	 * Búsqueda de un libro de la biblioteca
	 * @param titulo Parámetro para la búsqueda del libro.
	 */
	public Libro buscarLibrosPorTitulo(String titulo) 
	{
		//Llamamos a la clase de gestión de la BBDD y le pasamos la consulta a ejecutar
		resultados = gestionBBDD.obtenerResultadoConsultaSQL(Queries.buscarLibro + "'" + titulo + "'");
		Libro libro = new Libro();
		try {
			// Procesar los resultados 
			while (resultados.next()) 
			{ 
				libro.setIsbn(resultados.getString("isbn")); 
				libro.setAutor(resultados.getString("autor")); 
				libro.setTitulo(resultados.getString("titulo")); 
			}
		} catch (SQLException e) {
			this.gestionarExcepcion(e);
		}
	    return libro;
	}
	
	/**
	 * Registro de un prestamo de la biblioteca y llamamos a la regla de negocio para revisar si el usuario puede solicitar el prestamo
	 * @param titulo Parámetro para registrar el prestamo.
	 * @param usuario Usuario que toma el prestamo
	 */
	public void prestarLibro(String isbn, String usuario) 
	{
		try 
		{
			//Llamamos a la regla de negocio para ver si el usuario puede solicitar un prestamo
			if (ReglasNegocio.puedeUsuarioSolicitarPrestamo(usuario))
			{	
				//Llamamos a la clase de gestión de la BBDD y le pasamos la consulta a ejecutar
				int filasAfectadas = gestionBBDD.ejecutarUpdateInsertSQL("INSERT INTO PRESTAMOS (isbn_libro, id_usuario, fecha_prestamo) VALUES (" + isbn + "," + usuario + "," + java.time.LocalDateTime.now()+")");
				if (filasAfectadas == 0)
					throw new Exception("No se pudo actualizar el libro.");		
			}
			else
			{
				throw new Exception("El usuario ha excedido el número máximo de prestamos.");		
			}
		} 
		catch (Exception e) 
		{
			this.gestionarExcepcion(e);
		}
	}
	
	/**
	 * Registro de una devolución de la biblioteca
	 * @param titulo Parámetro para registrar la devolución.
	 * @param usuario Usuario que devuelve el libro
	 */
	public void devolverLibro(String isbn, String usuario) 
	{
		try 
		{
			//Llamamos a la clase de gestión de la BBDD y le pasamos la consulta a ejecutar
			int filasAfectadas = gestionBBDD.ejecutarUpdateInsertSQL("DELETE FROM PRESTAMOS WHERE isbn = " + isbn + " and usuario = " + usuario + ")");
			if (filasAfectadas == 0)
				throw new Exception("No se pudo realizar la devolución del prestamo el libro.");		
		} 
		catch (Exception e) 
		{
			this.gestionarExcepcion(e);
		}
	}
	
	/**
	 * Búsqueda de un libro de la biblioteca
	 * @param autor Parámetro para la búsqueda del libro.
	 */
	public Libro buscarLibrosPorAutor(String autor) 
	{
		//Llamamos a la clase de gestión de la BBDD y le pasamos la consulta a ejecutar
		resultados = gestionBBDD.obtenerResultadoConsultaSQL(Queries.buscarLibro + "'" + autor + "'");
		Libro libro = new Libro();
		try 
		{
			// Procesar los resultados 
			while (resultados.next()) 
			{ 
				libro.setIsbn(resultados.getString("isbn")); 
				libro.setAutor(resultados.getString("autor")); 
				libro.setTitulo(resultados.getString("titulo")); 
			}
		} 
		catch (SQLException e) 
		{
			this.gestionarExcepcion(e);
		}
	    return libro;
	}
	
	/**
	 * Búsqueda de un libro de la biblioteca
	 * @param isbn Parámetro para la búsqueda del libro.
	 */
	public Libro buscarLibrosPorISBN(String isbn) 
	{
		//Llamamos a la clase de gestión de la BBDD y le pasamos la consulta a ejecutar
		resultados = gestionBBDD.obtenerResultadoConsultaSQL(Queries.buscarLibro + "'" + isbn + "'");
		Libro libro = new Libro();
		try 
		{
			// Procesar los resultados 
			while (resultados.next()) 
			{ 
				libro.setIsbn(resultados.getString("isbn")); 
				libro.setAutor(resultados.getString("autor")); 
				libro.setTitulo(resultados.getString("titulo")); 
			}
		} 
		catch (SQLException e) 
		{
			this.gestionarExcepcion(e);
		}
	    return libro;
	}
	
	/**
	 * Almacenamiento en sistema de trazabilidad la excepción producida
	 * @param e Excepcion a gestionar
	 */
	private void gestionarExcepcion(Exception e)
	{
		//Almacenamiento en sistema de trazabilidad la excepción producida
		//Funcionalidad no implementada para la actividad 3
	}
}
