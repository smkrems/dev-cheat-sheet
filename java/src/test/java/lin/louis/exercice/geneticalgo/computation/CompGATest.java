package lin.louis.exercice.geneticalgo.computation;

import org.junit.Test;

public class CompGATest {
    @Test
    public void geneticAlgo() {
//        GeneticAlgo.proceed(200);
        CompPopulation population = new CompPopulation(20, 5, 20);
        CompChromosome chromosome = population.generation(5000);
        System.out.println("Found GOAL ----------------");
        System.out.println("Generations: " + population.getGenerationNumber());
        System.out.println(chromosome.toString());
    }
}
