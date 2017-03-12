package by.htp.play;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class TestCondition {

    private Condition condition;
    private Map<City, Integer> cities;

    @Before
    public void setUp() {
        condition = new Condition();
        cities = new HashMap<City, Integer>();
        cities.put(new City("Анапа"), 1);
        cities.put(new City("Аризона"), 0);
        condition.setCities(cities);
        condition.setLastChar('а');
    }

    @Test
    public void compMoveTest() {

        assertEquals("Аризона", condition.computerMove("Пиза"));
        assertTrue(condition.computerMove("Берлин").equals(GameMessage.USER_WIN));
    }

    @After
    public void tearDown(){
        condition = null;
        cities = null;
    }

}