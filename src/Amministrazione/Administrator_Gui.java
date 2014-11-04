package Amministrazione;



import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import application.Login;
import application.MainPage;
import application.MainPageAdmin;
import DataBaseAmministratore.StorageAmministrazione;
import GestioneAmministrazione.Amministratore;
import GestioneAmministrazione.Personale;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.effect.Shadow;
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
;public class Administrator_Gui {

	private TableView<Amministratore> AdminTab= new TableView<Amministratore>();
	TextField lMatricola= new TextField();
	TextField lNome= new TextField();
	TextField lCognome= new TextField();
	TextField lUserId= new TextField();
	TextField lPassword= new TextField();
	static Amministratore a;
	public void start() {
		try {
			StageStyle stile = null;
			Stage primaryStage=new Stage(stile.TRANSPARENT);
			primaryStage.setFullScreen(true);
			DBConnector db= new DBConnector();
			db.openConnection();
			Text scenetitle=new Text("Amministratore");
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
			DropShadow shadow = new DropShadow();
			shadow.setOffsetX(5.0);
			shadow.setOffsetY(5.0);
			shadow.setColor(Color.BLACK);
			Reflection reflect = new Reflection();
			scenetitle.setEffect(reflect);
			AdminTab.setEditable(true);
			StorageAmministrazione sa= new StorageAmministrazione();
			ObservableList<Amministratore> data = sa.getElencoAmministratore();
			
			/*for(int i=0; i<data.size();i++){
				new Amministratore(data.get(i).getMatricola(),data.get(i).getNome(),data.get(i).getCognome(),data.get(i).getUserId(),data.get(i).getPassword());
			}*/
			
			Callback<TableColumn, TableCell> cellFactory= new Callback<TableColumn, TableCell>() {
				 public TableCell call(TableColumn p){
					 
					 return new EditingCell();
				 }
			};
			
			TableColumn matricola = new TableColumn("Maricola");
			matricola.setMinWidth(200);
			matricola.setCellValueFactory(new PropertyValueFactory<Amministratore,String>("matricola"));

			TableColumn nome =new TableColumn("Nome");
			nome.setMaxWidth(200);
			nome.setCellValueFactory(new PropertyValueFactory<Amministratore,String>("nome"));
			nome.setCellFactory(cellFactory);
			nome.setOnEditCommit(new EventHandler<CellEditEvent<Amministratore, String>>() {
				
				@Override
				public void handle(CellEditEvent<Amministratore, String> t) {

					a=((Amministratore)t.getTableView().getItems().get(t.getTablePosition().getRow()));
					a.setNome(t.getNewValue());
					
				}
			});
			
			TableColumn cognome =new TableColumn("Cognome");
			cognome.setMaxWidth(200);
			cognome.setCellValueFactory(new PropertyValueFactory<Amministratore,String>("cognome"));
			cognome.setCellFactory(cellFactory);
			cognome.setOnEditCommit(new EventHandler<CellEditEvent<Amministratore, String>>() {
				
				@Override
				public void handle(CellEditEvent<Amministratore, String> t) {
					
					a=((Amministratore)t.getTableView().getItems().get(t.getTablePosition().getRow()));
							a.setCognome(t.getNewValue());
					
				}
			});
			
			TableColumn userId = new TableColumn("UserId");
			userId.setMaxWidth(200);
			userId.setCellValueFactory(new PropertyValueFactory<Amministratore, String>(
					"userId"));
			userId.setCellFactory(cellFactory);
			userId.setOnEditCommit(new EventHandler<CellEditEvent<Amministratore, String>>() {

				@Override
				public void handle(CellEditEvent<Amministratore, String> t) {

					a = ((Amministratore) t.getTableView().getItems()
							.get(t.getTablePosition().getRow()));
					a.setUserId(t.getNewValue());

				}
			});
			
			AdminTab.getColumns().addAll(matricola,nome,cognome,userId);
			AdminTab.setCursor(Cursor.TEXT);
			Image homebtn= new Image(getClass().getResource("home.jpg").toExternalForm());
			Button home = new Button();
			home.setEffect(shadow);
			home.setGraphic(new ImageView(homebtn));
			home.setCursor(Cursor.OPEN_HAND);
		
			
			home.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent arg0) {
					primaryStage.close();
					
					MainPageAdmin mpg=new MainPageAdmin();
					
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
			add.setEffect(shadow);
			add.setText("Aggiungi");
			add.setCursor(Cursor.HAND);
			add.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
				
					Amministratore a= new Amministratore(lMatricola.getText(),lNome.getText(),lCognome.getText(),lUserId.getText(),lPassword.getText());
					try {
						sa.inserisciAmministraotre(a);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					lMatricola.clear();
					lNome.clear();
					lCognome.clear();
					lUserId.clear();
					lPassword.clear();
					data.add(a);
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
						sa.modificaAmministraotre(a);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
			});
			Button erese= new Button();
			erese.setText("Cancella");
			erese.setEffect(reflect);
			erese.setCursor(Cursor.HAND);
			erese.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					try {
						sa.rimuoviAmministratore(a.getMatricola());
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					int i=0;
					while(a.getMatricola()!=data.get(i).getMatricola()){
						i++;
					}
					data.remove(i);
					//data.remove(data.size()-1);
				}
				
			});
			
			Image prebtn= new Image(getClass().getResource("prenotazione.jpg").toExternalForm());
			Button personale = new Button();
			personale.setEffect(reflect);
			personale.setGraphic(new ImageView(prebtn));
			personale.setMinWidth(500);
			personale.setCursor(Cursor.CLOSED_HAND);
			personale.setOnAction(new EventHandler<ActionEvent>(){

				@Override
				public void handle(ActionEvent arg0) {
					
					PersonaleGui pers= new PersonaleGui();
					primaryStage.close();
					pers.start();
				}
				
				
			});

			VBox vb= new VBox();
			vb.setSpacing(5);
			vb.setPadding(new Insets(10, 0, 0, 10));
			vb.getChildren().addAll(add,mod,erese);
			
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
			lPassword.setMaxWidth(userId.getPrefWidth());
			lPassword.setCursor(Cursor.TEXT);
			
			final HBox hb= new HBox();
			hb.setSpacing(8);
			hb.getChildren().addAll(lMatricola,lNome,lCognome,lUserId,lPassword);
			
			AdminTab.setItems(data);
			AdminTab.setEffect(shadow);
			//index.add(scenetitle, 0,0);
			index.add(AdminTab,0,1);
			index.add(vb,1,1);
			index.add(hb,0,2);
			index.add(personale,0,3);
		}
			catch(Exception e){
				e.printStackTrace();
			}
	}
}
