package sirenorder.common;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import sirenorder.OrderDetailApplication;

@CucumberContextConfiguration
@SpringBootTest(classes = { OrderDetailApplication.class })
public class CucumberSpingConfiguration {}
