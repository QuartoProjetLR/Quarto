package quartoprojet;

public class GameSystem
{
    //**********************//
    // **** VARIABLES **** //
    //********************//
    
    private final Joueur joueur1;
    private final Joueur joueur2;
    private final SoundSystem soundSystem;
    private final MusicSystem musicSystem;
    
    //**************************//
    // **** CONSTRUCTEURS **** //
    //************************//
    
    GameSystem()
    {
        this.joueur1 = new Joueur();
        this.joueur2 = new Joueur();
        this.soundSystem = new SoundSystem();
        this.musicSystem = new MusicSystem();
    }
    
    //********************//
    // **** GETTERS **** //
    //******************//
    
    public Joueur getJoueur1()
    {
        return this.joueur1;
    }
    
    public Joueur getJoueur2()
    {
        return this.joueur2;
    }
    
    public SoundSystem getSoundSystem()
    {
        return this.soundSystem;
    }
    
    public MusicSystem getMusicSystem()
    {
        return this.musicSystem;
    }
    
    //********************//
    // **** SETTERS **** //
    //******************//
    
    //*********************************//
    // **** METHODES ACCESSIBLES **** //
    //*******************************//
    
    //*************************************//
    // **** METHODES NON ACCESSIBLES **** //
    //***********************************//
}
