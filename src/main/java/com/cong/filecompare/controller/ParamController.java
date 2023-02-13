package com.cong.filecompare.controller;

import org.springframework.web.bind.annotation.*;

/**
 * @author cong
 * @date 2023/2/13 14:34
 */
@RestController
@RequestMapping("param")
public class ParamController {

    @PostMapping("test")
    public String test(@RequestParam String name){
        return name;
    }
}
