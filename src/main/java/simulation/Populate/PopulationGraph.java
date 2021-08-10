package simulation.Populate;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class PopulationGraph extends Application {

    private static final XYChart.Series seriesInfected = new XYChart.Series();
    private static final XYChart.Series seriesFitness = new XYChart.Series();

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

    public void showGenerationFitnessGraph(List<Integer> generationFitnessList) {
        Platform.runLater(() -> {
            //System.out.println("Generation: " + generations);

            for (int i = 0; i < generationFitnessList.size(); i++) {
                seriesFitness.getData().add(new XYChart.Data(String.valueOf(i),generationFitnessList.get(i)));
            }

        });
    }

//    @Override
//    public void start(Stage stage) {
//        stage.setTitle("");
//        Label label = new Label();
//        final CategoryAxis xAxis = new CategoryAxis();
//        final NumberAxis yAxis = new NumberAxis(0, 1100, 5);
//
//        xAxis.setLabel("Time");
//        yAxis.setLabel("Value");
//        AreaChart<String, Number> areaChart = new AreaChart<>(xAxis, yAxis);
//        areaChart.setTitle("Viral Evolution Chart");
//        seriesInfected.setName("Infected");
//        GridPane grid = new GridPane();
//        grid.setLayoutX(60);
//        grid.setLayoutY(5);
//        grid.setAlignment(Pos.CENTER);
//        grid.setHgap(10);
//        grid.setVgap(10);
//        grid.setPadding(new Insets(25, 25, 25, 25));
//        grid.add(label, 0, 1);
//        grid.add(text, 1, 1);
//        Group root = new Group(areaChart, grid);
//        scene = new Scene(root, 600, 400);
//        stage.setScene(scene);
//        areaChart.prefHeightProperty().bind(scene.heightProperty());
//        areaChart.prefWidthProperty().bind(scene.widthProperty());
//
//        areaChart.getData().addAll(seriesInfected);
//        stage.show();
//    }

    public void start(Stage stage) {

        FlowPane pane = new FlowPane(createPopulationChart(), createFitnessChart());
        stage.setTitle("");
        scene = new Scene(pane, 600, 800);
        stage.setScene(scene);
        stage.show();
    }

    public AreaChart<String, Number> createPopulationChart() {
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis(0, 1100, 5);

        xAxis.setLabel("Time");
        yAxis.setLabel("Value");
        AreaChart<String, Number> areaChart = new AreaChart<>(xAxis, yAxis);
        areaChart.setTitle("Viral Evolution Chart");
        seriesInfected.setName("Infected");

        areaChart.setPrefSize(600, 400);

        areaChart.getData().addAll(seriesInfected);

        return areaChart;
    }

    public AreaChart<String, Number> createFitnessChart() {
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis(0, 1100, 5);

        xAxis.setLabel("Generations");
        yAxis.setLabel("Fitness");
        AreaChart<String, Number> areaChart = new AreaChart<>(xAxis, yAxis);
        areaChart.setTitle("Generation Fitness Graph");
        seriesFitness.setName("Generations");

        areaChart.setPrefSize(600, 400);

        areaChart.getData().addAll(seriesFitness);
        return areaChart;
    }
}
