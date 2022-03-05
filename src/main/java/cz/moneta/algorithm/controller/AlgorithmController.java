package cz.moneta.algorithm.controller;

import cz.moneta.algorithm.service.AlgorithmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class AlgorithmController {

    @Autowired
    private AlgorithmService algorithmService;

    @GetMapping("/getNumber/{number}")
    public String index(@PathVariable("number") String number) {
        Integer.parseInt(number);
        return algorithmService.getNumber(number);
    }

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler({NumberFormatException.class})
    public void handleMyRunTimeException(Exception e) {
        System.out.println(e);
    }
}
