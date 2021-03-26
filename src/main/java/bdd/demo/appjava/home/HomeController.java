package bdd.demo.appjava.home;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Home")
@RestController
@RequestMapping(path = "/home")
public class HomeController {
    private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);

    @Value("${app.name}")
    private String name;
    @Value("${app.shortname}")
    private String shortname;
    @Value("${app.version}")
    private String version;

    @GetMapping(value = "about", produces = MediaType.APPLICATION_JSON_VALUE)
    public HomeResponse about() {
        LOG.info("Home.about called");
        return HomeResponse.builder().name(name).shortname(shortname).version(version).build();
    }
}
