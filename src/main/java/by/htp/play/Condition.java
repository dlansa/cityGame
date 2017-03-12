package by.htp.play;

import by.htp.play.City;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Condition {

    private static final String URL = "jdbc:mysql://localhost:3306/worldcities?useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "01236589aA!";
    private static final String SELECT_ALL_CITIES = "select city from city_list";

    private Map<City, Integer> cities;
    private char lastChar;
    private boolean isFirstMove = true;
    private String usersInput;

    public Condition() {
    }

    public void setCities(Map<City, Integer> cities) {
        this.cities = cities;
    }

    public char getLastChar() {
        return lastChar;
    }

    public void setLastChar(char lastChar) {
        this.lastChar = lastChar;
    }

    public String userMove(String compCity) {
        String message;
        if (isFirstMove) {
            isFirstMove = false;
            lastChar = getLastChar(usersInput);
            markAsUsed(usersInput);
            return usersInput;
        }
        else if (usersInput.isEmpty())
            message = GameMessage.COMP_WIN;
        else
            message = checkPlayerCity(usersInput);
        return message;
    }

    public String computerMove(String userCity) {
        for (Map.Entry<City, Integer> key : cities.entrySet()) {
            String city = key.getKey().toString();
            if (city.toLowerCase().charAt(0) == lastChar && key.getValue() == 0) {
                key.setValue(1);
                lastChar = getLastChar(city);
                return city;
            }
        }
        return GameMessage.USER_WIN;
    }

    public boolean isValidate(String move) {
        if (move.equals(GameMessage.DONT_EXIST) || move.equals(GameMessage.REPEATED) || move.equals(GameMessage.WRONG_FIRST_LETTER)
                || (move.equals(GameMessage.COMP_WIN)))
            return false;
        else
            return true;
    }

    private char getLastChar(String city) {
        char c = city.toLowerCase().charAt(city.length() - 1);
        if (c != 'ь' && c != 'ы' && c != 'ъ')
            return c;
        else
            return city.charAt(city.length() - 2);
    }

    public void initCities() {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Map<City, Integer> cities = new HashMap<City, Integer>();

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.prepareStatement(SELECT_ALL_CITIES);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                City city = new City();
                city.setName(resultSet.getString("city"));
                cities.put(city, 0);
            }
            this.cities = cities;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private String checkPlayerCity(String city) {
        String message = null;
        if (lastChar != city.charAt(0))
            message = GameMessage.WRONG_FIRST_LETTER;
        else
            for (Map.Entry<City, Integer> key : cities.entrySet()) {
                if (key.getKey().toString().equalsIgnoreCase(city)) {
                    if (key.getValue() == 1) {
                        message = GameMessage.REPEATED;
                        break;
                    } else {
                        message = key.getKey().toString();
                        lastChar = getLastChar(message);
                        key.setValue(1);
                        break;
                    }
                } else
                    message = GameMessage.DONT_EXIST;
            }
        return message;
    }

    public void printIntro() {
        System.out.println("--- I hope you know the rules ---\n--- Here we go! You go first. ---");
    }

    private void markAsUsed(String city) {
        for (Map.Entry<City, Integer> key : cities.entrySet()) {
            if (city.equalsIgnoreCase(key.getKey().toString())) {
                key.setValue(1);
                break;
            }
        }
    }

    public void readUserInput(){
        Scanner scanner = new Scanner(System.in);
        this.usersInput = scanner.nextLine();
    }

    public String getUsersInput() {
        return usersInput;
    }

    public void setUsersInput(String usersInput) {
        this.usersInput = usersInput;
    }

    public void isFirstMove(boolean firstMove) {
        isFirstMove = firstMove;
    }
}