package application;
import java.io.IOException;
import java.sql.SQLException;

import Amministrazione.Administrator_Gui;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.Separator;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import chart.BilancioChart;
import clienti.Clienti_GUI;

public class MainPageAdmin {

	public void start() {
		try {
			
			StageStyle stile = null;
			Stage primaryStage=new Stage(stile.TRANSPARENT);
			primaryStage.setFullScreen(true);
			GridPane index= new GridPane();
			index.setAlignment(Pos.CENTER);
			index.setVgap(10);
			index.setHgap(10);
			index.setPadding(new Insets(25, 25, 25, 25));
			
			//StackPane index= new StackPane();
			//BorderPane root = new BorderPane();
			Scene scene = new Scene(index,800,600);
			scene.getStylesheets().add(getClass().getResource("Logon.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			DropShadow shadow = new DropShadow();
			shadow.setOffsetX(5.0);
			shadow.setOffsetY(5.0);
			shadow.setColor(Color.BLACK);
			Reflection reflect= new Reflection();
			
			Text scenetitle= new Text("MyH@tel Administrator");
			scenetitle.setFont(Font.font("Tahoma",FontWeight.BOLD,30));
			scenetitle.setEffect(reflect);
			index.add(scenetitle,0, 0,2,1);
			
			
			Image img = new Image(getClass().getResource("hotel-cattolica.jpg").toExternalForm());
			ImageView imgV= new ImageView();
			imgV.setImage(img);
			index.add(imgV, 0, 2);
			
			Image persbtn= new Image(getClass().getResource("taxi.jpg").toExternalForm());
			Button person = new Button("Amministrazione",new ImageView(persbtn));
			person.setEffect(reflect);
			person.setCursor(Cursor.CLOSED_HAND);
			HBox Hblbnt= new HBox(10);
			Hblbnt.setAlignment(Pos.BOTTOM_RIGHT);
			Hblbnt.getChildren().add(person);
			index.add(Hblbnt, 1,7);
			person.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					Administrator_Gui admin = new Administrator_Gui();
					admin.start();
					primaryStage.close();
					
				}
			});
			
			Image admbtn= new Image(getClass().getResource("clienti.jpg").toExternalForm());
			Button admin = new Button("Bilancio",new ImageView(admbtn));
			admin.setEffect(reflect);
			admin.setCursor(Cursor.CLOSED_HAND);
			HBox Hbrbnt=new HBox(10);
			Hbrbnt.setAlignment(Pos.BOTTOM_LEFT);
			Hbrbnt.getChildren().add(admin);
			index.add(Hbrbnt, 0, 7);
			admin.setOnAction(new EventHandler<ActionEvent>(){
				
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
			});
		
			
			Image logoffbtn= new Image(getClass().getResource("logout.png").toExternalForm());
			Button logoff = new Button();
			logoff.setEffect(shadow);
			logoff.setCursor(Cursor.CROSSHAIR);
			logoff.setGraphic(new ImageView(logoffbtn));
			HBox Hbfbnt= new HBox(10);
			Hbfbnt.setAlignment(Pos.BOTTOM_RIGHT);
			Hbfbnt.getChildren().add(logoff);
			index.add(Hbfbnt, 1,0);
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
				
					/*Stage stage= new Stage();
					stage.setHeight(400);
					stage.setHeight(800);
					Main main =new Main();
					main.start(stage);*/
					
				}
			});
			final Separator separator= new Separator();
			separator.setMaxWidth(300);
			final Separator separator2= new Separator();
			separator2.setMaxWidth(300);
			index.add(separator,0, 1);
			index.add(separator2,0, 6);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
		
