package bdd.demo.appjava.api;

import bdd.demo.appjava.CucumberTestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.server.LocalServerPort;

public class AbstractApiStepDef {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractApiStepDef.class);

    private CucumberTestContext CONTEXT = CucumberTestContext.CONTEXT;

    @LocalServerPort
    private int port;

    protected String getUrl(String urlPath) {
        return "http://localhost:" + port + urlPath;
    }

    protected CucumberTestContext testContext() {
        return CONTEXT;
    }

}
