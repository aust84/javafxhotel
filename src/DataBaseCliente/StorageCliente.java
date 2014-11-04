package DataBaseCliente;

/**
 * 
 * @author Aniello Cozzolino
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
import GestioneClienti.Cliente;
import GestioneClienti.Prenotazione;

import com.mysql.jdbc.Statement;


public class StorageCliente {
	
	private DBConessione dbCliente;
	private Statement stmnt;
	
	/**
	 * 
	 * @throws SQLException lanciata se si verifica un errore di accesso al database
	 * @throws ClassNotFoundException lanciata se non viene trovata la classe
	 * @throws FileNotFoundException lanciata se non viene trovato il file
	 * @throws IOException lanciata se si verifica un errore di lettura su file
	 */
	
	public StorageCliente() throws SQLException, ClassNotFoundException, FileNotFoundException, IOException{
		
		dbCliente = new DBConessione();
		dbCliente.openConnection();
		stmnt = (Statement) dbCliente.getStatement();
	
	}
	
	
	/**
	 * chiude la connessione con il database
	 * @throws SQLException lanciata se si verifica un errore di accesso al database
	 */
	
	public void closeConnection() throws SQLException{
		
	dbCliente.closeConnection();
	}
		
	
	/**
	 * inserisce un cliente nel database
	 * 
	 * @param cliente cliente da inserire
	 * @throws SQLException lanciata se si verifica un errore di accesso al database
	 */
	
	public void inserisciCliente(Cliente cliente) throws SQLException {
		String query=("INSERT INTO cliente(CodiceFiscale,Nome,Cognome,Tipologia)" +
				" values('"+cliente.getCodFiscale()+"','"+cliente.getNome()+"','"+
				cliente.getCognome()+"','"+cliente.getTipologia()+"')");
		stmnt.execute(query);
	}
	
	
	
	
	/**
	 * inserisce una prenotazione nel database
	 * 
	 * @param prenotazione prenotazione da inserire
	 * @param c cliente da inserire
	 * @throws SQLException lanciata se si verifica un errore di accesso al database
	 */
	public void inserisciPrenotazione(Prenotazione prenotazione,Cliente c) throws SQLException {
		String query=("INSERT INTO prenotazione(Codice,Data,NumPersone,Cliente,Camera,Durata)" +
				" values('"+prenotazione.getCodice()+"','"+prenotazione.getData()+"','"+
				prenotazione.getNumeroPersone()+"','"+prenotazione.getCliente()+"','"+prenotazione.getCamera()+"','"+prenotazione.getDurata()+"')");
		/*if(c.getCodFiscale()!=null)
			inserisciCliente(c);*/
		stmnt.execute(query);
	}

	
	
	
	/**
	 * modifica di una prenotazione nel database
	 * @param prenotazione oggetto di tipo Prenotazione
	 * @throws SQLException lanciata se si verifica un errore di accesso al database
	 */

	public void modificaPrenotazione(Prenotazione prenotazione) throws SQLException{
			stmnt.executeUpdate("UPDATE prenotazione SET "+
					"Data='"+prenotazione.getData()+"',"
					+"NumPersone='"+prenotazione.getNumeroPersone()+"',"
					+"Camera='"+prenotazione.getCamera()+"',"
					+"Durata='"+prenotazione.getDurata()+"'"
					+"WHERE Codice='"+prenotazione.getCodice()+"'");
				}
	
	
	
	
	/**
	 * modifica dei dati di un cliente nel database
	 * @param c cliente da modificare
	 * @throws SQLException lanciata se si verifia un errore di accesso al database
	 */
	public void modificaCliente(Cliente c) throws SQLException{

		stmnt.executeUpdate("UPDATE cliente SET "+
			"Nome='"+c.getNome()+"',"
			+"Cognome='"+c.getCognome()+"',"
			+"Tipologia='"+c.getTipologia()+"'"
			+"WHERE CodiceFiscale='"+c.getCodFiscale()+"'");
	}
	
	
	
	
	/**
	 * rimuove un cliente dal database
	 * @param codice codice del cliente da rimuovere
	 * @throws SQLException lanciata se si verific aun errore di accesso al database
	 */
	public void rimuoviCliente(String codice) throws SQLException{
		String query = "DELETE FROM cliente WHERE cliente.CodiceFiscale='"+codice+"';";
		stmnt.executeUpdate(query);	
		}
	
	
	
	
	/**
	 * rimuove una prenotazione dal database
	 * @param codice codice della prenotazione da eliminare
	 * @throws SQLException lanciata se si verifica un errore di accesso al database
	 */
	public void rimuoviPrenotazione(String codice) throws SQLException{
		String query = "DELETE FROM prenotazione WHERE prenotazione.Codice='"+codice+"';";
		stmnt.executeUpdate(query);	
		}
	
	
		
	
	
	
	/**
	 * 
	 * @return ritorna elenco dei clienti
	 * @throws SQLException lanciata se si verifica un errore di accesso al database
	 */
	public ObservableList<Cliente> getElencoClienti() throws SQLException{
		ResultSet rs=stmnt.executeQuery("SELECT * FROM cliente  ");
		ObservableList<Cliente> list=FXCollections.observableArrayList();
		while(rs.next()){
			Cliente c=new Cliente(rs.getString("CodiceFiscale"), rs.getString("Nome"),rs.getString("Cognome"),
					rs.getString("Tipologia"));
			list.add(c);
		}
		rs.close();
		return list;
	}
	
	
		
	
	
	/**
	 * 
	 * @return ritorna elenco delle prenotazioni
	 * @throws SQLException lanciata se si verifica un errore di accesso al database
	 */
	public ObservableList<Prenotazione> getElencoPrenotazioni() throws SQLException{
		ResultSet rs=stmnt.executeQuery("SELECT * FROM Prenotazione  ");
		ObservableList<Prenotazione> list=FXCollections.observableArrayList();
		while(rs.next()){
			Prenotazione p=new Prenotazione(rs.getString("Codice"), rs.getString("Data"),rs.getString("NumPersone"),
					rs.getString("Cliente"),rs.getString("Camera"),rs.getString("Durata"));
			list.add(p);
		}
		rs.close();
		return list;
	}
	
	
	
	
	
	/**
	 * 
	 * @param cf codice fiscale del cliente
	 * @return ritorna il relativo cliente
	 * @throws SQLException lanciata se si verifica un errore di accesso al database
	 */
	public Cliente getCliente(String cf) throws SQLException {
		Cliente c=null;
		
		ResultSet rs=stmnt.executeQuery("SELECT * FROM cliente WHERE codicefiscale='"+cf+"'");

		
		if(rs.first())
			c=new Cliente(rs.getString("CodiceFiscale"), rs.getString("Nome"),rs.getString("Cognome"),
					rs.getString("Tipologia"));
		rs.close();
		return c;
	}
	
	

	

	/**
	 * 
	 * @param codice codice della prenotazione
	 * @return ritorna la relativa prenotazione
	 * @throws SQLException lanciata se si verifica un errore di accesso al database
	 */
	public Prenotazione getPrenotazione(String codice) throws SQLException {
		Prenotazione p=null;

		ResultSet rs=stmnt.executeQuery("SELECT * FROM prenotazione WHERE Codice='"+codice+"'");

		
		if(rs.first())
			p=new Prenotazione(rs.getString("Codice"), rs.getString("Data"),rs.getString("NumPersone"),rs.getString("Cliente"),rs.getString("Camera"),rs.getString("Durata"));
		rs.close();
		return p;
	}
	
	
	
	
	/**
	 * 
	 * @param codiceFiscale codice fiscale del cliente da cercare
	 * @return ritorna true se è presente false altrimenti
	 * @throws SQLException lanciata se si verifica un errore di accesso al database
	 */
	public boolean isPresenteCliente(String codiceFiscale) throws SQLException{
		
		Cliente c=getCliente(codiceFiscale);
		if (c==null)
			return false;
		return true;
	}
	
	
	
	
	/**
	 * 
	 * @param codice codice della prenotazione
	 * @return ritorna true se è presente e false altrimenti
	 * @throws SQLException lanciata se si verifica un errore di accesso al database
	 */
	public boolean isPresentePrenotazione(String codice) throws SQLException{
		
		Prenotazione p=getPrenotazione(codice);
		if (p==null)
			return false;
		return true;
	}

	

}
