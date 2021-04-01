package bdd.demo.appjava.home;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Optional;

import static bdd.demo.appjava.utils.MessageTranslator.I18N;

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

    @GetMapping(value = "/about", produces = MediaType.APPLICATION_JSON_VALUE)
    public AboutResponse about() {
        LOG.info("Home.about called");
        return AboutResponse.builder().name(name).shortname(shortname).version(version).build();
    }


    @GetMapping(value = "/greetings/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public GreetingsResponse greetings(@PathVariable("id") Long id
            //, @RequestHeader(value = "accept-language") String language
            , Locale locale
            , @RequestParam Optional<String> firstName, @RequestParam Optional<String> lastName, @RequestParam(defaultValue = "good") String mood) {
        LOG.info("Home.greetings called");
        LOG.info(locale.toString());
        return GreetingsResponse.builder().id(id).greetings(I18N("greetings")).mood(I18N("mood", new Object[]{mood})).serverTime(LocalDateTime.now()).build();
    }

/*
    @GetMapping(value = "/greetings/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String greetings(@PathVariable("id") Long id
                                          // , @RequestHeader(value = "accept-language") String language
            , Locale locale, @RequestParam Optional<String> firstName,
                                          @RequestParam Optional<String> lastName,
                                          @RequestParam(defaultValue = "good") String mood) {
        return "Greetings";
    }

 */

}
