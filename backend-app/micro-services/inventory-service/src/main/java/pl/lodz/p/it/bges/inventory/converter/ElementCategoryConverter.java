package pl.lodz.p.it.bges.inventory.converter;


import org.springframework.stereotype.Component;
import pl.lodz.p.it.bges.inventory.entity.ElementCategory;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
@Component
public class ElementCategoryConverter implements AttributeConverter<ElementCategory, String>, org.springframework.core.convert.converter.Converter<String, ElementCategory> {

    @Override
    public String convertToDatabaseColumn(ElementCategory category) {
        if (category == null) {
            return null;
        }
        return category.getCode();
    }

    @Override
    public ElementCategory convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }

        return Stream.of(ElementCategory.values())
                .filter(c -> c.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public ElementCategory convert(String source) {
        try {
            return Stream.of(ElementCategory.values())
                    .filter(c -> c.getCode().equals(source))
                    .findFirst()
                    .orElseThrow(IllegalArgumentException::new);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}