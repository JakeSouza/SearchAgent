package Tests;

import java.io.FileNotFoundException;

import Websearch.SearchEngine;
import Websearch.URLWords;
import Websearch.WebCrawlResultReader;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import jsjf.ArrayStack;
import jsjf.ArrayUnorderedList;


public class SearchEngineGUI extends Application {
	
	String s = "www.rwu.edu";
	String current ="";
	ArrayStack<String> back = new ArrayStack<String>();
	ArrayStack<String> forward = new ArrayStack<String>();
	SearchEngine se;
	

	@Override
	public void start(Stage primaryStage) throws Exception{
		try {
			ArrayUnorderedList<URLWords> crawlResult = WebCrawlResultReader.readFromFile("crawlResultRWU.txt");
	        se = new SearchEngine(crawlResult);
		 }catch (FileNotFoundException ex) {
	         System.out.println("File Not Found");
	     }
		
		//Web Address TextField
		TextField webAddress = new TextField();
		webAddress.setText("http://www.rwu.edu");
		
		//Area to display web site
		WebView webView = new WebView();
		WebEngine webEngine = webView.getEngine();
		
		
		//Go Button
		Button goButton = new Button("Go");
		goButton.setAlignment(Pos.CENTER);
		goButton.setOnAction(e -> {
			s = webAddress.getText();
			webEngine.load(s);
			back.push(current);
			current = webAddress.getText();
			int counter = forward.size();
			for(int i = 0; i < counter; i++){
				forward.pop();
			}
			
		});
		
		//Back Button
		Button backButton = new Button("<-");
		backButton.setAlignment(Pos.CENTER);
		backButton.setOnAction(e -> {
			forward.push(current);
			current = back.pop();
			webAddress.setText(current);
			webEngine.load(current);
		});
		
		//Forward Button
		Button forwardButton = new Button("->");
		forwardButton.setAlignment(Pos.CENTER);
		forwardButton.setOnAction(e -> {	
			back.push(current);
			current = forward.pop();
			webAddress.setText(current);
			webEngine.load(current
					);
		});
		
		//Web Address TextField
		TextField searchTerm = new TextField();
		searchTerm.setText("Search Here");
		
		//TextArea display contents of queue
		TextArea displayArea = new TextArea();
		displayArea.setText("Search Results");
		
		//Search Button
		Button searchButton = new Button("Search");
		searchButton.setAlignment(Pos.CENTER);
		searchButton.setOnAction(e -> {
			String result = se.search(searchTerm.getText().toLowerCase());
			displayArea.setText(result);
		});
		
		//HBox for forward and back buttons
		HBox backForth = new HBox(15);
		backForth.setAlignment(Pos.CENTER);
		backForth.getChildren().addAll(backButton, forwardButton);
		
		//HBox for web address and go button
		HBox urlGo = new HBox(15);
		urlGo.setAlignment(Pos.CENTER);
		urlGo.getChildren().addAll(webAddress, goButton);
		
		//HBox for the buttons
		HBox webRibbon = new HBox(35);
		webRibbon.setAlignment(Pos.CENTER);
		webRibbon.getChildren().addAll(backForth, urlGo);
		
		//VBox for web area
		VBox webArea = new VBox(15);
		webArea.setAlignment(Pos.CENTER);
		webArea.getChildren().addAll(webRibbon, webView);
		
		//HBox for search ribbon
		HBox searchRibbon = new HBox(15);
		searchRibbon.setAlignment(Pos.CENTER);
		searchRibbon.getChildren().addAll(searchTerm, searchButton);
		
		//VBox for web area
		VBox searchArea = new VBox(15);
		searchArea.setAlignment(Pos.CENTER);
		searchArea.getChildren().addAll(searchRibbon, displayArea);
		
		//HBox for whole screen
		HBox fullDisplay = new HBox(15);
		fullDisplay.setAlignment(Pos.CENTER);
		fullDisplay.getChildren().addAll(webArea, searchArea);
		
		// Make the scene
		Scene scene = new Scene(fullDisplay);
		primaryStage.setTitle("Search Engine");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
