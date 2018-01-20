/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quartoprojet;

import java.util.Arrays;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;

/**
 *
 * @author Laurent Fouetillou
 */
public class Plateau
{

    private final int NB_CASES = 4;
    private Pion[][] cases;
    private Integer [] propsPionsLigne, propsPionsColonne, propsPionsDiagTD, propsPionsDiagDT;
    private int nbPoses = 0;

    public Plateau ()
    {
        cases = new Pion[NB_CASES][NB_CASES];
        propsPionsLigne     = new Integer [NB_CASES];
        propsPionsColonne   = new Integer [NB_CASES];
        propsPionsDiagTD    = new Integer [NB_CASES];
        propsPionsDiagDT    = new Integer [NB_CASES];
    }

    public boolean isLigneFull (int row)
    {
        //Ligne complete est sur TRUE car on vient de poser le pion
        //Jusqu'a preuve du contraire, la propsPionsLigne ne contient pas de trou
        boolean ligneComplete = true;
        int n = 0;

        //Parcoure la propsPionsLigne en recherche de pion
        while (n < NB_CASES && ligneComplete)
        {
            Pion pion = cases[n][row];

            //Rempli le tableau propsPionsLigne pour comparaison des pions si la propsPionsLigne est remplie
            if (pion != null)
                propsPionsLigne[n] = pion.getProprietes();

            //Si il n'y a pas de pion, sur la case courante, resultat est mis sur FALSE
            //Et les valeurs du tableau propsLignePion sont remises à null
            else
            {
                ligneComplete = false;
                Arrays.fill(propsPionsLigne, null);
            }
            n++;
        }
        return ligneComplete;
    }

    public boolean isColonneFull (int col)
    {
        //Colonne complete est sur TRUE car on vient de poser le pion
        //Jusqu'a preuve du contraire, la propsPionsColonne ne contient pas de trou
        boolean colonneComplete = true;
        int n = 0;

        //Parcoure la propsPionsColonne en recherche de pion
        while (n < NB_CASES && colonneComplete)
        {
            Pion pion = cases[col][n];

            //Rempli le tableau propsPionsColonne pour comparaison des pions si la propsPionsColonne est remplie
            if (pion != null)
                propsPionsColonne[n] = pion.getProprietes();

            //Si il n'y a pas de pion, sur la case courante, resultat est mis sur FALSE
            //Et les valeurs du tableau "Colonne" sont remises à -1
            else
            {
                colonneComplete = false;
                Arrays.fill(propsPionsColonne, null);
            }
            n++;
        }
        return colonneComplete;
    }

    public boolean isDiagTopDownFull ()
    {
        int i = 0;
        boolean full = true;
        while ( i<NB_CASES && full )
        {
            if (cases[i][i] != null)
                propsPionsDiagTD[i] = cases[i][i].getProprietes();
            else 
                full = false;
            i++;
        }
        return full;
    }
    
    public boolean isDiagDownTopFull ()
    {
        int col = 0;
        int lgn = NB_CASES-1;
        boolean full = true;
        while ( col<NB_CASES && full )
        {
            if (cases[col][lgn] != null) 
                propsPionsDiagDT[col] = cases[col][lgn].getProprietes();
            else 
                full = false;
            lgn--;
            col++;
        }
        return full;
    }
    
    public int comparaisonPions (Integer [] T)
    {
        try 
        {
            int resultat0 = ~(T[0] | T[1] | T[2] | T[3]) & 0b1111;    //Donne 1 si les 4 pions ont le meme bit à 0 
            int resultat1 =  (T[0] & T[1] & T[2] & T[3]);    //Donne un 1 si les 4 pions ont le meme bit à 1
            int resultat = resultat0 | resultat1;
            System.out.println("Resultat 0: "+Integer.toBinaryString(resultat0));
            System.out.println("Resultat 1: "+Integer.toBinaryString(resultat1));
            System.out.println("Resultat: "+Integer.toBinaryString(resultat));
            return resultat;
        }
        catch (NullPointerException e)
        {
            return 0;
        }
    
}
    public boolean ligneGagnante ()
    {
        int resultat = comparaisonPions(propsPionsLigne);
                
        System.out.println("Pions identiques");
        return resultat != 0;
    }

    public boolean colonneGagnante ()
    {
        int resultat = comparaisonPions(propsPionsColonne);
        return resultat != 0;
    }
    
    public boolean diagTDGagnante ()
    {
        int resultat = comparaisonPions(propsPionsDiagTD);
        return resultat != 0;
    }
    
    public boolean diagDTGagnante ()
    {
        int resultat = comparaisonPions(propsPionsDiagDT);
        return resultat != 0;
    }

    public boolean placementVictorieux ()
    {
        return ligneGagnante() || colonneGagnante() || diagDTGagnante() || diagTDGagnante();
    }

    public boolean isCaseVide (int x, int y)
    {
        return cases[x][y] == null;
    }

    public void viderPlateau ()
    {
        cases = new Pion[NB_CASES][NB_CASES];
    }

    public void retirerPion ()
    {
        //TODO retirer pion
    }

    //Insert un pion en X,Y, retourne FALSE si echec de placement
    public boolean insererPion (Pion p, int x, int y)
    {
        if (isCaseVide(x, y))
        {
            cases[x][y] = p;
            nbPoses++;
            if (nbPoses >= 4)
            {
                isLigneFull(y);
                isColonneFull(x);
                isDiagDownTopFull();
                isDiagTopDownFull();
            }
            return true;
        }
        return false;
    }

    //Debug ?
    public String informationPion (int x, int y)
    {
        return cases[x][y].toString();
    }
    

    public void plateauString ()
    {
        for (Pion[] row : cases)
        {
            System.out.println("Row");
            for (Pion p : row)
            {
                if (p != null)
                    System.out.println(p.toString());
                else
                    System.out.println("Vide");
            }
        }
    }
    public int getNB_CASES ()
    {
        return NB_CASES;
    }
    
    public Group getCaseRender ()
    {
        Circle circle = new Circle (60);
        circle.getStyleClass().add("cases");
        StackPane stack = new StackPane (circle);
        stack.setStyle ("-fx-alignment: CENTER;");
        Group Render = new Group (stack);
        Render.getStylesheets().add("QuartoStyle.css");
        Render.getStyleClass().add("cases");
        
        return Render;
    }
}
