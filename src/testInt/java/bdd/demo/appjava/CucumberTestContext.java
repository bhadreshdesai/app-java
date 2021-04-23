package bdd.demo.appjava;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static java.lang.ThreadLocal.withInitial;

@Slf4j
public enum CucumberTestContext {
    CONTEXT;

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

    public ResponseEntity<String> getResponse() {
        return get(RESPONSE, ResponseEntity.class);
    }

    public void setResponse(ResponseEntity<String> response) {
        set(RESPONSE, response);
    }

    public void reset() {
        log.info("------------- TEST CONTEXT RESET -------------");
        testContextMap().clear();
    }
}
