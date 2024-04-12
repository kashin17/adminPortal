package com.kashinCode.springboot.adminPortal.controller;


import com.kashinCode.springboot.adminPortal.dao.environmentRepository;
import com.kashinCode.springboot.adminPortal.dao.keyValuePairRepository;
import com.kashinCode.springboot.adminPortal.entity.Environment;
import com.kashinCode.springboot.adminPortal.entity.KeyValuePair;
import com.kashinCode.springboot.adminPortal.service.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/adminPortal")
public class AdminController {


    //removing whitespaces
    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }
    private AdminService theService;

//    @Autowired
//    private keyValuePairRepository theService;

    @Autowired
    private environmentRepository envService;

    public AdminController(AdminService theService){
        this.theService = theService;
    }
//    @GetMapping("/list")
//    public String Listkvp(/*@RequestParam("id") String id,*/Model theModel){
//
//        List<KeyValuePair> theList = theService.getAll();
//
//        theModel.addAttribute("kvps",theList);
//
//        return "admin-list";
//    }
    @GetMapping("/list")
    public String Listkvp(@RequestParam("EnvId") long EnvId, Model theModel){


        List<KeyValuePair> theList = theService.findByEnvironmentId(EnvId);
       // long EnvId = id;

        Optional<Environment> Env = envService.findById(EnvId);

        String ename  = Env.get().getName();
        //System.out.println(Env);

        theModel.addAttribute("kvps",theList);
        theModel.addAttribute("EnvId",EnvId);
        theModel.addAttribute("ename",ename);
        return "admin-list";

    }




    @GetMapping("/showFormForAdd")
    public String showFormForAdd(@RequestParam("EnvId") long EnvId,Model theModel){

        KeyValuePair theKVP = new KeyValuePair();
        Environment env = new Environment();
        env.setId(EnvId);
        theKVP.setEnvironment(env);

        theModel.addAttribute("kvp",theKVP);
        theModel.addAttribute("EnvId",EnvId);

        return "admin-form";
        //return "redirect:/adminPortal/showFormForAdd(EnvId=${EnvId})";
    }
    @PostMapping("/save")
    public String saveKVP(@Valid @ModelAttribute("kvp") KeyValuePair theKVP, BindingResult theBindingResult, Model theModel){

        long Envid = theKVP.getEnvId(theKVP.getEnvironment());
        theModel.addAttribute("EnvId",Envid);


        if(theBindingResult.hasErrors()){
            //theModel.addAttribute("EnvId",EnvId);
           // return "redirect:/adminPortal/showFormForAdd(EnvId=${EnvId})";


            return "admin-form"; // Show form again with validation errors
        }
//        Environment env = theKVP.getEnvironment();
//        long envId = env.getId();



       // theService.existByKeyName(theKVP.getKeyName()) && theService.existsByEnvironmentId(theKVP.getEnvId(theKVP.getEnvironment()))
        if (theService.existsByEnvironmentIdAndKeyName(theKVP.getEnvId(theKVP.getEnvironment()),theKVP.getKeyName() )) {
            theBindingResult.rejectValue("keyName", "error.key", "Key already exists");
            return "admin-form"; // Show form again with validation error for duplicate key
        }
            // save the
            theService.save(theKVP);

//            Environment Env = theKVP.getEnvironment();
//            long EnvID = Env.getId();

            //use a redirect to prevent duplicate submissions
        //"redirect:/adminPortal/list?EnvId=${Envid}";
            return "redirect:/adminPortal/list?EnvId="+Envid;
            //return "redirect:/list";

    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("kvpId") long theId, Model theModel){


        //get the employee from the service
        KeyValuePair theKVP = theService.getById(theId);

        long Envid = theKVP.getEnvId(theKVP.getEnvironment());
        theModel.addAttribute("EnvId",Envid);

        //set employee in the model to prepopulate the form
        theModel.addAttribute("kvp",theKVP);

        //send over our form
        return "admin-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("kvpId") int theId){

        KeyValuePair theKVP = theService.getById(theId);
        long Envid = theKVP.getEnvId(theKVP.getEnvironment());
//        theModel.addAttribute("EnvId",Envid);
        //delete the employee
        theService.deleteById(theId);

        //redirect to the /employee/list
//        return "redirect:/employees/list";
        return "redirect:/adminPortal/list?EnvId="+Envid;
    }


}
