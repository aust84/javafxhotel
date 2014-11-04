package application;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;



public class Main extends Application {

	final Float[] values = new Float[] {-1.0f, 0f, 0.6f, 1.0f};
	final Label [] labels = new Label[values.length];
	final ProgressBar[] pbs = new ProgressBar[values.length];
	final ProgressIndicator[] pins = new ProgressIndicator[values.length];
	final HBox[] hbs = new HBox[values.length];
	@Override
	public void start(Stage primaryStage) {
		try {
			
			Group root = new Group();
	        Scene scene = new Scene(root, 300, 150);
	        //scene.getStylesheets().add("progresssample/Style.css");
	        primaryStage.setScene(scene);
	/*        primaryStage.setTitle("Please Wait... ");
	 
	 
	        for (int i = 0; i < values.length; i++) {
	            Label label = labels[i] = new Label();
	            label.setText("progress:" + values[i]);
	 
	            ProgressBar pb = pbs[i] = new ProgressBar();
	            pb.setProgress(values[i]);
	 
	            ProgressIndicator pin = pins[i] = new ProgressIndicator();
	            pin.setProgress(values[i]);
	            final HBox hb = hbs[i] = new HBox();
	            hb.setSpacing(5);
	            hb.setAlignment(Pos.CENTER);
	            hb.getChildren().addAll(label, pb, pin);
	        }
	 
	        final VBox vb = new VBox();
	        vb.setSpacing(5);
	        vb.getChildren().addAll(hbs);
	        scene.setRoot(vb);
	        primaryStage.show();*/
			Login log= new Login();
			log.start();
	
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
