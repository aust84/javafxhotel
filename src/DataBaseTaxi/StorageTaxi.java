package DataBaseTaxi;


/**
 * 
 * @author Giovanni Citro
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
import GestioneTaxi.PrenotazioneTaxi;
import GestioneTaxi.Taxi;

import com.mysql.jdbc.Statement;

public class StorageTaxi {
	
	private DBConessione dbTaxi;
	private Statement stmnt;
	
	/**
	 * 
	 * @throws SQLException lanciata se si verifica un errore di accesso al database
	 * @throws FileNotFoundException lanciata se non viene trovato il file
	 * @throws IOException lanciata se si verifica un errore di lettura sul file
	 * @throws ClassNotFoundException lanciata se non viene trovata la classe
	  */
	public StorageTaxi() throws SQLException, ClassNotFoundException, FileNotFoundException, IOException{
		
		dbTaxi = new DBConessione();
		dbTaxi.openConnection();
		stmnt = (Statement) dbTaxi.getStatement();
	
	}

	
	
	/**
	 * chiude la connessione del database
	 * @throws SQLException lanciata se si verifica un errore di accesso al database
	 */
	public void closeConnection() throws SQLException{
		dbTaxi.closeConnection();	
	}
	
	
	
	
	/**
	    * 
	    * @return restituisce l'elenco dei taxi
	    * @throws SQLException lanciata se si verifica un errore di accesso al database
	    */

	public ObservableList<Taxi> getElencoTaxi() throws SQLException{
		ResultSet rs=stmnt.executeQuery("SELECT * FROM Taxi  ");
		ObservableList<Taxi> list = FXCollections.observableArrayList();
		while(rs.next()){
			Taxi t=new Taxi(rs.getString("Codice"));
			list.add(t);
		}
		rs.close();
		return list;
	}
	
	
	
	
	/**
	 * 
	 * @return restituisce l'elenco delle prenotazioni taxi
	 * @throws SQLException lanciata se si verifica un errore di accesso al database
	 */
	public ObservableList<PrenotazioneTaxi> getElencoPrenotazioni() throws SQLException{
		ResultSet rs=stmnt.executeQuery("SELECT * FROM prenotazioneTaxi  ");
		ObservableList<PrenotazioneTaxi> list=FXCollections.observableArrayList();
		while(rs.next()){
			PrenotazioneTaxi t=new PrenotazioneTaxi(rs.getString("Codice"),
					rs.getString("Taxi"),rs.getString("Data"),rs.getString("Ora"),rs.getString("Durata"));
			list.add(t);
		}
		rs.close();
		return list;
	}
	
	
	
	
	/**
	 * 
	 * @param prenotazione la prenotazione da inserire
	 * @throws SQLException lanciata se si verifica un errore di accesso al database
	 */
	public void inserisciPrenotazione(PrenotazioneTaxi prenotazione) throws SQLException {
		String query=("INSERT INTO prenotazioneTaxi(Codice,Taxi,Data,Ora,Durata)" +
				" values('"+prenotazione.getCodice()+"','"+prenotazione.getTaxi()+"','"+
				prenotazione.getData()+"','"+prenotazione.getOra()+"','"+prenotazione.getDurata()+"')");
		
		stmnt.execute(query);
	}
	
	
	
	/**
	 * 
	 * @param codice codice della prenotazione taxi
	 * @return restituisce la relativa prenotazione
	 * @throws SQLException lanciata se si verifica un errore di accesso al database
	 */
	public PrenotazioneTaxi getPrenotazione(String codice) throws SQLException {
		PrenotazioneTaxi p=null;

		ResultSet rs=stmnt.executeQuery("SELECT * FROM prenotazioneTaxi WHERE Codice='"+codice+"'");

		
		if(rs.first())
			p=new PrenotazioneTaxi(rs.getString("Codice"), rs.getString("Taxi"),rs.getString("Data"),rs.getString("Ora"),rs.getString("Durata"));
		rs.close();
		return p;
	}
	
	
	
	/**
	 * 
	 * @param codice codice del taxi da verificare
	 * @return riturna true se è disponibile false altrimenti
	 * @throws SQLException lanciata se si verifica un errore di accesso al database
	 */
	public boolean isPresenteTaxi(String codice) throws SQLException{
		
		PrenotazioneTaxi p=getPrenotazione(codice);
		if (p==null)
			return false;
		return true;
	}
	
	
	
	
	/**
	 * 
	 * @param codice codice della prenotazione da eliminare
	 * @throws SQLException lanciata se si verifica un errore di accesso al database
	 */
	public void rimuoviPrenotazione(String codice) throws SQLException{
		String query = "DELETE FROM prenotazioneTaxi WHERE prenotazioneTaxi.Codice='"+codice+"';";
		stmnt.executeUpdate(query);	
		}
	
	
	
	
	/**
	 * 
	 * @param prenotazione prenotazione da eliminare
	 * @throws SQLException lanciata se si verifica un errore di accesso al database
	 */
	public void modificaPrenotazione(PrenotazioneTaxi prenotazione) throws SQLException{
		stmnt.executeUpdate("UPDATE prenotazioneTaxi SET "+
				"Taxi='"+prenotazione.getTaxi()+"',"
				+"Data='"+prenotazione.getData()+"',"
				+"Ora='"+prenotazione.getOra()+"',"
				+"Durata='"+prenotazione.getDurata()+"'"
				+"WHERE Codice='"+prenotazione.getCodice()+"'");
			}
	
	
	
	
	
	
}