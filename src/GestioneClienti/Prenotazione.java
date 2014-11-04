package GestioneClienti;

import javafx.beans.property.SimpleStringProperty;

/**
 * 
 * @author Giovanni Citro
 *
 */

public class Prenotazione {
	
	private SimpleStringProperty codice;
	
	private SimpleStringProperty data;
	
	private SimpleStringProperty persone;
	
	private SimpleStringProperty camera;
	
	private SimpleStringProperty cliente;
	
	private SimpleStringProperty durata;
	
	/**
	 * 
	 * @param cod codice della prenotazione
	 * @param data data della prenotazione
	 * @param num numero di persone
	 * @param cliente nome e cognome del cliente
	 * @param camera camera della prenotazione
	 * @param durata durata pernottamento
	 */
	
	public Prenotazione(String cod,String data,String num,String cliente,String camera,String durata){
		
		this.codice = new SimpleStringProperty(cod);
		
		this.data = new SimpleStringProperty(data);
		
		this.persone = new SimpleStringProperty(num);
		
		this.cliente = new SimpleStringProperty(cliente);
		
		this.camera = new SimpleStringProperty(camera);
		
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
	 * @return il numero delle persone
	 */
	
	public String getNumeroPersone(){
		
		return persone.getValue();
	
	}
	
	
	/**
	 * 
	 * @return il codice fiscale del cliente
	 */
	
	public String getCliente(){
		
		return cliente.getValue();
	
	}
	
	
	
	/**
	 * 
	 * @return numero della camera
	 */
	
	public String getCamera(){
		
		return camera.getValue();
	
	}
	
	
	
	/**
	 * 
	 * @return durata del pernottamento
	 */
	
	public String getDurata(){
		
		return durata.getValue();
	
	}
	
	
	/**
	 * 
	 * @param c il codice da settare
	 */
	
	public void setCodice(String c){
		
		codice.setValue(c);
	
	}
	
	
	
	/**
	 * 
	 * @param d la durata da settare
	 */
	
	public void setData(String d){
		
		data.setValue(d);
	
	}
	
	
	/**
	 * 
	 * @param num numero di persone da settare
	 */
	
	public void setNumeroPersone(String num){
		
		persone.setValue(num);
	
	}
	
	
	
	
	/**
	 * 
	 * @param c il nome e il cognome da settare 
	 */
	
	public void setCliente(String c){
		
		cliente.setValue(c);
	
	}
	
	
	
	/**
	 * 
	 * @param c la camera da settare
	 */
	
	public void setCamera(String c){
		
		camera.setValue(c);
	
	}
	
	
	
	/**
	 * 
	 * @param d la durata da settare
	 */
	
	public void setDurata(String d){
		
		durata.setValue(d);
	
	}

	

}
