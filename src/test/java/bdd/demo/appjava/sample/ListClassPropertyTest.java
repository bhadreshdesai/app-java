package bdd.demo.appjava.sample;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasProperty;

@Slf4j
public class ListClassPropertyTest {

    //Single Object
    @Test
    public void testClassProperty() {

        Book obj = new Book("Mkyong in Action");

        assertThat(obj, hasProperty("name"));

        assertThat(obj, hasProperty("name", is("Mkyong in Action")));

    }

    // List Objects
    @Test
    public void testClassPropertyInList() {

        List<Book> list = Arrays.asList(
                new Book("Java in Action"),
                new Book("Spring in Action")
        );

        assertThat(list, containsInAnyOrder(
                hasProperty("name", is("Spring in Action")),
                hasProperty("name", is("Java in Action"))
        ));

    }

    @Test
    public void testClassPropertyInListExtraEntryInActual() {

        List<Book> list = Arrays.asList(
                new Book("Java in Action"),
                new Book("Spring in Action"),
                new Book("Additional Book In Actual")
        );

        try {
            assertThat(list, containsInAnyOrder(
                    hasProperty("name", is("Spring in Action")),
                    hasProperty("name", is("Java in Action"))
            ));
        } catch (AssertionError error) {
            assertThat(error.getMessage(), is("\nExpected: iterable with items [hasProperty(\"name\", is \"Spring in Action\"), hasProperty(\"name\", is \"Java in Action\")] in any order\n" +
                    "     but: no match for: <Additional Book In Actual>"));
            //log.info(error.getMessage());
        }
    }


    @Test
    public void testClassPropertyInListExtraEntryInExpected() {

        List<Book> list = Arrays.asList(
                new Book("Java in Action"),
                new Book("Spring in Action")
        );

        try {
            assertThat(list, containsInAnyOrder(
                    hasProperty("name", is("Spring in Action")),
                    hasProperty("name", is("Java in Action")),
                    hasProperty("name", is("Additional Book In Expected"))
            ));
        } catch (AssertionError error) {
            assertThat(error.getMessage(), is("\nExpected: iterable with items [hasProperty(\"name\", is \"Spring in Action\"), hasProperty(\"name\", is \"Java in Action\"), hasProperty(\"name\", is \"Additional Book In Expected\")] in any order\n" +
                    "     but: no item matches: hasProperty(\"name\", is \"Additional Book In Expected\") in [<Java in Action>, <Spring in Action>]"));
            //log.info(error.getMessage());
        }

    }

    public class Book {

        public Book(String name) {
            this.name = name;
        }

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String toString() {
            return this.name;
        }
    }
}