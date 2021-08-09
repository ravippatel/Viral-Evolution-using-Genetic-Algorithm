package model;

public enum PersonStatus {
    NAIVE(0),
    INFECTED(1),
    VACCINATED(2),
    INFECTED_VACCINATED(3);

    private final int value;

    PersonStatus(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }
}
