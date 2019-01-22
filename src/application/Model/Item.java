package application.Model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Item {
	
  public final SimpleIntegerProperty id;
  private final SimpleStringProperty description;
  private final SimpleDoubleProperty price;
  private final SimpleIntegerProperty quantity;
  private final SimpleIntegerProperty minQuantity;
  private final SimpleIntegerProperty maxQuantity;
  
  
  public Item(int id, String description, double price, int quantity, int minQuantity, int maxQuantity)
  {
	  this.id = new SimpleIntegerProperty(id);
	  this.description = new SimpleStringProperty(description);
	  this.price = new SimpleDoubleProperty(price);
	  this.quantity = new SimpleIntegerProperty(quantity);
	  this.minQuantity = new SimpleIntegerProperty(minQuantity);
	  this.maxQuantity = new SimpleIntegerProperty(maxQuantity);
  }


public int getId() {
	return id.get();
}


public String getDescription() {
	return description.get();
}


public double getPrice() {
	return price.get();
}


public int getQuantity() {
	return quantity.get();
}


public int getMinQuantity() {
	return minQuantity.get();
}


public int getMaxQuantity() {
	return maxQuantity.get();
}
  
public void setId(int id)
{
	this.id.set(id);
}

public void setPrice(double price) {
	this.price.set(price);
}
  public void setDescription(String description) {
	  this.description.set(description);
  }
  
  public void setQuantity(int quantity) {
	  this.quantity.set(quantity);
  }
  
  public void setMinQuantity(int minQuantity) {
	  this.minQuantity.set(minQuantity);
  }
  
  public void setMaxQuantity(int maxQuantity) {
	  this.maxQuantity.set(maxQuantity);
  }
  
}
