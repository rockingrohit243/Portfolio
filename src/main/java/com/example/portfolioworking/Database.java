package com.example.portfolioworking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Database {
    @Autowired
    private PortRepo portRepo;
    @GetMapping("/getAllUsers")
    private List<Model> getAllUsers()
    {
return  portRepo.findAll();
    }
    @DeleteMapping("/deleteById/{id}")
    private String  deleteUserById(@PathVariable("id") int id)
    { portRepo.deleteById(id);
        return  "deleted sucessfully";
    }
    @DeleteMapping("/deleteAllUsers")
    private String deleteAllUsers(){
        portRepo.deleteAll();
        return "All data deleted successfully";
    }
    @PutMapping("/updateUser")
    private String updateUser(@RequestBody Model model)
    {
        Model model1=portRepo.findById(model.getId()).orElseThrow(()-> new RuntimeException("employee doesnot exist"+model.getId()));
        model1.setId(model.getId());
        model1.setName(model.getName());
        model1.setMessage(model.getMessage());
        model1.setEmail(model.getEmail());
        portRepo.save(model1);
        return "Updated successfully";
}}
