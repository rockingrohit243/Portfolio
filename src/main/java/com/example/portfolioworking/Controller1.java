package com.example.portfolioworking;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@Controller
public class Controller1 {
    @Autowired
    private PortRepo repository;



    @Autowired
    private JavaMailSender emailSender;
    @RequestMapping(value="/home",method = RequestMethod.GET)
    public String home()
    {
        return "index";
    }

    @RequestMapping(value="/submit-form",method = RequestMethod.GET)
    public String submitForm(@RequestParam("name") String name,@RequestParam("email") String email,@RequestParam String message) throws MessagingException {
        MimeMessage message11=emailSender.createMimeMessage();
        MimeMessageHelper helper;
        helper=new MimeMessageHelper(message11,true);
        helper.setTo(email);
        helper.setFrom("rohitsen.portfolio@gmail.com");
        helper.setSubject("Thank You for Visiting My Portfolio Website");
        helper.setText(" Dear "+ name+",\n I hope this message finds you well. I wanted to take a moment to express my sincere gratitude for taking the time to visit my portfolio website. Your interest in my work means a lot to me, and I truly appreciate your support and attention. Creating and maintaining a portfolio is a labor of love, and it's incredibly rewarding to see that it has caught your attention. Knowing that you've taken the time to explore my projects, read my thoughts, and delve into my creative journey is truly inspiring. Your visit signifies a significant validation of my efforts, and it motivates me to continue pushing the boundaries of my craft.\n  I hope that my portfolio website was able to provide you with a comprehensive glimpse into my skills, experience, and passion. It serves as a representation of who I am as a professional and an artist. If there are any specific projects or areas of my portfolio that you found particularly intriguing or if you have any feedback to share, I would be thrilled to hear your thoughts. Your insights can help me grow and refine my work further.\n\n Once again, thank you for visiting my portfolio website and for your interest in my work. Your support means the world to me, and I am grateful for the opportunity to share my creative endeavors with you. If you have any further questions or if there's anything else I can assist you with, please don't hesitate to reach out. \n\nWarmest regards,\n Rohit Sen");
        emailSender.send(message11);
        System.out.println("Email has been sent.");
        Model model=new Model();
        model.setName(name);
        model.setEmail(email);
        model.setMessage(message);
        repository.save(model);
        System.out.println("saved successfully");
        return "success";
    }




}
