package quartoprojet;

import java.util.HashMap;
import javafx.scene.media.AudioClip;

public class SoundSystem
{
    //**********************//
    // **** VARIABLES **** //
    //********************//
    
    private final HashMap<String, AudioClip> sound;
    private double volume;

    //**************************//
    // **** CONSTRUCTEURS **** //
    //************************//
    
    SoundSystem()
    {
        this.sound = new HashMap<>();
        this.volume = 0.5;

        this.sound.put("bouton_clique", new AudioClip(this.getClass().getResource("Sound/bouton_clique.wav").toString()));
        this.sound.put("bouton_survol", new AudioClip(this.getClass().getResource("Sound/bouton_survol.wav").toString()));
    }
    
    //********************//
    // **** GETTERS **** //
    //******************//
    
    public double getVolume()
    {
        return this.volume;
    }
    
    //********************//
    // **** SETTERS **** //
    //******************//
    
    public void setVolume(double volume)
    {
        this.volume = volume;
    }

    //*********************************//
    // **** METHODES ACCESSIBLES **** //
    //*******************************//
    
    public void jouerSoundBoutonClique()
    {
        this.sound.get("bouton_clique").setVolume(this.volume);
        this.sound.get("bouton_clique").play();
    }

    public void jouerSoundBoutonSurvol()
    {
        this.sound.get("bouton_survol").setVolume(this.volume);
        this.sound.get("bouton_survol").play();
    }
    
    //*************************************//
    // **** METHODES NON ACCESSIBLES **** //
    //***********************************//
}
