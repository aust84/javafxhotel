package GestioneTaxi;

import javafx.beans.property.SimpleStringProperty;

/**
 * 
 * @author Michele D'Avino
 *
 */

public class PrenotazioneTaxi {

	private SimpleStringProperty codice;
	private SimpleStringProperty taxi;
	private SimpleStringProperty data;
	private SimpleStringProperty ora;
	private SimpleStringProperty durata;
	
	/**
	 * 
	 * @param cod codice della prenotazione
	 * @param t codice del taxi
	 * @param dataI data della prenotazione
	 * @param ora ora della prenotazione
	 * @param durata durata della prenotazione
	 */
	
	public PrenotazioneTaxi(String cod,String t,String dataI,String ora,String durata){
		
		this.codice = new SimpleStringProperty(cod);
		this.taxi = new SimpleStringProperty(t);
		this.data = new SimpleStringProperty(dataI);
		this.ora = new SimpleStringProperty(ora);
		this.durata = new SimpleStringProperty(durata);				
	}
	
	
	/**
	 * 
	 * @return il codice della prenotazione
	 */
	
	public String getCodice(){
		
		return codice.getValue();
	
	}
	
	
	/**
	 * 
	 * @return la data della prenotazione
	 */
	
	public String getData(){
		
		return data.getValue();
	
	}


	
	/**
	 * 
	 * @return l'ora della prenotazione
	 */
	
	public String getOra(){
	
	return ora.getValue();
	
	}
	
	
	
	/**
	 * 
	 * @return il codice del taxi
	 */
	
	public String getTaxi(){
		
		return taxi.getValue();
	
	}
	
	
	/**
	 * 
	 * @return la durata della prenotazione
	 */
	
	public String getDurata(){
		
		return durata.getValue();
	
	}
	
	
	/**
	 * 
	 * @param temp setta il codice della prenotazione
	 */
	
	public void setCodice(String temp){
		
		codice.set(temp);
	
	}
	
	
	/**
	 * 
	 * @param temp setta la data della prenotazione
	 */
	
	public void setData(String temp){
		
		data.set(temp);
	
	}
	
	
	/**
	 * 
	 * @param temp setta l'ora della prenotazione
	 */
	
	public void setOra(String temp){
		
		ora.set(temp);
	
	}
	
	
	/**
	 * 
	 * @param temp setta il codice del taxi
	 */
	
	public void setTaxi(String temp){
		
		taxi.set(temp);
	
	}
	
	
	/**
	 * 
	 * @param temp setta la durata della prenotazione
	 */
	
	public void setDurata(String temp){
		
		durata.set(temp);
	
	}
	


}
