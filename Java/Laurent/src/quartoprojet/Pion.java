/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quartoprojet;

import javafx.collections.ObservableList;
import javafx.scene.*;

import javafx.scene.shape.*;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
/**
 *
 * @author Laurent Fouetillou
 */
public class Pion
{
    final short COULEUR = (1<<0);
    final short FORME   = (1<<1);
    final short TAILLE  = (1<<2);
    final short PERCAGE = (1<<3);
    
    private String color, forme, taille, percage;
    
    protected int proprietes = 0b0000;
    private double shapeSize = 50;
    private boolean selectionnable;
    
    private StackPane stack;
    private ObservableList list;
    Shape r_Forme, r_Percage ;
    Group Render;
    
    public Pion (int val)
    {
        selectionnable = true;
        this.proprietes = val;
        color   = ((val & COULEUR)  != 0) ? "BLANC" : "NOIR";
        forme   = ((val & FORME)    != 0) ? "CARRE" : "ROND";
        taille  = ((val & TAILLE)   != 0) ? "LARGE" : "MINCE";
        percage = ((val & PERCAGE)  != 0) ? "PERCE" : "REMPLI";

        genererGraphics(val);
        
    }
    
    private void genererGraphics (int val)
    {
        //Defini la forme du pion
        if ((FORME & val) != 0)
        {
            forme = "CARRE";
            r_Forme = new Rectangle(shapeSize*2, shapeSize*2);
            //(Rectangle) r_Forme
        }
        else
        {
            forme = "ROND";
            r_Forme = new Circle(shapeSize);
        }
        
        stack = new StackPane (r_Forme);
        list = stack.getChildren();
        
        //Defini sa couleur
        if ((COULEUR & val) != 0)
        {
            color = "BLANC";
            r_Forme.setFill(Color.BEIGE);
        }
        else
        {
            color = "NOIR";
            r_Forme.setFill(Color.GREY);
        }
        r_Forme.getStyleClass().add("pion");
        
        //Defini la taille
        if ((TAILLE & val) != 0)
        {
            taille = "MINCE";
            r_Forme.setScaleX(0.75);
            r_Forme.setScaleY(0.75);
        }
        else
        {
            taille = "LARGE";
        }
        
        //Ajoute ou non le perçage
        if ((PERCAGE & val) != 0)
        {
            percage = "PERCE";
            r_Percage = new Circle(15);
            r_Percage.getStyleClass().add("hole");
            list.add(r_Percage);
        }
        else
        {
            percage = "REMPLI";
        }
    
        Render = new Group (stack);
        Render.getStylesheets().add("PionStyle.css");
    }
    
    public boolean isSelectionnable ()
    {
        return selectionnable;
    }
    public void setSelectionnable (boolean selectionnable)
    {
        this.selectionnable = selectionnable;
    }
    
    public int getProprietes ()
    {
        return proprietes;
    }

    public String getBinProperties ()
    {
        return Integer.toBinaryString(proprietes);
    }
    public String toString ()
    {
        return color+" "+forme+" "+taille+" "+percage+" "+Integer.toBinaryString(proprietes);
    }
    
    public String toShortString ()
    {
        return ""+color.charAt(0)+forme.charAt(0)+taille.charAt(0)+percage.charAt(0);
    }
    
    public Group getRender ()
    {
        return Render;
    }
    
    
}
