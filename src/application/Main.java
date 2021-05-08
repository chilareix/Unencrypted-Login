package application;

import java.io.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	static JSONParser loginParser = new JSONParser();
	
	static JSONObject loginInfo = new JSONObject();
	
	public static void main(String[] args) {
		//Creates a login file if one does not exist
		try {
			File login = new File("login.JSON");
			if(login.createNewFile()) {
				System.out.println("Login DB created");
			}else 
				System.out.println("File \"login\" already exists. Loading...");
			
		}catch(Exception e){
			System.out.println("An error has occurred while creating a file: login.JSON");
			e.printStackTrace();
		}
		
		//Reads the login file to the variable
		try {
			loginInfo = (JSONObject) loginParser.parse(new FileReader("login.JSON"));
			
		}catch(Exception e) {
			System.out.println("An error has occurred while reading \"login.JSON\" to loginInfo");
			e.printStackTrace();
		}
		

		launch(args);
		
	}
	@Override
	public void start(Stage primaryStage) throws Exception{
		
		Parent root = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setTitle("Log in");
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setResizable(false);
		//Writes login information to file when app is closed
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
		    public void handle(WindowEvent e) {
				System.out.println("Closing...");
				try {
					FileWriter jsonWriter = new FileWriter("login.JSON");
					jsonWriter.write(loginInfo.toJSONString());
					jsonWriter.flush();
					jsonWriter.close();
				}catch(Exception ex) {
					System.out.println("An error has occurred while writing to: login.JSON");
					ex.printStackTrace();
				}
			    Platform.exit();
			    System.exit(0);
		    }
		  });
	}
}
