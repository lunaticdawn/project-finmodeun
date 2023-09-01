package kr.co.finmodeun.admin;

import kr.co.finmodeun.admin.cmn.security.SecurityInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.project.cmn", "kr.co.finmodeun.admin"})
@MapperScan(basePackages = "kr.co.finmodeun.admin")
public class FinModeunAdmin extends SpringBootServletInitializer {
    /**
     * @param args Arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(FinModeunAdmin.class, args);
    }

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addInterceptors(@NonNull InterceptorRegistry registry) {
                registry.addInterceptor(securityInterceptor());
            }
        };
    }

    @Bean
    public SecurityInterceptor securityInterceptor() {
        return new SecurityInterceptor();
    }
}
