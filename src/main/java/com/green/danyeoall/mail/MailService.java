package com.green.danyeoall.mail;

import com.green.danyeoall.mail.model.MailCheckDto;
import com.green.danyeoall.mail.model.MemberEmailVerifyReq;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;
    private static final String senderEmail = "tlaqhdbs12@gmail.com";
    private final MailMapper mailMapper;

    // 랜덤으로 숫자 생성
    public String createNumber() {
        Random random = new Random();
        StringBuilder key = new StringBuilder();

        for (int i = 0; i < 8; i++) { // 인증 코드 8자리
            int index = random.nextInt(3); // 0~2까지 랜덤, 랜덤값으로 switch문 실행

            switch (index) {
                case 0 -> key.append((char) (random.nextInt(26) + 97)); // 소문자
                case 1 -> key.append((char) (random.nextInt(26) + 65)); // 대문자
                case 2 -> key.append(random.nextInt(10)); // 숫자
            }
        }
        return key.toString();
    }

    public MimeMessage createMail(String mail, String number) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();

        message.setFrom(senderEmail);
        message.setRecipients(MimeMessage.RecipientType.TO, mail);
        message.setSubject("이메일 인증");
        String body = "";
        body += "<h3>요청하신 인증 번호입니다.</h3>";
        body += "<h1>" + number + "</h1>";
        body += "<h3>감사합니다.</h3>";
        message.setText(body, "UTF-8", "html");

        return message;
    }

    // 메일 발송
    public String sendSimpleMessage(String sendEmail) throws MessagingException {
        String number = createNumber(); // 랜덤 인증번호 생성
        MimeMessage message = createMail(sendEmail, number); // 메일 생성
        try {
            javaMailSender.send(message); // 메일 발송
        } catch (MailException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("메일 발송 중 오류가 발생했습니다.");
        }
        MailCheckDto dto = new MailCheckDto();
        dto.setEmail(sendEmail);
        dto.setAuthCode(number);
        dto.setExpiredTime(LocalDateTime.now().plusMinutes(30));
        mailMapper.insAuthInfo(dto);

        return number; // 생성된 인증번호 반환
    }

    public int verifyCode(MemberEmailVerifyReq p) {
        MailCheckDto dto = mailMapper.selAuthInfo(p.getEmail());
        int result = 0;
        if(LocalDateTime.now().isBefore(dto.getExpiredTime()) && p.getAuthCode().equals(dto.getAuthCode())) {
            MemberEmailVerifyReq res = new MemberEmailVerifyReq();
            res.setAuthCode(p.getAuthCode());
            res.setEmail(p.getEmail());
            res.setAuthCheck(1);
            result = mailMapper.updAuthCheck(res);
            return result;
        }
        MemberEmailVerifyReq res = new MemberEmailVerifyReq();
        res.setAuthCheck(0);
        return result;
    }
}
