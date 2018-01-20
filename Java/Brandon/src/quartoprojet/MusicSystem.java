package quartoprojet;

import javafx.scene.media.AudioClip;
import static javafx.scene.media.AudioClip.INDEFINITE;

public class MusicSystem
{
    //**********************//
    // **** VARIABLES **** //
    //********************//
    
    private final AudioClip music;

    //**************************//
    // **** CONSTRUCTEURS **** //
    //************************//
    
    MusicSystem()
    {
        this.music = new AudioClip(this.getClass().getResource("Music/music_1.wav").toString());
        this.music.setVolume(0.5);
    }
    
    //********************//
    // **** GETTERS **** //
    //******************//
    
    public double getVolume()
    {
        return this.music.getVolume();
    }
    
    //********************//
    // **** SETTERS **** //
    //******************//
    
    public void setVolume(double volume)
    {
        this.music.stop();
        this.music.setVolume(volume);
        this.music.play();
    }

    //*********************************//
    // **** METHODES ACCESSIBLES **** //
    //*******************************//
    
    public void jouerMusicPrincipale()
    {
        this.music.setCycleCount(INDEFINITE);
        this.music.play();
    }
    
    public void stopMusicPrincipale()
    {
        this.music.stop();
    }
    
    //*************************************//
    // **** METHODES NON ACCESSIBLES **** //
    //***********************************//
}