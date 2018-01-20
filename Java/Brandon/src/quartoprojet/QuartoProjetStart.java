package quartoprojet;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class QuartoProjetStart extends Application
{
    @Override
    public void start(Stage fenetre) throws Exception
    {
        GameSystem gameSystem = new GameSystem();
        
        gameSystem.getMusicSystem().jouerMusicPrincipale();
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("FXML/MenuPrincipal.fxml"));

        Parent MenuPrincipalParent = loader.load();

        Scene MenuPrincipalScene = new Scene(MenuPrincipalParent);

        MenuPrincipalController menuPrincipalController = loader.getController();
        
        menuPrincipalController.recupererData(gameSystem);

        fenetre.setTitle("Quarto par Laurent Fouetillou, Pierrick Staes et Brandon Largeau");
        fenetre.setHeight(720);
        fenetre.setWidth(1280);
        fenetre.setResizable(false);
        fenetre.setScene(MenuPrincipalScene);
        fenetre.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }

}
