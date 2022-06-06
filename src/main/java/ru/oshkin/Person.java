package ru.oshkin;

import lombok.Data;

@Data
public class Person {
    private final int[] chromosome;
    private double fitnessFunction = -1;

    public Person(int[] chromosome) {
        // Create individual chromosome
        this.chromosome = chromosome;
    }

    public Person(int chromosomeLength) {
        this.chromosome = new int[chromosomeLength];
        for (int gene = 0; gene < chromosomeLength; gene++) {
            if (0.5 < Math.random()) {
                this.setGene(gene, 1);
            } else {
                this.setGene(gene, 0);
            }
        }
    }

    public int getChromosomeLength() {
        return this.chromosome.length;
    }

    public void setGene(int offset, int gene) {
        this.chromosome[offset] = gene;
    }

    public int getGene(int offset) {
        return this.chromosome[offset];
    }

    public String toString() {
        StringBuilder output = new StringBuilder();

        for (int i : this.chromosome) {
            output.append(i);
        }
        return output.toString();
    }
}


