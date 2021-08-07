package model;

import java.util.Random;

public class Person {
    public PersonStatus status=PersonStatus.NAIVE;
    private final Random random = new Random();
    public int x;
    public int y;
    public boolean can_move = true;
    private int vel_x = 0;
    private int vel_y = 0;
    public int fitness = 0;
    public String gene = "";
    public boolean vaccinated = false;
    public boolean recovered = false;
    public boolean infected = false;

    public Person(int x, int y) {
        this.x = x;
        this.y = y;
        vel_x = random.nextInt(10) - 5;
        vel_y = random.nextInt(10) - 5;
    }

    public void move() {
        if (can_move) {
            x += vel_x;
            y += vel_y;
            int width = 2000;
            if (x > width || x < 0) {
                vel_x = -vel_x;
            }
            int height = 1800;
            if (y > height || y < 0) {
                vel_y = -vel_y;
            }
        }
    }
}
