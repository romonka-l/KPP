package com.solution.lab1;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class SolutionControllerRest {
    @GetMapping("/sol")
    public ResponseEntity<Object> sol(@RequestParam("value1") int value1, @RequestParam("value2") int value2,
                                      @RequestParam("interval1") int interval1,
                                      @RequestParam("interval2") int interval2) {

        if (interval1 == interval2 || interval1 > interval2) {
            return new ResponseEntity<>("The beginning of the interval cannot be greater than the end",
                    HttpStatus.BAD_REQUEST);
        }

        int result=value2-value1;
        if(result <= interval2 && result>=interval1){
            return ResponseHandler.generateResponse(HttpStatus.OK,value1, value2,interval1,interval2,result);
        }
        else
            return new  ResponseEntity<>("The result is not included in the interval",HttpStatus.BAD_REQUEST);
    }
}

