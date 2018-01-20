package quartoprojet;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PlateauDeuxJoueursController
{
    private GameSystem gameSystem;

    @FXML
    private void boutonQuitter(MouseEvent event)
    {
        /*
        this.jouerSoundAuClique();
        Stage fenetre = (Stage) this.boutonQuitter.getScene().getWindow();
        this.gameSystem.getMusicSystem().stopMusicPrincipale();
        fenetre.close();
        */
    }
    
    public void recupererData(GameSystem gameSystem)
    {
        this.gameSystem = gameSystem;
    }
    
    @FXML
    private void jouerSoundAuSurvol()
    {
        this.gameSystem.getSoundSystem().jouerSoundBoutonSurvol();
    }
    
    @FXML
    private void jouerSoundAuClique()
    {
        this.gameSystem.getSoundSystem().jouerSoundBoutonClique();
    }
}
