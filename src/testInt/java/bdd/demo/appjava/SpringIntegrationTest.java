package bdd.demo.appjava;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.spring.CucumberContextConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringIntegrationTest {

    private static final Logger LOG = LoggerFactory.getLogger(SpringIntegrationTest.class);

    @Before
    public void setUp() {
        LOG.info("------------- TEST CONTEXT SETUP -------------");
    }

    @After
    public void tearDown() {
        LOG.info("------------- TEST CONTEXT TEAR DOWN -------------");
        CucumberTestContext.CONTEXT.reset();
    }

}
