/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quartoprojet;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.shape.*;

/**
 *
 * @author Laurent Fouetillou
 */
public class Quarto extends Application
{
    ZoneJeu zoneJeu = new ZoneJeu ();
    Options options = new Options();

    
    @Override
    public void start (Stage primaryStage)
    {
        //zoneJeu.test();
        
        GridPane grille = new GridPane();
        
        //Boite horizontale pour les pions de la pioche
        HBox piocheBox = new HBox();
        ObservableList listPioche = piocheBox.getChildren();
        
        EventHandler eventClickPion = new EventHandler()
        {
            @Override
            public void handle (Event event)
            {
                System.out.println("clicked");
                
            }
        };
        //Generation des 16 pions "Boutons" de la pioche
        for (int i = 0; i < zoneJeu.getPioche().getTaillePioche()-1; i++)
        {
            Button btn = new Button();
            Pion p = zoneJeu.getPioche().getPion(i);
            String s = zoneJeu.getPioche().getPion(i).toShortString();
            System.out.println(s);
            
            btn.setText(s);
            btn.setOnMouseClicked(eventClickPion);
            btn.setCursor(Cursor.HAND);
           
            listPioche.add(btn);
        }
        
        Rectangle rect = new Rectangle (50, 50, 80, 80);
        
        //Creation de la grille de jeu
        GridPane casesPlateau = new GridPane();
        
        casesPlateau.setMinSize(300,300);
        casesPlateau.setPadding(new Insets (10,10,10,10));
        casesPlateau.setAlignment(Pos.CENTER);
        
        for (int r = 0; r<zoneJeu.getPlateau().getNB_CASES(); r ++)
        {
            for (int c = 0; c<zoneJeu.getPlateau().getNB_CASES(); c ++)
            {
                Button btn = new Button ();
                btn.setText(r+":"+c);
                btn.setPrefSize(60, 60);
                casesPlateau.add(btn, c, r);
            }
        }
        
        grille.add(piocheBox, 0, 0);
        grille.add(casesPlateau, 0, 1);
        grille.add(zoneJeu.getPioche().getPion(10).getRender(), 0, 2);
        Scene scene = new Scene(grille, 800, 800);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main (String[] args)
    {
        launch(args);
        
    }
   
}
