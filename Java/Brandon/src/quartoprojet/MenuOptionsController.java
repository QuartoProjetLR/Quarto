package quartoprojet;

import java.io.IOException;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MenuOptionsController
{
    private GameSystem gameSystem;

    @FXML
    private Button boutonRetour;
    @FXML
    private Button boutonValiderJoueurs;
    @FXML
    private Button boutonValiderVolumes;
    @FXML
    private Label labelValidation;
    @FXML
    private TextField textFieldPseudoJoueur1;
    @FXML
    private TextField textFieldPseudoJoueur2;
    @FXML
    private ImageView imageViewJoueur;
    @FXML
    private ImageView imageViewSound;
    @FXML
    private AnchorPane anchorPaneJoueur;
    @FXML
    private AnchorPane anchorPaneSound;
    @FXML
    private Slider sliderSound;
    @FXML
    private Slider sliderMusic;

    @FXML
    public void boutonRetour() throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("FXML/MenuPrincipal.fxml"));

        Parent MenuPrincipalParent = loader.load();

        Scene MenuPrincipalScene = new Scene(MenuPrincipalParent);

        MenuPrincipalController menuPrincipalController = loader.getController();

        menuPrincipalController.recupererData(gameSystem);

        Stage fenetre = (Stage) boutonRetour.getScene().getWindow();
        this.jouerSoundAuClique();
        fenetre.setScene(MenuPrincipalScene);
        fenetre.show();
    }

    @FXML
    public void boutonValiderJoueur()
    {
        if (this.textFieldPseudoJoueur1.getText().equals("") || this.textFieldPseudoJoueur2.getText().equals(""))
        {
            this.messageValidationChangement("au moins un pseudo est manquant", 1, true);

            return;
        }

        if (this.textFieldPseudoJoueur1.getText().equals(this.gameSystem.getJoueur1().getPseudo()) && this.textFieldPseudoJoueur2.getText().equals(this.gameSystem.getJoueur2().getPseudo()))
        {
            this.messageValidationChangement("aucun changement détecté", 1, true);

            return;
        }
        
        if (this.textFieldPseudoJoueur1.getText().equals(this.textFieldPseudoJoueur2.getText()))
        {
            this.messageValidationChangement("les pseudos doivent être différents", 1, true);

            return;
        }

        if (!this.textFieldPseudoJoueur1.getText().equals(this.gameSystem.getJoueur1().getPseudo()))
        {
            this.gameSystem.getJoueur1().setPseudo(this.textFieldPseudoJoueur1.getText());
        }

        if (!this.textFieldPseudoJoueur2.getText().equals(this.gameSystem.getJoueur2().getPseudo()))
        {
            this.gameSystem.getJoueur2().setPseudo(this.textFieldPseudoJoueur2.getText());
        }

        this.messageValidationChangement("changement validé", 1, false);
    }
    
    public void boutonValiderSon()
    {
        if (this.sliderSound.getValue() == this.gameSystem.getSoundSystem().getVolume() && this.sliderMusic.getValue() == this.gameSystem.getMusicSystem().getVolume())
        {
            this.messageValidationChangement("aucun changement détecté", 2, true);

            return;
        }

        if (this.sliderSound.getValue() != this.gameSystem.getSoundSystem().getVolume())
        {
            this.gameSystem.getSoundSystem().setVolume(this.sliderSound.getValue());
        }
        
        if (this.sliderMusic.getValue() != this.gameSystem.getMusicSystem().getVolume())
        {
            this.gameSystem.getMusicSystem().setVolume(this.sliderMusic.getValue());
        }
        
        this.messageValidationChangement("changement validé", 2, false);
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
    
    @FXML
    private void afficherAnchorPaneSound()
    {
        if(this.anchorPaneSound.isVisible())
        {
            return;
        }

        this.jouerSoundAuClique();
        this.anchorPaneJoueur.setVisible(false);
        this.anchorPaneSound.setVisible(true);
        this.imageViewJoueur.setOpacity(0.6);
        this.imageViewJoueur.setScaleX(0.7);
        this.imageViewJoueur.setScaleY(0.7);
        this.imageViewSound.setOpacity(1);
        this.imageViewSound.setScaleX(1);
        this.imageViewSound.setScaleY(1);
    }
    
    @FXML
    private void afficherAnchorPaneJoueur()
    {
        if(this.anchorPaneJoueur.isVisible())
        {
            return;
        }

        this.jouerSoundAuClique();
        this.anchorPaneSound.setVisible(false);
        this.anchorPaneJoueur.setVisible(true);
        this.imageViewJoueur.setOpacity(1);
        this.imageViewJoueur.setScaleX(1);
        this.imageViewJoueur.setScaleY(1);
        this.imageViewSound.setOpacity(0.6);
        this.imageViewSound.setScaleX(0.7);
        this.imageViewSound.setScaleY(0.7);
    }

    public void recupererData(GameSystem gameSystem)
    {
        this.gameSystem = gameSystem;

        this.ecrirePseudoDansTextField(this.gameSystem.getJoueur1(), this.gameSystem.getJoueur2());
        this.initialiserSliderVolume(this.gameSystem.getSoundSystem().getVolume(), this.gameSystem.getMusicSystem().getVolume());
    }

    private void ecrirePseudoDansTextField(Joueur joueur1, Joueur joueur2)
    {
        this.textFieldPseudoJoueur1.setText(joueur1.getPseudo());
        this.textFieldPseudoJoueur2.setText(joueur2.getPseudo());
    }
    
    private void initialiserSliderVolume(double volumeSound, double volumeMusic)
    {
        this.sliderSound.setValue(volumeSound);
        this.sliderMusic.setValue(volumeMusic);
    }

    private void messageValidationChangement(String message, int page, boolean erreur)
    {
        String couleurText = "#289044";

        if (erreur)
        {
            couleurText = "#8c332a";
        }

        this.labelValidation.setStyle("-fx-text-fill: " + couleurText + ";");
        this.labelValidation.setText(message.toUpperCase());
        this.labelValidation.setVisible(true);
        
        if(page == 1)
        {
            this.boutonValiderJoueurs.setDisable(true);
        }
        else
        {
            this.boutonValiderVolumes.setDisable(true);
        }

        PauseTransition visiblePause = new PauseTransition(Duration.seconds(2));
        PauseTransition desactiverBoutonValider = new PauseTransition(Duration.seconds(2));

        visiblePause.setOnFinished(e -> this.labelValidation.setVisible(false));
        desactiverBoutonValider.setOnFinished(e ->
        {
            if(page == 1)  
            {
                this.boutonValiderJoueurs.setDisable(false);
            }
            else
            {
                this.boutonValiderVolumes.setDisable(false);
            }
        });

        visiblePause.play();
        desactiverBoutonValider.play();
    }
    
    @FXML
    public void changerVolumeSonRelachement()
    {
        this.jouerSoundAuClique();
    }
}
