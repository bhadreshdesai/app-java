package bdd.demo.appjava.employee;

import javax.persistence.AttributeConverter;
import java.util.stream.Stream;

public class GenderConverter implements AttributeConverter<Gender, String> {

    @Override
    public String convertToDatabaseColumn(Gender gender) {
        if (gender == null) {
            return null;
        }
        return gender.toString();
    }

    @Override
    public Gender convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }

        return Stream.of(Gender.values())
                .filter(c -> c.toString().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}