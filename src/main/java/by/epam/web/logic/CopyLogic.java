package by.epam.web.logic;

import by.epam.web.entity.CalculableParameters;
import by.epam.web.exeptions.IllegalArgumentsException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.util.*;
import java.util.stream.Stream;

@Service
public class CopyLogic {
    private static final Logger logger = LogManager.getLogger(CopyLogic.class);

    @Autowired
    private CopyHash hashMap = new CopyHash();

    public Map<String, Object> calculateResult(CalculableParameters requestParameters) throws IllegalArgumentsException {
        Map<String, Object> result_map = new HashMap<>();

        if (hashMap.isContain(requestParameters)) {
            result_map = hashMap.getParameters(requestParameters);
            logger.info("get hashMap");
        } else {
            switch (requestParameters.getAction()) {
                case "copy1_2":
                    requestParameters.setNumber2(requestParameters.getNumber1());
                    break;
                case "copy2_3":
                    requestParameters.setNumber3(requestParameters.getNumber2());
                    break;
                case "empty":
                    requestParameters.setNumber1(0);
                    break;
                default:
                    logger.error("400 Error!");
                    throw new IllegalArgumentsException();
            }
            result_map.put("number1", requestParameters.getNumber1());
            result_map.put("number2", requestParameters.getNumber2());
            result_map.put("number3", requestParameters.getNumber3());
            result_map.put("action", requestParameters.getAction());
            hashMap.addToMap(requestParameters, result_map);
            logger.info("add hashMap");
        }
        return result_map;
    }

    public double getAverageNumber(List<Integer> resultList) {
        double average = 0;
        if (!resultList.isEmpty()) {
            average = resultList.stream().mapToInt(Integer::intValue).average().getAsDouble();
        }
        return average;
    }

    public int getMaximumNumber(List<Integer> resultList) {
        int max = 0;
        if (!resultList.isEmpty()) {
            max = resultList.stream().mapToInt(Integer::intValue).max().getAsInt();
        }
        return max;
    }

    public int getMinimumNumber(List<Integer> resultList) {
        int min = 0;
        if (!resultList.isEmpty()) {
            min = resultList.stream().mapToInt(Integer::intValue).min().getAsInt();
        }
        return min;
    }
}