package GUItaxi;



import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import Alert.Alert;
import prenotazioni.Prenotazione_gui;
import application.Login;
import application.MainPage;
import application.MainPageAdmin;
import DataBaseAmministratore.StorageAmministrazione;
import DataBaseTaxi.StorageTaxi;
import GestioneAmministrazione.Amministratore;
import GestioneAmministrazione.Personale;
import GestioneTaxi.PrenotazioneTaxi;
import GestioneTaxi.Taxi;
import Personale_Gui.PersonaleGui;
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
import javafx.scene.control.TitledPane;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import edittable.*
;public class Taxi_Gui {

	private TableView<PrenotazioneTaxi> TaxiTab= new TableView<PrenotazioneTaxi>();
	TextField lCodice= new TextField();
	TextField lCodiceTaxi= new TextField();
	TextField lTaxi= new TextField();
	TextField lData= new TextField();
	TextField lOra= new TextField();
	TextField lDurata= new TextField();
	Alert al= new Alert();
	static PrenotazioneTaxi pt;
	public void start() {
		try {
			
			StageStyle stile = null;
			Stage primaryStage=new Stage(stile.TRANSPARENT);
			primaryStage.setFullScreen(true);
			DBConnector db= new DBConnector();
			db.openConnection();
			Text scenetitle=new Text("Prenotazione Taxi");
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
		
			Reflection reflect= new Reflection();
			DropShadow shadow= new DropShadow();
			shadow.setOffsetX(5.0);
			shadow.setOffsetY(5.0);
			shadow.setColor(Color.BLACK);
			scenetitle.setEffect(reflect);
			
			TaxiTab.setEditable(true);
			TaxiTab.setCursor(Cursor.TEXT);
			StorageTaxi st= new StorageTaxi();
			ObservableList<PrenotazioneTaxi> datalist = st.getElencoPrenotazioni();
			ObservableList<Taxi>taxilist= st.getElencoTaxi();
			
			/*for(int i=0; i<data.size();i++){
				new Amministratore(data.get(i).getMatricola(),data.get(i).getNome(),data.get(i).getCognome(),data.get(i).getUserId(),data.get(i).getPassword());
			}*/
			
			Callback<TableColumn, TableCell> cellFactory= new Callback<TableColumn, TableCell>() {
				 public TableCell call(TableColumn p){
					 
					 return new EditingCell();
				 }
			};
			
			TableColumn codice = new TableColumn("Codice");
			codice.setMinWidth(100);
			codice.setCellValueFactory(new PropertyValueFactory<PrenotazioneTaxi,String>("codice"));
			
			TableColumn codicetaxi = new TableColumn("Codice Taxi");
			codicetaxi.setMinWidth(100);
			codicetaxi.setCellValueFactory(new PropertyValueFactory<PrenotazioneTaxi,String>("taxi"));

			TableColumn data =new TableColumn("Data(gg-mm-aa)");
			data.setMaxWidth(200);
			data.setCellValueFactory(new PropertyValueFactory<PrenotazioneTaxi,String>("data"));
			data.setCellFactory(cellFactory);
			data.setOnEditCommit(new EventHandler<CellEditEvent<PrenotazioneTaxi, String>>() {
				
				@Override
				public void handle(CellEditEvent<PrenotazioneTaxi, String> t) {

					pt=((PrenotazioneTaxi)t.getTableView().getItems().get(t.getTablePosition().getRow()));
					pt.setData(t.getNewValue());
					
				}
			});
			
			TableColumn durata =new TableColumn("Durata");
			durata.setMaxWidth(200);
			durata.setCellValueFactory(new PropertyValueFactory<PrenotazioneTaxi,String>("durata"));
			durata.setCellFactory(cellFactory);
			durata.setOnEditCommit(new EventHandler<CellEditEvent<PrenotazioneTaxi, String>>() {
				
				@Override
				public void handle(CellEditEvent<PrenotazioneTaxi, String> t) {
					
					pt=((PrenotazioneTaxi)t.getTableView().getItems().get(t.getTablePosition().getRow()));
							pt.setDurata(t.getNewValue());
					
				}
			});
			
			TableColumn ora = new TableColumn("Ora");
			ora.setMaxWidth(200);
			ora.setCellValueFactory(new PropertyValueFactory<PrenotazioneTaxi, String>(
					"ora"));
			ora.setCellFactory(cellFactory);
			ora.setOnEditCommit(new EventHandler<CellEditEvent<PrenotazioneTaxi, String>>() {

				@Override
				public void handle(CellEditEvent<PrenotazioneTaxi, String> t) {

					pt = ((PrenotazioneTaxi) t.getTableView().getItems()
							.get(t.getTablePosition().getRow()));
					pt.setOra(t.getNewValue());

				}
			});
			
			TaxiTab.getColumns().addAll(codice,codicetaxi,data,durata,ora);
			
			Image homebtn= new Image(getClass().getResource("home.jpg").toExternalForm());
			Button home = new Button();
			home.setEffect(shadow);
			home.setGraphic(new ImageView(homebtn));
		
			
			home.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent arg0) {
					primaryStage.close();
					
					MainPage mpg=new MainPage();
					
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
				
					PrenotazioneTaxi pt= new PrenotazioneTaxi(lCodice.getText(),lCodiceTaxi.getText(),lData.getText(),lOra.getText(),lDurata.getText());
					try {
						st.inserisciPrenotazione(pt);;
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					lCodice.clear();
					lData.clear();
					lDurata.clear();
					lOra.clear();
					lCodiceTaxi.clear();
					datalist.add(pt);
				}
			});
			Button mod = new Button();
			mod.setText("Modifica");
			mod.setEffect(shadow);
			mod.setCursor(Cursor.HAND);
			mod.setOnAction(new EventHandler<ActionEvent>(){

				@Override
				public void handle(ActionEvent arg0) {
					try {
						st.modificaPrenotazione(pt);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
			});
			Button erese= new Button();
			erese.setText("Cancella");
			erese.setEffect(shadow);
			erese.setCursor(Cursor.CLOSED_HAND);
			erese.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					try {
						st.rimuoviPrenotazione(pt.getCodice());
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					int i=0;
					while(pt.getCodice()!=datalist.get(i).getCodice()){
						i++;
					}
					datalist.remove(i);
					//data.remove(data.size()-1);
				}
				
			});
			
			TitledPane info= new TitledPane();
			GridPane grid = new GridPane();
			grid.setVgap(4);
			grid.setPadding(new Insets(5, 5, 5, 5));
			for(int i=0; i<taxilist.size();i++){
				grid.add(new Label("codice taxi n° "+i),0,i);
				grid.add(new Label("   "+taxilist.get(i).getCodice()),1,i);
			}
			info.setText("Info codice Taxi");
			info.setEffect(reflect);
			info.setContent(grid);
			
			Button control= new Button();
			control.setEffect(reflect);
			control.setCursor(Cursor.HAND);
			control.setText("Controlla data");
			control.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					
					if(lCodiceTaxi.getText().isEmpty()){
						Stage dialog= new Stage();
						dialog.initModality(Modality.WINDOW_MODAL);
						dialog.setScene(al.msgBox("Campo Taxi Vuoto"));
						dialog.show();
						
					} else
						try {
							if(st.isPresenteTaxi(lCodiceTaxi.getText())){
								Stage dialog= new Stage();
								dialog.initModality(Modality.WINDOW_MODAL);
								dialog.setScene(al.msgBox("Codice Taxi non esistente"));
								dialog.show();
								//al.msgBox("Codice Taxi non esistente");
								
							}else if(lData.getText().isEmpty()){
								Stage dialog= new Stage();
								dialog.initModality(Modality.WINDOW_MODAL);
								dialog.setScene(al.msgBox("Campo data Vuoto"));
								dialog.show();
								//al.msgBox("Campo data Vuto");
								
							}else if(!(Controllo.controllaData(Integer.parseInt(lData.getText().substring(0,2)),Integer.parseInt(lData.getText().substring(3,5)),Integer.parseInt(lData.getText().substring(6))))){
								Stage dialog= new Stage();
								dialog.initModality(Modality.WINDOW_MODAL);
								dialog.setScene(al.msgBox("Data errata"));
								dialog.show();
								//al.msgBox("Data errata");
								
							}else if(Controllo.controllaTaxi(lCodiceTaxi.getText())){
								Stage dialog= new Stage();
								dialog.initModality(Modality.WINDOW_MODAL);
								dialog.setScene(al.msgBox("taxi occupato"));
								dialog.show();
								//al.msgBox("taxi occupato");
								
							}else{
								Stage dialog= new Stage();
								dialog.initModality(Modality.WINDOW_MODAL);
								dialog.setScene(al.msgBox("Taxi libero"));
								dialog.show();
								//al.msgBox("taxi libero");
								
							}
								
							
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (NumberFormatException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
				}
				
				
			});

			VBox vb= new VBox();
			vb.setSpacing(5);
			vb.setPadding(new Insets(10, 0, 0, 10));
			vb.getChildren().addAll(add,mod,erese,control);
			
			lCodice.setPromptText("Codice");
			lCodice.setMaxWidth(codice.getPrefWidth());
			lCodice.setCursor(Cursor.TEXT);
			lData.setPromptText("Data");
			lData.setMaxWidth(data.getPrefWidth());
			lData.setCursor(Cursor.TEXT);
			lDurata.setPromptText("Durata");
			lDurata.setMaxWidth(durata.getPrefWidth());
			lDurata.setCursor(Cursor.TEXT);
			lOra.setPromptText("Ora");
			lOra.setMaxWidth(ora.getPrefWidth());
			lOra.setCursor(Cursor.TEXT);
			lCodiceTaxi.setPromptText("Codice Taxi");
			lCodiceTaxi.setMaxWidth(codicetaxi.getPrefWidth());
			lCodiceTaxi.setCursor(Cursor.TEXT);
			
			
			final VBox vbb= new VBox();
			vbb.setSpacing(5);
			final HBox hb= new HBox();
			hb.setSpacing(8);
			hb.getChildren().addAll(lCodice,lCodiceTaxi,lData,lDurata,lOra);
			
			vbb.getChildren().addAll(hb,info);
			TaxiTab.setItems(datalist);
			TaxiTab.setEffect(shadow);
			//index.add(scenetitle, 0,0);
			index.add(TaxiTab,0,1);
			index.add(vb,1,1);
			index.add(vbb,0,2);
			//index.add(personale,0,3);
		}
			catch(Exception e){
				e.printStackTrace();
			}
	}
}
