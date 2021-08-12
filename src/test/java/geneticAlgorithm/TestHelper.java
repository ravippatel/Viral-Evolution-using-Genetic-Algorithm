package geneticAlgorithm;

import org.mockito.Mockito;
import simulation.PopulationGraph;

import static org.mockito.Mockito.mock;

public class TestHelper {
    public static PopulationGraph getMockPopulationGraph() {
        PopulationGraph pG = mock(PopulationGraph.class);
        Mockito.doNothing().when(pG).showGenerationFitnessGraphForFirstVariant(Mockito.anyList());
        Mockito.doNothing().when(pG).showGenerationFitnessGraphForSecondVariant(Mockito.anyList());
        return  pG;
    }
}
