/**
 * 
 */
/**
 * @author jerry
 *
 */
module javafx11 {
	requires transitive javafx.controls;
	requires transitive javafx.fxml;
	requires transitive javafx.graphics;
	requires transitive java.sql;
	requires transitive javafx.base;
	exports application.View;
	exports application;
	exports application.Model;
	
}