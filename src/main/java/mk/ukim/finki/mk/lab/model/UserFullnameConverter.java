package mk.ukim.finki.mk.lab.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class UserFullnameConverter implements AttributeConverter<UserFullname, String> {
    @Override
    public String convertToDatabaseColumn(UserFullname userFullname) {
        if (userFullname == null) return null;

        return String.format("%s %s", userFullname.getName(), userFullname.getSurname());
    }

    @Override
    public UserFullname convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;

        String[] parts = dbData.split(" ");
        UserFullname fn = new UserFullname();
        fn.setName(parts[0]);
        fn.setSurname(parts[1]);

        return fn;
    }
}
