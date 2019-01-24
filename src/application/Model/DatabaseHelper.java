package application.Model;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DatabaseHelper {
 ConnectionHelper ch = new ConnectionHelper();
	public DatabaseHelper() 
	{

	}
	
	//check if item table exists in the database
	//if it does not then create it
	public void setUpItemTable() {
		try(Connection con = ch.getConnection())
		{
			DatabaseMetaData dbmd = con.getMetaData();
			ResultSet rs = dbmd.getTables(null,null,"ITEMTABLE",null);
			if(rs.next())
			{
				
				testTable();
			}
			else
			{
				createItemTable();
			}
			rs.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	//create item table in the database if it does not exist
	public void createItemTable()
	{
		String sql = "CREATE TABLE ITEMTABLE(ID INT NOT NULL PRIMARY KEY, DESCRIPTION VARCHAR(100),PRICE DOUBLE,"+
	                  "QUANTITY INT, MINQUANTITY INT, MAXQUANTITY INT)";
		try(Connection con = ch.getConnection())
		{
			PreparedStatement ps = con.prepareStatement(sql);
			ps.executeUpdate();
			ps.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	//check if the customer table exists in the database
	//if not create the table
	public void setUpCustomerTable() {
		try(Connection con = ch.getConnection())
		{
			DatabaseMetaData dbmd = con.getMetaData();
			ResultSet rs = dbmd.getTables(null,null,"CUSTOMERTABLE",null);
			if(rs.next())
			{
				
				testTable();
			}
			else
			{
				createCustomerTable();
			}
			rs.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	//create the customer table in the database
	public void createCustomerTable()
	{
		
		String sql = "CREATE TABLE CUSTOMERTABLE"
				+ "(ID INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1)"
				+ " ,NAME VARCHAR(30),"+
	             "STREET VARCHAR(30),CITY VARCHAR(30),STATE VARCHAR(30),ZIP INT,PHONENUMBER BIGINT,"
	             + "NUMBEROFSALES INT)";
		try(Connection con = ch.getConnection()){
			PreparedStatement ps = con.prepareStatement(sql);
			ps.executeUpdate();
			ps.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	//test method to test the tables in the database.
	public void testTable()
	{
		
	/*	String addString3 = "INSERT INTO CUSTOMERTABLE VALUES(1,'jerry','street','city','ky',41230,606,1)";
		
		try(Connection con = ch.getConnection()){
			Statement stmt = con.createStatement();
			stmt.executeUpdate(addString3);			
		}catch(SQLException e) {
			e.printStackTrace();
		}	*/	
		
	}
	//get items for the item selection setting and return to MainScreenController to fill table
	public ObservableList<Item> getAllItems()
	{
		ObservableList<Item> items = FXCollections.observableArrayList();
		
		String sql = "SELECT * FROM ITEMTABLE";
		
		try(Connection con = ch.getConnection())
		{
			PreparedStatement ps = con.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				Item item =new Item(rs.getInt("ID"),rs.getString("DESCRIPTION"),
						rs.getDouble("PRICE"),rs.getInt("QUANTITY"),rs.getInt("MINQUANTITY"),
						rs.getInt("MAXQUANTITY"));
				items.add(item);
				
			}
			ps.close();
			rs.close();
			return items;
		}catch(SQLException e) {
			e.printStackTrace();
			
			return null;	
		}
	}
	//get all customers for table view customer management screen
	public ObservableList<Customer> getAllCustomers()
	{
		ObservableList<Customer> customers = FXCollections.observableArrayList();
		String sql = "SELECT * FROM CUSTOMERTABLE";
		try(Connection con = ch.getConnection()){
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				Customer customer = new Customer(rs.getInt("ID"),rs.getString("NAME"),rs.getString("STREET"),
						rs.getString("CITY"),rs.getString("STATE"),rs.getInt("ZIP"),rs.getLong("PHONENUMBER"),
						rs.getInt("NUMBEROFSALES"));
				customers.add(customer);
			}
			ps.close();
			rs.close();
			return customers;
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//returns customer back to the MainScreenController when search for customer id is pressed
	public Customer findCustomer(String customerId) {
		Customer customer = null;
		int id = Integer.parseInt(customerId);
		
		String sql = "SELECT * FROM CUSTOMERTABLE WHERE ID =?";
		try(Connection con = ch.getConnection()){
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				customer = new Customer(rs.getInt("ID"),rs.getString("NAME"),
	      				rs.getString("STREET"),rs.getString("CITY"),rs.getString("STATE"),
	      				rs.getInt("ZIP"),rs.getLong("PHONENUMBER"),rs.getInt("NUMBEROFSALES"));	
			}
			ps.close();
			rs.close();
			return customer;
			
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}	
	}
	
	
	public void addCustomer(Customer customer) 
	{
		
		
		String sql = "INSERT INTO CUSTOMERTABLE(NAME,STREET,CITY,STATE,ZIP,PHONENUMBER,NUMBEROFSALES)"
				+ " VALUES(?,?,?,?,?,?,?)";
		try(Connection con = ch.getConnection()){
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1,customer.getName());
			ps.setString(2,customer.getStreet());
			ps.setString(3,customer.getCity());
			ps.setString(4,customer.getState());
			ps.setInt(5,customer.getZip());
			ps.setLong(6,customer.getPhoneNumber());
			ps.setInt(7,customer.getNumberOfSales());
			
			ps.executeUpdate();
			ps.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public void removeCustomer(Customer customer) {
		String sql = "DELETE FROM CUSTOMERTABLE WHERE ID = ?";
		try(Connection con = ch.getConnection()){
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,customer.getId());
			ps.executeUpdate();
			ps.close();
			
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	public void updateCustomer(Customer customer)
	{
		String sql = "UPDATE CUSTOMERTABLE SET NAME = ?, STREET = ?, CITY = ?, STATE = ?, ZIP = ?, PHONENUMBER = ? WHERE ID = ?";
		try(Connection con = ch.getConnection()){
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, customer.getName());
			ps.setString(2,customer.getStreet());
			ps.setString(3,customer.getCity());
			ps.setString(4,customer.getState());
			ps.setInt(5,customer.getZip());
			ps.setLong(6,customer.getPhoneNumber());
			ps.setInt(7,customer.getId());
			ps.executeUpdate();
			ps.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void removeItem(Item item) {
		String sql = "DELETE FROM ITEMTABLE WHERE ID = ?";
		try(Connection con = ch.getConnection()){
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,item.getId());
			ps.executeUpdate();
			ps.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		
			
		}
	public void addItem(Item item) throws SQLException {
		String sql = "INSERT INTO ITEMTABLE (ID,DESCRIPTION,PRICE,QUANTITY,MINQUANTITY,MAXQUANTITY) VALUES(?,?,?,?,?,?)";
		try(Connection con = ch.getConnection()){
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,item.getId());
			ps.setString(2, item.getDescription());
			ps.setDouble(3,item.getPrice());
			ps.setInt(4,item.getQuantity());
			ps.setInt(5,item.getMinQuantity());
			ps.setInt(6,item.getMaxQuantity());
			ps.executeUpdate();
			ps.close();
		}
	}
	public void updateItem(Item item)
	{
		String sql = "UPDATE ITEMTABLE SET DESCRIPTION = ?, PRICE = ?, QUANTITY = ?, MINQUANTITY = ?, MAXQUANTITY = ? WHERE ID = ?";
		try(Connection con = ch.getConnection()){
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,item.getDescription());
			ps.setDouble(2,item.getPrice());
			ps.setInt(3,item.getQuantity());
			ps.setInt(4,item.getMinQuantity());
			ps.setInt(5,item.getMaxQuantity());
			ps.setInt(6,item.getId());
			ps.executeUpdate();
			ps.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void subtractItems(int id, int numberToSubtract) {
		String sql = "UPDATE ITEMTABLE SET QUANTITY = QUANTITY - ? WHERE ID = ?";
		try(Connection con = ch.getConnection()){
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, numberToSubtract);
			ps.setInt(2, id);
			ps.executeUpdate();
			ps.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	}


