package bdd.demo.appjava;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static java.lang.ThreadLocal.withInitial;

public enum CucumberTestContext {
    CONTEXT;

    private static final Logger LOG = LoggerFactory.getLogger(CucumberTestContext.class);

    private static final String REQUEST = "REQUEST";
    private static final String RESPONSE = "RESPONSE";

    private final ThreadLocal<Map<String, Object>> threadLocal = withInitial(HashMap::new);

    private Map<String, Object> testContextMap() {
        return threadLocal.get();
    }

    public void set(String key, Object value) {
        testContextMap().put(key, value);
    }

    public Object get(String key) {
        return testContextMap().get(key);
    }

    public <T> T get(String key, Class<T> clazz) {
        return clazz.cast(testContextMap().get(key));
    }

    public void setResponse(ResponseEntity<String> response) {
        set(RESPONSE, response);
    }

    public ResponseEntity<String> getResponse() {
        return get(RESPONSE, ResponseEntity.class);
    }

    public void reset() {
        LOG.info("------------- TEST CONTEXT RESET -------------");
        testContextMap().clear();
    }
}
