package com.studyplatzi.springboot.fundaments.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    //@RequestMapping: Sirve para aceptar todas las solicitudes en el metodo a nivel de HTTP
    //@ResponseBody: responde un cuerpo a nivel de servicio
    @RequestMapping
    @ResponseBody
    public ResponseEntity<String> function(){
        return new ResponseEntity<>("Hello from controller a change new new", HttpStatus.OK);
    }
}
