package org.haockerandpainter.springbootjibdocker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author Gao Hang Hang
 * @Date 2019-07-15 22:45
 **/
@RestController
public class HelloController {

    @GetMapping("/")
    public String hello() {
        return "hello";
    }
}
