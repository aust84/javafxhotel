package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import chart.BilancioChart;
import DataBaseAmministratore.StorageAmministrazione;
import DataBaseAmministratore.StoragePersonale;
import DataBaseCliente.StorageCliente;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.GroupBuilder;
import javafx.scene.Scene;
import javafx.scene.SceneBuilder;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import databaseconnector.*;

public class Login {

	TextField userField= new TextField();
	PasswordField pwdField = new PasswordField();
	final GridPane index= new GridPane();
	static Text actionevent= new Text();
	
	
	public void start() {
		try {
			
			//GridPane index= new GridPane();
			index.setAlignment(Pos.CENTER);
			index.setVgap(10);
			index.setHgap(10);
			index.setPadding(new Insets(25, 25, 25, 25));
			
			//StackPane index= new StackPane();
			BorderPane root = new BorderPane();
			Scene scene = new Scene(index,500,400);
			//scene.getStylesheets().add(getClass().getResource("Logon.css").toExternalForm());
			scene.getStylesheets().add(getClass().getResource("Logon.css").toExternalForm());
			//scene.setUserAgentStylesheet(STYLESHEET_MODENA);
			StageStyle stile = null;
			Stage primaryStage=new Stage(stile.TRANSPARENT);
			primaryStage.setFullScreen(true);
			primaryStage.setScene(scene);
			primaryStage.show();
			
			/*Text scenetitle= new Text("MyH@tel");
			scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			scenetitle.setId("intestazione");
			index.add(scenetitle,0, 0,2,1);*/
			
			Image imgtitle = new Image(getClass().getResource("logohotel.jpg").toExternalForm());
			ImageView imgTitleV= new ImageView();
			imgTitleV.setImage(imgtitle);
			index.add(imgTitleV,0,1);
			/*Image img = new Image(getClass().getResource("hotel-cattolica.jpg").toExternalForm());
			ImageView imgV= new ImageView();
			imgV.setImage(img);
			index.add(imgV, 0, 1);*/
			Reflection reflect=  new Reflection();
			DropShadow shadow = new DropShadow();
			shadow.setOffsetX(5.0);
			shadow.setOffsetY(5.0);
			shadow.setColor(Color.BLACK);
			
			scene.setCursor(Cursor.OPEN_HAND);
			imgTitleV.setEffect(shadow);
			
			Label userlabel= new Label("UserID");
			index.add(userlabel, 0, 3);
			
			userField.setPromptText("Insert UserID");
			index.add(userField,0,4);
			userField.setCursor(Cursor.TEXT);
			Label pwdlabel=new Label("Password");
			index.add(pwdlabel, 0, 5);
	
			pwdField.setPromptText("Insert Password");
			index.add(pwdField,0,6);
			pwdField.setCursor(Cursor.TEXT);
			
			Image okbtn= new Image(getClass().getResource("ok.png").toExternalForm());
			Button login = new Button("Enter",new ImageView(okbtn));
			login.setEffect(reflect);
			login.setDisable(true);
			login.setCursor(Cursor.HAND);
			login.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					try {
						if(EventControl(arg0))
							primaryStage.close();
							//primaryStage.close();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			pwdField.setOnKeyPressed(new EventHandler<KeyEvent>() {

				@Override
				public void handle(KeyEvent arg0) {
					// TODO Auto-generated method stub
					if(pwdField.getText().isEmpty())
						login.setDisable(true);
					else
						login.setDisable(false);		
				}
				
				
			});
			Image exitbtn= new Image(getClass().getResource("TastoX.png").toExternalForm());
			Button exit = new Button("Exit",new ImageView(exitbtn));
			exit.setEffect(reflect);
			exit.setCursor(Cursor.CLOSED_HAND);
			HBox Hbrbnt=new HBox(10);
			Hbrbnt.setAlignment(Pos.BOTTOM_RIGHT);
			Hbrbnt.getChildren().add(exit);
			index.add(Hbrbnt, 1, 8);
			exit.setOnAction(new EventHandler<ActionEvent>(){
				
				@Override
				public void handle(ActionEvent arg0){
					Uscita(arg0);
				}
			});
			
			HBox Hblbnt= new HBox(10);
			Hblbnt.setAlignment(Pos.BOTTOM_LEFT);
			Hblbnt.getChildren().addAll(login, exit);
			index.add(Hblbnt, 0,8);
	
			index.add(actionevent,0,7);
			DBConnector dbconnect=new DBConnector();
			dbconnect.openConnection();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		

		/*Image exitbtn= new Image(getClass().getResource("TastoX.png").toExternalForm());
		Button bilancio = new Button("bilncio",new ImageView(exitbtn));
		HBox Hbrbnb=new HBox(10);
		Hbrbnb.setAlignment(Pos.BASELINE_CENTER);
		Hbrbnb.getChildren().add(bilancio);
		index.add(Hbrbnb, 2, 8);
		bilancio.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent arg0){
				BilancioChart b= new BilancioChart();
				try {
					b.start();
				} catch (ClassNotFoundException | SQLException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});*/
		
	}

	public static void Uscita(ActionEvent arg0){
		System.exit(0);
	}
	
	public boolean EventControl(ActionEvent arg0) throws ClassNotFoundException, FileNotFoundException, SQLException, IOException{
		
		
		StorageAmministrazione sa = new StorageAmministrazione();
		
		String us=userField.getText();
		String pwd=pwdField.getText();
		if(sa.isCorrect(us,pwd)){
			
			actionevent.setText("corretto");
			actionevent.setFill(Color.LIME);
			MainPageAdmin avvio= new MainPageAdmin();
			userField.clear();
			pwdField.clear();
			avvio.start();
			return true;
		}
		
		else{
			
			StoragePersonale sc= new StoragePersonale();
			if(sc.isCorrect(us,pwd)){
				
				actionevent.setText("corretto");
				actionevent.setFill(Color.LIME);
				MainPage avvio= new MainPage();
				userField.clear();
				pwdField.clear();
				avvio.start();
				return true;
			
			}else{
				
				actionevent.setText("Password o User ID non corretti tiprovare");
				actionevent.setFill(Color.FIREBRICK);
				userField.clear();
				pwdField.clear();
				return false;
			}
		}
	}		
}
