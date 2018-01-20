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
public class Joueur
{
    private String nom;
    private Pion pionEnMain;
    
    public Joueur (String nom)
    {
        pionEnMain = null;
        this.nom = nom;
    }
    
    public Pion getPionEnMain ()
    {
        return pionEnMain;
    }
    public Pion poserPion ()
    {
        Pion p = pionEnMain;
        pionEnMain = null;
        return p;
    }
    public void setPionEnMain (Pion p)
    {
        pionEnMain = p;
    }
    
    @Override
    public String toString ()
    {
        return "Joueur{"+"nom="+nom+'}';
    }
    
    public String getNom ()
    {
        return nom;
    }
    
}
