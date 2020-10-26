package com.example.demo.service;

import com.example.demo.constant.ResultEnum;
import com.example.demo.util.MailUtils;
import com.example.demo.vo.MailVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author dustforest
 */
@Service
public class MailService {
    private static final Logger logger = LoggerFactory.getLogger(MailService.class);

    @Autowired
    private MailUtils mailUtils;

    public ResultEnum test(MailVo mailVo) {
        mailUtils.sendMail(mailVo);
        return ResultEnum.ok;
    }
}

