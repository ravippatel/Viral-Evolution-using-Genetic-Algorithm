package simulation.Populate;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.List;


public class PopulationGraph extends Application {
    private static final XYChart.Series seriesFitnessOne = new XYChart.Series();
    private static final XYChart.Series seriesFitnessTwo = new XYChart.Series();
    private static final XYChart.Series seriesInfected = new XYChart.Series();
    private static final XYChart.Series seriesRecovered = new XYChart.Series();
    private static final XYChart.Series seriesDied = new XYChart.Series();
    private static final XYChart.Series seriesVaccinated = new XYChart.Series();

    private Scene scene;

    public static void showChartVirusEvolution(int infected, int population, int recovered, int vaccinated, int died, int days) {
        Platform.runLater(() -> {
            seriesInfected.getData().add(new XYChart.Data(String.valueOf(days), infected));
            seriesRecovered.getData().add(new XYChart.Data(String.valueOf(days), recovered));
            seriesVaccinated.getData().add(new XYChart.Data(String.valueOf(days), vaccinated));
            seriesDied.getData().add(new XYChart.Data(String.valueOf(days), died));
        });
    }

    public void showGenerationFitnessGraphForFirstVariant(List<Integer> generationFitnessList) {
        Platform.runLater(() -> {
            for (int i = 0; i < generationFitnessList.size(); i++) {
                seriesFitnessOne.getData().add(new XYChart.Data(String.valueOf(i), generationFitnessList.get(i)));
            }
        });
    }

    public void showGenerationFitnessGraphForSecondVariant(List<Integer> generationFitnessList) {
        Platform.runLater(() -> {
            for (int i = 0; i < generationFitnessList.size(); i++) {
                seriesFitnessTwo.getData().add(new XYChart.Data(String.valueOf(i), generationFitnessList.get(i)));
            }
        });
    }

    @Override
    public void start(Stage stage) {
        FlowPane pane = new FlowPane(createPopulationChart(), createFitnessChartVariantOne(), createFitnessChartVariantTwo());
        stage.setTitle("Graphical Representation");
        scene = new Scene(pane, 600, 1000);
        stage.setScene(scene);
        stage.show();
    }

    public AreaChart<String, Number> createPopulationChart() {
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();

        xAxis.setLabel("Number of Days");
        yAxis.setLabel("Count");
        xAxis.setTickLabelFill(Color.BLACK.darker().darker().darker().darker());
        yAxis.setTickLabelFill(Color.BLACK.darker().darker().darker().darker());
        AreaChart<String, Number> areaChart = new AreaChart<>(xAxis, yAxis);

        areaChart.setTitle("Viral Evolution Chart");
        seriesInfected.setName("Infected");
        seriesRecovered.setName("Recovered");
        seriesVaccinated.setName("Vaccinated");
        seriesDied.setName("Died");

        areaChart.setPrefSize(600, 400);
        areaChart.setLegendSide(Side.TOP);
        areaChart.getData().addAll(seriesInfected);
        areaChart.getData().addAll(seriesRecovered);
        areaChart.getData().addAll(seriesVaccinated);
        areaChart.getData().addAll(seriesDied);

        return areaChart;
    }

    public AreaChart<String, Number> createFitnessChartVariantOne() {
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();

        xAxis.setLabel("Generations");
        yAxis.setLabel("Fitness");
        AreaChart<String, Number> areaChart = new AreaChart<>(xAxis, yAxis);
        areaChart.setTitle("Generation Fitness Graph for Variant One");
        seriesFitnessOne.setName("Generations");

        areaChart.setPrefSize(600, 280);
        areaChart.setLegendSide(Side.TOP);
        areaChart.getData().addAll(seriesFitnessOne);
        return areaChart;
    }

    public AreaChart<String, Number> createFitnessChartVariantTwo() {
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();

        xAxis.setLabel("Generations");
        yAxis.setLabel("Fitness");
        AreaChart<String, Number> areaChart = new AreaChart<>(xAxis, yAxis);
        areaChart.setTitle("Generation Fitness Graph for Variant Two ");
        seriesFitnessTwo.setName("Generations");

        areaChart.setPrefSize(600, 280);
        areaChart.setLegendSide(Side.TOP);
        areaChart.getData().addAll(seriesFitnessTwo);
        return areaChart;
    }
}
