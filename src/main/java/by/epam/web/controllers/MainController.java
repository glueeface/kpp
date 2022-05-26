package by.epam.web.controllers;

import by.epam.web.DTO.Data;
import by.epam.web.counter.RequestCounter;
import by.epam.web.counter.Synchronization;
import by.epam.web.entity.CalculableParameters;
import by.epam.web.exeptions.IllegalArgumentsException;
import by.epam.web.logic.CopyLogic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Console;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Controller
@ControllerAdvice
public class MainController{
    private static final Logger logger = LogManager.getLogger(MainController.class);
    @Autowired
    private CopyLogic copyLogic;

    @GetMapping("/copy")
    public String calculateParams(Model model,
                                  @RequestParam(name = "first_number", required = false, defaultValue = "0") Integer number1,
                                  @RequestParam(name = "second_number", required = false, defaultValue = "0") Integer number2,
                                  @RequestParam(name = "third_number", required = false, defaultValue = "0") Integer number3,
                                  @RequestParam(name = "action", required = false, defaultValue = "empty") String action)
            throws IllegalArgumentsException {
        CalculableParameters requestParameters = new CalculableParameters();
        RequestCounter.increment();
        requestParameters.setAction(action);
        requestParameters.setNumber1(number1);
        requestParameters.setNumber2(number2);
        requestParameters.setNumber3(number3);
        copyLogic.calculateResult(requestParameters);

        Synchronization.semaphore.release();

        model.addAttribute("first_number", requestParameters.getNumber1());
        model.addAttribute("second_number", requestParameters.getNumber2());
        model.addAttribute("third_number", requestParameters.getNumber3());

        logger.info("Successfully getMapping");
        return "home";
    }

    @PostMapping("/copy")
    public ResponseEntity<?> calculateBulkParams(@Valid @RequestBody Data data) {
        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> bodyList = new LinkedList<>();
        CalculableParameters currentElement = new CalculableParameters(data.number1, data.number2, data.number3, data.action);
        try {
            bodyList.add(copyLogic.calculateResult(currentElement));
            logger.info("Successfully postMapping");
        } catch (IllegalArgumentsException e) {
            logger.error("Error in postMapping");
        }

        List<Integer> resultList = new ArrayList<>();
        resultList.add((int) bodyList.get(0).get("number1"));
        resultList.add((int) bodyList.get(0).get("number2"));
        resultList.add((int) bodyList.get(0).get("number3"));

        result.put("Data", bodyList);
        result.put("Average", copyLogic.getAverageNumber(resultList));
        result.put("MaxNumber", copyLogic.getMaximumNumber(resultList));
        result.put("MinNumber", copyLogic.getMinimumNumber(resultList));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public String handlerException() {
        logger.info("handlerException");
        return ("/error/400.html");
    }
}