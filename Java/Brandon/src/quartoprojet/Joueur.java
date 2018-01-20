package quartoprojet;

public class Joueur
{
    //**********************//
    // **** VARIABLES **** //
    //********************//

    private static int NOMBRE_JOUEURS = 0;

    private String pseudo;
    private Pion pionSelectionne;

    //**************************//
    // **** CONSTRUCTEURS **** //
    //************************//
    
    public Joueur()
    {
        NOMBRE_JOUEURS++;
        this.pseudo = "Joueur " + NOMBRE_JOUEURS;
        this.pionSelectionne = null;
    }

    public Joueur(String pseudo)
    {
        NOMBRE_JOUEURS++;
        this.pseudo = pseudo;
    }

    //********************//
    // **** GETTERS **** //
    //******************//
    
    public String getPseudo()
    {
        return this.pseudo;
    }

    //********************//
    // **** SETTERS **** //
    //******************//
    
    public void setPseudo(String pseudo)
    {
        this.pseudo = pseudo;
    }

    //*********************************//
    // **** METHODES ACCESSIBLES **** //
    //*******************************//
    
    public void recupererPion(Pion pion)
    {
        this.pionSelectionne = pion;
    }

    public Pion deposerPion()
    {
        Pion pionTemporaire = this.pionSelectionne;
        this.pionSelectionne = null;

        return pionTemporaire;
    }

    //*************************************//
    // **** METHODES NON ACCESSIBLES **** //
    //***********************************//
}
