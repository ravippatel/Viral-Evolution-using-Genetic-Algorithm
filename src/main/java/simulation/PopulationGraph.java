package simulation;

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
    private static final XYChart.Series fitnessOneSeries = new XYChart.Series();
    private static final XYChart.Series fitnessTwoSeries = new XYChart.Series();
    private static final XYChart.Series infectedSeries = new XYChart.Series();
    private static final XYChart.Series recoveredSeries = new XYChart.Series();
    private static final XYChart.Series diedSeries = new XYChart.Series();
    private static final XYChart.Series vaccinatedSeries = new XYChart.Series();
    private static final XYChart.Series deltaSeries = new XYChart.Series();



    private Scene scene;

    @Override
    public void start(Stage stage) {
        FlowPane pane = new FlowPane(createPopulationChart(), createFitnessChartVariantOne(), createFitnessChartVariantTwo(), createDeltaVariantChart());
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
        infectedSeries.setName("Infected");
        recoveredSeries.setName("Recovered");
        vaccinatedSeries.setName("Vaccinated");
        diedSeries.setName("Died");

        areaChart.setPrefSize(600, 350);
        areaChart.setLegendSide(Side.TOP);
        areaChart.getData().addAll(infectedSeries);
        areaChart.getData().addAll(recoveredSeries);
        areaChart.getData().addAll(vaccinatedSeries);
        areaChart.getData().addAll(diedSeries);

        return areaChart;
    }

    public AreaChart<String, Number> createFitnessChartVariantOne() {
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();

        xAxis.setLabel("Generations");
        yAxis.setLabel("Fitness");
        AreaChart<String, Number> areaChart = new AreaChart<>(xAxis, yAxis);
        areaChart.setTitle("Generation Fitness Graph for Variant One");
        fitnessOneSeries.setName("Generations");

        areaChart.setPrefSize(600, 200);
        areaChart.setLegendSide(Side.TOP);
        areaChart.getData().addAll(fitnessOneSeries);
        return areaChart;
    }

    public AreaChart<String, Number> createFitnessChartVariantTwo() {
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();

        xAxis.setLabel("Generations");
        yAxis.setLabel("Fitness");
        AreaChart<String, Number> areaChart = new AreaChart<>(xAxis, yAxis);
        areaChart.setTitle("Generation Fitness Graph for Variant Two ");
        fitnessTwoSeries.setName("Generations");

        areaChart.setPrefSize(600, 200);
        areaChart.setLegendSide(Side.TOP);
        areaChart.getData().addAll(fitnessTwoSeries);
        return areaChart;
    }

    public AreaChart<String, Number> createDeltaVariantChart() {
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();

        xAxis.setLabel("Generations");
        yAxis.setLabel("Fitness");
        AreaChart<String, Number> areaChart = new AreaChart<>(xAxis, yAxis);
        areaChart.setTitle("Generation Fitness Graph for Delta Variant ");
        deltaSeries.setName("Generations");

        areaChart.setPrefSize(600, 200);
        areaChart.setLegendSide(Side.TOP);
        areaChart.getData().addAll(deltaSeries);
        return areaChart;
    }

    public static void showChartVirusEvolution(int infected, int population, int recovered, int vaccinated, int died, int days) {
        Platform.runLater(() -> {
            infectedSeries.getData().add(new XYChart.Data(String.valueOf(days), infected));
            recoveredSeries.getData().add(new XYChart.Data(String.valueOf(days), recovered));
            vaccinatedSeries.getData().add(new XYChart.Data(String.valueOf(days), vaccinated));
            diedSeries.getData().add(new XYChart.Data(String.valueOf(days), died));
        });
    }

    public void showGenerationFitnessGraphForFirstVariant(List<Integer> generationFitnessList) {
        Platform.runLater(() -> {
            for (int i = 0; i < generationFitnessList.size(); i++) {
                fitnessOneSeries.getData().add(new XYChart.Data(String.valueOf(i), generationFitnessList.get(i)));
            }
        });
    }

    public void showGenerationFitnessGraphForSecondVariant(List<Integer> generationFitnessList) {
        Platform.runLater(() -> {
            for (int i = 0; i < generationFitnessList.size(); i++) {
                fitnessTwoSeries.getData().add(new XYChart.Data(String.valueOf(i), generationFitnessList.get(i)));
            }
        });
    }

    public void showDeltaVariant(List<Integer> generationFitnessList) {
        Platform.runLater(() -> {
            for (int i = 0; i < generationFitnessList.size(); i++) {
                deltaSeries.getData().add(new XYChart.Data(String.valueOf(i), generationFitnessList.get(i)));
            }
        });
    }
}
