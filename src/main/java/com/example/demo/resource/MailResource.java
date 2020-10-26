package com.example.demo.resource;

import com.alibaba.fastjson.JSON;
import com.example.demo.constant.ResultEnum;
import com.example.demo.service.MailService;
import com.example.demo.vo.MailVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author dustforest
 */
@RestController
@RequestMapping("/mail")
public class MailResource {
    private static final Logger logger = LoggerFactory.getLogger(MailResource.class);

    @Autowired
    MailService mailService;

    @Value("${spring.mail.from}")
    private String from;

    @PostMapping("/test")
    public ResultEnum test(String mailVo, MultipartFile[] files) {
        logger.info(mailVo);
        MailVo mailVoReal = JSON.parseObject(mailVo, MailVo.class);
        mailVoReal.setFrom(from);
        mailVoReal.setFiles(files);
        logger.info(mailVoReal.toString());
        return mailService.test(mailVoReal);
    }
}
