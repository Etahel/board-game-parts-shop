package pl.lodz.p.it.bges.shop.util.converter;

import org.springframework.stereotype.Component;
import pl.lodz.p.it.bges.shop.entity.OrderStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
@Component
public class OrderStatusConverter implements AttributeConverter<OrderStatus, String>, org.springframework.core.convert.converter.Converter<String, OrderStatus> {

    @Override
    public String convertToDatabaseColumn(OrderStatus category) {
        if (category == null) {
            return null;
        }
        return category.getCode();
    }

    @Override
    public OrderStatus convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }

        return Stream.of(OrderStatus.values())
                .filter(c -> c.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public OrderStatus convert(String source) {
        try {
            return Stream.of(OrderStatus.values())
                    .filter(c -> c.getCode().equals(source))
                    .findFirst()
                    .orElseThrow(IllegalArgumentException::new);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}