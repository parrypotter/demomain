package com.panrui.panrui.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/*
* 邮件验证码发送
* */
@Component
public class EmailVerification {

    public Map<String , Object> resultMap = new HashMap<>();

    @Value("${mail.fromMail.sender}")
    public String sender;//发送者

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private MD5Utils md5Utils;

    public Boolean sendEmailVerification(String email){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        String code = VerifyCode(6);
        simpleMailMessage.setFrom(sender);
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject("自动化测试平台");
        simpleMailMessage.setText("【自动化测试平台】你的验证码为："+code+"，有效时间为5分钟(若不是本人操作，可忽略该条邮件)");
        try{
            javaMailSender.send(simpleMailMessage);
            saveCode(code);
            return true;
        }catch (Exception e){
            /*当前邮箱不存在*/
            e.printStackTrace();
            return false;
        }
    }

    //生成随机六位编码
    public String VerifyCode(int n){
        Random re = new Random();
        StringBuilder sBu = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int ran = re.nextInt(10);
            sBu.append(String.valueOf(ran));
        }
        return sBu.toString();
    }

    //保存验证码和时间
    public void saveCode(String code){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE,5);
        String currentTime = simpleDateFormat.format(calendar.getTime());//生成5分钟后时间，用户校验是否过期
        String hash = md5Utils.setCode(code);
        resultMap.put("hash",hash);
        resultMap.put("tamp",currentTime);
    }
}
