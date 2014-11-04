package prenotazioni;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import clienti.Clienti_GUI;
import application.Login;
import application.MainPage;
import DataBaseCliente.StorageCliente;
import GestioneClienti.Cliente;
import GestioneClienti.Prenotazione;
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
import javafx.scene.control.TitledPane;
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
import edittable.*;

public class Prenotazione_gui {

	private TableView<Prenotazione> ClientTab = new TableView<Prenotazione>();
	TextField lCodice = new TextField();
	TextField lData = new TextField();
	TextField lCliente = new TextField();
	TextField lCamera = new TextField();
	TextField lDurata = new TextField();
	TextField lPersone = new TextField();
	final HBox hb2 = new HBox();
	static Prenotazione p;

	public void start() {
		try {

			StageStyle stile = null;
			Stage primaryStage=new Stage(stile.TRANSPARENT);
			primaryStage.setFullScreen(true);
			DBConnector db = new DBConnector();
			db.openConnection();
			Text scenetitle = new Text("PRENOTAZIONI");
			scenetitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 30));

			GridPane index = new GridPane();
			index.setAlignment(Pos.CENTER);
			index.setVgap(10);
			index.setHgap(10);
			index.setPadding(new Insets(25, 25, 25, 25));

			StackPane root = new StackPane();
			// BorderPane root = new BorderPane();
			Scene scene = new Scene(index, 800, 600);
			scene.getStylesheets().add(
					getClass().getResource("Logon.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();

			Reflection reflect = new Reflection();
			DropShadow shadow = new DropShadow();
			shadow.setOffsetX(5.0);
			shadow.setOffsetY(5.0);
			shadow.setColor(Color.BLACK);
			scenetitle.setEffect(reflect);
			ClientTab.setEditable(true);
			ClientTab.setCursor(Cursor.TEXT);
			StorageCliente sc = new StorageCliente();
			ObservableList<Prenotazione> datalist = sc.getElencoPrenotazioni();

			for (int i = 0; i < datalist.size(); i++) {
				new Prenotazione(datalist.get(i).getCodice(), datalist.get(i)
						.getData(), datalist.get(i).getNumeroPersone(),
						datalist.get(i).getCliente(), datalist.get(i)
								.getCamera(), datalist.get(i).getData());
			}

			Callback<TableColumn, TableCell> cellFactory = new Callback<TableColumn, TableCell>() {
				public TableCell call(TableColumn p) {

					return new EditingCell();
				}
			};

			TableColumn codice = new TableColumn("Codice");
			codice.setMinWidth(200);
			codice.setCellValueFactory(new PropertyValueFactory<Prenotazione, String>(
					"codice"));

			TableColumn data = new TableColumn("Data");
			data.setMaxWidth(200);
			data.setCellValueFactory(new PropertyValueFactory<Prenotazione, String>(
					"data"));
			data.setCellFactory(cellFactory);
			data.setOnEditCommit(new EventHandler<CellEditEvent<Prenotazione, String>>() {

				@Override
				public void handle(CellEditEvent<Prenotazione, String> t) {

					p = ((Prenotazione) t.getTableView().getItems()
							.get(t.getTablePosition().getRow()));
					p.setData(t.getNewValue());

				}
			});

			TableColumn persone = new TableColumn("Persone n°");
			persone.setMaxWidth(200);
			persone.setCellValueFactory(new PropertyValueFactory<Prenotazione, String>(
					"numeroPersone"));
			persone.setCellFactory(cellFactory);
			persone.setOnEditCommit(new EventHandler<CellEditEvent<Prenotazione, String>>() {

				@Override
				public void handle(CellEditEvent<Prenotazione, String> t) {

					p = ((Prenotazione) t.getTableView().getItems()
							.get(t.getTablePosition().getRow()));
					p.setNumeroPersone(t.getNewValue());

				}
			});

			TableColumn cliente = new TableColumn("Cliete");
			cliente.setMaxWidth(200);
			cliente.setCellValueFactory(new PropertyValueFactory<Prenotazione, String>(
					"cliente"));
			cliente.setCellFactory(cellFactory);
			cliente.setOnEditCommit(new EventHandler<CellEditEvent<Prenotazione, String>>() {

				@Override
				public void handle(CellEditEvent<Prenotazione, String> t) {

					p = ((Prenotazione) t.getTableView().getItems()
							.get(t.getTablePosition().getRow()));
					p.setCliente(t.getNewValue());

				}
			});

			TableColumn camera = new TableColumn("Camera");
			camera.setMaxWidth(200);
			camera.setCellValueFactory(new PropertyValueFactory<Prenotazione, String>(
					"camera"));
			camera.setCellFactory(cellFactory);
			camera.setOnEditCommit(new EventHandler<CellEditEvent<Prenotazione, String>>() {

				@Override
				public void handle(CellEditEvent<Prenotazione, String> t) {

					p = ((Prenotazione) t.getTableView().getItems()
							.get(t.getTablePosition().getRow()));
					p.setCamera(t.getNewValue());

				}
			});

			TableColumn durata = new TableColumn("Durata(in gg)");
			durata.setMaxWidth(200);
			durata.setCellValueFactory(new PropertyValueFactory<Prenotazione, String>(
					"durata"));
			durata.setCellFactory(cellFactory);
			durata.setOnEditCommit(new EventHandler<CellEditEvent<Prenotazione, String>>() {

				@Override
				public void handle(CellEditEvent<Prenotazione, String> t) {

					p = ((Prenotazione) t.getTableView().getItems()
							.get(t.getTablePosition().getRow()));
					p.setDurata(t.getNewValue());

				}
			});
			ClientTab.getColumns().addAll(codice, data, persone, cliente,
					camera, durata);

			Image logoffbtn = new Image(getClass().getResource("logout.png")
					.toExternalForm());
			Button logoff = new Button();
			logoff.setEffect(shadow);
			logoff.setCursor(Cursor.CROSSHAIR);
			logoff.setGraphic(new ImageView(logoffbtn));
			index.add(logoff, 1, 0);

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
			add.setEffect(shadow);
			add.setText("Aggiungi");
			add.setCursor(Cursor.HAND);
			add.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					try {
						if(sc.isPresenteCliente(lPersone.getText())){
							try {
								{
									Prenotazione p = new Prenotazione(lCodice.getText(), lData
											.getText(), lPersone.getText(), lCliente.getText(),
											lCamera.getText(), lDurata.getText());
									
									sc.inserisciPrenotazione(p, sc.getCliente(persone.getText()));
									datalist.add(p);

								}
								
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						else{
							Label l= new Label("Cliente non trovato inserisci cliente");
							l.setFont(Font.font("Tahoma", FontWeight.BOLD, 30));
							hb2.getChildren().addAll(scenetitle,l);
							index.add(hb2, 0, 0);
						}
						
						
					lCodice.clear();
					lData.clear();
					lPersone.clear();
					lCliente.clear();
					lCamera.clear();
					lDurata.clear();
					datalist.add(p);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
			});
			Button mod = new Button();
			mod.setText("Modifica");
			mod.setEffect(shadow);
			mod.setCursor(Cursor.HAND);
			mod.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					try {
						sc.modificaPrenotazione(p);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			});
			Button erese = new Button();
			erese.setText("Cancella");
			erese.setCursor(Cursor.CLOSED_HAND);
			erese.setEffect(shadow);
			erese.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					try {
						sc.rimuoviPrenotazione(p.getCodice());
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					int i = 0;
					while (p.getCodice() != datalist.get(i).getCodice()) {
						i++;
					}
					datalist.remove(i);
					// data.remove(data.size()-1);
				}

			});

			Image prebtn = new Image(getClass().getResource("prenotazione.jpg")
					.toExternalForm());
			Button prenota = new Button();
			prenota.setEffect(reflect);
			prenota.setCursor(Cursor.HAND);
			prenota.setGraphic(new ImageView(prebtn));
			prenota.setMinWidth(500);
			prenota.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {

					Clienti_GUI cli = new Clienti_GUI();
					primaryStage.close();
					cli.start();
				}

			});
			
			
			hb2.setSpacing(8);
			hb2.getChildren().add(scenetitle);
			
			TitledPane gridTitlePane = new TitledPane();
			GridPane grid = new GridPane();
			grid.setVgap(4);
			grid.setPadding(new Insets(5, 5, 5, 5));
			grid.add(new Label("Codice Fiscale"), 0, 0);
			TextField codfisc= new TextField();
			codfisc.setCursor(Cursor.TEXT);
			grid.add(codfisc, 1, 0);
			grid.add(new Label("Nome: "), 0, 1);
			TextField nome= new TextField();
			nome.setCursor(Cursor.TEXT);
			grid.add(nome, 1, 1);
			grid.add(new Label("Cognome: "), 0, 2);
			TextField cognome = new TextField();
			cognome.setCursor(Cursor.TEXT);
			grid.add(cognome, 1, 2);
			grid.add(new Label("Tipologia "), 0, 3);
			TextField tipologia = new TextField();
			tipologia.setCursor(Cursor.TEXT);
			grid.add(tipologia, 1, 3);
			Button nwcli = new Button("ok");
			nwcli.setEffect(reflect);
			nwcli.setCursor(Cursor.HAND);
			nwcli.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					StorageCliente scc = null;
					try {
						scc = new StorageCliente();
					} catch (ClassNotFoundException | SQLException
							| IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						scc.inserisciCliente(new Cliente(codfisc.getText(),nome.getText(),cognome.getText(),tipologia.getText()));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				
			});
			grid.add(nwcli,0,4);
			
			gridTitlePane.setText("Inserisci nuovo cliente");
			
			gridTitlePane.setContent(grid);
			gridTitlePane.setEffect(reflect);
		
			VBox vb = new VBox();
			vb.setSpacing(5);
			vb.setPadding(new Insets(10, 0, 0, 10));
			vb.getChildren().addAll(add, mod, erese,gridTitlePane);

			lCodice.setPromptText("Codice");
			lCodice.setMaxWidth(codice.getPrefWidth());
			lCodice.setCursor(Cursor.TEXT);
			lData.setPromptText("Data");
			lData.setMaxWidth(data.getPrefWidth());
			lData.setCursor(Cursor.TEXT);
			lPersone.setPromptText("Persone");
			lPersone.setMaxWidth(persone.getPrefWidth());
			lPersone.setCursor(Cursor.TEXT);
			lCliente.setPromptText("Cliente");
			lCliente.setMaxWidth(cliente.getPrefWidth());
			lCliente.setCursor(Cursor.TEXT);
			lCamera.setPromptText("Camera");
			lCamera.setMaxWidth(camera.getPrefWidth());
			lCamera.setCursor(Cursor.TEXT);
			lDurata.setPromptText("Durata");
			lDurata.setMaxWidth(durata.getPrefWidth());
			lDurata.setCursor(Cursor.TEXT);

			final HBox hb = new HBox();
			hb.setSpacing(8);
			hb.getChildren().addAll(lCodice, lData, lPersone, lCliente,
					lCamera, lDurata);
			
			ClientTab.setItems(datalist);
			ClientTab.setEffect(shadow);
			index.add(scenetitle, 0, 0);
			index.add(ClientTab, 0, 1);
			index.add(vb, 1, 1);
			index.add(hb, 0, 2);
			index.add(prenota, 0, 3);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}
