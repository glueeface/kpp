package by.epam.web;

import by.epam.web.entity.CalculableParameters;
import by.epam.web.exeptions.IllegalArgumentsException;
import by.epam.web.logic.CopyHash;
import org.junit.jupiter.api.Test;
import by.epam.web.logic.CopyLogic;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CopyHashTest {
    CopyHash hashMap = new CopyHash();
    private final CopyLogic copyLogic = new CopyLogic();

    @Test
    void testCalculateHashIsContainFalse() {
        CalculableParameters requestParameters = new CalculableParameters();
        requestParameters.setNumber1(2);
        requestParameters.setAction("copy1_2");

        boolean result = hashMap.isContain(requestParameters);
        assertFalse(result);
    }

    @Test
    void testCalculateHashIsContainTrue() {
        CalculableParameters requestParameters = new CalculableParameters();
        Map<String, Object> result_map = new HashMap<>();

        requestParameters.setNumber1(2);
        requestParameters.setAction("copy1_2");

        result_map.put("number1", requestParameters.getNumber1());
        result_map.put("number2", requestParameters.getNumber2());
        result_map.put("number3", requestParameters.getNumber3());
        result_map.put("action", requestParameters.getAction());
        hashMap.addToMap(requestParameters, result_map);

        assertEquals(result_map, hashMap.getParameters(requestParameters));
    }

    @Test
    void testCalculateHash() throws IllegalArgumentsException {
        CalculableParameters requestParameters = new CalculableParameters();
        requestParameters.setNumber1(2);
        requestParameters.setAction("copy1_2");

        int result = (int) copyLogic.calculateResult(requestParameters).get("number2");
        int expected = requestParameters.getNumber2();
        assertEquals(expected, result);
    }
}
