/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quartoprojet;

//import org.json.*;

/**
 *
 * @author Laurent Fouetillou
 */
public class Options
{
    private static String nomJoueur1, nomJoueur2;
    boolean jouerContreIA;
    
    public Options ()
    {
        nomJoueur1 = "Joueur 1";
        nomJoueur2 = "Joueur 2";
    }
    
    public static String getNomJoueur1 ()
    {
        return nomJoueur1;
    }
    public static String getNomJoueur2 ()
    {
        return nomJoueur2;
    }
    
    public void collecterStatuts ()
    {
        
    }
}
