package bdd.demo.appjava.api;

import bdd.demo.appjava.CucumberTestContext;
import org.springframework.boot.web.server.LocalServerPort;

public class AbstractApiStepDef {

    private final CucumberTestContext CONTEXT = CucumberTestContext.CONTEXT;

    @LocalServerPort
    private int port;

    protected String getUrl(String urlPath) {
        return "http://localhost:" + port + urlPath;
    }

    protected CucumberTestContext testContext() {
        return CONTEXT;
    }

}
