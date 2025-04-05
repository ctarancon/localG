/**
 * Paquete que contiene las clases de negocio
 */
package BibliotecaVIU_Negocio;

/**
 * Clase DTO para manejar un libro
 */
public class Libro {
	
	private String titulo; 
	private String autor; 
	private String isbn; 
	
	// Constructor 
	public Libro (String titulo, String autor, String isbn) 
	{ 
		this.titulo = titulo; 
		this.autor = autor; 
		this.isbn = isbn; 
	} 
	
	//Constructor
	public Libro()
	{}
	
	// Métodos getter para acceder a las variables 
	public String getTitulo() 
	{ 
		return titulo; 
	} 
	public String getAutor() 
	{ 
		return autor; 
	} 
	public String getIsbn() 
	{ 
		return isbn; 
	} 
	
	// Métodos setter para acceder a las variables 
	public void setTitulo(String titulo) 
	{ 
		this.titulo = titulo; 
	} 
	public void setAutor(String autor) 
	{ 
		this.autor = autor; 
	} 
	public void setIsbn(String isbn) 
	{ 
		this.isbn = isbn;
	}
}
