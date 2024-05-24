module CortinasJavaFX {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	
	opens model to javafx.base;
	
	opens application to javafx.graphics, javafx.fxml;
	opens controller to javafx.fxml;
}
