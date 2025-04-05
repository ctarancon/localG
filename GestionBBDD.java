/** 
 * Paquete que contiene las clases que se comunican con la BBDD
 */
package BibliotecaVIU_Datos;

import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.SQLException;
import java.sql.ResultSet; 
import java.sql.Statement;

/**
 * Clase para gestionar el acceso a la BBDD
 */
public class GestionBBDD {
	
	/**
	 * Variables privadas de la clase
	 */
	private String url = "jdbc:postgresql://localhost:7070/BibliotecaVIU"; 
	private String user = "admin"; 
	private String pass = "localpass"; 
	private Connection conexion;
	private Statement declaracion = null; 
	
	/**
	 * Abrimos conexión a la BBDD
	 * @throws SQLException
	 */
	private void abrirConexion() throws SQLException 
	{
		setConexion(DriverManager.getConnection(url,user,pass)); 
	}
	

	/**
	 * Método setter conexión privado
	 * @param conexion Conexión
	 */
	private void setConexion(Connection conexion) 
	{
		this.conexion = conexion;
	}

	/**
	 * Cierre de conexión de BBDD
	 * @param conexion Conexión a cerrar
	 */
	private void cerrarConexion(Connection conexion) 
	{
		try 
		{
			conexion.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}


	/**
	 * Obtiene los resultados de una sentencia SQL de consulta en un resultset
	 * @param consultaSQL Consulta SQL
	 * @return Resultado consulta
	 */
	public ResultSet obtenerResultadoConsultaSQL(String consultaSQL) 
	{
		ResultSet resultados = null;
		try 
		{
			this.abrirConexion();
			// Crear una declaración 
			declaracion = conexion.createStatement(); 
			// Ejecutar una consulta SQL  
			resultados = declaracion.executeQuery(consultaSQL);
			declaracion.close();
			this.cerrarConexion(conexion);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return resultados;
	}	
	
	/**
	 * Obtiene los resultados de una sentencia SQL de consulta en un resultset
	 * @param consultaSQL Consulta SQL
	 * @return Resultado consulta
	 */
	public int ejecutarUpdateInsertSQL(String consultaSQL) 
	{
		int filasActualizadas = 0;
		try 
		{
			this.abrirConexion();
			// Crear una declaración 
			declaracion = conexion.createStatement(); 
			// Ejecutar una consulta SQL  
			filasActualizadas = declaracion.executeUpdate(consultaSQL);
			declaracion.close();
			this.cerrarConexion(conexion);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return filasActualizadas;
	}	
    
}
