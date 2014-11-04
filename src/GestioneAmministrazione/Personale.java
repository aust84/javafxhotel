package GestioneAmministrazione;

import javafx.beans.property.SimpleStringProperty;

/**
 * @author Hermann Antonio Aloisio
 *
 */
public class Personale {

	SimpleStringProperty Matricola;
	SimpleStringProperty Nome;
	SimpleStringProperty Cognome;
	SimpleStringProperty UserId;
	SimpleStringProperty Password;
	
	/**
	 * @param Matricola del personale
	 * @param Nome del personale
	 * @param Cognome del personale
	 * @param UserId del personale
	 * @param Password del personale
	 */
	public Personale(String Matricola,String Nome,String Cognome,String UserId,String Password){
		
		this.Matricola=new SimpleStringProperty(Matricola);
		this.Nome=new SimpleStringProperty(Nome);
		this.Cognome=new SimpleStringProperty(Cognome);
		this.UserId=new SimpleStringProperty(UserId);
		this.Password=new SimpleStringProperty(Password);
	}

	/**
	 * @return Matricola
	 */
	public String getMatricola() {
		return Matricola.getValue();
	}

	/**
	 * @param matricola del personale
	 */
	public void setMatricola(String matricola) {
		Matricola.setValue(matricola);
	}

	/**
	 * @return nome
	 */
	public String getNome() {
		return Nome.getValue();
	}

	/**
	 * @param nome del personale
	 */
	public void setNome(String nome) {
		Nome.setValue(nome);
	}

	/**
	 * @return cognome
	 */
	public String getCognome() {
		return Cognome.getValue();
	}

	/**
	 * @param cognome del personale
	 */
	public void setCognome(String cognome) {
		Cognome.set(cognome);
	}

	/**
	 * @return userId
	 */
	public String getUserId() {
		return UserId.getValue();
	}

	/**
	 * @param userId del personale
	 */
	public void setUserId(String userId) {
		UserId.set(userId);
	}

	/**
	 * @return password
	 */
	public String getPassword() {
		return Password.getValue();
	}

	/**
	 * @param password del personale
	 */
	public void setPassword(String password) {
		Password.setValue(password);
	}
	
}

