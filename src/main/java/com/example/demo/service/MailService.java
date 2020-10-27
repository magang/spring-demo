package com.example.demo.service;

import com.example.demo.constant.ResultEnum;
import com.example.demo.util.MailUtils;
import com.example.demo.vo.MailVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

/**
 * @author dustforest
 */
@Service
public class MailService {
    private static final Logger logger = LoggerFactory.getLogger(MailService.class);

    @Autowired
    private MailUtils mailUtils;

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @Value("${spring.mail.from}")
    private String from;

    public ResultEnum send(MailVo mailVo) {
        mailVo.setFrom(from);
        mailUtils.sendMail(mailVo);
        return ResultEnum.ok;
    }
}

