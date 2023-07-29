package demo.controller;

import com.alibaba.druid.pool.DruidDataSource;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import demo.config.TestBean;
import demo.model.Customer;
import demo.service.CustomerService;
import demo.util.PropertyUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Controller
@RequestMapping("/customer")
@ResponseBody
@RequiredArgsConstructor
@Slf4j
public class CustomerController {

    private final CustomerService customerService;

    private final TestBean testBean;

    private final DruidDataSource dataSource;

    @Value("${spring.datasource.password}")
    private String password;

    private final Environment environment;

    private final ObjectMapper objectMapper;

    @RequestMapping(value = "/", produces = APPLICATION_JSON_VALUE)
    public List<Customer> findAll() {
        return customerService.findAll();
    }

    @RequestMapping("/testBean")
    public TestBean testBean() throws JsonProcessingException {
        log.info("testBean:{}", objectMapper.writeValueAsString(testBean));
        log.info("property:{}:{}", "timeout", PropertyUtil.getProperty("timeout"));
        log.info("property:{}:{}", "timeout", PropertyUtil.resolvePlaceholders("${timeout:1}"));
        log.info("dataSource:password:{}", dataSource.getPassword());
        log.info("property:{}:{}", "spring.datasource.password", PropertyUtil.getProperty("spring.datasource.password"));
        log.info("@Value:{}:{}", "spring.datasource.password", password);
        log.info("Environment:{}:{}", "spring.datasource.password", environment.getProperty("spring.datasource.password"));
        return testBean;
    }
}
