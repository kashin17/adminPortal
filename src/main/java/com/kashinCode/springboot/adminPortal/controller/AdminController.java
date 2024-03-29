package com.kashinCode.springboot.adminPortal.controller;


import com.kashinCode.springboot.adminPortal.dao.keyValuePairRepository;
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
//    private keyValuePairRepository kVP;


    public AdminController(AdminService theService){
        this.theService = theService;
    }
    @GetMapping("/list")
    public String Listkvp(Model theModel){

        List<KeyValuePair> theList = theService.getAll();

        theModel.addAttribute("kvps",theList);

        return "admin-list";
    }
    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel){

        KeyValuePair theKVP = new KeyValuePair();

        theModel.addAttribute("kvp",theKVP);

        return "admin-form";
    }
    @PostMapping("/save")
    public String saveKVP(@Valid @ModelAttribute("kvp") KeyValuePair theKVP, BindingResult theBindingResult){

        if(theBindingResult.hasErrors()){
            return "admin-form"; // Show form again with validation errors
        }

        if (theService.existByKeyName(theKVP.getKeyName())) {
            theBindingResult.rejectValue("keyName", "error.key", "Key already exists");
            return "admin-form"; // Show form again with validation error for duplicate key
        }
            // save the
            theService.save(theKVP);

            //use a redirect to prevent duplicate submissions
            return "redirect:/adminPortal/list";

    }


}
