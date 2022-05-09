package by.epam.web;

import by.epam.web.entity.CalculableParameters;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CalculableParametersTest {
    @Test
    void testCalculableParametersEquals() {
        CalculableParameters requestParameters1 = new CalculableParameters();
        CalculableParameters requestParameters2 = new CalculableParameters();

        boolean result = requestParameters1.equals(requestParameters2);
        assertTrue(result);
    }

    @Test
    void estCalculableParametersToString() {
        CalculableParameters requestParameters = new CalculableParameters();
        requestParameters.setNumber1(2);
        requestParameters.setAction("copy1_2");

        String result = "\n" + requestParameters.getClass().getSimpleName() + "{" +
                "first_number=" + 2 + "second_number=" + 0 + "third_number=" + 0 +
                ", action='" + "copy1_2" + "}";

        assertEquals(requestParameters.toString(), result);

    }
}