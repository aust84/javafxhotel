package GestioneAmministrazione;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class Bilancio {

	SimpleStringProperty Codice;
	SimpleStringProperty Tipo;
	SimpleDoubleProperty Valore;
	
	/**
	 * @param Codice del bilancio
	 * @param Tipo del bilancio
	 * @param Valore del bilancio
	 */
	public Bilancio(String Codice, String Tipo, Double Valore){
		this.Codice=new SimpleStringProperty(Codice);
		this.Tipo=new SimpleStringProperty(Tipo);
		this.Valore=new SimpleDoubleProperty(Valore);
	}

	/**
	 * @return codice
	 */
	public String getCodice() {
		return Codice.getValue();
	}

	/**
	 * @param codice del bilancio
	 */
	public void setCodice(String codice) {
		Codice.set(codice);
	}

	/**
	 * @return tipo
	 */
	public String getTipo() {
		return Tipo.getValue();
	}

	/**
	 * @param tipo del bilancio
	 */
	public void setTipo(String tipo) {
		Tipo.set(tipo);
	}

	/**
	 * @return valore
	 */
	public Double getValore() {
		return Valore.getValue();
	}

	/**
	 * @param valore del bilancio
	 */
	public void setValore(Double valore) {
		Valore.set(valore);
	}
	
	
}
