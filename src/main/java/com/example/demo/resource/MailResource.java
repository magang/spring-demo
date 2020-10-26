package com.example.demo.resource;

import com.example.demo.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dustforest
 */
@RestController
@RequestMapping("/mail")
public class MailResource {
    @Autowired
    MailService mailService;

    @GetMapping("/test/simple/mail")
    public void testSimpleMail() {
        String to = "magang@zybank.com.cn";
        String subject = "test";
        String content = "test";
        mailService.sendSimpleMail(to, subject, content);
    }
}
