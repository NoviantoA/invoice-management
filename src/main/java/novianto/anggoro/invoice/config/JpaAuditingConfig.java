package novianto.anggoro.invoice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaAuditingConfig {

    @Bean
   public AuditorAware<String> auditorProvider(){
        /* anonymous function
        return new AuditorAware<String>() {
            @Override
            public Optional<String> getCurrentAuditor() {
                return Optional.empty();
            }
        }
         */

        /* jika ingin lebih dari satu baris
        return () -> {
            System.out.println("Hello World");
            Optional.ofNullable("Test User");
        }
         */
       return () -> Optional.ofNullable("Test User");
   }
}
