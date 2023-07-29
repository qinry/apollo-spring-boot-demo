package demo.config;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableApolloConfig
public class ApolloConfig {

    @Bean
    public TestBean testBean() {
        return new TestBean();
    }

    @Bean
    public static EncryptPropertySourceProcessor encryptPropertySourceProcessor() {
        return new EncryptPropertySourceProcessor();
    }
}
