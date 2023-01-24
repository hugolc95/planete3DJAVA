import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.PointLight;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

public class Moon extends Group {

    private Sphere sph;
    public Moon() {
        sph = new Sphere(50.0);
        this.getChildren().add(sph);
        sph.setTranslateZ(500);

        PhongMaterial material = new PhongMaterial();
        material.setDiffuseMap(new Image("file:./data/lune.jpg"));
        sph.setMaterial(material);

        Rotate ry = new Rotate(-1.0, Rotate.Y_AXIS);
        this.getTransforms().add(ry);

        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long t) {
                ry.setAngle(t*1.30e-8);
            }
        };
        animationTimer.start();
    }

    public static void main(String[] args) {
    }
}
