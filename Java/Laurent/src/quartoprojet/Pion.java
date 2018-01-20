/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quartoprojet;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.*;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorAdjust;

import javafx.scene.shape.*;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
/**
 *
 * @author Laurent Fouetillou
 */
public class Pion // extends Node
{
    final short COULEUR = (1<<0);
    final short FORME   = (1<<1);
    final short TAILLE  = (1<<2);
    final short PERCAGE = (1<<3);
    
    private String color, forme, taille, percage;
    
    protected int proprietes = 0b0000;
    private double shapeSize = 30;
    private boolean selectionnable;
    
    private StackPane stack;
    private ObservableList list;
    ImageView view_Forme, view_Hole;
    Image r_Forme, r_Percage ;
    Group Render;
    
    
    public Pion (int val)
    {
        super();
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
            r_Forme = new Image("Square.png");
        }
        else
        {
            forme = "ROND";
            r_Forme = new Image("Circle.png");
        }
        //Forme visible
        view_Forme = new ImageView(r_Forme);
        
        
        //Defini sa couleur
        if ((COULEUR & val) != 0)
        {
            color = "BLANC";
        }
        else
        {
            color = "NOIR";
            //Change la couleur du pion à partir de la couleur du pion de base
            ColorAdjust colorAdjust = new ColorAdjust ();
            colorAdjust.setHue(0.2);
            colorAdjust.setSaturation(0.2);
            view_Forme.setEffect(colorAdjust);
        }
        
        //Defini la taille
        if ((TAILLE & val) != 0)
        {
            taille = "MINCE";
            view_Forme.setScaleX(0.75);
            view_Forme.setScaleY(0.75);
        }
        else
        {
            taille = "LARGE";
        }
        //Rend la forme de la taille du conteneur
        view_Forme.setFitWidth(100);
        view_Forme.setPreserveRatio(true);
        stack = new StackPane (view_Forme);
        //stack.setStyle ("-fx-alignment: CENTER;");
        list = stack.getChildren();
        
        
        //Ajout de l'image du perçage
        r_Percage = new Image("Hole.png");
        view_Hole = new ImageView (r_Percage);
        //Rend la view de la meme taille que le container
        view_Hole.setFitWidth(100);
        view_Hole.setPreserveRatio(true);
        //Ajoute ou non le perçage
        if ((PERCAGE & val) != 0)
        {
            percage = "PERCE";
            
            view_Hole.setBlendMode(BlendMode.MULTIPLY);
            
            //Ajout de la meme image avec un blend mode different car
            //JavaFX ne gere pas les blends de la meme façon que Photoshop apparement...
            ImageView view_Hole2 = new ImageView (r_Percage);
            view_Hole2.setBlendMode(BlendMode.LIGHTEN);
            view_Hole2.setFitWidth(100);
            view_Hole2.setPreserveRatio(true);
            list.add(view_Hole2);
        }
        else
        {
            percage = "REMPLI";
            /*L'opacité du trou est mise à 0 afin que le pion occupe toujours la meme taille
            mais que le trou ne soit pas visible (pirouette pour eviter de faire une image
            pour chaque petit pion)*/
            view_Hole.setOpacity(0);
        }
        list.add(view_Hole);
        
        Render = new Group (list);
        //Render.getChildren().add(view_Hole);
        Render.getStylesheets().add("QuartoStyle.css");
        Render.setId(""+proprietes);
        Render.setCursor(Cursor.HAND);
        //Render.setDisable(true);
    }
    public Pion getPion()
    {
        return this;
    }
    public boolean isSelectionnable ()
    {
        return selectionnable;
    }
    public void setSelectionnable (boolean selectionnable)
    {
        this.selectionnable = selectionnable;
        if (!selectionnable)
            Render.setCursor(Cursor.DEFAULT);
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
