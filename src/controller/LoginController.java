package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class LoginController {

	@FXML
	private TextField cpfUser;
	
	@FXML
	private TextField emailUser;
	
	@FXML
	private TextField passwordUser;
	
	public Stage primaryStage;
	
	
	public void entrar() {
		System.out.println("Tentativa de login...");
		if (cpfUser.getText().equals("123") && emailUser.getText().equals("@gmail") && passwordUser.getText().equals("admin")){
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/DashboardCorinthians.fxml"));
				
				StackPane root = loader.load();
				
				Scene scene = new Scene(root,915,631);
				
				Stage currentStage = (Stage) cpfUser.getScene().getWindow();
				currentStage.setScene(scene);
				currentStage.setTitle("Dashboard");
				currentStage.show();
				System.out.println("Login executado!");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else {
			msgErro();
		}
	}
	
	public void msgErro() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("sua senha está errada");
		alert.setContentText("senha incorreta");
		alert.setHeaderText(null);
		alert.showAndWait();
	}
}
