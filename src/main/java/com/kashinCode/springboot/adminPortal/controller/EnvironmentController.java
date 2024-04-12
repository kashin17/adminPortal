package com.kashinCode.springboot.adminPortal.controller;


import com.kashinCode.springboot.adminPortal.dao.environmentRepository;
import com.kashinCode.springboot.adminPortal.entity.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class EnvironmentController {

    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @Autowired
    private environmentRepository theService;

    @GetMapping("/list")
    public String envList(Model theModel){

        List<Environment> environments= theService.findAll();
        theModel.addAttribute("envs", environments);

        return "env-list";
    }

    @PostMapping("/createEnv")
    public String createEnv(@RequestParam("name")String name){
        Environment newEnvironment = new Environment();
        newEnvironment.setName(name);
        theService.save(newEnvironment);

        long EnvId = newEnvironment.getId();

        return "redirect:/list";

        //return "'redirect:/adminPortal/list/'+'EnvId'";
    }
}
