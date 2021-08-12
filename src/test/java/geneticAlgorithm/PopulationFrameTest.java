package geneticAlgorithm;

import org.junit.jupiter.api.Test;
import simulation.PopulationFrame;
import simulation.PopulationGraph;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PopulationFrameTest {

    @Test
    public void testHeightWidth(){
        PopulationGraph populationGraph=TestHelper.getMockPopulationGraph();
        PopulationFrame pf=new PopulationFrame(1000,1000, populationGraph);
        assertEquals(pf.getWidth(),1000);
        assertEquals(pf.getHeight(),1000);
    }

    @Test
        public void totalInfectedTest(){
        PopulationGraph populationGraph=TestHelper.getMockPopulationGraph();
        PopulationFrame pf=new PopulationFrame(1000,1000, populationGraph);

        assertEquals(1,pf.infected_main());
    }

    @Test
    public void totalRecoveredTest(){
        PopulationGraph populationGraph=TestHelper.getMockPopulationGraph();
        PopulationFrame pf=new PopulationFrame(1000,1000, populationGraph);

        assertEquals(0,pf.totalRecovered());
    }

    @Test
    public void totalVaccinatedTest(){
        PopulationGraph populationGraph=TestHelper.getMockPopulationGraph();
        PopulationFrame pf=new PopulationFrame(1000,1000, populationGraph);

        assertEquals(0,pf.totalVaccinated());
    }

    @Test
    public void totalDiedTest(){
        PopulationGraph populationGraph=TestHelper.getMockPopulationGraph();
        PopulationFrame pf=new PopulationFrame(1000,1000,populationGraph);

        assertEquals(0,pf.totalDied());
    }

}
