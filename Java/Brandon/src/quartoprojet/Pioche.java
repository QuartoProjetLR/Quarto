package quartoprojet;

import java.util.Arrays;

public class Pioche
{
    //**********************//
    // **** VARIABLES **** //
    //********************//

    private final Pion pioche[];

    //**************************//
    // **** CONSTRUCTEURS **** //
    //************************//
    
    public Pioche()
    {
        this.pioche = new Pion[Pion.NB_POSSIBILITES];
    }

    //********************//
    // **** GETTERS **** //
    //******************//
    
    public Pion getPionNumero(int numero)
    {
        Pion pionTemporaire = this.pioche[numero];
        this.pioche[numero] = null;

        return pionTemporaire;
    }

    //********************//
    // **** SETTERS **** //
    //******************//
    
    //*********************************//
    // **** METHODES ACCESSIBLES **** //
    //*******************************//
    
    public void genererPioche()
    {
        for (int i = 0; i < Pion.NB_POSSIBILITES; i++)
        {
            this.pioche[i] = new Pion(i);
        }
    }

    public void viderPioche()
    {
        Arrays.fill(this.pioche, null);
    }

    public void reinitialiserPioche()
    {
        this.viderPioche();
        this.genererPioche();
    }

    //*************************************//
    // **** METHODES NON ACCESSIBLES **** //
    //***********************************//
}
