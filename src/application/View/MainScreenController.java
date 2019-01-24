package application.View;

import java.io.IOException;
import java.text.DecimalFormat;

import application.Model.Customer;
import application.Model.DatabaseHelper;
import application.Model.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class MainScreenController {

    
	DatabaseHelper dh = new DatabaseHelper();
	@FXML 
	public BorderPane mainLayout;
	
	@FXML
	public TextField searchByTransactionIDTextField;
	@FXML
	public Button searchByTransactionIDTextFieldSearchButton;
	@FXML 
	public Label testLabel;
	
	//Menus
	@FXML
	public MenuItem goToCustomer;
	public MenuItem goToInventory;
	
	//Inside customer section
	@FXML
	public Label customerStatusLabel;	
	
	@FXML
	public TextField customerIdSearchTextField;
	@FXML
	public Button customerIdSearchButton;
	@FXML
	public Button addCustomerButton;
	@FXML
	public  Button cancelAddCustomerButton;
	
	
	@FXML
	public void customerIdSearchButtonPressed() throws IOException {
		String searchId = customerIdSearchTextField.getText();
		Customer customer = dh.findCustomer(searchId);
		if(customer == null) {
			customerNotFound();
		}
		else {
			
			customerFound(customer);
		}
	}
	
	//customer details section
	@FXML
	public Label customerResultNameLabel;
	@FXML
	public Label customerResultIdLabel;
	@FXML
	public Label customerResultNumberSalesLabel;
	@FXML
	public Label customerResultDiscountLabel;
	@FXML
	public Label customerResultDiscountAmount;
	
	//inside customer cart section
	@FXML
	public Label customerCartIdLabel;
	@FXML
	public Label customerCartDescriptionLabel;
	@FXML 
	public Label customerCartPriceLabel;
	@FXML
	public Button customerCartAddButton;
	
	@FXML
	public Spinner<Integer> amountSpinner;
	Item addItem = null;
	
	
	//inside total section
	@FXML
	public Label totalNumberOfItemsLabel;
	@FXML
	public Label totalSubTotalLabel;
	@FXML
	public Label totalDiscountLabel;
	@FXML
	public Label totalTaxLabel;
	@FXML
	public Label totalTotalLabel;
	@FXML
	public Button totalCompleteButton;
	@FXML
	public Button totalCancelButton;
	
	int totalNumberOfItems = 0;
	int numberToSubtractFromDatabase=0;
	double subTotal = 0.0;
	double discountAmount = 0.0;
	double tax = 0.0;
	double total = 0.0;
	
	//review section
	@FXML
	public Button reviewRemoveButton;
	ObservableList<Item> reviewItems = FXCollections.observableArrayList();
	
	
	
	
	//item selection section
	@FXML
	public TableView<Item> itemTableView = new TableView<Item>();
	@FXML
	public TableColumn<Item,Integer> itemIdTableColumn = new TableColumn<Item,Integer>();
	@FXML
	public TableColumn<Item,String> itemDescriptionColumn = new TableColumn<Item,String>();
	@FXML
	public TableColumn<Item,Double> itemPriceColumn = new TableColumn<Item,Double>();
	@FXML
	public TableColumn<Item,Integer> quantityColumn = new TableColumn<Item,Integer>();
	ObservableList<Item> items = FXCollections.observableArrayList();
	
	@FXML
	public void initialize() {
	itemIdTableColumn.setCellValueFactory(new PropertyValueFactory<Item,Integer>("id"));
	itemDescriptionColumn.setCellValueFactory(new PropertyValueFactory<Item,String>("description"));
	itemPriceColumn.setCellValueFactory(new PropertyValueFactory<Item,Double>("price"));
	quantityColumn.setCellValueFactory(new PropertyValueFactory<Item,Integer>("quantity"));
	
	refreshTable();
	}
	
	public void refreshTable()
	{
		items = dh.getAllItems();
		itemTableView.getItems().setAll(items);
	}
	
	//if customer not found
	public void customerNotFound()
	{
		customerStatusLabel.setText("NOT FOUND");
		addCustomerButton.setDisable(false);
		cancelAddCustomerButton.setDisable(false);
		
	}
	@FXML
	public void addCustomerButtonPressed(Event event) throws IOException {
		customerStatusLabel.setText("");
		customerIdSearchTextField.setText("");
		goToCustomer(event);
		
	}
	//if customer found
	public void customerFound(Customer customer)
	{
		customerResultIdLabel.setText(Integer.toString(customer.getId()));
		customerResultNameLabel.setText(customer.getName());
		customerResultNumberSalesLabel.setText(Integer.toString(customer.getNumberOfSales()));
		
	}
	
	public void goToCustomer(Event event) throws IOException {
		
		Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("application/view/customerWindow.fxml"));
		Scene scene = new Scene(parent);
		Stage window = (Stage)(customerResultIdLabel.getScene().getWindow());
		
		window.setTitle("Customer Management");
		window.setFullScreen(true);
		window.setResizable(false);
		
		window.setScene(scene);
		window.show();

		}
	public void gotToInventory() throws IOException {
		Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("application/view/inventoryManagementScreen.fxml"));
		Scene scene = new Scene(parent);
		Stage window = (Stage)(customerResultIdLabel.getScene().getWindow());
		
		window.setTitle("Inventory Management");
		window.setFullScreen(true);
		window.setResizable(false);
		
		window.setScene(scene);
		window.show();
	}
	
	public void onTableClicked() {
		Item item = itemTableView.getSelectionModel().getSelectedItem();
		customerCartIdLabel.setText(Integer.toString(item.getId()));
		customerCartDescriptionLabel.setText(item.getDescription());
		customerCartPriceLabel.setText(Double.toString(item.getPrice()));
		int max = item.getQuantity();
		SpinnerValueFactory<Integer>quantityValueFactory = 
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0,max,0);
		this.amountSpinner.setValueFactory(quantityValueFactory);
		addItem = item;
		
	}
	public void customerCartAddButtonPressed() {
		itemTableView.getSelectionModel().clearSelection();
		reviewItems.add(addItem);
		totalNumberOfItems += (int)amountSpinner.getValue();
		numberToSubtractFromDatabase = (int)amountSpinner.getValue();
		subTotal += (addItem.getPrice() * (int)amountSpinner.getValue());
		//discount needs to determined here from customer data
		tax = subTotal* 0.06;
		total = (subTotal + tax);
		
		//set labels in total section
		totalNumberOfItemsLabel.setText(Integer.toString(totalNumberOfItems));
		DecimalFormat dec = new DecimalFormat("#0.00");
		
		totalSubTotalLabel.setText(dec.format(subTotal));
		//set discount label later
		totalTaxLabel.setText(dec.format(tax));
		totalTotalLabel.setText(dec.format(total));
		//subtract quantity from quantity in itemTable for the added item.
		dh.subtractItems(addItem.getId(),numberToSubtractFromDatabase);
		refreshTable();
		
		clearLabelsCustomerCart();
		
		
		
	}
	
	public void clearLabelsCustomerCart() {
		customerCartIdLabel.setText("");
		customerCartDescriptionLabel.setText("");
		customerCartPriceLabel.setText("");
		amountSpinner.getValueFactory().setValue(0);
	}
}
