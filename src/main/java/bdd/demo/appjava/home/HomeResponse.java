package bdd.demo.appjava.home;

@lombok.AllArgsConstructor
@lombok.Builder
@lombok.Data
@lombok.NoArgsConstructor
public class HomeResponse {
    private String name;
    private String shortname;
    private String version;
}
