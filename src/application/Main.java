package application;
	

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import application.Model.DatabaseHelper;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
	
	private Stage primaryStage;
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		DatabaseHelper dh = new DatabaseHelper();
		dh.setUpItemTable();
		dh.setUpCustomerTable();
		
		this.primaryStage = primaryStage;
		Parent root = FXMLLoader.load(getClass().getResource("view/MainScreen.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setMaximized(true);
		primaryStage.setResizable(false);
		
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
	}
	
	@Override
	public void stop() {
		try {
			DriverManager.getConnection("jdbc:derby:paintshopdatabase;shutdown=true");
			System.out.println("closed");
				   
		}catch(SQLException e) {
			e.printStackTrace();
			//System.out.println("shutdown exception");
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
