package bdd.demo.appjava.configuration;

import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    /***
     * Set the server url to / so it the Try it out from browser with https works.
     * Without this the Generated server url is http:// instead of https:// See
     * https://github.com/springdoc/springdoc-openapi/issues/118#issuecomment-615306836
     * for more details
     *
     */
    // @Bean
    // public OpenAPI openAPI() {
    // return new OpenAPI().addServersItem(new Server().url("/"));
    // }
    @Bean
    public OpenApiCustomiser serverOpenApiCustomiser1() {
        return openAPI -> openAPI.getServers().forEach(server -> {
            server.setDescription("Demo Java Application");
            // Set server.forward-headers-strategy=framework in application.properties
            // TODO: Find a better way to get https:// instead of http://
            // https://github.com/springdoc/springdoc-openapi/issues/751#issuecomment-650734224
            // String url = server.getUrl();
            // url = url.replaceAll("http://", "https://");
            // server.setUrl(url);
        });
    }
}
