package chapter2;

import jdk.jfr.DataAmount;
import lombok.Data;

import java.util.Arrays;
import java.util.Random;

@Data
public class Population {
    private final Individual[] population;
    private double populationFitness = -1;

    public Population(int populationSize) {
        this.population = new Individual[populationSize];
    }

    public Population(int populationSize, int chromosomeLength) {
        this.population = new Individual[populationSize];
        for (int individualCount = 0; individualCount <
                populationSize; individualCount++) {
            Individual individual = new
                    Individual(chromosomeLength);
            this.population[individualCount] = individual;
        }
    }

    public Individual getFittest(int offset) {
        Arrays.sort(this.population, (o1, o2) -> {
            if (o1.getFitness() > o2.getFitness()) {
                return -1;
            } else if (o1.getFitness() < o2.getFitness()) {
                return 1;
            }
            return 0;
        });

        return this.population[offset];
    }

    public int size() {
        return this.population.length;
    }

    public void setIndividual(int offset, Individual individual) {
        population[offset] = individual;
    }
}