/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package project3;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;


public class Project3FXMLController implements Initializable {

    FileChooser fileChooser = new FileChooser();
    @FXML
    private Button inputFileButton;
    @FXML
    private TextArea textArea;
    @FXML
    private Button inputCompareFile;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       fileChooser.setInitialDirectory(new File("C:\\Users\\Alex\\Documents\\UMD Classes\\Fall 2023\\CIS 296 - Java Programming\\Homework\\Project-3-HashMap\\Project 3\\src\\project3"));
    }    

    @FXML
    private void inputFileToProgram(ActionEvent event) {
        fileChooser.getExtensionFilters().add(new ExtensionFilter("Java Files","*.java"));
        File file = fileChooser.showOpenDialog(null);
        if (file != null){
            try{
                Scanner scanner = new Scanner(file);
                StringBuilder fileCont = new StringBuilder();
                while (scanner.hasNextLine()){
                    fileCont.append(scanner.nextLine()).append("\n");
                }
                scanner.close();
                textArea.setText(fileCont.toString());
            }
            catch (FileNotFoundException e){
                e.printStackTrace();
            }
            
            
        }
        
    }
    
    
}
