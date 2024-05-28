package com.tiago.siteswap.visualiser.siteswap_visualiser.diagram;

import com.tiago.siteswap.visualiser.siteswap_visualiser.Application;
import com.tiago.siteswap.visualiser.siteswap_visualiser.model.Sequence;
import com.tiago.siteswap.visualiser.siteswap_visualiser.model.Dot;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;

public class DiagramController {

    @FXML
    private AnchorPane diagramPane;
    @FXML
    private Line baseLine;
    Sequence sequence;
    ArrayList<DotGUI> guiContent;



    class DotGUI extends Circle {
        Dot dot;
        DotGUI destination;

        public DotGUI(Dot dot, double x, double y) {
            this.dot = dot;

            this.setCenterX(x);
            this.setCenterY(y);
        }
    }

    public void returnToMenu(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("welcome-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),640,400);

        stage.setScene(scene);
        stage.show();
    }



    public void initialize(String sequence) {
        this.guiContent = new ArrayList<>();
        this.sequence = new Sequence(sequence);

        // Initialisons les Dots
        double lineLength = Math.sqrt(Math.pow((baseLine.getEndX() - baseLine.getStartX()), 2) + Math.pow((baseLine.getEndY() - baseLine.getStartY()), 2));
        double offset = lineLength / this.sequence.getSize();

        double x = baseLine.localToScene(baseLine.getStartX(), baseLine.getStartY()).getX();
        double y = baseLine.localToScene(baseLine.getStartX(), baseLine.getStartY()).getY();

        for (int i = 0; i < this.sequence.getSize(); i++) {
            DotGUI d = new DotGUI(this.sequence.getContent().get(i),x,y);
            d.setRadius(5);
            guiContent.add(i,d);

            Label id = new Label(Integer.toString(d.dot.getNum()));
            Font f = new Font("CaskaydiaCove NF Regular", 11);
            id.setFont(f);
            id.setLayoutX(x-3);
            id.setLayoutY(y+10);

            diagramPane.getChildren().add(id);
            diagramPane.getChildren().add(d);
            x += offset;
        }

        for (int i = 0; i < guiContent.size(); i++) {
            DotGUI d = guiContent.get(i);
            d.destination = i+d.dot.getNum() > guiContent.size()-1 ? null : guiContent.get(i+d.dot.getNum());
        }
        // Apres avoir initialise les destinations, on verifie qu'aucun Dot n'est pointe par deux autres



        // On creer les arcs
        int idColor = 0;
        Color color = Color.GREEN;
        for (DotGUI d : guiContent) {
            idColor %= this.sequence.getNbUniq();
            switch (idColor) {
                case 0 : color = Color.YELLOWGREEN; break;
                case 1 : color = Color.BLUEVIOLET; break;
                case 2 : color = Color.SADDLEBROWN; break;
                case 3 : color = Color.DARKCYAN; break;
            }
            if (d.dot.getDestination() != null) {
                Path path = createArcBetweenPoints(d, d.destination, color);
                diagramPane.getChildren().add(path);
            }
            idColor++;
        }
    }

    private Path createArcBetweenPoints(DotGUI start, DotGUI end, Color color) {
        double startX = start.getCenterX();
        double startY = start.getCenterY();
        double endX = end.getCenterX();
        double endY = end.getCenterY();

        double radius = Math.abs(startX - endX) / 2; // Adjust the radius based on the distance between points

        Path path = new Path();
        path.getElements().add(new MoveTo(startX, startY));

        ArcTo arcTo = new ArcTo();
        arcTo.setX(endX);
        arcTo.setY(endY);
        arcTo.setRadiusX(radius);
        arcTo.setRadiusY(radius);
        arcTo.setSweepFlag(true); // Set to true for a curve in the positive angle direction
        arcTo.setLargeArcFlag(true); // Set to true for an arc larger than 180 degrees

        path.getElements().add(arcTo);

        path.setStroke(color); // Set the arc's color
        path.setStrokeWidth(2); // Set the arc's line width

        return path;
    }
}
