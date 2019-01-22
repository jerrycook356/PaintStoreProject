/**
 * 
 */
/**
 * @author jerry
 *
 */
module javafx11 {
	requires javafx.controls;
	requires javafx.fxml;
	requires transitive javafx.graphics;
	requires java.sql;
	requires javafx.base;
	exports application.View;
	exports application;
	exports application.Model;
	
}