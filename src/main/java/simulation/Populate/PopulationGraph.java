package simulation.Populate;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.text.SimpleDateFormat;
import java.util.Date;


public class PopulationGraph extends Application {

    private static final XYChart.Series seriesInfected = new XYChart.Series();
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
    Text text = new Text();
    private Scene scene;

    public void showChartVirusEvolution(int infected, int population) {
        Platform.runLater(() -> {
            System.out.println("Infected: " + infected);
            Date now = new Date();
            seriesInfected.getData().add(new XYChart.Data(simpleDateFormat.format(now), infected));
        });
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("");
        Label label = new Label();
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis(0, 1100, 5);

        xAxis.setLabel("Time");
        yAxis.setLabel("Value");
        AreaChart<String, Number> areaChart = new AreaChart<>(xAxis, yAxis);
        areaChart.setTitle("Viral Evolution Chart");
        GridPane grid = new GridPane();
        grid.setLayoutX(60);
        grid.setLayoutY(5);
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.add(label, 0, 1);
        grid.add(text, 1, 1);
        Group root = new Group(areaChart, grid);
        scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        areaChart.prefHeightProperty().bind(scene.heightProperty());
        areaChart.prefWidthProperty().bind(scene.widthProperty());
        areaChart.getData().addAll(seriesInfected);
        stage.show();
    }
}
