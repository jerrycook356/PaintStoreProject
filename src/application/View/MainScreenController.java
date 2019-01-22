package application.View;

import application.Model.DatabaseHelper;
import application.Model.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainScreenController {

	
	DatabaseHelper dh = new DatabaseHelper();
	@FXML
	public TextField searchByTransactionIDTextField;
	@FXML
	public Button searchByTransactionIDTextFieldSearchButton;
	@FXML 
	public Label testLabel;
	
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
	public Button cancelAddCustomerButton;
	
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
	public Label customerCartDescriptionLabel;
	@FXML 
	public Label customerCartPriceLabel;
	@FXML
	public Button customerCartAddButton;
	@FXML
	public Button customerCartClearButton;
	
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
	
	//review section
	@FXML
	public Button reviewRemoveButton;
	
	
	
	
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
	
}
