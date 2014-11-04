package GestioneClienti;

import javafx.beans.property.SimpleStringProperty;


/**
 * 
 * @author Giovanni Citro
 *
 */

public class Cliente {
	
	private  SimpleStringProperty nome;
	
	private SimpleStringProperty cognome;
	
	private SimpleStringProperty codFiscale;
	
	private SimpleStringProperty tipologia;
	
	
	
	/**
	 * 
	 * @param codFiscale codice fiscale del cliente
	 * @param nome nome del cliente
	 * @param cognome cognome del cliente
	 * @param tipologia tipologia del cliente
	 */
	
	public Cliente(String codFiscale, String nome,String cognome,String tipologia){
		
		this.nome=new SimpleStringProperty(nome);
		
		this.codFiscale= new SimpleStringProperty(codFiscale);
		
		this.cognome=new SimpleStringProperty(cognome);
		
		this.tipologia=new SimpleStringProperty(tipologia);
		
	}
	
	
	
	/**
	 * 
	 * @return il codice fiscale
	 */
	
	public String getCodFiscale (){
		
		return codFiscale.getValue();
	
	}
	
	
	
	/**
	 * 
	 * @return il nome del cliente
	 */
	public String getNome (){
		
		return nome.getValue();
	
	}
	
	
	
	/**
	 * 
	 * @return il cognome del cliente
	 */

	public String getCognome(){
		
		return cognome.getValue();
	
	}
	
	
	
	
	/**
	 * 
	 * @return la tipologia del cliente
	 */
	
	public String getTipologia(){
	
		return tipologia.getValue();
	
	}
	
	public void setCodFiscale(String codF){
		
		codFiscale.set(codF);
	}
	
	public void setNome(String name){
		
		nome.set(name);
	}
	
	public void setCognome(String lastname){
		
		cognome.set(lastname);
	}
	public void setTipologia(String type){
		
		tipologia.setValue(type);
	}

}
