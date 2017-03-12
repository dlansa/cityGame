package by.htp.play;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.*;

public class ConditionUserMoveTest {

    private Condition condition;
    private Map<City, Integer> cities;

    @BeforeMethod
    public void setUp() {

        condition = new Condition();
        cities = new HashMap<City, Integer>();
        cities.put(new City("Анапа"), 0);
        condition.setCities(cities);
        condition.isFirstMove(false);
    }

    @Test
    public void testUserMove() {

        String compMoveTest = "Мадагаскар";
        condition.setUsersInput("");
        assertEquals(GameMessage.COMP_WIN, condition.userMove(compMoveTest));

        condition.setUsersInput("керапр");
        assertEquals(GameMessage.WRONG_FIRST_LETTER, condition.userMove(compMoveTest));

    }

    @AfterMethod
    public void tearDown() {
        condition = null;
        cities = null;
    }
}