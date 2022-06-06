package ru.oshkin;

public class BasicGA {
    public static void main(String[] args) {
        // Create GA object
        GeneticAlgorithm ga = new GeneticAlgorithm(100, 0.01,
                0.95, 2); // We’ll add a lot more here...
        // Initialize population
        Population population = ga.initPopulation(40);
        // The following is the new code you should be adding:
        ga.evalPopulation(population);
        int generation = 1;
        while (!ga.isTerminationConditionMet(population)) {
            // Print fittest individual from population
            System.out.println("Best solution: " + population.
                    getFittest(0).toString());
            // Apply crossover
            population = ga.crossoverPopulation(population);

            // Apply mutation
            population = ga.mutatePopulation(population);
            // Evaluate population
            ga.evalPopulation(population);
            // Increment the current generation
            generation++;
        }
        System.out.println("Found solution in " + generation + " generations");
        System.out.println("Best solution: " + population.getFittest(0).toString());
    }
}
