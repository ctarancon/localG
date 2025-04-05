/** 
 *  * Paquete que contiene las clases de negocio
 */
package BibliotecaVIU_Negocio;

/**
 * Clase que contiene las reglas de negocio
 */
public class ReglasNegocio {
	
	/**
	 * Regla de negocio que devuelve si el usuario puede tomar más prestamos
	 * @param usuario Usuario
	 * @return Valor binario que indica si está habilitado para solicitar otro prestamo
	 */
	public static final boolean puedeUsuarioSolicitarPrestamo(String usuario)
	{
		//Hacemos una consulta a la base de datos y vemos cuantos libros tiene el usuario en prestamo
		int librosEnPrestamo = 2; // Simulamos la respuesta a la consulta de la BBDD
		if (librosEnPrestamo > Constantes.cantidadMaximoLibrosPrestados)
			return false;
		else
			return true;
	}
	
	/**
	 * Regla de negocio que devuelve si el formato del isbn es el correcto
	 * @param isbn Isbn
	 * @return Valor binario que indica si el formato isbn es correcto
	 */
	public static final boolean formatoIsbnCorrecto(String isbn)
	{
		//Hacemos una consulta a la base de datos y vemos si el formato del isbn es correcto
		if (isbn.length() > 10)
			return false;
		else
			return true;
	}
}
