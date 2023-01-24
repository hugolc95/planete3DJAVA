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

import java.awt.*;

public class Earth extends Group {

    private Sphere sph;
    public Earth() {
        sph = new Sphere(300.0);
        this.getChildren().add(sph);

        PhongMaterial material = new PhongMaterial();
        material.setDiffuseMap(new Image("file:./data/earth_lights_4800.png"));
        material.setSelfIlluminationMap(new Image("file:./data/night_earth.png"));
        sph.setMaterial(material);

        Rotate ry = new Rotate(1.0, Rotate.Y_AXIS);
        this.getTransforms().add(ry);

        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long t) {
                ry.setAngle(t*2.30e-8);
            }
        };
        animationTimer.start();
    }

    public Sphere createSphere(Aeroport a, Color color) {
        Sphere rond = new Sphere(2);
        double teta = a.getLatitude();
        double phi = a.getLongitude();
        double sphRayon = sph.getRadius();
        double aeroX = sphRayon * Math.cos(Math.toRadians(teta * 0.65)) * Math.sin(Math.toRadians(phi));
        double aeroY = -sphRayon * Math.sin(Math.toRadians(teta * 0.65));
        double aeroZ = -sphRayon * Math.cos(Math.toRadians(teta * 0.65)) * Math.cos(Math.toRadians(phi));

        rond.setTranslateX(aeroX);
        rond.setTranslateY(aeroY);
        rond.setTranslateZ(aeroZ);
        PhongMaterial material = new PhongMaterial(color);
        rond.setMaterial(material);
        return rond;
    }

    public void displayRedSphere(Aeroport a){
        Sphere redsph = createSphere(a, Color.RED);
        this.getChildren().add(redsph);
    }


    public static void main(String[] args) {
    }
}
