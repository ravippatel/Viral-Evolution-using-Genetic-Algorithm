package POJO;

import java.util.Random;

public class Person {
    private final int infected_duration = 140;
    private final Random random = new Random();
    public int x;
    public int y;
    public boolean immune = false;
    public boolean died = false;
    public boolean can_move = true;
    public int infected = 0;
    public int no_of_person_infected = 0;
    private int vel_x = 0;
    private int vel_y = 0;
    private int height = 600;
    private int width = 800;

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
            if (x > width || x < 0) {
                vel_x = -vel_x;
            }
            if (y > height || y < 0) {
                vel_y = -vel_y;
            }
        }
    }
}
