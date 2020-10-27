package com.example.demo.service;

import com.example.demo.constant.ResultEnum;
import com.example.demo.constant.TestDataConstants;
import com.example.demo.vo.MailVo;
import freemarker.template.Template;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTests {
    @Autowired
    private MailService mailService;

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @Test
    public void testSimpleMail() throws Exception {
        MailVo mailVo = new MailVo();
        mailVo.setTo(TestDataConstants.MAIL_TO);
        mailVo.setCc(TestDataConstants.MAIL_CC);
        mailVo.setSubject(TestDataConstants.MAIL_SUBJECT);
        mailVo.setText(TestDataConstants.MAIL_TEXT);
        ResultEnum result = mailService.send(mailVo);
        Assert.assertEquals(ResultEnum.ok, result);
    }

    @Test
    public void testTemplateMail() throws Exception {
        MailVo mailVo = new MailVo();
        mailVo.setTo(TestDataConstants.MAIL_TO);
        mailVo.setCc(TestDataConstants.MAIL_CC);
        mailVo.setSubject(TestDataConstants.MAIL_SUBJECT);

        Template template = freeMarkerConfigurer.getConfiguration().getTemplate(TestDataConstants.MAIL_TEMPLATE);
        Map<String, Object> model = new HashMap<>();
        model.put("key", "value");
        String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        mailVo.setText(text);
        
        ResultEnum result = mailService.send(mailVo);
        Assert.assertEquals(ResultEnum.ok, result);
    }
}
