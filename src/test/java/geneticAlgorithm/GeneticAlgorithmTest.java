package geneticAlgorithm;

import org.junit.Assert;
import org.junit.Test;
import simulation.PopulationGraph;

public class GeneticAlgorithmTest {
    @Test
    public void testGA() {
        GeneticAlgorithm ga = new GeneticAlgorithm();
        PopulationGraph pG = new PopulationGraph();
        Assert.assertNotNull(ga.runGA(new Virus(), pG, 1));
    }

}
    
