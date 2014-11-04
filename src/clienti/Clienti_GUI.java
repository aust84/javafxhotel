package clienti;



import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import prenotazioni.Prenotazione_gui;
import application.Login;
import application.MainPage;
import DataBaseCliente.StorageCliente;
import GestioneClienti.Cliente;
import databaseconnector.DBConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import edittable.*
;public class Clienti_GUI {

	private TableView<Cliente> ClientTab= new TableView<Cliente>();
	TextField lCodFis= new TextField();
	TextField lNome= new TextField();
	TextField lCognome= new TextField();
	TextField lTipologia= new TextField();
	static Cliente c;
	public void start() {
		try {
			
			StageStyle stile = null;
			Stage primaryStage=new Stage(stile.TRANSPARENT);
			primaryStage.setFullScreen(true);
			DBConnector db= new DBConnector();
			db.openConnection();
			Text scenetitle=new Text("CLIENTI");
			scenetitle.setFont(Font.font("Tahoma",FontWeight.BOLD,30));
			
			GridPane index= new GridPane();
			index.setAlignment(Pos.CENTER);
			index.setVgap(10);
			index.setHgap(10);
			index.setPadding(new Insets(25, 25, 25, 25));
			
			StackPane root= new StackPane();
			//BorderPane root = new BorderPane();
			Scene scene = new Scene(index, 800,600);
			scene.getStylesheets().add(getClass().getResource("Logon.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
			Reflection reflect = new Reflection();
			DropShadow shadow= new DropShadow();
			shadow.setOffsetX(5.0);
			shadow.setOffsetY(5.0);
			shadow.setColor(Color.BLACK);
			scenetitle.setEffect(reflect);
			ClientTab.setEditable(true);
			ClientTab.setCursor(Cursor.TEXT);
			StorageCliente sc= new StorageCliente();
			ObservableList<Cliente> data = sc.getElencoClienti();
			
			for(int i=0; i<data.size();i++){
				new Cliente(data.get(i).getCodFiscale(),data.get(i).getNome(),data.get(i).getCognome(),data.get(i).getTipologia());
			}
			
			Callback<TableColumn, TableCell> cellFactory= new Callback<TableColumn, TableCell>() {
				 public TableCell call(TableColumn p){
					 
					 return new EditingCell();
				 }
			};
			
			TableColumn codFiscale = new TableColumn("Codice Fiscale");
			codFiscale.setMinWidth(200);
			codFiscale.setCellValueFactory(new PropertyValueFactory<Cliente,String>("codFiscale"));

			TableColumn nome =new TableColumn("Nome");
			nome.setMaxWidth(200);
			nome.setCellValueFactory(new PropertyValueFactory<Cliente,String>("nome"));
			nome.setCellFactory(cellFactory);
			nome.setOnEditCommit(new EventHandler<CellEditEvent<Cliente, String>>() {
				
				@Override
				public void handle(CellEditEvent<Cliente, String> t) {

					c=((Cliente)t.getTableView().getItems().get(t.getTablePosition().getRow()));
					c.setNome(t.getNewValue());
					
				}
			});
			
			TableColumn cognome =new TableColumn("Cognome");
			cognome.setMaxWidth(200);
			cognome.setCellValueFactory(new PropertyValueFactory<Cliente,String>("cognome"));
			cognome.setCellFactory(cellFactory);
			cognome.setOnEditCommit(new EventHandler<CellEditEvent<Cliente, String>>() {
				
				@Override
				public void handle(CellEditEvent<Cliente, String> t) {
					
					c=((Cliente)t.getTableView().getItems().get(t.getTablePosition().getRow()));
							c.setCognome(t.getNewValue());
					
				}
			});
			
			TableColumn tipologia =new TableColumn("Tipologia");
			tipologia.setMaxWidth(200);
			tipologia.setCellValueFactory(new PropertyValueFactory<Cliente,String>("tipologia"));
			tipologia.setCellFactory(cellFactory);
			tipologia.setOnEditCommit(new EventHandler<CellEditEvent<Cliente, String>>() {
				
				@Override
				public void handle(CellEditEvent<Cliente, String> t) {
					
					c=((Cliente)t.getTableView().getItems().get(t.getTablePosition().getRow()));
							c.setTipologia(t.getNewValue());;
					
				}
			});
			ClientTab.getColumns().addAll(codFiscale,nome,cognome,tipologia);
			
			Image homebtn= new Image(getClass().getResource("home.jpg").toExternalForm());
			Button home = new Button();
			home.setCursor(Cursor.OPEN_HAND);
			home.setGraphic(new ImageView(homebtn));
		
			
			home.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent arg0) {
					primaryStage.close();
					
					Login mpg=new Login();
					
				mpg.start();
				
				}
			});
			
			HBox hb2 = new HBox();
			hb2.setSpacing(5);
			hb2.setPadding(new Insets(10, 0, 0, 10));
			hb2.getChildren().addAll(home,scenetitle);
			index.add(hb2, 0, 0);
			
			Image logoffbtn= new Image(getClass().getResource("logout.png").toExternalForm());
			Button logoff = new Button();
			logoff.setEffect(shadow);
			logoff.setCursor(Cursor.CROSSHAIR);
			logoff.setGraphic(new ImageView(logoffbtn));
			index.add(logoff, 1,0);
			
			logoff.addEventHandler(MouseEvent.MOUSE_ENTERED,
			        new EventHandler<MouseEvent>() {
			          @Override
			          public void handle(MouseEvent e) {
			           
			          }
			        });
			logoff.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent arg0) {
					primaryStage.close();
					
					Login log = new Login();
					
				log.start();
				
				}
			});
			
			Button add = new Button();
			add.setText("Aggiungi");
			add.setEffect(shadow);
			add.setCursor(Cursor.HAND);
			add.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
				
					Cliente c= new Cliente(lCodFis.getText(),lNome.getText(),lCognome.getText(),lTipologia.getText());
					try {
						sc.inserisciCliente(c);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					lCodFis.clear();
					lNome.clear();
					lCognome.clear();
					lTipologia.clear();
					data.add(c);
				}
			});
			Button mod = new Button();
			mod.setEffect(shadow);
			mod.setText("Modifica");
			mod.setCursor(Cursor.HAND);
			mod.setOnAction(new EventHandler<ActionEvent>(){

				@Override
				public void handle(ActionEvent arg0) {
					try {
						sc.modificaCliente(c);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
			});
			Button erese= new Button();
			erese.setText("Cancella");
			erese.setEffect(reflect);
			erese.setCursor(Cursor.CLOSED_HAND);
			erese.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					try {
						sc.rimuoviCliente(c.getCodFiscale());
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					int i=0;
					while(c.getCodFiscale()!=data.get(i).getCodFiscale()){
						i++;
					}
					data.remove(i);
					//data.remove(data.size()-1);
				}
				
			});
			
			Image prebtn= new Image(getClass().getResource("prenotazione.jpg").toExternalForm());
			Button prenota = new Button();
			prenota.setEffect(reflect);
			prenota.setCursor(Cursor.HAND);
			prenota.setGraphic(new ImageView(prebtn));
			prenota.setMinWidth(500);
			prenota.setOnAction(new EventHandler<ActionEvent>(){

				@Override
				public void handle(ActionEvent arg0) {
					
					Prenotazione_gui pren= new Prenotazione_gui();
					primaryStage.close();
					pren.start();
				}
				
				
			});

			VBox vb= new VBox();
			vb.setSpacing(5);
			vb.setPadding(new Insets(10, 0, 0, 10));
			vb.getChildren().addAll(add,mod,erese);
			
			lCodFis.setPromptText("Codice fiscale");
			lCodFis.setMaxWidth(codFiscale.getPrefWidth());
			lCodFis.setCursor(Cursor.TEXT);
			lNome.setPromptText("Nome");
			lNome.setMaxWidth(nome.getPrefWidth());
			lNome.setCursor(Cursor.TEXT);
			lCognome.setPromptText("Cognome");
			lCognome.setMaxWidth(cognome.getPrefWidth());
			lCognome.setCursor(Cursor.TEXT);
			lTipologia.setPromptText("Tipologia");
			lTipologia.setMaxWidth(tipologia.getPrefWidth());
			lTipologia.setCursor(Cursor.TEXT);
			
			final HBox hb= new HBox();
			hb.setSpacing(8);
			hb.getChildren().addAll(lCodFis,lNome,lCognome,lTipologia);
			
			ClientTab.setItems(data);
			ClientTab.setEffect(shadow);
			//index.add(scenetitle, 0,0);
			index.add(ClientTab,0,1);
			index.add(vb,1,1);
			index.add(hb,0,2);
			index.add(prenota,0,3);
		}
			catch(Exception e){
				e.printStackTrace();
			}
	}
}
