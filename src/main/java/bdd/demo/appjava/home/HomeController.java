package bdd.demo.appjava.home;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Optional;

import static bdd.demo.appjava.home.Constants.*;
import static bdd.demo.appjava.utils.MessageTranslator.I18N;

@Tag(name = TAG_HOME, description = DESC_HOME_API)
@RestController
@RequestMapping(path = APIPATH_HOME)
@Slf4j
public class HomeController {

    @Value("${app.name}")
    private String name;
    @Value("${app.shortname}")
    private String shortname;
    @Value("${app.version}")
    private String version;

    @GetMapping(value = APIPATH_GET_ABOUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public AboutResponse about() {
        log.info("Home.about called");
        return AboutResponse.builder().name(name).shortname(shortname).version(version).build();
    }


    @GetMapping(value = APIPATH_GET_GREETINGS, produces = MediaType.APPLICATION_JSON_VALUE)
    public GreetingsResponse greetings(@PathVariable("id") Long id
                                       //, @RequestHeader(value = "accept-language") String language
            , Locale locale
            , @RequestParam Optional<String> firstName, @RequestParam Optional<String> lastName, @RequestParam(defaultValue = "good") String mood) {
        log.info("Home.greetings called");
        log.info(locale.toString());
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
