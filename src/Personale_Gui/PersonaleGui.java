package Personale_Gui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import clienti.Clienti_GUI;
import application.Login;
import application.MainPage;
import application.MainPageAdmin;
import DataBaseAmministratore.StoragePersonale;
import DataBaseCliente.StorageCliente;
import GestioneAmministrazione.Amministratore;
import GestioneAmministrazione.Personale;
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

public class PersonaleGui {

	private TableView<Personale> persoTab = new TableView<Personale>();
	TextField lMatricola = new TextField();
	TextField lNome = new TextField();
	TextField lCognome = new TextField();
	TextField lUserId = new TextField();
	TextField lPassword = new TextField();
	
	static Personale p;

	public void start() {
		try {

			StageStyle stile = null;
			Stage primaryStage=new Stage(stile.TRANSPARENT);
			primaryStage.setFullScreen(true);
			DBConnector db = new DBConnector();
			db.openConnection();
			Text scenetitle = new Text("Personale");
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
			
			Reflection reflect= new Reflection();
			DropShadow shadow = new DropShadow();
			shadow.setOffsetX(5.0);
			shadow.setOffsetY(5.0);
			shadow.setColor(Color.BLACK);
			scenetitle.setEffect(reflect);
			persoTab.setEditable(true);
			persoTab.setCursor(Cursor.TEXT);
			StoragePersonale sp= new StoragePersonale();
			ObservableList<Personale> datalist = sp.getElencoPersonale();

			/*for (int i = 0; i < datalist.size(); i++) {
				new Personale(datalist.get(i).getMatricola(), datalist.get(i)
						.getNome(), datalist.get(i).getCognome(),
						datalist.get(i).getUserId(), datalist.get(i)
								.getPassword());
			}*/

			Callback<TableColumn, TableCell> cellFactory = new Callback<TableColumn, TableCell>() {
				public TableCell call(TableColumn p) {

					return new EditingCell();
				}
			};

			TableColumn matricola = new TableColumn("Matricola");
			matricola.setMinWidth(200);
			matricola.setCellValueFactory(new PropertyValueFactory<Personale, String>(
					"matricola"));

			TableColumn nome = new TableColumn("Nome");
			nome.setMaxWidth(200);
			nome.setCellValueFactory(new PropertyValueFactory<Personale, String>(
					"nome"));
			nome.setCellFactory(cellFactory);
			nome.setOnEditCommit(new EventHandler<CellEditEvent<Personale, String>>() {

				@Override
				public void handle(CellEditEvent<Personale, String> t) {

					p = ((Personale) t.getTableView().getItems()
							.get(t.getTablePosition().getRow()));
					p.setNome(t.getNewValue());

				}
			});

			TableColumn cognome = new TableColumn("Cognome");
			cognome.setMaxWidth(200);
			cognome.setCellValueFactory(new PropertyValueFactory<Personale, String>(
					"cognome"));
			cognome.setCellFactory(cellFactory);
			cognome.setOnEditCommit(new EventHandler<CellEditEvent<Personale, String>>() {

				@Override
				public void handle(CellEditEvent<Personale, String> t) {

					p = ((Personale) t.getTableView().getItems()
							.get(t.getTablePosition().getRow()));
					p.setCognome(t.getNewValue());

				}
			});

			TableColumn userId = new TableColumn("UserId");
			userId.setMaxWidth(200);
			userId.setCellValueFactory(new PropertyValueFactory<Personale, String>(
					"userId"));
			userId.setCellFactory(cellFactory);
			userId.setOnEditCommit(new EventHandler<CellEditEvent<Personale, String>>() {

				@Override
				public void handle(CellEditEvent<Personale, String> t) {

					p = ((Personale) t.getTableView().getItems()
							.get(t.getTablePosition().getRow()));
					p.setUserId(t.getNewValue());

				}
			});

			TableColumn password = new TableColumn("Password");
			password.setMaxWidth(200);
			password.setCellValueFactory(new PropertyValueFactory<Personale, String>(
					"password"));
			password.setCellFactory(cellFactory);
			password.setOnEditCommit(new EventHandler<CellEditEvent<Personale, String>>() {

				@Override
				public void handle(CellEditEvent<Personale, String> t) {

					p = ((Personale) t.getTableView().getItems()
							.get(t.getTablePosition().getRow()));
					p.setPassword(t.getNewValue());

				}
			});

					persoTab.getColumns().addAll(matricola,nome,cognome,userId,password);

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

					MainPageAdmin log = new MainPageAdmin();

					log.start();

				}
			});

			Button add = new Button();
			add.setEffect(shadow);
			add.setCursor(Cursor.HAND);
			add.setText("Aggiungi");
			add.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
				
					Personale p= new Personale(lMatricola.getText(),lNome.getText(),lCognome.getText(),lUserId.getText(),lPassword.getText());
					try {
						sp.inserisciPersonale(p);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					lMatricola.clear();
					lNome.clear();
					lCognome.clear();
					lUserId.clear();
					lPassword.clear();
					datalist.add(p);
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
						sp.modificaPersonale(p);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			});
			Button erese = new Button();
			erese.setText("Cancella");
			erese.setEffect(reflect);
			erese.setCursor(Cursor.CLOSED_HAND);
			erese.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					try {
						sp.rimuoviPersonale(p.getMatricola());
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					int i = 0;
					while (p.getMatricola() != datalist.get(i).getMatricola()) {
						i++;
					}
					datalist.remove(i);
					// data.remove(data.size()-1);
				}

			});

		/*	Image prebtn = new Image(getClass().getResource("prenotazione.jpg")
					.toExternalForm());
			Button prenota = new Button();
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
*/
			VBox vb = new VBox();
			vb.setSpacing(5);
			vb.setPadding(new Insets(10, 0, 0, 10));
			vb.getChildren().addAll(add, mod, erese);

			lMatricola.setPromptText("Matricola");
			lMatricola.setMaxWidth(matricola.getPrefWidth());
			lMatricola.setCursor(Cursor.TEXT);
			lNome.setPromptText("Nome");
			lNome.setMaxWidth(nome.getPrefWidth());
			lNome.setCursor(Cursor.TEXT);
			lCognome.setPromptText("Cognome");
			lCognome.setMaxWidth(cognome.getPrefWidth());
			lCognome.setCursor(Cursor.TEXT);
			lUserId.setPromptText("UserID");
			lUserId.setMaxWidth(userId.getPrefWidth());
			lUserId.setCursor(Cursor.TEXT);
			lPassword.setPromptText("Password");
			lPassword.setMaxWidth(password.getPrefWidth());
			lPassword.setCursor(Cursor.TEXT);
		
			final HBox hb = new HBox();
			hb.setSpacing(8);
			hb.getChildren().addAll(lMatricola, lNome, lCognome, lUserId,
					lPassword);

			persoTab.setItems(datalist);
			persoTab.setEffect(shadow);
			index.add(scenetitle, 0, 0);
			index.add(persoTab, 0, 1);
			index.add(vb, 1, 1);
			index.add(hb, 0, 2);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}
