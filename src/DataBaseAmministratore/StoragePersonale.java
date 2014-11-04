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
import GestioneAmministrazione.Personale;

import com.mysql.jdbc.Statement;

public class StoragePersonale {

	private DBConessione dbPersonale;
	private Statement stmnt;
	
	/**
	 * 
	 * @throws SQLException lanciata se si verifica un errore di accesso al database
	 * @throws ClassNotFoundException lanciata se non viene trovata la classe
	 * @throws FileNotFoundException lanciata se non viene trovato il file
	 * @throws IOException lanciata se si verifica un errore di lettura su file
	 */
	
	public StoragePersonale() throws SQLException, ClassNotFoundException, FileNotFoundException, IOException{
		
		dbPersonale = new DBConessione();
		dbPersonale.openConnection();
		stmnt = (Statement) dbPersonale.getStatement();
	
	}
	
	
	/**
	 * chiude la connessione con il database
	 * @throws SQLException lanciata se si verifica un errore di accesso al database
	 */
	
	public void closeConnection() throws SQLException{
		
	dbPersonale.closeConnection();
	}
		
	
	/**
	 * inserisce il personale nel database
	 * 
	 * @param personale personale da inserire
	 * @throws SQLException lanciata se si verifica un errore di accesso al database
	 */
	
	public void inserisciPersonale(Personale personale) throws SQLException {
		String query=("INSERT INTO  personale(Matricola,Nome,Cognome,UserId,Password)" +
				" values('"+personale.getMatricola()+"','"+personale.getNome()+"','"+
				personale.getCognome()+"','"+personale.getUserId()+"','"+personale.getPassword()+"')");
		stmnt.execute(query);
	}
	

	/**
	 * modifica dei dati di un personale nel database
	 * @param p personale da modificare
	 * @throws SQLException lanciata se si verifia un errore di accesso al database
	 */
	public void modificaPersonale(Personale p) throws SQLException{

		stmnt.executeUpdate("UPDATE  personale SET "+
			"Nome='"+p.getNome()+"',"
			+"Cognome='"+p.getCognome()+"',"
			+"Password='"+p.getPassword()+"',"
			+"UserId='"+p.getUserId()+"'"
			+"WHERE Matricola='"+p.getMatricola()+"'");
	}
	
	
	
	
	/**
	 * rimuove un personale dal database
	 * @param matricola matricola del personale da rimuovere
	 * @throws SQLException lanciata se si verific aun errore di accesso al database
	 */
	public void rimuoviPersonale(String matricola) throws SQLException{
		String query = "DELETE FROM personale WHERE personale.Matricola='"+matricola+"';";
		stmnt.executeUpdate(query);	
		}
	

	/**
	 * 
	 * @return ritorna elenco del personale
	 * @throws SQLException lanciata se si verifica un errore di accesso al database
	 */
	public ObservableList<Personale> getElencoPersonale() throws SQLException{
		ResultSet rs=stmnt.executeQuery("SELECT * FROM personale  ");
		ObservableList<Personale> list=FXCollections.observableArrayList();
		while(rs.next()){
			Personale p=new Personale(rs.getString("Matricola"), rs.getString("Nome"),rs.getString("Cognome"),rs.getString("UserId"),rs.getString("Password"));
			list.add(p);
		}
		rs.close();
		return list;
	}
	
	
	
	
	/**
	 * 
	 * @param mt matricola del personale
	 * @return ritorna il relativo personale
	 * @throws SQLException lanciata se si verifica un errore di accesso al database
	 */
	public Personale getPersonale(String mt) throws SQLException {
		Personale a=null;
		
		ResultSet rs=stmnt.executeQuery("SELECT * FROM personale WHERE Matricola='"+mt+"'");

		
		if(rs.first())
			a=new Personale(rs.getString("Matricola"), rs.getString("Nome"),rs.getString("Cognome"),
					rs.getString("UserId"),rs.getString("Password"));
		rs.close();
		return a;
	}
	
		
	/**
	 * 
	 * @param Matricola matricola del personale da cercare
	 * @return ritorna true se è presente false altrimenti
	 * @throws SQLException lanciata se si verifica un errore di accesso al database
	 */
	public boolean isPresentePersonale(String Matricola) throws SQLException{
		
		Personale a=getPersonale(Matricola);
		if (a==null)
			return false;
		return true;
	}
	
	/**
	 * 
	 * @param Userid username del personale da verificare
	 * @param Password password del personale da verificare
	 * @return ritorna true se è presente false altrimenti
	 * @throws SQLException lanciata se si verifica un errore di accesso al database
	 */
	public boolean isCorrect(String Userid,String Password) throws SQLException{
	ResultSet rs = stmnt.executeQuery("SELECT UserId,Password FROM personale WHERE Userid='"+Userid+"' and Password='"+Password+"'");
		
		if(rs.next()){
			return true;
		}
		return false;
	}
}
