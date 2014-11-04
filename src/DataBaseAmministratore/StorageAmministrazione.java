package DataBaseAmministratore;

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
import GestioneAmministrazione.Amministratore;

import com.mysql.jdbc.Statement;

public class StorageAmministrazione {

	private DBConessione dbAmministratore;
	private Statement stmnt;
	
	/**
	 * 
	 * @throws SQLException lanciata se si verifica un errore di accesso al database
	 * @throws ClassNotFoundException lanciata se non viene trovata la classe
	 * @throws FileNotFoundException lanciata se non viene trovato il file
	 * @throws IOException lanciata se si verifica un errore di lettura su file
	 */
	
	public StorageAmministrazione() throws SQLException, ClassNotFoundException, FileNotFoundException, IOException{
		
		dbAmministratore = new DBConessione();
		dbAmministratore.openConnection();
		stmnt = (Statement) dbAmministratore.getStatement();
	
	}
	
	
	/**
	 * chiude la connessione con il database
	 * @throws SQLException lanciata se si verifica un errore di accesso al database
	 */
	
	public void closeConnection() throws SQLException{
		
	dbAmministratore.closeConnection();
	}
		
	
	/**
	 * inserisce un cliente nel database
	 * 
	 * @param amm amministratore da inserire
	 * @throws SQLException lanciata se si verifica un errore di accesso al database
	 */
	
	public void inserisciAmministraotre(Amministratore amm) throws SQLException {
		String query=("INSERT INTO amministratore(Matricola,Nome,Cognome,UserId,Password)" +
				" values('"+amm.getMatricola()+"','"+amm.getNome()+"','"+
				amm.getCognome()+"','"+amm.getUserId()+"','"+amm.getPassword()+"')");
		stmnt.execute(query);
	}
	

	/**
	 * modifica dei dati di un amministratore nel database
	 * @param a amministratore da modificare
	 * @throws SQLException lanciata se si verifia un errore di accesso al database
	 */
	public void modificaAmministraotre(Amministratore a) throws SQLException{

		stmnt.executeUpdate("UPDATE amministratore SET "+
			"Nome='"+a.getNome()+"',"
			+"Cognome='"+a.getCognome()+"',"
			+"Password='"+a.getPassword()+"',"
			+"UserId='"+a.getUserId()+"'"
			+"WHERE matricola='"+a.getMatricola()+"'");
	}
	
	
	
	
	/**
	 * rimuove un Amministratore dal database
	 * @param matricola matricola dell' amministratore da rimuovere
	 * @throws SQLException lanciata se si verific aun errore di accesso al database
	 */
	public void rimuoviAmministratore(String matricola) throws SQLException{
		String query = "DELETE FROM amministratore WHERE amministratore.Matricola='"+matricola+"';";
		stmnt.executeUpdate(query);	
		}
	

	/**
	 * 
	 * @return ritorna elenco degli amministratori
	 * @throws SQLException lanciata se si verifica un errore di accesso al database
	 */
	public ObservableList<Amministratore> getElencoAmministratore() throws SQLException{
		ResultSet rs=stmnt.executeQuery("SELECT * FROM amministratore  ");
		ObservableList<Amministratore> list=FXCollections.observableArrayList();
		while(rs.next()){
			Amministratore a=new Amministratore(rs.getString("Matricola"),rs.getString("Nome"),rs.getString("Cognome"),
					rs.getString("UserId"),rs.getString("Password"));
			list.add(a);
		}
		rs.close();
		return list;
	}
	
	
	
	
	/**
	 * 
	 * @param mt matricola dell' amministratore
	 * @return ritorna il relativo amministratore
	 * @throws SQLException lanciata se si verifica un errore di accesso al database
	 */
	public Amministratore getAmministratore(String mt) throws SQLException {
		Amministratore a=null;
		
		ResultSet rs=stmnt.executeQuery("SELECT * FROM amministratore WHERE Matricola='"+mt+"'");

		
		if(rs.first())
			a=new Amministratore(rs.getString("Matricola"), rs.getString("Nome"),rs.getString("Cognome"),
					rs.getString("UserId"),rs.getString("Password"));
		rs.close();
		return a;
	}
	
		
	/**
	 * 
	 * @param Matricola matricola del relativo amministratore da cercare
	 * @return ritorna true se è presente false altrimenti
	 * @throws SQLException lanciata se si verifica un errore di accesso al database
	 */
	public boolean isPresenteAmmministratore(String Matricola) throws SQLException{
		
		Amministratore a=getAmministratore(Matricola);
		if (a==null)
			return false;
		return true;
	}
	
	/**
	 * 
	 * @param Userid username da verificare se esiste all' interno del database
	 * @param Password password da verificare se esiste all' interno del database
	 * @return ritorna true se è presente false altrimenti
	 * @throws SQLException lanciata se si verifica un errore di accesso al database
	 */
	
	public boolean isCorrect(String Userid,String Password) throws SQLException{
		ResultSet rs = stmnt.executeQuery("SELECT UserId,Password FROM amministratore WHERE Userid='"+Userid+"' and Password='"+Password+"'");
		
		if(rs.next()){
			return true;
		}
		return false;
	}
}
