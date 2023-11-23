// Created by Alex Parsons
package project3;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;


public class Project3FXMLController implements Initializable {
    @FXML
    private ListView<String> listViewArea;
    
    private final FileChooser fileChooser = new FileChooser();
    private final Map<String,Integer> frequencyMap = new HashMap<>(); // Create a hashmap to hold <keyword, count> intially going to be 0 for count
    private final String keywordsFilePath = "C:\\Users\\Alex\\Documents\\UMD Classes\\Fall 2023\\CIS 296 - Java Programming\\Homework\\Project-3-HashMap\\Project 3\\src\\project3\\keywords.java";
    private final ObservableList<String> listViewData = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // loading in the keywords from file
        loadKeywords(); 
    } // end of initialize
    
    // function to load keywords in from file
    private void loadKeywords(){
        File fin = new File(keywordsFilePath);
        try (Scanner scanner = new Scanner(fin)){
            scanner.useDelimiter("\\W+");
            while(scanner.hasNext()){
                String word = scanner.next();
                if(!word.isEmpty()){
                    frequencyMap.put(word, 0);
                }
            }
        } catch (FileNotFoundException e){
        }
    } // end of loadKeywords()
    
    private void updateListViewOutput(){       
        listViewData.clear(); // clear out old list information
        frequencyMap.forEach((word, count) -> {
            if (count > 0){
                listViewData.add(word + " : " + count);// go through the frequencyMap adding to the observable array list ListViewData if count is greater than 0
            }
        });
        listViewArea.setItems(listViewData); // output the observable array list to the listViewArea
    } // end of updateLsitViewOutput()
    
    // A function that resets the values stored in the hashmap, incase user wants to keep comparing files.
    private void resetHashMapCounts(){
        for (String key : frequencyMap.keySet()){
            frequencyMap.put(key, 0);
        }
    } // end of resetHashMapCounts()

    @FXML
    private void compareFile() {
        //Setting default directory to be the project folder
        fileChooser.setInitialDirectory(new File("C:\\Users\\Alex\\Documents\\UMD Classes\\Fall 2023\\CIS 296 - Java Programming\\Homework\\Project-3-HashMap\\Project 3\\src\\project3"));
        fileChooser.getExtensionFilters().add(new ExtensionFilter("Java Files","*.java")); // only looking for .java 
        File file = fileChooser.showOpenDialog(null);
        
        if(file!=null){
            resetHashMapCounts(); // reset frequency counts back to 0 on input file load
            try(Scanner scanner = new Scanner(file)){
                scanner.useDelimiter("\\W+");
                while(scanner.hasNext()){
                    String word = scanner.next();
                    if (frequencyMap.containsKey(word)){
                        frequencyMap.put(word,frequencyMap.get(word)+ 1);
                    }
                }
            } catch (FileNotFoundException e){
            }
            updateListViewOutput(); // update the listView
        }
    } // end of compare file
}
