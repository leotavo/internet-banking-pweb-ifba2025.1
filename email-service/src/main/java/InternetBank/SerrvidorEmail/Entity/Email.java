package InternetBank.SerrvidorEmail.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name="emails")
public class Email {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String mailFrom;

    private String mailTo;

    private String mailSubject;

    private String mailText;

    private LocalDateTime sendDateEmail;

    @Enumerated(EnumType.STRING)

    private EmailStatus status = EmailStatus.SENT;
}
