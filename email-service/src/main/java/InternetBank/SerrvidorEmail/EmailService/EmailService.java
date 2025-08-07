package InternetBank.SerrvidorEmail.EmailService;

import InternetBank.SerrvidorEmail.Dto.EmailDTO;
import InternetBank.SerrvidorEmail.Entity.Email;
import InternetBank.SerrvidorEmail.Entity.EmailStatus;
import InternetBank.SerrvidorEmail.Repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailService {

    @Autowired

    private EmailRepository emailRepository;

    @Autowired

    private JavaMailSender emailSender;


    public Email sendEmail(EmailDTO dto) {
        Email data = new Email(dto);
        data.setSendDateEmail(LocalDateTime.now());
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(dto.mailFrom());
        message.setTo(dto.mailTo());
        message.setSubject(dto.mailSubject());
        message.setText(dto.mailText());
        data.setStatus(EmailStatus.SENT);
        emailSender.send(message);
        emailRepository.save(data);
        return data;
    }

}