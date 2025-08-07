package InternetBank.SerrvidorEmail.Repository;

import InternetBank.SerrvidorEmail.Entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, Long> {

}
