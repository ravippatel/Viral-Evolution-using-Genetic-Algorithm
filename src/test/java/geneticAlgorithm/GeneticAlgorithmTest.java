package geneticAlgorithm;

import config.Constant;
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

    @Test
    public void testHostPopulation() {
        GeneticAlgorithm ga = new GeneticAlgorithm();
        PopulationGraph pG = new PopulationGraph();
        ga.runGA(new Virus(), pG, 1);
        Assert.assertTrue(ga.virusPopulation.viruses.length > 0);
    }

    @Test
    public void testMutation() {
        GeneticAlgorithm ga = new GeneticAlgorithm();
        VirusPopulation vP = new VirusPopulation();
        PopulationGraph pG = new PopulationGraph();
        vP.initializePopulation(Constant.testPopulation);
        ga.runGA(new Virus(), pG, 1);
        Assert.assertNotEquals(ga.getGenerationCount(), 1000);
    }


}
    
