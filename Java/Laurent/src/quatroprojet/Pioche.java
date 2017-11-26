/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quartoprojet;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Laurent Fouetillou
 */
public class Pioche
{
    private int NB_PIONS = 16;
    //Pion [] pioche = new Pion [NB_PIONS];
    ArrayList <Pion> pioche = new ArrayList();
    
    public Pioche ()
    {
        remplirPioche();
    }
    
    public void viderPioche ()
    {
        //Arrays.fill(pioche, null);
        for (int i = 0; i<NB_PIONS; i++)
            pioche.add(i, null);
    }
    
    public void remplirPioche ()
    {
        System.out.println("Remplir pioche");
        pioche.clear();
        System.out.println(pioche.size());
        
        for (int i = 0; i<NB_PIONS; i++)
        {
            pioche.add(new Pion(i));
        }
        System.out.println(pioche.size());
    }
    
    public Pion retirerPion (int n)
    {
        return pioche.set(n, null);
    }
    
    public String toString ()
    {
        return "";
    }
    
    public void afficherPioche ()
    {
        for (Pion p : pioche)
        {
            if (p != null)
                System.out.println( p.getProprietes()+"  "+p);
            else
                System.out.println("Null");
        } 
    }
    
    public int getTaillePioche ()
    {
        return this.pioche.size();
    }
    
    public Pion getPion(int n)
    {
        return pioche.get(n);
    }
}
