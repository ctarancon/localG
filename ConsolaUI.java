/** 
 * Paquete que contiene las clase de presentación
 */
package BibliotecaVIU_Presentacion;
 
import java.util.ArrayList;
import java.util.Scanner;

import BibliotecaVIU_Negocio.Libro;

/**
 * Clase principal por la cual iniciamos la ejecución de la aplicación
 */
public class ConsolaUI {

	/**
	 * Método principal por el cual iniciamos la ejecución de la aplicación
	 */
	public static void main(String[] args) 
	{
		ConsolaUI.mostrarMenu();
	}
	
	/**
	 * Menú de la aplicación de nuestra bilioteca
	 */
	static void mostrarMenu () 
	{
		// Controlamos las posibles excepciones
		try
		{
			ConsolaUI.muestraMensaje("Bienvenido a la biblioteca VIU");
			Scanner sc = new Scanner(System.in);
	        String eleccion = "";
	        while (!eleccion.equals("0")) 
	        {
	        	ConsolaUI.muestraMensaje("\n 1. Registrar libro\n 2. Registrar prestamo\n 3. Registrar usuario\n 4. Ver libros disponibles\n 5. Ver libros prestados\n \n Escoge una opción:");
	            eleccion = sc.nextLine();
	            Controlador controlador = new Controlador();
	            if (eleccion.equals("1")) 
	            {
	                //LLamamos al controlador del catálogo y agregamos un libro
	            	Libro libro = new Libro(sc.nextLine(),sc.nextLine() ,sc.nextLine());
	            	controlador.agregarLibro(libro);
	            	ConsolaUI.muestraMensaje("Libro " + libro.getTitulo() + " agregado al catálogo.");
	            }
	            if (eleccion.equals("2")) 
	            {
	            	//LLamamos al controlador del catalago y registramos un prestamo
	            	controlador.registrarPrestamosDevoluciones(sc.nextLine(), sc.nextLine(), true);
	            	ConsolaUI.muestraMensaje("Libro prestado.");
	            	
	            }
	            if (eleccion.equals("3")) 
	            {
	            	//LLamamos al controlador de usuarios y registramos un user
	            	controlador.agregarUsuario(sc.nextLine());
	            	ConsolaUI.muestraMensaje("Usuario agregado");
	            }
	            if (eleccion.equals("4")) 
	            {
	            	//LLamamos al controlador del catálogo y mostramos libros disponibles
	            	ArrayList<String> libros = controlador.consultarLibrosCatalogoNoPrestados();
	            	for (int i=0; i < libros.size(); i++)
	            	{
	            		//Mostramos el título de todos los libros prestados
	            		ConsolaUI.muestraMensaje(libros.get(i));
	            	}
	            }  
	            if (eleccion.equals("5")) 
	            {
	            	//LLamamos al controlador del catálogo y mostramos libros no disponibles
	            	ArrayList<String> libros = controlador.consultarLibrosCatalogoPrestados();
	            	for (int i=0; i < libros.size(); i++)
	            	{
	            		//Mostramos el título de todos los libros disponibles
	            		ConsolaUI.muestraMensaje(libros.get(i));
	            	}
	            }
	        }
	        sc.close();
	        ConsolaUI.muestraMensaje("Adios y gracias por tu visita!");
		}
		catch (Exception e)
		{
			ConsolaUI.muestraMensaje("Excepción genérica detectada en tiempo de ejecución:" + e.getMessage());
		}
		finally
		{
			ConsolaUI.muestraMensaje("El programa se ejecutó y no se detectaron excepciones.");
		}
	}
	
	/**
	 * Método utilidad para mostrar los mensajes por pantalla
	 */
	static void muestraMensaje (String msg)
	{
		System.out.println(msg);
	}

}
