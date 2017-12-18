/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quartoprojet;

import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Laurent
 */
public class EndGameScreen
{

    private SplitPane splitPane;
    private Group Render;
    private Button btn_newGame, btn_menu;
    private Label joueurGagnant, textJoueur;
    private VBox listeBtn = new VBox();
    private Pane rightPane = new Pane ();
    public EndGameScreen ()
    {
        btn_newGame = new Button ("Nouvelle Partie");
        btn_menu = new Button ("Retourner au menu");
        textJoueur = new Label ("Bravo, vous avez gagné !");
        joueurGagnant = new Label ("");
        
        rightPane.getChildren().addAll(textJoueur, joueurGagnant);
        
        listeBtn.getChildren().addAll(btn_newGame, btn_menu);
        splitPane = new SplitPane();
        splitPane.getStylesheets().add("QuartoStyle.css");
        splitPane.getStyleClass().add("splitPane");
        
        btn_menu.getStyleClass().add("buttons");
        btn_newGame.getStyleClass().add("buttons");
        splitPane.getItems().addAll(listeBtn, rightPane);
        
        
        

    }
    
    public void setTextGagnant (String s)
    {
        joueurGagnant.setText(s);
    }
    
    public SplitPane getRender()
    {
        return splitPane;
    }
}
