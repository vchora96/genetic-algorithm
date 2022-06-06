package ru.oshkin;

public class BasicGA {
    public static void main(String[] args) {
        // Create GA object
        GeneticAlgorithmHelper ga = new GeneticAlgorithmHelper(100, 0.01,
                0.95, 3); // We’ll add a lot more here...
        // Initialize population
        Population population = ga.initPopulation(40);
        // The following is the new code you should be adding:
        ga.evaluatePopulation(population);
        int generation = 1;
        while (!ga.isShouldStop(population)) {
            // Print fittest individual from population
            System.out.println("Лучшее решение: " + population.
                    getBest(0).toString());
            // Apply crossover
            population = ga.crossoverPopulation(population);

            // Apply mutation
            population = ga.mutatePopulation(population);
            // Evaluate population
            ga.evaluatePopulation(population);
            // Increment the current generation
            generation++;
        }
        System.out.println("Решение найдено за " + generation + " поколений");
        System.out.println("Лучшее решение: " + population.getBest(0).toString());
    }
}
