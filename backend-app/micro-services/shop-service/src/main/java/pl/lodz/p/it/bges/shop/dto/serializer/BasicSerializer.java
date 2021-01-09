package pl.lodz.p.it.bges.shop.dto.serializer;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import pl.lodz.p.it.bges.core.definitions.Views;

import java.io.IOException;

public class BasicSerializer extends JsonSerializer<Object> {
    @Override
    public void serialize(Object value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonGenerationException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writerWithView(Views.Basic.class).writeValue(jgen, value);
    }
}
