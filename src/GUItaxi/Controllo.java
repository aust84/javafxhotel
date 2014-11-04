package GUItaxi;

/**
 * 
 * @author Loris D'Avanzo
 *
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import DataBaseTaxi.StorageTaxi;
import GestioneTaxi.PrenotazioneTaxi;
import GestioneTaxi.Taxi;

public class Controllo {

	
	
	/**
	 * 
	 * @param g giorno
	 * @param m mese 
	 * @param a anno
	 * @return ritorna true se la data è giusta false altrimenti
	 * @throws FileNotFoundException lanciata se non viene trovato il file
	 * @throws SQLException lanciata se si verifica un errore di accesso al database
	 * @throws ClassNotFoundException lanciata se non viene trovata la classe
	 * @throws IOException lanciata se vi è un errore di lettura su file
	 */
	
	public static boolean controllaData(int g,int m,int a) throws FileNotFoundException, SQLException, ClassNotFoundException, IOException{
		
		
		StorageTaxi sc=new StorageTaxi();
		ObservableList<PrenotazioneTaxi> p=FXCollections.observableArrayList();
		ObservableList<Taxi> t=sc.getElencoTaxi();
		boolean controllo=true;
		for(int j=0;j<t.size();j++){
			for(int i=0;i<p.size();i++){
			
			PrenotazioneTaxi temp=p.get(i);
			if(t.get(j).getCodice().equalsIgnoreCase(temp.getTaxi())){
			String data=temp.getData();
			String l=data.substring(0, 2);
			int gg=Integer.parseInt(l);
			l=data.substring(3,5);
			int mm=Integer.parseInt(l);
			l=data.substring(6);
			int aa=Integer.parseInt(l);
			if((gg==g)&(mm==m)&(aa==a))
		
			controllo=false;
			
						
			}
			}
			
		}
		return controllo;
	}
	
	
	/**
	 * 
	 * @param g giorno
	 * @param m mese
	 * @param a anno
	 * @param taxi codice del taxi
	 * @return un ArrayList contente la lista dei taxi occupati
	 * @throws FileNotFoundException lanciata se non viene trovato il file
	 * @throws SQLException lanciata se si verifica un errore di accesso al database
	 * @throws ClassNotFoundException lanciata se non viene trovata la classe
	 * @throws IOException lanciata se vi è un errore di lettura su file
	 */
	
	public static ObservableList<PrenotazioneTaxi> getTaxiOccupati(int g,int m,int a,String taxi) throws FileNotFoundException, SQLException, ClassNotFoundException, IOException{
		
		
		StorageTaxi sc=new StorageTaxi();
		ObservableList<PrenotazioneTaxi> p=sc.getElencoPrenotazioni();
		ObservableList<PrenotazioneTaxi> occupati=FXCollections.observableArrayList();
		
		
			for(int i=0;i<p.size();i++){
			
			PrenotazioneTaxi temp=p.get(i);
			if(taxi.equalsIgnoreCase(temp.getTaxi())){
			String data=temp.getData();
			String l=data.substring(0, 2);
			int gg=Integer.parseInt(l);
			l=data.substring(3,5);
			int mm=Integer.parseInt(l);
			l=data.substring(6);
			int aa=Integer.parseInt(l);
			if((gg==g)&(mm==m)&(aa==a)){
				occupati.add(temp);
		
			}
			
						
			}
			}
			
		
		return occupati;
	}

	
	
	/**
	 * 
	 * @param codice codice del taxi
	 * @return controlla la disponibilità del taxi
	 * @throws SQLException lanciata se si verific a un errore di accesso al database
	 * @throws ClassNotFoundException lanciata se non viene trovata la classe
	 * @throws IOException lanciata se vi è un errore di lettura su file
	 */
	
	public static boolean controllaTaxi(String codice) throws  SQLException, ClassNotFoundException, IOException{
		StorageTaxi sc=new StorageTaxi();
		ObservableList<Taxi> t=sc.getElencoTaxi();
		boolean control=false;
		for(int i=0;i<t.size();i++){
			if(t.get(i).getCodice().equalsIgnoreCase(codice))
				control=true;
		}
		return control;
	}

}
