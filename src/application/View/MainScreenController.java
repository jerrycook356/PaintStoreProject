package application.View;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class MainScreenController {

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
	
	
	
	
}
