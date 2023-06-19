package apap.tugaskelompok.rumahsehat.restcontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldRestController {

    @RequestMapping("/api/v1/hello")
    public String helloWorld() {
        return "Hello World";
    }
}
