package com.example.demo.resource;

import com.example.demo.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(MailResource.class);

    @Autowired
    MailService mailService;

    @GetMapping("/test/simple")
    public String testSimpleMail() {
        String to = "magang@zybank.com.cn";
        String subject = "test";
        String content = "test";
        return mailService.sendSimpleMail(to, subject, content);
    }
}
