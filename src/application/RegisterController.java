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
	private PasswordField pw = new PasswordField();
	@FXML
	private PasswordField pw2 = new PasswordField();
	@FXML
	private TextField un = new TextField();
	@FXML
	private Label pwReq = new Label();
	@FXML
	private Label pwrNoMatch = new Label();
	
	//REGEX
	//Determine if the password is "secure enough"
	Pattern secPat = Pattern.compile("\\w\\d");
	Matcher secMat;
	//Determine if the password does not contain white spaces
	Pattern noWtSpc = Pattern.compile("\\s");
	Matcher nWSMatcher;
	
	public void register(ActionEvent e) {
		
		secMat = secPat.matcher(pw.getText());
		nWSMatcher = noWtSpc.matcher(pw.getText());
		//Proxies for matchers because for reasons I don't know, once the find() function is called from a matcher, it immediately turns false for the next read
		boolean secProxy = secMat.find();
		boolean nWSMProxy = nWSMatcher.find();
		
		if(pw2.getText().equals(pw.getText()) && secProxy && !nWSMProxy && pw.getText().length() >= 6 && !application.Main.loginInfo.containsKey(un.getText().toLowerCase())) {
			//Called if registration is complete correctly
			application.Main.loginInfo.put(un.getText().toLowerCase(), pw.getText());
			pwrNoMatch.setText("Registered!");
			System.out.println(application.Main.loginInfo);
			
		}else if(!secProxy || pw.getLength() < 6 || nWSMProxy) {
			pwrNoMatch.setText("Password does not meet\n requirements!");
		}else if(!pw2.getText().equals(pw.getText())) {
			pwrNoMatch.setText("Passwords do not match!");
		}else if(application.Main.loginInfo.containsKey(un.getText())) {
			pwrNoMatch.setText("Username already exists!");
		}
		
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
