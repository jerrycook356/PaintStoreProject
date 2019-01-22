package application.Model;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import application.Model.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DatabaseHelper {
 ConnectionHelper ch = new ConnectionHelper();
	public DatabaseHelper() 
	{

	}
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
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void createItemTable()
	{
		String sql = "CREATE TABLE ITEMTABLE(ID INT NOT NULL PRIMARY KEY, DESCRIPTION VARCHAR(100),PRICE DOUBLE,"+
	                  "QUANTITY INT, MINQUANTITY INT, MAXQUANTITY INT)";
		try(Connection con = ch.getConnection())
		{
			PreparedStatement ps = con.prepareStatement(sql);
			ps.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void setUpCustomerTable() {
		try(Connection con = ch.getConnection())
		{
			DatabaseMetaData dbmd = con.getMetaData();
			ResultSet rs = dbmd.getTables(null,null,"CUSTOMERTABLE",null);
			if(rs.next())
			{
				
				//testTable();
			}
			else
			{
				createCustomerTable();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void createCustomerTable()
	{
		String sql = "CREATE TABLE CUSTOMERTABLE(ID INT NOT NULL PRIMARY KEY,NAME VARCHAR(30),"+
	             "STREET VARCHAR(30),CITY VARCHAR(30),STATE VARCHAR(30),ZIP INT,PHONENUMBER INT)";
		try(Connection con = ch.getConnection()){
			PreparedStatement ps = con.prepareStatement(sql);
			ps.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public void testTable()
	{
		String addString = "INSERT INTO ITEMTABLE VALUES(1,'HELLO',10.00,1,1,10)";
		String addString2 = "INSERT INTO ITEMTABLE VALUES(2,'Yellow Paint 80',10.00,1,1,10)";
		try(Connection con = ch.getConnection()){
			Statement stmt = con.createStatement();
			stmt.executeUpdate(addString);			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		try(Connection con = ch.getConnection()){
			Statement stmt = con.createStatement();
			stmt.executeUpdate(addString2);			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public ObservableList<Item> getAllItems()
	{
		ObservableList<Item> items = FXCollections.observableArrayList();
		
		String sql = "SELECT * FROM ITEMTABLE";
		
		try(Connection con = ch.getConnection())
		{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				Item item =new Item(rs.getInt("ID"),rs.getString("DESCRIPTION"),
						rs.getDouble("PRICE"),rs.getInt("QUANTITY"),rs.getInt("MINQUANTITY"),
						rs.getInt("MAXQUANTITY"));
				items.add(item);
				
			}
			System.out.println("size of items = "+items.size());
			return items;
		}catch(SQLException e) {
			e.printStackTrace();
			return null;	
		}
	}

}
