package com.solution.lab1.controllers;
import com.solution.lab1.entity.DatabaseEntity;
import com.solution.lab1.exceptions.IntervalsExceptions;
import com.solution.lab1.ResponseHandler;
import com.solution.lab1.Services.SolutionService;
import com.solution.lab1.Cache.SolutionHash;
import com.solution.lab1.entity.SolutionParameters;
import com.solution.lab1.repository.DatabaseEntityRepository;
import counter.CounterThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedList;
import java.util.List;


@RestController("/")
public class SolutionControllerRest {
    private final SolutionService solutionService;
    private final SolutionHash cache;
    private static final Logger logger = LoggerFactory.getLogger(SolutionControllerRest.class);
    @Autowired
    public SolutionControllerRest(SolutionService solutionService, SolutionHash cache, DatabaseEntityRepository databaseEntityRepository) {
        this.solutionService = solutionService;
        this.cache = cache;
        this.databaseEntityRepository = databaseEntityRepository;
    }
    private final DatabaseEntityRepository databaseEntityRepository;
    @GetMapping("/sol")
    public ResponseEntity<Object> sol(@RequestParam("firstValue") int firstValue, @RequestParam("secondValue") int secondValue,
                                      @RequestParam("lowerInterval") int lowerInterval,
                                      @RequestParam("higherInterval") int higherInterval)  throws IntervalsExceptions,InterruptedException {
        SolutionParameters params = new SolutionParameters(firstValue, secondValue, lowerInterval, higherInterval);
        logger.info("Getting params...");
        int result;
        if (cache.isContain(params)) {
            result = cache.getParameters(params);
            logger.info("get hashMap");
        }else {
            result = solutionService.PerformCalculation(params);
            logger.info("adding to HashMap");
            cache.addToMap(params, result);
        }
        DatabaseEntity db = new DatabaseEntity(result);
        databaseEntityRepository.save(db);
        CounterThread counterThread = new CounterThread("Counter-Thread");
        counterThread.start();
        logger.info("Forming response in json format");
        return ResponseHandler.generateResponse(HttpStatus.OK, firstValue, secondValue, lowerInterval, higherInterval,result);
    }

    @PostMapping("/bulk")
    public ResponseEntity<?> calculateBulkParams(@Valid @RequestBody List<SolutionParameters> bodyList) {

        List<Integer> resultList = new LinkedList<>();
        bodyList.forEach((currentElement) -> {
            try {
                resultList.add(solutionService.PerformCalculation(currentElement));
            }
            catch(NullPointerException exception){
                logger.error("NULL parameters not allowed");
                throw new IntervalsExceptions("NULL parameters not allowed");
            }

        });

        logger.info("Successfully postMapping");
        int sumResult = solutionService.calculateSumOfResult(resultList);
        int maxResult = solutionService.findMaxOfResult(resultList);
        int minResult = solutionService.findMinOfResult(resultList);

        return new ResponseEntity<>(resultList + "\nSum result: " + sumResult + "\nMax result: " + maxResult + "\nMin result: " + minResult, HttpStatus.OK);
    }
}

