package DataBaseConnection;

/**
 * @author Hermann Antonio Aloisio
 *
 */
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;



public class DBConessione {
	
	
	private Connection conn;
	private Statement stmt;
	
	
	/**
	 * 
	 * @throws SQLException lanciata se si verifica un errore di accesso al database
	 * @throws ClassNotFoundException lanciata se non viene trovata la classe
	 */
	
	public DBConessione() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");	
	}
	
	
	
	/**
	 * serve per aprire la connessione
	 * @throws SQLException lanciata se si verifica un errore di accesso al database
	 * @throws FileNotFoundException lanciata se non viene trovato il file
	 * @throws IOException lanciata se si verifica un errore di lettura sul file
	 * @throws ClassNotFoundException lanciata se non viene trovata la classe
	 */
	
	public void openConnection()throws SQLException, FileNotFoundException, IOException, ClassNotFoundException{
		
		String dbname = "myhotel";
		String usr="issviluppo";
		String psw="loris";

		String url="jdbc:mysql://localhost:3306/"+dbname;
		conn=DriverManager.getConnection(url,usr,psw);
		stmt=conn.createStatement();
	}
	
	
	
	/**
	 *  
	 * @return lo statement per effettuare le query
	 * @throws SQLException lanciata se si verifica un errore di accesso al database
	 */
	
	public Statement getStatement () throws SQLException{
		stmt = conn.createStatement();
		return stmt;
	}
	
	
	
	/**
	 * chiude la connessione del database
	 * @throws SQLException lanciata se si verifica un errore di accesso al database
	 */

	public void closeConnection() throws SQLException{
		stmt.close();
		conn.close();
	}	
	
	
	
}
