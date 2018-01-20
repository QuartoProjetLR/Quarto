package quartoprojet;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MenuPrincipalController
{
    private GameSystem gameSystem;

    @FXML
    private Label boutonPlateauDeuxJoueurs;
    @FXML
    private Label boutonOptions;
    @FXML
    private Label boutonQuitter;

    @FXML
    private void boutonPlateauDeuxJoueurs(MouseEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("FXML/PlateauDeuxJoueurs.fxml"));

        Parent PlateauDeuxJoueursParent = loader.load();

        Scene PlateauDeuxJoueursScene = new Scene(PlateauDeuxJoueursParent);

        PlateauDeuxJoueursController plateauDeuxJoueursController = loader.getController();
        
        plateauDeuxJoueursController.recupererData(gameSystem);
        
        Stage fenetre = (Stage) this.boutonOptions.getScene().getWindow();
        
        this.jouerSoundAuClique();
        
        fenetre.setScene(PlateauDeuxJoueursScene);
        fenetre.show();
    }
    
    @FXML
    private void boutonOptions(MouseEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("FXML/MenuOptions.fxml"));

        Parent MenuOptionsParent = loader.load();

        Scene MenuOptionsScene = new Scene(MenuOptionsParent);

        MenuOptionsController menuOptionsController = loader.getController();
        
        menuOptionsController.recupererData(gameSystem);
        
        Stage fenetre = (Stage) this.boutonOptions.getScene().getWindow();
        
        this.jouerSoundAuClique();
        
        fenetre.setScene(MenuOptionsScene);
        fenetre.show();
    }

    @FXML
    private void boutonQuitter(MouseEvent event)
    {
        this.jouerSoundAuClique();
        Stage fenetre = (Stage) this.boutonQuitter.getScene().getWindow();
        this.gameSystem.getMusicSystem().stopMusicPrincipale();
        fenetre.close();
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
