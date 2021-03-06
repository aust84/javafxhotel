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
	
	private SimpleBooleanProperty disponibilitÓ;
	
	
	/**
	 * 
	 * @param cod codice del taxi
	 */
	public Taxi(String cod){
		
		this.codice= new SimpleStringProperty(cod);
		disponibilitÓ= new SimpleBooleanProperty(true);
	
	}
	
	/**
	 * setta la disponibilita a false
	 */
	public void OccupaTaxi(){
		
		disponibilitÓ.set(false);
	
	}
	
	/**
	 * setta la disonibilita a true
	 */
	public void LiberaTaxi(){
		
		disponibilitÓ.set(true);
		
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
	 * @return la disponibilitÓ del taxi
	 */
	
	public boolean isDisponibile(){
		
		return disponibilitÓ.getValue();
	
	}

}
