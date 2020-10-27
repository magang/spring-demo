package com.example.demo.resource;

import com.alibaba.fastjson.JSON;
import com.example.demo.constant.ResultEnum;
import com.example.demo.constant.TestDataConstants;
import com.example.demo.service.MailService;
import com.example.demo.vo.MailVo;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dustforest
 */
@RestController
@RequestMapping("/mail")
public class MailResource {
    private static final Logger logger = LoggerFactory.getLogger(MailResource.class);

    @Autowired
    MailService mailService;

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @PostMapping("/attachment")
    public ResultEnum sendAttachmentMail(String mailVo, MultipartFile[] files) {
        logger.info(mailVo);
        MailVo mailVoReal = JSON.parseObject(mailVo, MailVo.class);
        mailVoReal.setFiles(files);
        logger.info(mailVoReal.toString());
        return mailService.send(mailVoReal);
    }

    @PostMapping("/simple")
    public ResultEnum sendSimpleMail(String mailVo) {
        logger.info(mailVo);
        MailVo mailVoReal = JSON.parseObject(mailVo, MailVo.class);
        logger.info(mailVoReal.toString());
        return mailService.send(mailVoReal);
    }

    @PostMapping("/template")
    public ResultEnum sendTemplateMail(String mailVo) throws Exception {
        logger.info(mailVo);
        MailVo mailVoReal = JSON.parseObject(mailVo, MailVo.class);

        Template template = freeMarkerConfigurer.getConfiguration().getTemplate(TestDataConstants.MAIL_TEMPLATE);
        Map<String, Object> model = new HashMap<>();
        model.put("key", "value");
        String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        mailVoReal.setText(text);
        
        logger.info(mailVoReal.toString());
        return mailService.send(mailVoReal);
    }
}
