package game.characters;

import engine.Action;
import engine.Character;
import engine.Location;

import java.util.List;
import java.util.Scanner;

public class Player extends Character {
    private Scanner scanner;
    public Player(String name, Location startLocation) {
        super(name, startLocation);
        scanner = new Scanner(System.in);
    }

    @Override
    public Action selectAction(List<Action> actions) {
        for (int i = 0; i < actions.size(); i++) {
            System.out.println(i + ". " + actions.get(i).description());
        }
        return actions.get( getChoice(actions.size()-1) );
    }
    private int getChoice(int max) {
        int choice;
        do {
            choice = scanner.nextInt(); //fails with anything but ints.
        } while (choice < 0 || choice > max);
        return choice;
    }
}
