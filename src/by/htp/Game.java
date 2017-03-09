package by.htp;

import by.htp.entity.City;

import java.util.Map;
import java.util.Scanner;

public class Game {

    private static final int REPEATED = 1;
    private static final int WRONG_FIRST_LETTER = 2;
    private static final int DONT_EXIST = 3;
    private static final int NEXT_STEP = 4;

    private Map<City, Integer> cities;
    private char lastChar;

    public Game(Map<City, Integer> cities){
        this.cities = cities;
    }

    public void run() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("--- I hope you know the rules ---\n--- Here we go! You go first. ---");
        String firstCity = scanner.nextLine();
        markAsUsed(firstCity);
        String firstCompCity = getCity(getLastChar(firstCity));
        System.out.println(firstCompCity);
        lastChar = getLastChar(firstCompCity);
        boolean start = true;
        while (start) {

            String city = scanner.nextLine();
            if (city.isEmpty()) {
                System.out.println("Well, of course I win! Try better next time.");
                break;
            }
            int message = checkPlayerCity(city);
            switch (message) {
                case WRONG_FIRST_LETTER:
                    System.out.println("Bad try, do it again.");
                    break;
                case DONT_EXIST:
                    System.out.println("I don't know such city.");
                    break;
                case REPEATED:
                    System.out.println("It was.");
                    break;
                case NEXT_STEP:
                    String computerCity = getCity(getLastChar(city));
                    if (computerCity != null) {
                        System.out.println(computerCity);
                        lastChar = getLastChar(computerCity);
                    } else {
                        System.out.println("Well, I'm done. You win!");
                        start = false;
                        break;
                    }
                    break;
            }
        }
    }

    public char getLastChar(String city){
        char c = city.toLowerCase().charAt(city.length()-1);
        if (c != 'ь' && c != 'ы' && c != 'ъ')
            return c;
        else
            return city.charAt(city.length()-2);
    }

    private String getCity(char firstChar){
        for (Map.Entry<City, Integer> key: cities.entrySet()){
            String city = key.getKey().toString();
            if (city.toLowerCase().charAt(0)==firstChar && key.getValue() == 0){
                key.setValue(1);
                return city;
            }
        }
        return null;
    }

    private int checkPlayerCity(String city){
        int message = 0;
        if (lastChar != city.charAt(0))
            message = WRONG_FIRST_LETTER;
        else
            for (Map.Entry<City, Integer> key : cities.entrySet()) {
                if (key.getKey().toString().equalsIgnoreCase(city)) {
                    if (key.getValue() == 1) {
                        message = REPEATED;
                        break;
                    } else {
                        key.setValue(1);
                        message = NEXT_STEP;
                        break;
                    }
                } else
                    message = DONT_EXIST;
            }
        return message;
    }

    private void markAsUsed(String city){
        for (Map.Entry<City, Integer> key: cities.entrySet()){
            if (city.equalsIgnoreCase(key.getKey().toString())) {
                key.setValue(1);
                break;
            }
        }
    }

}
