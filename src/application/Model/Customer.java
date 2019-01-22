package application.Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Customer {

	private final SimpleIntegerProperty id;
	private final SimpleStringProperty name;
	private final SimpleStringProperty street;
	private final SimpleStringProperty city;
	private final SimpleStringProperty state;
	private final SimpleIntegerProperty zip;
	private final SimpleIntegerProperty phoneNumber;
	
	public Customer(int id, String name, String street, String city, String state, int zip, int phoneNumber) {
		
		this.id = new SimpleIntegerProperty(id);
		this.name = new SimpleStringProperty(name);
		this.street = new SimpleStringProperty(street);
		this.city = new SimpleStringProperty(city);
		this.state = new SimpleStringProperty(state);
		this.zip = new SimpleIntegerProperty(zip);
		this.phoneNumber = new SimpleIntegerProperty(phoneNumber);
		
	}
	
	
	public int getId() {
		return id.get();
	}
	public String getName() {
		return name.get();
	}
	public String getStreet() {
		return street.get();
	}
	public String getCity() {
		return city.get();
	}
	public String geState() {
		return state.get();
	}
	
	public int getZip() {
		return zip.get();
	}
	public int getPhoneNumber() {
		return phoneNumber.get();
	}
}
