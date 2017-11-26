/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quartoprojet;

import java.util.Arrays;

/**
 *
 * @author Laurent Fouetillou
 */
public class Plateau
{

    private final int NB_CASES = 4;
    private Pion[][] cases;
    private int[] propsPionsLigne, propsPionsColonne;

    public Plateau ()
    {
        cases = new Pion[NB_CASES][NB_CASES];
        propsPionsLigne = new int[NB_CASES];
        propsPionsColonne = new int[NB_CASES];
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
            Pion pion = cases[row][n];

            //Rempli le tableau propsPionsLigne pour comparaison des pions si la propsPionsLigne est remplie
            if (pion != null)
                propsPionsLigne[n] = pion.getProprietes();

            //Si il n'y a pas de pion, sur la case courante, resultat est mis sur FALSE
            //Et les valeurs du tableau "Ligne" sont remises à -1
            else
            {
                ligneComplete = false;
                Arrays.fill(propsPionsLigne, -1);
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
            Pion pion = cases[n][col];

            //Rempli le tableau propsPionsColonne pour comparaison des pions si la propsPionsColonne est remplie
            if (pion != null)
                propsPionsColonne[n] = pion.getProprietes();

            //Si il n'y a pas de pion, sur la case courante, resultat est mis sur FALSE
            //Et les valeurs du tableau "Colonne" sont remises à -1
            else
            {
                colonneComplete = false;
                Arrays.fill(propsPionsColonne, -1);
            }
            n++;
        }
        return colonneComplete;
    }

    public int comparaisonPions (int [] T)
    {
        int resultat0 = ~(T[0] | T[1] | T[2] | T[3]) & 0b1111;    //Donne 1 si les 4 pions ont le meme bit à 0 
        int resultat1 =  (T[0] & T[1] & T[2] & T[3]);    //Donne un 1 si les 4 pions ont le meme bit à 1
        int resultat = resultat0 | resultat1;
        System.out.println("Resultat 0: "+Integer.toBinaryString(resultat0));
        System.out.println("Resultat 1: "+Integer.toBinaryString(resultat1));
        System.out.println("Resultat: "+Integer.toBinaryString(resultat));
        return resultat;
    
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

    public boolean placementVictorieux ()
    {
        return ligneGagnante() || colonneGagnante();
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
    
    
}
