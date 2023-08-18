package kr.co.finmodeun.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

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
}