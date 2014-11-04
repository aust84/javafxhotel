package Alert;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Alert{

//private Stage dialogStage;



public Scene msgBox(String title){
	
    StageStyle stile=null;
	
    
    GridPane grd_pan = new GridPane();
    grd_pan.setAlignment(Pos.CENTER);
    grd_pan.setHgap(10);
    grd_pan.setVgap(10);//pading
    Scene scene =new Scene(grd_pan,300,150);
    scene.getStylesheets().add(getClass().getResource("Logon.css").toExternalForm());
//    scene.setPadding(new Insets(10));
    //dialogStage.setScene(scene);
    //dialogStage.setTitle("alert");
    //dialogStage.initModality(Modality.APPLICATION_MODAL);

    Label lab_alert= new Label(title);
    grd_pan.add(lab_alert, 0, 1);

    /*Button btn_ok = new Button("OK");
    btn_ok.setCursor(Cursor.CLOSED_HAND);
    btn_ok.setOnAction(new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent arg0) {
            // TODO Auto-generated method stub
            //dialogStage.close();

        }
    });
    grd_pan.add(btn_ok, 0, 2);
*/
    return scene;
    //dialogStage.toFront();
    //dialogStage.show();
    

}
}
