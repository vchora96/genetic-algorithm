package ru.oshkin;

import lombok.Data;

import java.util.Arrays;

@Data
public class Population {
    private final Person[] population;
    private double populationFitness = -1;

    public Population(int populationSize) {
        this.population = new Person[populationSize];
    }

    public Population(int populationSize, int chromosomeLength) {
        this.population = new Person[populationSize];
        for (int individualCount = 0; individualCount <
                populationSize; individualCount++) {
            Person person = new
                    Person(chromosomeLength);
            this.population[individualCount] = person;
        }
    }

    public Person getFittest(int offset) {
        Arrays.sort(this.population, new PersonComparator());
        return this.population[offset];
    }

    public int size() {
        return this.population.length;
    }

    public void setIndividual(int offset, Person person) {
        population[offset] = person;
    }
}