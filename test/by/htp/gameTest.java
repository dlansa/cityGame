package by.htp;

import by.htp.entity.City;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class gameTest {

    private static Game game;
    private static Map<City, Integer> cities;
    private String cityOne;
    private String cityTwo;

    @BeforeClass
    public static void init(){
        cities = new HashMap<City, Integer>();
        game = new Game(cities);
    }

    @Test
    public void getLastCharTest(){
        cityOne = "Афины";
        cityTwo = "Анапа";
        char expectedOne = 'н';
        char expectedTwo = 'а';

        assertEquals(expectedOne, game.getLastChar(cityOne));
        assertEquals(expectedTwo, game.getLastChar(cityTwo));
    }


}