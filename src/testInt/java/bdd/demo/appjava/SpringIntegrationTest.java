package bdd.demo.appjava;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.spring.CucumberContextConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {
        "spring.security.user.name=duke",
        "spring.security.user.password=secret",
        "spring.security.user.roles=ADMIN"
})
@Slf4j
public class SpringIntegrationTest {

    @Before
    public void setUp() {
        log.info("------------- TEST CONTEXT SETUP -------------");
    }

    @After
    public void tearDown() {
        log.info("------------- TEST CONTEXT TEAR DOWN -------------");
        CucumberTestContext.CONTEXT.reset();
    }

}
