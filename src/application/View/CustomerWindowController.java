package application.View;


import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import application.Model.Customer;
import application.Model.DatabaseHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class CustomerWindowController {


	@FXML
	public TextField nameTextField;
	@FXML
	public TextField streetTextField;
	@FXML
	public TextField cityTextField;
	@FXML
	public TextField stateTextField;
	@FXML
    public TextField zipTextField;
	@FXML
	public TextField phoneNumberTextField;
	@FXML
	public Button addCustomerButton;
	@FXML
	public Button cancelButton;
	@FXML
	public TableView<Customer> customerTableView;
	@FXML
	public TableColumn<Customer,Integer>idColumn;
	@FXML
	public TableColumn<Customer,String>nameColumn;
	@FXML
	public TableColumn<Customer,String>streetColumn;
	@FXML
	public TableColumn<Customer,String>cityColumn;
	@FXML
	public TableColumn<Customer,Integer> zipColumn;
	@FXML
	public TableColumn<Customer,Long> phoneNumberColumn;
	@FXML
	public TableColumn<Customer, Integer>numberOfSalesColumn;
	@FXML
	public TableColumn<Customer,String> stateColumn;
	@FXML 
	public Button removeCustomerButton;
	@FXML 
	public Button modifyCustomerButton;
	
	
	ObservableList<Customer> customers = FXCollections.observableArrayList();
	DatabaseHelper dh = new DatabaseHelper();
	Boolean isUpdate = false;
	int customerUpdateId;
	int customerUpdateSales;
	
	@FXML
	public void initialize() {
	
		idColumn.setCellValueFactory(new PropertyValueFactory<Customer,Integer>("id"));
		nameColumn.setCellValueFactory(new PropertyValueFactory<Customer,String>("name"));
		streetColumn.setCellValueFactory(new PropertyValueFactory<Customer,String>("street"));
		cityColumn.setCellValueFactory(new PropertyValueFactory<Customer,String>("city"));
		stateColumn.setCellValueFactory(new PropertyValueFactory<Customer,String>("state"));
		zipColumn.setCellValueFactory(new PropertyValueFactory<Customer,Integer>("zip"));
		phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<Customer,Long>("phoneNumber"));
		numberOfSalesColumn.setCellValueFactory(new PropertyValueFactory<Customer,Integer>("numberOfSales"));
		
		refreshTable();
	}
	
	public void refreshTable() {	
		
		customers = dh.getAllCustomers();
		customerTableView.getItems().setAll(customers);
	}
	
	@FXML
	public void cancelButtonPressed(Event event) throws IOException
	{
		
		Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("application/view/MainScreen.fxml"));
		Scene scene = new Scene(parent);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setFullScreen(true);
		window.setResizable(false);
		
		window.setScene(scene);
		window.show();
	}
	@FXML
	public void AddButtonPressed()
	{
		Customer customer = new Customer(nameTextField.getText(),streetTextField.getText(),
				cityTextField.getText(),stateTextField.getText(),Integer.parseInt(zipTextField.getText()),
				Long.parseLong(phoneNumberTextField.getText()),0);
		if(isUpdate == true)
		{
			Customer customer2 = new Customer(customerUpdateId,nameTextField.getText(),streetTextField.getText(),
					cityTextField.getText(),stateTextField.getText(),Integer.parseInt(zipTextField.getText()),
					Long.parseLong(phoneNumberTextField.getText()),customerUpdateSales);
			dh.updateCustomer(customer2);
			clearTextFields();
			customerUpdateId = 0;
			customerUpdateSales = 0;
			isUpdate = false;
		}
		else {
		dh.addCustomer(customer);
		clearTextFields();	
		}
			
		refreshTable();
		
	}
	public void clearTextFields()
	{
		nameTextField.clear();
		streetTextField.clear();
		cityTextField.clear();
		stateTextField.clear();
		zipTextField.clear();
		phoneNumberTextField.clear();
		
	}
	public void removeCustomerButtonPressed() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Delete Customer ?");
		alert.setHeaderText("Delete Customer ?");
		alert.setContentText("Delete Customer ?");
		
		Optional<ButtonType>result=alert.showAndWait();
		if(result.get()==ButtonType.OK) {
		Customer customer = (Customer) customerTableView.getSelectionModel().getSelectedItem();
		dh.removeCustomer(customer);
		}
		else {
			customerTableView.getSelectionModel().clearSelection();
		}
		refreshTable();
	}
	
public void goToInventory(Event event) throws IOException {
		
		Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("application/view/inventoryManagementScreen.fxml"));
		Scene scene = new Scene(parent);
		Stage window = (Stage)(nameTextField.getScene().getWindow());
		
		window.setTitle("Inventory Management");
		window.setFullScreen(true);
		window.setResizable(false);
		
		window.setScene(scene);
		window.show();

		}
public void goToMainScreen(Event event) throws IOException {
	
	Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("application/view/MainScreen.fxml"));
	Scene scene = new Scene(parent);
	Stage window = (Stage)(nameTextField.getScene().getWindow());
	
	window.setTitle("Main Screen");
	window.setFullScreen(true);
	window.setResizable(false);
	
	window.setScene(scene);
	window.show();

	}

public void modifyCustomerButtonPressed()
{
	
	Alert alert = new Alert(AlertType.CONFIRMATION);
	alert.setTitle("Mofidy Customer ?");
	alert.setHeaderText("Modify Customer ?");
	alert.setContentText("Would you like to modify customter?");
	Optional<ButtonType>result = alert.showAndWait();
	if(result.get() == ButtonType.OK) {
		Customer customer =(Customer) customerTableView.getSelectionModel().getSelectedItem();
		
		nameTextField.setText(customer.getName());
		streetTextField.setText(customer.getStreet());
		cityTextField.setText(customer.getCity());
		stateTextField.setText(customer.getState());
		zipTextField.setText(Integer.toString(customer.getZip()));
		phoneNumberTextField.setText(Long.toString(customer.getPhoneNumber()));
		isUpdate = true;
		customerUpdateId = customer.getId();
		customerUpdateSales = customer.getNumberOfSales();
	}
	
}
}
