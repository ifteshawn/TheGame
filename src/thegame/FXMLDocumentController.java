/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thegame;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;

import javafx.scene.control.TextInputDialog;

/**
 *
 * @author 12171117
 */
//this class is to handle the action events from the user clicking on differen buttons and making changes to the GUI text area.
public class FXMLDocumentController implements Initializable, IView {

    @FXML
    private Button load;

    @FXML
    private Button save;

    @FXML
    private Button display;

    @FXML
    private Button play;

    @FXML
    private TextArea textArea;

    @FXML
    private Button exit;

    private Game game;

    //display button handler to display tree in the text area once display button clicked
    @FXML
    void displayAction(ActionEvent event) {
        textArea.setText(game.display());
    }

    //exit button action handler
    //to exit the game ince user clicks on exit
    @FXML
    void exitAction(ActionEvent event) {
        System.exit(0);
    }

    //load button action handler
    //to load a saved game from file once user clicks on load
    @FXML
    void loadAction(ActionEvent event) {
        game.load("animal.txt");
    }

    //to strt playing the game
    @FXML
    void playAction(ActionEvent event) {
        game.play();
    }

    //save button action handler
    //to save a current game into a text file.
    @FXML
    void saveAction(ActionEvent event) {
        game.save("animal.txt");
        File file = new File("animal.txt");
        Desktop desk = Desktop.getDesktop();
        try {
            desk.open(file);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @Override
    public void display(String s) {
        textArea.setText(s);
    }

    //to add text to the text area
    @Override
    public void append(String s) {
        StringBuilder sb = new StringBuilder();
        sb.append(s);
    }

    //to pop up window to ask user a question and take in the answer entered by the user.
    @Override
    public String ask(String q) {
        TextInputDialog tid = new TextInputDialog("");
        tid.setHeaderText(q);
        // Block execution until the user responds
        tid.showAndWait();
        return tid.getEditor().getText();

    }

    // For yes/no responses to a question
    @Override
    public boolean choose(String q) {
        String r = choose(q, "Yes", "No");
        if (r.equals("Yes")) {
            return true;
        }
        return false;
    }

    // more general version of choose - used to implement yes/no 
    // version above
    @Override
    public String choose(String q, String a1, String a2) {
        ButtonType b1 = new ButtonType(a1);
        ButtonType b2 = new ButtonType(a2);
        Alert alert = new Alert(Alert.AlertType.NONE, q, b1, b2);
        alert.setTitle("Choose");
        // Block execution until the user responds
        java.util.Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == b1) {
            return a1;
        }
        return a2;
    }

    //to pass in a reference to the game. 
    public void bind(Game game) {
        this.game = game;
    }

}
