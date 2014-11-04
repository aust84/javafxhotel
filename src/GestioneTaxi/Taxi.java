package GestioneTaxi;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * 
 * @author Michele D'Avino
 *
 */

public class Taxi {
	
	private SimpleStringProperty codice;
	
	private SimpleBooleanProperty disponibilità;
	
	
	/**
	 * 
	 * @param cod codice del taxi
	 */
	public Taxi(String cod){
		
		this.codice= new SimpleStringProperty(cod);
		disponibilità= new SimpleBooleanProperty(true);
	
	}
	
	/**
	 * setta la disponibilita a false
	 */
	public void OccupaTaxi(){
		
		disponibilità.set(false);
	
	}
	
	/**
	 * setta la disonibilita a true
	 */
	public void LiberaTaxi(){
		
		disponibilità.set(true);
		
	}
	
	
	
	/**
	 * 
	 * @return il codice del taxi
	 */
	public String getCodice(){
		
		return codice.getValue();
		
	}
	
	
	/**
	 * 
	 * @return la disponibilità del taxi
	 */
	
	public boolean isDisponibile(){
		
		return disponibilità.getValue();
	
	}

}
