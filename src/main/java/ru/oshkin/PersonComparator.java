package ru.oshkin;

import java.util.Comparator;

public class PersonComparator implements Comparator<Person> {
    @Override
    public int compare(Person person1, Person person2) {
        if (person1.getFitnessFunction() > person2.getFitnessFunction()) {
            return -1;
        } else if (person1.getFitnessFunction() < person2.getFitnessFunction()) {
            return 1;
        }
        return 0;
    }
}