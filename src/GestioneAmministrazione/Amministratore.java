package GestioneAmministrazione;

import javafx.beans.property.SimpleStringProperty;

/**
 * @author Hermann Antonio Aloisio
 *
 */
public class Amministratore {

	public SimpleStringProperty Matricola;
	public SimpleStringProperty Nome;
	public SimpleStringProperty Cognome;
	public SimpleStringProperty UserId;
	public SimpleStringProperty Password;
	
	/**
	 * @param Matricola dell' amministratore
	 * @param Nome dell' amministratore
	 * @param Cognome dell' amministratore
	 * @param UserId dell' amministratore
	 * @param Password dell' amministratore
	 */
	public Amministratore(String Matricola, String Nome,String Cognome,String UserId, String Password){
		
		this.Matricola= new SimpleStringProperty(Matricola);
		this.Nome=new SimpleStringProperty(Nome);
		this.Cognome= new SimpleStringProperty(Cognome);
		this.UserId= new SimpleStringProperty(UserId);
		this.Password= new SimpleStringProperty(Password);
	}

	/**
	 * @return matricola
	 */
	public String getMatricola() {
		return Matricola.getValue();
	}

	/**
	 * @param matricola dell' amministratore
	 */
	public void setMatricola(String matricola) {
		Matricola.set(matricola);
	}

	/**
	 * @return nome
	 */
	public String getNome() {
		return Nome.getValue();
	}

	/**
	 * @param nome dell' amministratore
	 */
	public void setNome(String nome) {
		Nome.set(nome);
	}

	/**
	 * @return cognome
	 */
	public String getCognome() {
		return Cognome.getValue();
	}

	/**
	 * @param cognome dell' amministratore
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
	 * @param userId dell' amministratore
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
	 * @param password dell' amministratore
	 */
	public void setPassword(String password) {
		Password.set(password);
	}
	
}
