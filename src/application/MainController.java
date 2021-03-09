package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import org.json.simple.*;


public class MainController {
	//Login credentials object
	JSONObject loginInfo = new JSONObject();
	//Registration fields
	@FXML
	private PasswordField password = new PasswordField();
	@FXML
	private TextField username = new TextField();
	//Gives the user a message if they login successfully or not
	@FXML
	private Label loginText = new Label();
	
	//Action when login button is pressed
	public void login(ActionEvent e) {
		if(Main.loginInfo.containsKey(username.getText()) && Main.loginInfo.get(username.getText()).equals(password.getText())) {
			loginSuccess();
		}else if(Main.loginInfo.containsKey(username.getText())){
			loginFail("Incorrect password.");
		}else {
			loginFail("Username does not exist.");
		}
	}
	//Executed if login is successful
	public void loginSuccess() {
		loginText.setText("Logged in as: "+username.getText());
		System.out.println("User Login: "+username.getText());
	}
	//Executed if login fails
	public void loginFail(String failReason) {
		
		loginText.setText("Login Failed: "+failReason);
		System.out.println("Unsuccessful attempt to log into: "+username.getText());
	}
	//Action when register button is pressed
	public void register(ActionEvent e) throws Exception {
		new application.RegisterController().open();
	}
	
}
