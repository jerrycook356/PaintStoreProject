package application.Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class Customer {

	private  SimpleIntegerProperty id;
	private final SimpleStringProperty name;
	private final SimpleStringProperty street;
	private final SimpleStringProperty city;
	private final SimpleStringProperty state;
	private final SimpleIntegerProperty zip;
	private final SimpleLongProperty phoneNumber;
	private final SimpleIntegerProperty numberOfSales;
	
	
	
	public Customer(String name, String street, String city, String state, int zip, Long phoneNumber,
			int numberOfSales) {
		
		
		this.name = new SimpleStringProperty(name);
		this.street = new SimpleStringProperty(street);
		this.city = new SimpleStringProperty(city);
		this.state = new SimpleStringProperty(state);
		this.zip = new SimpleIntegerProperty(zip);
		this.phoneNumber = new SimpleLongProperty(phoneNumber);
		this.numberOfSales = new SimpleIntegerProperty(numberOfSales);
		
	}
	
	public Customer(int id, String name, String street, String city, String state, int zip, Long phoneNumber,int numberOfSales)
	{
		this.id = new SimpleIntegerProperty(id);
		this.name= new SimpleStringProperty(name);
		this.street = new SimpleStringProperty(street);
		this.city = new SimpleStringProperty(city);
		this.state = new SimpleStringProperty(state);
		this.zip = new SimpleIntegerProperty(zip);
		this.phoneNumber = new SimpleLongProperty(phoneNumber);
		this.numberOfSales = new SimpleIntegerProperty(numberOfSales);
	}
	
	public int getNumberOfSales()
	{
		return numberOfSales.get();
	}
	public void setNumberOfSales(int numberOfSales)
	{
		this.numberOfSales.set(numberOfSales);
	}
	public int getId() {
		return id.get();
	}
	public void setId(int id) {
		this.id.set(id);
	}
	public String getName() {
		return name.get();
	}
	public void setName(String name) {
		this.name.set(name);
	}
	public String getStreet() {
		return street.get();
	}
	public void setStreet(String street)
	{
		this.street.set(street);
	}
	public String getCity() {
		return city.get();
	}
	public void setCity(String city) {
		this.city.set(city);
	}
	public String getState() {
		return state.get();
	}
	
	public void setState(String state) {
		this.state.set(state);
	}
	
	public int getZip() {
		return zip.get();
	}
	
	public void setZip(int zip) {
		this.zip.set(zip);
	}
	public Long getPhoneNumber() {
		return phoneNumber.get();
	}
	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber.set(phoneNumber);
	}
}
