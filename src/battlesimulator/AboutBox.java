/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battlesimulator;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.*;

/**
 *
 * @author jhthx9
 */
public class AboutBox {
    
    public static void display(String title, String message){
   
    Stage window = new Stage();
    
    window.initModality(Modality.APPLICATION_MODAL);
    window.setTitle(title);
    window.setMinWidth(500);
    window.setMinHeight(400);
   
    
    Label label = new Label();
    label.setText(message);
    Button closeButton = new Button("Close this window");
    closeButton.setOnAction(e -> window.close());
    
    VBox layout = new VBox(10);
    layout.getChildren().addAll(label, closeButton);
    layout.setAlignment(Pos.CENTER);
    
    Scene scene = new Scene(layout); 
    window.setScene(scene);
    window.showAndWait();
    }
}
