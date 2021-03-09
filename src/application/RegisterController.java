package application;

import org.json.simple.*;

import java.io.FileWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class RegisterController {
	
	//Scene items so they can be altered 
	@FXML
	private PasswordField password = new PasswordField();
	@FXML
	private PasswordField password2 = new PasswordField();
	@FXML
	private TextField username = new TextField();
	@FXML
	private Label passRequirements = new Label();
	@FXML
	private Label registerError = new Label();
	
	//REGEX
	//Determine if the password is "secure enough"
	private Pattern securityPattern = Pattern.compile("\\w\\d");
	private Matcher securityMatcher;
	//Determine if the password does not contain white spaces
	Pattern noWhiteSpc = Pattern.compile("\\s");
	private Matcher noWhiteSpcMatcher;
	
	public void register(ActionEvent e) {
		
		securityMatcher = securityPattern.matcher(password.getText());
		noWhiteSpcMatcher = noWhiteSpc.matcher(password.getText());
		//Proxies for matchers because for reasons I don't know, once the find() function is called from a matcher, it immediately turns false for the next read
		boolean secProxy = securityMatcher.find();
		boolean nWSMProxy = noWhiteSpcMatcher.find();
		
		if(password2.getText().equals(password.getText()) &&
				secProxy && !nWSMProxy &&
				password.getText().length() >= 6 &&
				!application.Main.loginInfo.containsKey(username.getText().toLowerCase()) &&
				username.getLength() > 3) {
			//Called if registration is complete correctly
			application.Main.loginInfo.put(username.getText().toLowerCase(), password.getText());
			registerError.setText("Registered!");
			System.out.println(application.Main.loginInfo);
			
		//Executed if registration is not completed
		}else if(!secProxy || password.getLength() < 6 || nWSMProxy) {
			registerError.setText("Password does not meet\n requirements!");
		}else if(!password2.getText().equals(password.getText())) {
			registerError.setText("Passwords do not match!");
		}else if(application.Main.loginInfo.containsKey(username.getText())) {
			registerError.setText("Username already exists!");
		}else registerError.setText("Username must be more than 3 characters!");
		
	}
	
	
	
	//Creates new window for registration
	void open() throws Exception{
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/Register.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setTitle("Register");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
