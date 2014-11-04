package DataBaseBilancio;

/**
 * @author Hermann Antonio Aloisio
 *
 */
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import DataBaseConnection.DBConessione;
import GestioneAmministrazione.Bilancio;

import com.mysql.jdbc.Statement;

public class StorageBilancio {

	private DBConessione dbBilancio;
	private Statement stmnt;
	
	/**
	 * 
	 * @throws SQLException lanciata se si verifica un errore di accesso al database
	 * @throws ClassNotFoundException lanciata se non viene trovata la classe
	 * @throws FileNotFoundException lanciata se non viene trovato il file
	 * @throws IOException lanciata se si verifica un errore di lettura su file
	 */
	
	public StorageBilancio() throws SQLException, ClassNotFoundException, FileNotFoundException, IOException{
		
		dbBilancio = new DBConessione();
		dbBilancio.openConnection();
		stmnt = (Statement) dbBilancio.getStatement();
	
	}
	
	
	/**
	 * chiude la connessione con il database
	 * @throws SQLException lanciata se si verifica un errore di accesso al database
	 */
	
	public void closeConnection() throws SQLException{
		
	dbBilancio.closeConnection();
	}
		
	
	/**
	 * inserisce un nuovo bilancio nel Database
	 * 
	 * @param b bilancio da inserire
	 * @throws SQLException lanciata se si verifica un errore di accesso al database
	 */
	
	public void inserisciBilancio(Bilancio b) throws SQLException {
		String query=("INSERT INTO bilancio(Codice,Tipo,Valore)" +
				" values('"+b.getCodice()+"','"+b.getTipo()+"','"+
				b.getValore()+"')");
		stmnt.execute(query);
	}
	

	/**
	 * 
	 * @return ritorna elenco del bialncio
	 * @throws SQLException lanciata se si verifica un errore di accesso al database
	 */
	public ObservableList<Bilancio> getElencoBilancio() throws SQLException{
		ResultSet rs=stmnt.executeQuery("SELECT * FROM bilancio  ");
		ObservableList<Bilancio> list = FXCollections.observableArrayList();
		while(rs.next()){
			Bilancio b=new Bilancio(rs.getString("Codice"), rs.getString("Tipo"),rs.getDouble("Valore"));
			list.add(b);
		}
		rs.close();
		return list;
	}
	
	
	
	
	/**
	 * 
	 * @param cd codice dell bilancio
	 * @return ritorna il relativo bilancio
	 * @throws SQLException lanciata se si verifica un errore di accesso al database
	 */
	public Bilancio getBilancio(String cd) throws SQLException {
		Bilancio b=null;
		
		ResultSet rs=stmnt.executeQuery("SELECT * FROM bilancio WHERE Codice='"+cd+"'");

		
		if(rs.first())
			b=new Bilancio(rs.getString("Codice"), rs.getString("Tipo"),rs.getDouble("Valore"));
		rs.close();
		return b;
	}
	
	
}
