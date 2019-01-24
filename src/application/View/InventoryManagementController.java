package application.View;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import application.Model.DatabaseHelper;
import application.Model.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
	public Button removeItemButton;
	@FXML
	public Button modifyItemButton;
	
	
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
	
	
	ObservableList<Item> items = FXCollections.observableArrayList();
	DatabaseHelper dh = new DatabaseHelper();
	Boolean isUpdate = false;
	
	@FXML
	public void initialize() {
		idColumn.setCellValueFactory(new PropertyValueFactory<Item,Integer>("id"));
		descriptionColumn.setCellValueFactory(new PropertyValueFactory<Item,String>("description"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<Item,Double>("Price"));
		quantityColumn.setCellValueFactory(new PropertyValueFactory<Item,Integer>("quantity"));
		minQuantityColumn.setCellValueFactory(new PropertyValueFactory<Item,Integer>("minQuantity"));
		maxQuantityColumn.setCellValueFactory(new PropertyValueFactory<Item,Integer>("maxQuantity"));
		
		refreshTable();
		
	}
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
public void refreshTable() {
	items = dh.getAllItems();
	itemTableView.getItems().setAll(items);
}
	
 public  void removeItemButtonPressed()
 {
	 Alert alert = new Alert(AlertType.CONFIRMATION);
	 alert.setTitle("Remove Customer?");
	 alert.setHeaderText("Remove Customer?");
	 alert.setContentText("Remove Customer");
	 
	 Optional<ButtonType> result = alert.showAndWait();
	 if(result.get() == ButtonType.OK) {
		 Item item = (Item)itemTableView.getSelectionModel().getSelectedItem();
		 dh.removeItem(item);
		 refreshTable();
	 }
	 else {
		 itemTableView.getSelectionModel().clearSelection();
	 }
	
 }
 public void modifyItemButtonPressed() {
	 Alert alert = new Alert(AlertType.CONFIRMATION);
	 alert.setTitle("Modify Item");
	 alert.setHeaderText("Modify Item ?");
	 alert.setContentText("Would you like to modify item information ?");
	 Optional<ButtonType> result = alert.showAndWait();
	 if(result.get() == ButtonType.OK) {
		 Item item = (Item) itemTableView.getSelectionModel().getSelectedItem();
		 itemIdTextField.setText(Integer.toString(item.getId()));
		 itemDescriptionTextField.setText(item.getDescription());
		 itemPriceTextField.setText(Double.toString(item.getPrice()));
		 quantityTextField.setText(Integer.toString(item.getQuantity()));
		 minQuantityTextField.setText(Integer.toString(item.getMinQuantity()));
		 maxQuantityTextField.setText(Integer.toString(item.getMaxQuantity()));
		 isUpdate = true;
	 }
 }
		 
 public void addItemButtonPressed() {
	 Item item = new Item(Integer.parseInt(itemIdTextField.getText()),itemDescriptionTextField.getText(),
			 Double.parseDouble(itemPriceTextField.getText()),Integer.parseInt(quantityTextField.getText()),
			 Integer.parseInt(minQuantityTextField.getText()),Integer.parseInt(maxQuantityTextField.getText()));
	 
	 if(isUpdate == true) {
		 dh.updateItem(item);
		 isUpdate = false;
	 }
	 else if(isUpdate == false){
		 try {
		 dh.addItem(item);
		 }catch(SQLException e) {
			
			 int error = e.getErrorCode();
			 System.out.println(e.getErrorCode());
			 System.out.println("error code = "+error);
			 if(error == 30000) {
				 System.out.println("inside if");
				 Alert alert = new Alert(AlertType.ERROR);
				 alert.setTitle("Error item with id = "+item.getId()+" already exists");
				 alert.setHeaderText("Error item whith  id = "+item.getId()+ " already exists");
				 alert.setContentText("Please change item id number");
				 Optional<ButtonType>result = alert.showAndWait();
				 if(result.get() == ButtonType.OK) {
					 itemIdTextField.clear();
				 }
			 }
		 }
	 }
	 refreshTable();
 }
 public void cancelButtonPressed() {
	 itemIdTextField.clear();
	itemDescriptionTextField.clear();
	itemPriceTextField.clear();
	quantityTextField.clear();
	minQuantityTextField.clear();
	maxQuantityTextField.clear();
	if(isUpdate == true) {
		isUpdate = false;
	}
	 
 }

}
