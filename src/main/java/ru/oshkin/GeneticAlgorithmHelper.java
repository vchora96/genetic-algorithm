package ru.oshkin;

/**
 * Lots of comments in the source that are omitted here!
 */
public class GeneticAlgorithmHelper {
    private final int populationSize;
    private final double mutationRate;
    private final double crossoverRate;
    private final int elitismCount;

    public GeneticAlgorithmHelper(int populationSize, double mutationRate, double
            crossoverRate, int elitismCount) {
        this.populationSize = populationSize;
        this.mutationRate = mutationRate;
        this.crossoverRate = crossoverRate;
        this.elitismCount = elitismCount;
    }

    /**
     * Many more methods implemented later...
     */
    public Population initPopulation(int chromosomeLength) {
        return new Population(this.populationSize, chromosomeLength);
    }

    public double calculateFitnessFunction(Person person) {
        // Track number of correct genes
        int correctGenes = 0;
        // Loop over individual's genes
        for (int geneIndex = 0; geneIndex < person.getChromosomeLength(); geneIndex++) {
            // Add one fitness point for each "1" found
            if (person.getGene(geneIndex) == 1) {
                correctGenes += 1;
            }
        }

        // Calculate fitness
        double fitness = (double) correctGenes / person.getChromosomeLength();
        // Store fitness
        person.setFitnessFunction(fitness);
        return fitness;
    }

    public void evaluatePopulation(Population population) {
        double populationFitness = 0;
        for (Person person : population.getPopulation()) {
            populationFitness += calculateFitnessFunction(person);
        }
        population.setPopulationFitness(populationFitness);
    }

    public boolean isShouldStop(Population population) {
        for (Person person : population.getPopulation()) {
            if (person.getFitnessFunction() == 1) {
                return true;
            }
        }
        return false;
    }

    public Person chooseParent(Population population) {
        // Get individuals
        Person[] people = population.getPopulation();
        // Spin roulette wheel
        double populationFitness = population.getPopulationFitness();
        double rouletteWheelPosition = Math.random() * populationFitness;

        // Find parent
        double spinWheel = 0;
        for (Person person : people) {
            spinWheel += person.getFitnessFunction();
            if (spinWheel >= rouletteWheelPosition) {
                return person;
            }
        }
        return people[population.size() - 1];
    }

    public Population crossoverPopulation(Population population) {
        // Create new population
        Population newPopulation = new Population(population.size());
        // Loop over current population by fitness
        for (int i = 0; i < population.size();
             i++) {
            Person parent1 = population.getBest(i);
            // Apply crossover to this individual?
            if (this.crossoverRate > Math.random() && i >
                    this.elitismCount) {
                // Initialize offspring
                Person offspring = new Person(parent1.
                        getChromosomeLength());
                // Find second parent
                Person parent2 = chooseParent(population);
                // Loop over genome
                for (int geneIndex = 0; geneIndex < parent1.
                        getChromosomeLength(); geneIndex++) {
                    // Use half of parent1's genes and half of parent2 's genes
                    if (0.5 > Math.random()) {
                        offspring.setGene(geneIndex,
                                parent1.getGene(geneIndex));
                    } else {
                        offspring.setGene(geneIndex,
                                parent2.getGene(geneIndex));
                    }
                }
                // Add offspring to new population
                newPopulation.setPerson(i,
                        offspring);
            } else {
                // Add individual to new population without applying crossover
                newPopulation.setPerson
                        (i, parent1);
            }
        }
        return newPopulation;
    }

    public Population mutatePopulation(Population population) {
        // Initialize new population
        Population newPopulation = new Population(this.populationSize);
        // Loop over current population by fitness
        for (int populationIndex = 0; populationIndex < population.size();
             populationIndex++) {
            Person person = population.
                    getBest(populationIndex);
            // Loop over individual's genes
            for (int geneIndex = 0; geneIndex < person.
                    getChromosomeLength(); geneIndex++) {
                // Skip mutation if this is an elite individual
                if (populationIndex >= this.elitismCount) {
                    // Does this gene need mutation?
                    if (this.mutationRate > Math.random()) {
                        // Get new gene
                        int newGene = 1;
                        if (person.getGene(geneIndex) == 1) {
                            newGene = 0;
                        }
                        // Mutate gene
                        person.setGene(geneIndex, newGene);
                    }
                }
            }
            // Add individual to population
            newPopulation.setPerson(populationIndex, person);
        }
        // Return mutated population
        return newPopulation;
    }
}
