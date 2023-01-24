import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

import java.awt.*;
import java.awt.event.MouseWheelEvent;

// API key = 1f945e3d94622f2d51a9fec7d07651f9

// http://api.aviationstack.com/v1/flights?access_key=1f945e3d94622f2d51a9fec7d07651f9&arr_iata=CDG


public class Interface extends Application {
    double PrePosY, PosY;
    double posObjX, posObjY;
    double newPosObjY, newPosObjX;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("TP JAVA Planète");

        Image img = new Image("file:./data/galaxy4.jpg");
        ImageView iv = new ImageView(img);
        iv.getTransforms().add(new Translate(-img.getWidth()/2, -img.getHeight()/2,4000));
        iv.fitHeightProperty();
        iv.fitWidthProperty();

        Moon moon = new Moon();
        Earth terre = new Earth();
        Pane pane = new Pane();

        Group rootterre = new Group(terre);
        Group rootlune = new Group(moon);

        Group planete = new Group();

        PointLight pl1 = new PointLight();
        pl1.getTransforms().add(new Translate(1000,0,-500));

        planete.getChildren().add(rootterre);
        planete.getChildren().add(rootlune);

        pane.getChildren().addAll(iv);
        pane.getChildren().addAll(planete);
        pane.getChildren().add(pl1);

        Scene ihm = new Scene(pane, 800, 600);

        primaryStage.setScene(ihm);
        World w1 = new World("./data/airport-codes_no_comma.csv");

        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setTranslateZ(-1000);
        camera.setNearClip(0.1);
        camera.setFarClip(20000.0); // distance derriere la planête
        camera.setFieldOfView(35);
        ihm.setCamera(camera);

        primaryStage.show();

        ihm.addEventHandler(MouseEvent.ANY, event -> {
            if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
                PrePosY = event.getY();
                System.out.println("Clicked on : (" + event.getSceneX() + ", " + event.getSceneY() + ")");
            }
            PosY = event.getY();
            if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
                if (PrePosY > PosY) {
                    planete.getTransforms().add(new Rotate(-1.0, Rotate.X_AXIS));
                }
                if (PrePosY < PosY) {
                    planete.getTransforms().add(new Rotate(1.0, Rotate.X_AXIS));
                }
                // A vous de completer
            }
        });
        ihm.addEventHandler(MouseEvent.ANY, event -> {
            if (event.getButton() == MouseButton.SECONDARY && event.getEventType() == MouseEvent.MOUSE_CLICKED) {
                PickResult pickResult = event.getPickResult();
                if (pickResult.getIntersectedNode() != null) {
                    posObjY = pickResult.getIntersectedTexCoord().getY();
                    posObjX = pickResult.getIntersectedTexCoord().getX();
                    System.out.println("Clicked droit : (" + pickResult + ")");

                    newPosObjY = -180.0 * (posObjY - 0.5);
                    newPosObjX = 360.0 * (posObjX - 0.5);

                    System.out.println("Coordonée clic droit : (" + posObjX +" ; " + posObjY+ ")");
                    System.out.println("New Coordonée clic droit : (" + newPosObjX +" ; " + newPosObjY + ")");

                    System.out.println(w1.findNearest(newPosObjX,newPosObjY));

                    terre.displayRedSphere(w1.findNearest(newPosObjX,newPosObjY));
                    //posObjX = event.getSceneX();
                    //posObjY = event.getSceneY();
                }
            }
        });
        primaryStage.addEventHandler(ScrollEvent.SCROLL, event->{
            double delta = event.getDeltaY();
            planete.translateZProperty().set(planete.getTranslateZ() + delta);
        });

    }

    public static void main(String[] args) {
        launch(args);
    }
}
