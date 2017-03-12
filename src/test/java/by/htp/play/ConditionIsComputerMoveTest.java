package by.htp.play;

import org.junit.After;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class ConditionIsComputerMoveTest {

    private Condition condition;
    private Map<City, Integer> cities;
    private char lastChar;

    @org.junit.Before
    public void setUp() {
        condition = new Condition();
        cities = new HashMap<City, Integer>();
        cities.put(new City("Анапа"), 1);
        cities.put(new City("Аризона"), 0);
        cities.put(new City("Панама"), 0);
        condition.setCities(cities);
        lastChar = 'а';
        condition.setLastChar(lastChar);
    }

    @org.junit.Test
    public void compMoveTest() {

        assertTrue(condition.computerMove("Пиза").toLowerCase().charAt(0) == lastChar);
        assertTrue(condition.computerMove("Берлин").equals(GameMessage.USER_WIN));
    }

    @After
    public void tearDown(){
        condition = null;
        cities = null;
        lastChar = 0;
    }

}