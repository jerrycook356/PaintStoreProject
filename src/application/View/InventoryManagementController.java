package application.View;

import java.io.IOException;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class InventoryManagementController {

	@FXML
	public TextField itemIdTextField;
	
	@FXML
	public TextField itemDescriptionTextField;
	
	@FXML
	public TextField itemPriceTextField;
		
	@FXML
	public TextField quantityTextField;
	
	@FXML
	public TextField minQuantityTextField;
	
	@FXML
	public TextField maxQuantityTextField;
	
	@FXML
	public Button addItemButton;
	
	@FXML
	public Button cancelButton;
	
	@FXML
	public MenuItem goToMainScreen;
	
	@FXML
	public MenuItem goToCustomerManagement;
	
	@FXML
	public TableView itemTableView;
	
	@FXML
	public TableColumn idColumn;
	@FXML
	public TableColumn descriptionColumn;
	@FXML
	public TableColumn priceColumn;
	@FXML
	public TableColumn quantityColumn;
	@FXML
	public TableColumn minQuantityColumn;
	@FXML
	public TableColumn maxQuantityColumn;
	
	
	
public void goToCustomer(Event event) throws IOException {
		
		Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("application/view/customerWindow.fxml"));
		Scene scene = new Scene(parent);
		Stage window = (Stage)(itemIdTextField.getScene().getWindow());
		
		window.setTitle("Customer Management");
		window.setFullScreen(true);
		window.setResizable(false);
		
		window.setScene(scene);
		window.show();

		}
	
public void goToMain(Event event) {
	try {
	Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("application/view/MainScreen.fxml"));
	Scene scene = new Scene(parent);
	Stage window = (Stage)(itemIdTextField.getScene().getWindow());
	
	window.setTitle("Main Screen");
	window.setFullScreen(true);
	window.setResizable(false);
	
	window.setScene(scene);
	window.show();
	}catch(Exception e) {
		e.printStackTrace();
	}
	
}
	
}
