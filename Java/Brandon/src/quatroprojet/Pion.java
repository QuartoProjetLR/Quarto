package quatroprojet;

public class Pion
{
    //**********************//
    // **** VARIABLES **** //
    //********************//

    /*
    0 : 0000 :      NOIR    ROND    MINCE   PERCE 
    1 : 0001 :      BLANC   ROND    MINCE   PERCE 
    2 : 0010 :      NOIR    CARRE   MINCE   PERCE 
    3 : 0011 :      BLANC   CARRE   MINCE   PERCE 
    4 : 0100 :      NOIR    ROND    EPAIS   PERCE 
    5 : 0101 :      BLANC   ROND    EPAIS   PERCE 
    6 : 0110 :      NOIR    CARRE   EPAIS   PERCE 
    7 : 0111 :      BLANC   CARRE   EPAIS   PERCE 
    8 : 1000 :      NOIR    ROND    MINCE   PLEIN 
    9 : 1001 :      BLANC   ROND    MINCE   PLEIN 
    10 : 1010 :     NOIR    CARRE   MINCE   PLEIN 
    11 : 1011 :     BLANC   CARRE   MINCE   PLEIN 
    12 : 1100 :     NOIR    ROND    EPAIS   PLEIN 
    13 : 1101 :     BLANC   ROND    EPAIS   PLEIN 
    14 : 1110 :     NOIR    CARRE   EPAIS   PLEIN 
    15 : 1111 :     BLANC   CARRE   EPAIS   PLEIN
    */
    
    public final static int NOIR = 0;
    public final static int BLANC = 0b0001;
    public final static int ROND = 0;
    public final static int CARRE = 0b0010;
    public final static int MINCE = 0;
    public final static int EPAIS = 0b0100;
    public final static int PERCE = 0;
    public final static int PLEIN = 0b1000;

    public final static int NB_POSSIBILITES = 16;
    public final static int NB_CARACTERISTIQUES = 4;

    private final int caracteristique;
    private boolean selectionnable;

    //**************************//
    // **** CONSTRUCTEURS **** //
    //************************//
    
    public Pion(int caracteristique)
    {
        this.caracteristique = caracteristique;
        this.selectionnable = true;
    }

    //********************//
    // **** GETTERS **** //
    //******************//
    
    public int getCaracteristique()
    {
        return this.caracteristique;
    }

    //********************//
    // **** SETTERS **** //
    //******************//
    
    //*********************************//
    // **** METHODES ACCESSIBLES **** //
    //*******************************//
    
    public boolean estSelectionnable()
    {
        return this.selectionnable;
    }

    public void toggleSelectionnable()
    {
        this.selectionnable = !this.selectionnable;
    }

    @Override
    public String toString()
    {

        return "Pion : "
                + (((this.caracteristique & 0b0001) > 0) ? "blanc" : "noir") + ", "
                + (((this.caracteristique & 0b0010) > 0) ? "carré" : "rond") + ", "
                + (((this.caracteristique & 0b0100) > 0) ? "épais" : "mince") + ", "
                + (((this.caracteristique & 0b1000) > 0) ? "plein" : "percé");
    }

    //*************************************//
    // **** METHODES NON ACCESSIBLES **** //
    //***********************************//
}
