import javafx.application.Application;
import javafx.animation.RotateTransition;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Test extends Application {
    @Override
    public void start(Stage stage) {
        // Créer une sphère avec un rayon de 100 pixels
        Sphere sphere = new Sphere(100);

        // Charger l'image de la Terre
        Image earthImage = new Image("file:.data/earth_lights_4800.png");

        // Créer un matériau Phong à partir de l'image de la Terre
        PhongMaterial earthMaterial = new PhongMaterial();
        earthMaterial.setDiffuseMap(earthImage);

        // Appliquer le matériau à la sphère
        sphere.setMaterial(earthMaterial);
        sphere.setTranslateX(300);
        sphere.setTranslateY(300);

        // Créer un groupe pour contenir la sphère
        Group group = new Group(sphere);

        // Créer la scène et ajouter le groupe à la scène
        Scene scene = new Scene(group, 600, 600);

        // Définir le titre de la fenêtre et ajouter la scène à la fenêtre
        stage.setTitle("Earth Sphere Example");
        stage.setScene(scene);

        RotateTransition rotateTransition = new RotateTransition(Duration.millis(3000), sphere);
        rotateTransition.setAxis(Rotate.Y_AXIS);
        rotateTransition.setFromAngle(0);
        rotateTransition.setToAngle(360);
        rotateTransition.setCycleCount(RotateTransition.INDEFINITE);
        rotateTransition.setAutoReverse(false);

        // Démarrer l'animation
        rotateTransition.play();

        // Afficher la fenêtre
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
