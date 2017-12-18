/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quartoprojet;

import java.util.HashMap;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
    private final int W_WIDTH = 1600;
    private final int W_HEIGHT = 900;
    protected ZoneJeu zoneJeu = new ZoneJeu();
    private Options options = new Options();
    private HashMap <String, Pion> hm_pions = new HashMap();
    private Joueur joueurActuel = zoneJeu.getJoueurActuel();
    private EndGameScreen endGame = new EndGameScreen();
    
    //Grille Layout de l'espace de jeu
    private StackPane stack;
    private Node spl = endGame.getRender();
    private GridPane grille = new GridPane();
    
    //Creation de la grille du plateau jeu
    private GridPane casesPlateau = new GridPane();
    private Image posePion = new Image ("posePion.png");
    private ImageView view_PosePion = new ImageView (posePion);
    private StackPane stack_PosePion = new StackPane(view_PosePion);
    //Boite horizontale pour les pions de la pioche
    private HBox piocheBox = new HBox();
    
    @Override
    public void start (Stage primaryStage)
    {   
        ObservableList listPioche = piocheBox.getChildren();
        
        //Pose Pion
        view_PosePion.setFitHeight(100);
        view_PosePion.setPreserveRatio(true);
        
        casesPlateau.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle (MouseEvent event)
            {
               
            } 
        });
        
        //Generation des 16 pions de la pioche
        for (int i = 0; i<zoneJeu.getPioche().getTaillePioche(); i++)
        {
            Pion pion = zoneJeu.getPioche().getPion(i);
            Group render = pion.getRender();
            
            String s = pion.toShortString();
            System.out.println(s);
            
            hm_pions.put(""+pion.getProprietes(), pion);

            //Implementation des events pour le clickage sur les pions
            render.setOnMousePressed(new EventHandler<MouseEvent>(){
                @Override
                public void handle (MouseEvent event)
                {
                    String idPion = render.getId();
                    Pion pion = hm_pions.get(idPion);
                    
                    //Si le pion est selectionnable, le pion devient le pion en main du joueur Actuel
                    if (pion.isSelectionnable())
                    {
                        pion.setSelectionnable(false);
                        joueurActuel.setPionEnMain(pion);
                        stack_PosePion.getChildren().add(render);
                        piocheBox.setDisable(true);
                    }
                }            
            });
            
            listPioche.add(pion.getRender());
        }
        piocheBox.setAlignment(Pos.CENTER);

        //Génération du visuel du plateau
        generationPlateau();

        
        grille.setAlignment(Pos.CENTER);
        grille.setGridLinesVisible(false);
        
        grille.add(piocheBox, 0, 0);
        grille.add(casesPlateau, 0, 1);
        grille.add(stack_PosePion, 0, 2);
        
        spl.setDisable(true);
        spl.setOpacity(0);
        stack = new StackPane(grille);
        stack.getChildren().add(spl);
        stack.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(stack, W_WIDTH, W_HEIGHT);
        scene.getStylesheets().add("QuartoStyle.css");

        primaryStage.setScene(scene);
        primaryStage.show();
        System.out.println(grille.getLayoutBounds());
    }

    /**
     * @param args the command line arguments
     */
    public static void main (String[] args)
    {
        launch(args);
    }

    private void generationPlateau ()
    {
        casesPlateau.setPrefSize(W_WIDTH/1.5, W_HEIGHT/1.5);
        //casesPlateau.setPadding(new Insets (10,10,10,10));
        casesPlateau.setAlignment(Pos.CENTER);

        for (int r = 0; r<zoneJeu.getPlateau().getNB_CASES(); r++)
        {
            for (int c = 0; c<zoneJeu.getPlateau().getNB_CASES(); c++)
            {
                Image im = new Image("cases.png");
                //Créé l'objet image affichable de la case à partir du fichier
                ImageView r_case = new ImageView(im);
                //Redimensionne r_case pour qu'il occupe l'espace de la case de la grille (ni plus, ni moins)
                //Et preserve le ratio de l'image (evite de faire un setHeight)
                r_case.setFitWidth(100);
                r_case.setPreserveRatio(true);

                /*=============== Gestion des Events =================*/
                
                //Event du clickage de case
                r_case.setOnMouseClicked(new EventHandler<MouseEvent>()
                {
                    @Override
                    public void handle (MouseEvent event)
                    {
                        //Recupere la colonne et la ligne de la case clickée
                        int r = GridPane.getRowIndex(r_case);
                        int c = GridPane.getColumnIndex(r_case);
                        //System.out.println(c+":"+r);
                        
                        //Recupere le pion du joueur
                        Pion pion = joueurActuel.getPionEnMain();
                        
                        //Si le joueur a un pion en main, alors pose le pion sur la case
                        if (pion != null)
                        {
                            //Deplace le pion dans la grille 
                            Group render = pion.getRender();
                            casesPlateau.add(render,c,r);
                            render.toFront();
                            
                            /*Insere le pion dans la grille virtuelle du plateau
                            et le rend non selectionnable*/
                            zoneJeu.getPlateau().insererPion(pion, c, r);
                            pion.setSelectionnable(false);
                            
                            joueurActuel.setPionEnMain(null);
                            zoneJeu.changerJoueurActuel();
                            
                            //Desactive la case pour ne pas permettre de poser plusieurs pions dessus
                            r_case.setDisable(true);
                            
                            //Réactive la selection des pions de la pioche
                            piocheBox.setDisable(false);
                            
                            /*Verification de victoire */
                            boolean placementVic = zoneJeu.getPlateau().placementVictorieux();
                            
                            if (placementVic)
                                victoire();
                            
                            System.out.println(placementVic);   
                        }
                    }
                });
                //Ajoute la case à la grille plateau
                casesPlateau.add(r_case, c, r);
            }
        }
    }
    
    private void victoire ()
    {
        grille.setDisable(true);
        spl.setOpacity(10);
        spl.setDisable(false);
        endGame.setTextGagnant(joueurActuel.getNom());
    }
    
    private void reset ()
    {
        
    }
}
