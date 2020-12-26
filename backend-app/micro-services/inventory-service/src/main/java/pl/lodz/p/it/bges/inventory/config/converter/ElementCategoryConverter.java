package pl.lodz.p.it.bges.inventory.config.converter;


import pl.lodz.p.it.bges.inventory.entity.ElementCategory;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class ElementCategoryConverter implements AttributeConverter<ElementCategory, String> {

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
}