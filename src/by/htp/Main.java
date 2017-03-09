package by.htp;

import by.htp.entity.City;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class Main {

    private static final String URL = "jdbc:mysql://localhost:3306/worldcities?useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "01236589aA!";

    private static final String SELECT_ALL_CITIES = "select city from city_list";



    public static void main(String[] args) {

        Map<City, Integer> cities = createDBcity();
        Game game = new Game(cities);
        game.run();

    }


    private static Map<City, Integer> createDBcity(){

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Map<City, Integer> cities = new HashMap<City, Integer>();

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.prepareStatement(SELECT_ALL_CITIES);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                City city = new City();
                city.setName(resultSet.getString("city"));
                cities.put(city, 0);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return cities;
    }



}
