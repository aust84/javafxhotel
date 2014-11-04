package prenotazioni;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import databaseconnector.DBConnector;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import DataBaseCliente.StorageCliente;
import GestioneClienti.Cliente;

public class CliePre {
	
	Cliente c = null;
	public Cliente clientePrenotazione() throws ClassNotFoundException, SQLException, FileNotFoundException, IOException{
		Label avviso = new Label();
		avviso.setText("cliente non trovato inserirlo e ripetere prenotazione");
		StorageCliente sc= new StorageCliente();		
		DBConnector db=new DBConnector();
		db.openConnection();
		
		 Stage impress= new Stage();
		 GridPane root= new GridPane();
		 Scene scene = new Scene(root, 800,600);
		 impress.setScene(scene);
		 impress.show();
	

		 Label lCodFisc = new Label();
		 lCodFisc.setText("Codice Fiscale");
		 root.add(lCodFisc, 0, 0);
		 Label lNome = new Label();
		 lNome.setText("Nome");
		 root.add(lNome, 0, 1);
		 Label lCognome = new Label();
		 lCognome.setText("Cognome");
		 root.add(lCognome, 0, 2);
		 Label lTipologia = new Label();
		 lTipologia.setText("Tipologia");
		 root.add(lTipologia, 0, 3);
		 
		 TextField CodFisc= new TextField();
		 root.add(CodFisc, 1, 0);
		 TextField Nome= new TextField();
		 root.add(Nome, 1, 1);
		 TextField Cognome= new TextField();
		 root.add(Cognome, 1, 2);
		 TextField Tipologia= new TextField();
		 root.add(Tipologia, 1, 3);
		 
		 Button ok= new Button();
		 ok.setText("Ok");
		 ok.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
						
						c = new Cliente(CodFisc.getText(),Nome.getText(),Cognome.getText(),Tipologia.getText());
						try {
							sc.inserisciCliente(c);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						impress.close();
						try {
							sc.closeConnection();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
		
		 });
		 Button annulla= new Button();
		 annulla.setText("Annulla");
		 annulla.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
						
						impress.close();
						try {
							sc.closeConnection();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
		
		 });
		 root.add(ok, 0, 4);
		 root.add(annulla,1,4);
		 root.add(avviso, 2, 5);
		 return c;
	}
}
