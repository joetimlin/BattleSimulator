/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battlesimulator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author jhthx9
 */
public class FXMLDocumentController implements Initializable {
    
    
    private String description = "This application was created by Joe Timlin, a student at the University of Missouri.\n\nThe application simulates battle between two warriors that are given values\n for Health, Max Attack Damage, and Max Block.";
    
    @FXML
    Button start = new Button("Start Battle");
    
    @FXML
    Label labelName1 = new Label();
    
    @FXML
    Label labelName2 = new Label();
    
    @FXML
    ImageView warrior1Image = new ImageView();
    
    @FXML 
    ImageView warrior2Image = new ImageView();
    
    @FXML
    ImageView warrior1X = new ImageView();
    
    @FXML
    ImageView warrior2X = new ImageView();
    
    @FXML 
    TextArea battleLog = new TextArea();
    
    @FXML 
    AnchorPane root = new AnchorPane();
    
    
    Warrior warrior1;
    Warrior warrior2;
    Battle battle;
    AboutBox aboutBox;
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setupBattle();
    }  
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        
        
        setupBattle();
        
        Battle.StartFight(warrior1, warrior2);
        
        battleLog.setText(Battle.battleText.toString());
        
        if(warrior1.health <= 0) {
            warrior1X.setVisible(true);
        }
        
        if(warrior2.health <=0) {
            warrior2X.setVisible(true);
        }
    }
    
    private void setupBattle() {
        warrior1X.setVisible(false);
        warrior2X.setVisible(false);
        warrior1 = new Warrior("Joe", 300, 100, 30);
        warrior2 = new Warrior("Steve", 300, 100, 30);
        labelName1.setText(warrior1.name);
        labelName2.setText(warrior2.name);
        Battle.battleText.setLength(0);
    }
    
    @FXML 
    public void handleAbout(ActionEvent event){
        AboutBox.display("About", description);
    }
    
    
    @FXML
    public void handleSave(ActionEvent event){
        FileChooser fileChooser = new FileChooser(); 
        Stage stage = (Stage) root.getScene().getWindow(); 
        
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt")); 
        
        File file = fileChooser.showSaveDialog(stage); 
        
        FileWriter writer = null; 
        
        if(file != null){
            
            try { 
                
                writer = new FileWriter(file);
                
                writer.write(battleLog.getText());
                
                
            } catch (IOException ioex) {
                
                displayExceptionAlert(ioex); 
                
            } catch (Exception ex){
                
                displayExceptionAlert(ex); 
                
            } finally {
                
                if(writer != null){
                    
                    try {
                        writer.close();
                    } catch (IOException ioex) {
                        
                        displayExceptionAlert(ioex); 
                    
                    } catch (Exception ex){
                       
                        displayExceptionAlert(ex); 
                    }
                }   
            }   
        }
    }
    
    private void displayExceptionAlert(Exception ex){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Exception Dialog");
        alert.setHeaderText(ex.getClass().getCanonicalName());
        alert.setContentText(ex.getMessage());
        
        // Create expandable Exception.
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        String exceptionText = sw.toString();

        Label label = new Label("The exception stacktrace was:");

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

        // Set expandable Exception into the dialog pane.
        alert.getDialogPane().setExpandableContent(expContent);

        alert.showAndWait(); 
    }
    
    @FXML
    public void handleOpen(ActionEvent event){
        FileChooser fileChooser = new FileChooser(); 
        
        Stage stage = (Stage) root.getScene().getWindow(); 
        
        fileChooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("Text files", "*.txt", "*.html", "*.c")); 
        
        File file = fileChooser.showOpenDialog(stage);
        
        if(file != null){
            
            BufferedReader bufferedReader = null; 
            
            String document = ""; 
            String line = ""; 
            
            try { 
                bufferedReader = new BufferedReader(new FileReader(file));
                
                
                while( (line = bufferedReader.readLine()) != null){
                    document += line + "\n"; 
                }
                
                battleLog.setText(document); 
                
                
            } catch (FileNotFoundException fnfex) {
                displayExceptionAlert(fnfex); 
            } catch (IOException ioex) {
                displayExceptionAlert(ioex);
            } finally {
                if(bufferedReader != null){
                    
                    try { 

                        bufferedReader.close();
                    
                    } catch (IOException ioex) {
                        displayExceptionAlert(ioex); 
                 
                    }
                    
                }
            }   
        }
    }
}
