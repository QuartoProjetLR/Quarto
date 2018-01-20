package quartoprojet;

import java.util.Arrays;

public class Plateau
{
    //**********************//
    // **** VARIABLES **** //
    //********************//

    public final Pion plateau[][];

    //**************************//
    // **** CONSTRUCTEURS **** //
    //************************//
    
    public Plateau()
    {
        this.plateau = new Pion[Pion.NB_CARACTERISTIQUES][Pion.NB_CARACTERISTIQUES];
    }

    //********************//
    // **** GETTERS **** //
    //******************//
    
    //********************//
    // **** SETTERS **** //
    //******************//
    
    //*********************************//
    // **** METHODES ACCESSIBLES **** //
    //*******************************//
    
    public void deposerPion(Pion pion, int caseX, int caseY)
    {
        this.plateau[caseX][caseY] = pion;
    }

    public Pion reprendrePion(int caseX, int caseY)
    {
        Pion pionTemporaire = this.plateau[caseX][caseY];
        this.plateau[caseX][caseY] = null;

        return pionTemporaire;
    }

    public String informationPion(int caseX, int caseY)
    {
        return plateau[caseX][caseY].toString();
    }

    public void viderPlateau()
    {
        for (int i = 0; i < Pion.NB_CARACTERISTIQUES; i++)
        {
            Arrays.fill(this.plateau[i], null);
        }
    }

    public boolean isCaseVide(int caseX, int caseY)
    {
        return this.plateau[caseX][caseY] == null;
    }

    public boolean isLignePleine(int numeroLigne)
    {
        boolean lignePleine = true;

        for (Pion pion : this.plateau[numeroLigne])
        {
            if (pion == null)
            {
                lignePleine = false;
                break;
            }
        }
        return lignePleine;
    }

    public boolean isColonnePleine(int numeroColonne)
    {
        boolean colonnePleine = true;

        for (int i = 0; i < Pion.NB_CARACTERISTIQUES; i++)
        {
            if (this.plateau[i][numeroColonne] == null)
            {
                colonnePleine = false;
                break;
            }
        }
        return colonnePleine;
    }

    public boolean isDiagonalePleine(int numeroDiagonale)
    {
        boolean diagonalePleine = true;

        if (numeroDiagonale == 1)
        {
            for (int i = 0; i < Pion.NB_CARACTERISTIQUES; i++)
            {
                if (this.plateau[i][i] == null)
                {
                    diagonalePleine = false;
                    break;
                }
            }
        }
        else
        {
            for (int i = Pion.NB_CARACTERISTIQUES - 1; i >= 0; i--)
            {
                if (this.plateau[Pion.NB_CARACTERISTIQUES - i - 1][i] == null)
                {
                    diagonalePleine = false;
                    break;
                }
            }
        }
        return diagonalePleine;
    }

    public boolean isDiagonale(int caseX, int caseY)
    {
        return (((caseX == 0 && caseY == 0) || (caseX == 1 && caseY == 1) || (caseX == 2 && caseY == 2) || (caseX == 3 && caseY == 3))
                || ((caseX == 0 && caseY == 3) || (caseX == 1 && caseY == 2) || (caseX == 2 && caseY == 1) || (caseX == 3 && caseY == 0)));
    }

    public boolean victoireLigne(int numeroLigne)
    {
        /*
        0b00000001 :      NOIR
        0b00000010 :      BLANC
        0b00000100 :      ROND
        0b00001000 :      CARRE
        0b00010000 :      MINCE
        0b00100000 :      EPAIS
        0b01000000 :      PERCE
        0b10000000 :      PLEIN
         */

        int pion1 = (((this.plateau[numeroLigne][0].getCaracteristique() & (1 << 0)) > 0) ? 0b00000010 : 0b00000001)
                + (((this.plateau[numeroLigne][0].getCaracteristique() & (1 << 1)) > 0) ? 0b00001000 : 0b00000100)
                + (((this.plateau[numeroLigne][0].getCaracteristique() & (1 << 2)) > 0) ? 0b00100000 : 0b00010000)
                + (((this.plateau[numeroLigne][0].getCaracteristique() & (1 << 3)) > 0) ? 0b10000000 : 0b01000000);

        int pion2 = (((this.plateau[numeroLigne][1].getCaracteristique() & (1 << 0)) > 0) ? 0b00000010 : 0b00000001)
                + (((this.plateau[numeroLigne][1].getCaracteristique() & (1 << 1)) > 0) ? 0b00001000 : 0b00000100)
                + (((this.plateau[numeroLigne][1].getCaracteristique() & (1 << 2)) > 0) ? 0b00100000 : 0b00010000)
                + (((this.plateau[numeroLigne][1].getCaracteristique() & (1 << 3)) > 0) ? 0b10000000 : 0b01000000);

        int pion3 = (((this.plateau[numeroLigne][2].getCaracteristique() & (1 << 0)) > 0) ? 0b00000010 : 0b00000001)
                + (((this.plateau[numeroLigne][2].getCaracteristique() & (1 << 1)) > 0) ? 0b00001000 : 0b00000100)
                + (((this.plateau[numeroLigne][2].getCaracteristique() & (1 << 2)) > 0) ? 0b00100000 : 0b00010000)
                + (((this.plateau[numeroLigne][2].getCaracteristique() & (1 << 3)) > 0) ? 0b10000000 : 0b01000000);

        int pion4 = (((this.plateau[numeroLigne][3].getCaracteristique() & (1 << 0)) > 0) ? 0b00000010 : 0b00000001)
                + (((this.plateau[numeroLigne][3].getCaracteristique() & (1 << 1)) > 0) ? 0b00001000 : 0b00000100)
                + (((this.plateau[numeroLigne][3].getCaracteristique() & (1 << 2)) > 0) ? 0b00100000 : 0b00010000)
                + (((this.plateau[numeroLigne][3].getCaracteristique() & (1 << 3)) > 0) ? 0b10000000 : 0b01000000);

        return (pion1 & pion2 & pion3 & pion4) > 0;
    }

    public boolean victoireColonne(int numeroColonne)
    {
        /*
        0b00000001 :      NOIR
        0b00000010 :      BLANC
        0b00000100 :      ROND
        0b00001000 :      CARRE
        0b00010000 :      MINCE
        0b00100000 :      EPAIS
        0b01000000 :      PERCE
        0b10000000 :      PLEIN
         */

        int pion1 = (((this.plateau[0][numeroColonne].getCaracteristique() & (1 << 0)) > 0) ? 0b00000010 : 0b00000001)
                + (((this.plateau[0][numeroColonne].getCaracteristique() & (1 << 1)) > 0) ? 0b00001000 : 0b00000100)
                + (((this.plateau[0][numeroColonne].getCaracteristique() & (1 << 2)) > 0) ? 0b00100000 : 0b00010000)
                + (((this.plateau[0][numeroColonne].getCaracteristique() & (1 << 3)) > 0) ? 0b10000000 : 0b01000000);

        int pion2 = (((this.plateau[1][numeroColonne].getCaracteristique() & (1 << 0)) > 0) ? 0b00000010 : 0b00000001)
                + (((this.plateau[1][numeroColonne].getCaracteristique() & (1 << 1)) > 0) ? 0b00001000 : 0b00000100)
                + (((this.plateau[1][numeroColonne].getCaracteristique() & (1 << 2)) > 0) ? 0b00100000 : 0b00010000)
                + (((this.plateau[1][numeroColonne].getCaracteristique() & (1 << 3)) > 0) ? 0b10000000 : 0b01000000);

        int pion3 = (((this.plateau[2][numeroColonne].getCaracteristique() & (1 << 0)) > 0) ? 0b00000010 : 0b00000001)
                + (((this.plateau[2][numeroColonne].getCaracteristique() & (1 << 1)) > 0) ? 0b00001000 : 0b00000100)
                + (((this.plateau[2][numeroColonne].getCaracteristique() & (1 << 2)) > 0) ? 0b00100000 : 0b00010000)
                + (((this.plateau[2][numeroColonne].getCaracteristique() & (1 << 3)) > 0) ? 0b10000000 : 0b01000000);

        int pion4 = (((this.plateau[3][numeroColonne].getCaracteristique() & (1 << 0)) > 0) ? 0b00000010 : 0b00000001)
                + (((this.plateau[3][numeroColonne].getCaracteristique() & (1 << 1)) > 0) ? 0b00001000 : 0b00000100)
                + (((this.plateau[3][numeroColonne].getCaracteristique() & (1 << 2)) > 0) ? 0b00100000 : 0b00010000)
                + (((this.plateau[3][numeroColonne].getCaracteristique() & (1 << 3)) > 0) ? 0b10000000 : 0b01000000);

        return (pion1 & pion2 & pion3 & pion4) > 0;
    }

    public boolean victoireDiagonale(int numeroDiagonale)
    {
        /*
        0b00000001 :      NOIR
        0b00000010 :      BLANC
        0b00000100 :      ROND
        0b00001000 :      CARRE
        0b00010000 :      MINCE
        0b00100000 :      EPAIS
        0b01000000 :      PERCE
        0b10000000 :      PLEIN
        */

        int pion1, pion2, pion3, pion4;

        if (numeroDiagonale == 1)
        {
            pion1 = (((this.plateau[0][0].getCaracteristique() & (1 << 0)) > 0) ? 0b00000010 : 0b00000001)
                    + (((this.plateau[0][0].getCaracteristique() & (1 << 1)) > 0) ? 0b00001000 : 0b00000100)
                    + (((this.plateau[0][0].getCaracteristique() & (1 << 2)) > 0) ? 0b00100000 : 0b00010000)
                    + (((this.plateau[0][0].getCaracteristique() & (1 << 3)) > 0) ? 0b10000000 : 0b01000000);

            pion2 = (((this.plateau[1][1].getCaracteristique() & (1 << 0)) > 0) ? 0b00000010 : 0b00000001)
                    + (((this.plateau[1][1].getCaracteristique() & (1 << 1)) > 0) ? 0b00001000 : 0b00000100)
                    + (((this.plateau[1][1].getCaracteristique() & (1 << 2)) > 0) ? 0b00100000 : 0b00010000)
                    + (((this.plateau[1][1].getCaracteristique() & (1 << 3)) > 0) ? 0b10000000 : 0b01000000);

            pion3 = (((this.plateau[2][2].getCaracteristique() & (1 << 0)) > 0) ? 0b00000010 : 0b00000001)
                    + (((this.plateau[2][2].getCaracteristique() & (1 << 1)) > 0) ? 0b00001000 : 0b00000100)
                    + (((this.plateau[2][2].getCaracteristique() & (1 << 2)) > 0) ? 0b00100000 : 0b00010000)
                    + (((this.plateau[2][2].getCaracteristique() & (1 << 3)) > 0) ? 0b10000000 : 0b01000000);

            pion4 = (((this.plateau[3][3].getCaracteristique() & (1 << 0)) > 0) ? 0b00000010 : 0b00000001)
                    + (((this.plateau[3][3].getCaracteristique() & (1 << 1)) > 0) ? 0b00001000 : 0b00000100)
                    + (((this.plateau[3][3].getCaracteristique() & (1 << 2)) > 0) ? 0b00100000 : 0b00010000)
                    + (((this.plateau[3][3].getCaracteristique() & (1 << 3)) > 0) ? 0b10000000 : 0b01000000);
        }
        else
        {
            pion1 = (((this.plateau[0][3].getCaracteristique() & (1 << 0)) > 0) ? 0b00000010 : 0b00000001)
                    + (((this.plateau[0][3].getCaracteristique() & (1 << 1)) > 0) ? 0b00001000 : 0b00000100)
                    + (((this.plateau[0][3].getCaracteristique() & (1 << 2)) > 0) ? 0b00100000 : 0b00010000)
                    + (((this.plateau[0][3].getCaracteristique() & (1 << 3)) > 0) ? 0b10000000 : 0b01000000);

            pion2 = (((this.plateau[1][2].getCaracteristique() & (1 << 0)) > 0) ? 0b00000010 : 0b00000001)
                    + (((this.plateau[1][2].getCaracteristique() & (1 << 1)) > 0) ? 0b00001000 : 0b00000100)
                    + (((this.plateau[1][2].getCaracteristique() & (1 << 2)) > 0) ? 0b00100000 : 0b00010000)
                    + (((this.plateau[1][2].getCaracteristique() & (1 << 3)) > 0) ? 0b10000000 : 0b01000000);

            pion3 = (((this.plateau[2][1].getCaracteristique() & (1 << 0)) > 0) ? 0b00000010 : 0b00000001)
                    + (((this.plateau[2][1].getCaracteristique() & (1 << 1)) > 0) ? 0b00001000 : 0b00000100)
                    + (((this.plateau[2][1].getCaracteristique() & (1 << 2)) > 0) ? 0b00100000 : 0b00010000)
                    + (((this.plateau[2][1].getCaracteristique() & (1 << 3)) > 0) ? 0b10000000 : 0b01000000);

            pion4 = (((this.plateau[3][0].getCaracteristique() & (1 << 0)) > 0) ? 0b00000010 : 0b00000001)
                    + (((this.plateau[3][0].getCaracteristique() & (1 << 1)) > 0) ? 0b00001000 : 0b00000100)
                    + (((this.plateau[3][0].getCaracteristique() & (1 << 2)) > 0) ? 0b00100000 : 0b00010000)
                    + (((this.plateau[3][0].getCaracteristique() & (1 << 3)) > 0) ? 0b10000000 : 0b01000000);
        }

        return (pion1 & pion2 & pion3 & pion4) > 0;
    }

    public boolean verifierVictoire(int caseX, int caseY, boolean isLigne, boolean isColonne, boolean isDiagonale)
    {
        boolean victoire = false;

        if (isLigne && this.victoireLigne(caseX))
        {
            victoire = true;
        }

        if (!victoire && isColonne && this.victoireColonne(caseY))
        {
            victoire = true;
        }

        if (!victoire && isDiagonale && (this.victoireDiagonale(1) || this.victoireDiagonale(2)))
        {
            victoire = true;
        }

        return victoire;
    }

    //*************************************//
    // **** METHODES NON ACCESSIBLES **** //
    //***********************************//
}
