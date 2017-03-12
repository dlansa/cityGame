package by.htp.play;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.*;

public class ConditionUserMoveTest {

    // тут какой-то бред, видимо нельзя тестить методы со сканером :/

    private Condition condition;
    private Map<City, Integer> cities;
    private String firstInput;
    private String anotherInput;
    private String oneMore;
    private char lastChar;

    @BeforeMethod
    public void setUp() {

        condition = new Condition();
        cities = new HashMap<City, Integer>();
        cities.put(new City("Анапа"), 0);
        condition.setCities(cities);
        condition.setLastChar(lastChar='d');
        firstInput = condition.userMove("");            //вводим Анапа (будем проверять, будет ли город помечен как сказанный)
        anotherInput = condition.userMove("");          //будем проверять, выдаст ли ошибку первой буквы
        oneMore = condition.userMove("");               //будем провеярть на "существуемость" города
    }

    @AfterMethod
    public void tearDown() {
        condition = null;
        cities = null;
    }

    @Test(enabled = false)
    public void testUserMove() {
        assertTrue(cities.get("Анапа") == 1);
        assertFalse(anotherInput.toLowerCase().charAt(0) == lastChar);
        assertEquals(GameMessage.DONT_EXIST, oneMore);
    }

}