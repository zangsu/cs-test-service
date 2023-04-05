package GDHS.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@Controller
public class MainController {

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/")
    public void welcomePage(){
        log.info("welcome");
    }
}
