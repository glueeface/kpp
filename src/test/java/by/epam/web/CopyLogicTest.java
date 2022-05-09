package by.epam.web;

import by.epam.web.entity.CalculableParameters;
import by.epam.web.exeptions.IllegalArgumentsException;
import by.epam.web.logic.CopyLogic;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class CopyLogicTest {

    private final CopyLogic copyLogic = new CopyLogic();

    @Test
    void testCalculateResultCopy1_2() throws IllegalArgumentsException {
        CalculableParameters requestParameters = new CalculableParameters();
        requestParameters.setNumber1(1);
        requestParameters.setAction("copy1_2");

        int expected = 1;
        assertEquals(expected, copyLogic.calculateResult(requestParameters).get("number2"));
    }

    @Test
    void testCalculateResultCopy2_3() throws IllegalArgumentsException {
        CalculableParameters requestParameters = new CalculableParameters();
        requestParameters.setNumber2(2);
        requestParameters.setAction("copy2_3");

        int expected = 2;
        assertEquals(expected, copyLogic.calculateResult(requestParameters).get("number3"));
    }

    @Test
    void testCalculateResultEmpty() throws IllegalArgumentsException {
        CalculableParameters requestParameters = new CalculableParameters();

        int expected = 0;
        assertEquals(expected, copyLogic.calculateResult(requestParameters).get("number1"));
    }

    @Test
    void testCalculateResultError() {
        boolean resultException = false;
        try {
            CalculableParameters requestParameters = new CalculableParameters();
            requestParameters.setAction("qwerty");

            copyLogic.calculateResult(requestParameters);
        } catch (IllegalArgumentsException e) {
            resultException = true;
        }
        assertTrue(resultException);
    }
}