/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quartoprojet;

/**
 *
 * @author Laurent Fouetillou
 */
public class ZoneJeu
{
    private Pioche pioche;
    private Plateau plateau;
    private Pion pionPourAdversaire;
    private Joueur joueur1, joueur2, joueurActuel;
    
    public ZoneJeu ()
    {
        plateau = new Plateau ();
        pioche = new Pioche ();
        //TODO Recuperer les noms des joueurs dans les options
        joueur1 = new Joueur (Options.getNomJoueur1());
        joueur2 = new Joueur ("Joueur 2");
        joueurActuel = joueur1;
        
    }
    
    public void test ()
    {
        pioche.afficherPioche();
        //Le joueur1 prend un pion  BLANC CARRE ::MINCE:: PLEIN
        joueurActuel.setPionEnMain(pioche.retirerPion(3));
        pionPourAdversaire = joueurActuel.poserPion();
        
        //Le joueur2 pose le pion   BLANC CARRE ::MINCE:: PLEIN
        joueurActuel = joueur2;
        plateau.insererPion(pionPourAdversaire, 0, 0);
        //Le Joueur2 prend le pion  NOIR CARRE ::MINCE:: PLEIN
        joueurActuel.setPionEnMain(pioche.retirerPion(2));
        pionPourAdversaire = joueurActuel.getPionEnMain();
        
        //Le joueur1 pose le pion   NOIR ROND ::MINCE:: PLEIN
        joueurActuel = joueur1;
        plateau.insererPion(pionPourAdversaire, 0, 1);
        //Le Joueur1 prend le pion  BLANC ROND ::MINCE:: PLEIN
        joueurActuel.setPionEnMain(pioche.retirerPion(1));
        pionPourAdversaire = joueurActuel.getPionEnMain();
        
        //Le joueur2 prend un pion BLANC ROND ::MINCE:: PLEIN
        joueurActuel = joueur2;
        plateau.insererPion(pionPourAdversaire, 0, 2);
        //Le Joueur2 le pion        BLANC ROND ::MINCE:: PLEIN
        joueurActuel.setPionEnMain(pioche.retirerPion(8));
        //Le Joueur2 le pion        BLANC ROND ::LARGE:: PLEIN
        //joueurActuel.setPionEnMain(pioche.retirerPion(14));
        pionPourAdversaire = joueurActuel.getPionEnMain();
        
        //Le joueur1 pose le pion   BLANC CARRE ::MINCE:: PLEIN
        joueurActuel = joueur1;
        plateau.insererPion(pionPourAdversaire, 0, 3);
        joueurActuel.setPionEnMain(pioche.retirerPion(15));
        pionPourAdversaire = joueurActuel.getPionEnMain();
        
        plateau.plateauString();
        System.out.println(plateau.isLigneFull(0));
        System.out.println(plateau.ligneGagnante());
        
        plateau.insererPion(new Pion(0b1001), 0, 0);
        plateau.insererPion(new Pion(0b0011), 0, 1);
        plateau.insererPion(new Pion(0b0101), 0, 2);
        plateau.insererPion(new Pion(0b0001), 0, 3);
        System.out.println(plateau.isLigneFull(0));
        System.out.println(plateau.ligneGagnante());
        
    }
    //TODO reinitialiser
    public void reinitialiser ()
    {
        pioche.viderPioche();
        plateau.viderPlateau();
        pionPourAdversaire = null;
    }
    //TODO 
    public Pioche getPioche ()
    {
        return pioche;
    }
    public Plateau getPlateau ()
    {
        return plateau;
    }
    public Pion getPionPourAdversaire ()
    {
        return pionPourAdversaire;
    }
    public Joueur getJoueur1 ()
    {
        return joueur1;
    }
    public Joueur getJoueur2 ()
    {
        return joueur2;
    }
    
    public void setJoueurActuel(Joueur j)
    {
        joueurActuel = j;
    }
    
}
