package application;
	

import application.Model.DatabaseHelper;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {
	
	private Stage primaryStage;
	private BorderPane MainLayout;
	
	@Override
	public void start(Stage primaryStage) {
		DatabaseHelper dh = new DatabaseHelper();
		dh.setUpItemTable();
		dh.setUpCustomerTable();
		try {
			this.primaryStage = primaryStage;
			this.primaryStage.setTitle("The Greatest Paint Store: Main Screen");
			
			System.out.println("after setup");
			initMainLayout();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	public void initMainLayout()
	{
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/MainScreen.fxml"));
			MainLayout = (BorderPane)loader.load();
			Scene scene = new Scene(MainLayout);
			primaryStage.setMaximized(true);
			primaryStage.setResizable(false);
			
			//primaryStage.setMinHeight(1280);
			//primaryStage.minWidthProperty().bind(scene.heightProperty().multiply(1.25));
			primaryStage.setScene(scene);
			primaryStage.show();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public Stage getPrimaryStage()
	{
		return primaryStage;
	}
	public static void main(String[] args) {
		launch(args);
	}
	
	
}
