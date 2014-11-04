package chart;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import com.sun.media.jfxmedia.events.NewFrameEvent;

import application.MainPageAdmin;
import DataBaseBilancio.StorageBilancio;
import GestioneAmministrazione.Bilancio;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class BilancioChart {
	
	public void start() throws ClassNotFoundException, FileNotFoundException, SQLException, IOException{
		
		StageStyle stile = null;
		Stage stage=new Stage(stile.TRANSPARENT);
		
		
		stage.setTitle("Bilancio");
		
		final CategoryAxis axisY= new CategoryAxis();
		final NumberAxis axisX= new NumberAxis();
		
		axisX.setLabel("Mese");
		
		final LineChart<String, Number> lineChart = new LineChart<String, Number>(axisY, axisX);
		lineChart.setTitle("Andamento mese per mese");
		
		StorageBilancio sb = new StorageBilancio();
		XYChart.Series series1= new XYChart.Series();
		series1.setName("valori i entrata");
		lineChart.setCursor(Cursor.NONE);
		ObservableList<Bilancio> bilancioList= sb.getElencoBilancio();
		
		for(int i = 0; i<bilancioList.size();i++){
			
			series1.getData().add(new XYChart.Data(bilancioList.get(i).getTipo(),bilancioList.get(i).getValore()));
		}
		
		Reflection reflect= new Reflection();
		DropShadow shadow= new DropShadow();
		shadow.setOffsetX(5.0);
		shadow.setOffsetY(5.0);
		shadow.setColor(Color.BLACK);
		TitledPane gridTitlePane = new TitledPane();
		GridPane grid = new GridPane();
		grid.setVgap(4);
		grid.setPadding(new Insets(5, 5, 5, 5));
		grid.add(new Label("Codice "), 0, 0);
		TextField codg= new TextField();
		codg.setCursor(Cursor.TEXT);
		grid.add(codg, 1, 0);
		grid.add(new Label("Mese: "), 0, 1);
		TextField messg= new TextField();
		messg.setCursor(Cursor.TEXT);
		grid.add(messg, 1, 1);
		grid.add(new Label("Valore: "), 0, 2);
		TextField valg= new TextField();
		valg.setCursor(Cursor.TEXT);
		grid.add(valg, 1, 2);        
		gridTitlePane.setText("Inserisci bilancio");
		
		Button ok = new Button();
		ok.setText("OK");
		ok.setCursor(Cursor.HAND);
		ok.setEffect(reflect);
		ok.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
			try {
				 
				double valore= Double.parseDouble(valg.getText());
				Bilancio b = new Bilancio(codg.getText(),messg.getText(),valore);
				sb.inserisciBilancio(b);
				series1.getData().add(new XYChart.Data(b.getTipo(),b.getValore()));
				codg.clear();
				messg.clear();
				valg.clear();
			} catch (NumberFormatException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			}
		});
		Button annulla = new Button();
		annulla.setEffect(reflect);
		annulla.setCursor(Cursor.CLOSED_HAND);
		annulla.setText("Annulla");
		annulla.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				MainPageAdmin mpa= new MainPageAdmin();
				mpa.start();
				stage.close();
				
			}
		});
		lineChart.getData().addAll(series1);
		
		
		gridTitlePane.setContent(grid);
		gridTitlePane.setEffect(reflect);
	
		lineChart.setEffect(shadow);
		HBox Hb=new HBox(10);
		Hb.getChildren().addAll(gridTitlePane,ok,annulla);
		VBox Vb= new VBox(10);
		Hb.setAlignment(Pos.CENTER);
		Vb.getChildren().addAll(lineChart,Hb);
		
		Scene scene = new Scene(Vb,800,400);
		scene.getStylesheets().add(getClass().getResource("Logon.css").toExternalForm());
		
		stage.setScene(scene);
		
		stage.show();
		
	}

}
