package pl.lodz.p.it.bges.inventory.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.lodz.p.it.bges.core.definitions.Views;
import pl.lodz.p.it.bges.inventory.entity.Publisher;

import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class PublisherDto extends InventoryDto<Publisher> {

    @JsonView(Views.Basic.class)
    @Size(min = 1, max = 30)
    private String name;

    public PublisherDto(Publisher publisher) {
        super(publisher);
    }

    @Override
    public void fillProperties(Publisher entity) {
        super.fillProperties(entity);
        setName(entity.getName());
    }

    @Override
    public void putProperties(Publisher entity) {
        super.putProperties(entity);
        entity.setName(getName());
    }

    @Override
    public void patchProperties(Publisher entity) {
        super.patchProperties(entity);
        if (getName() != null) {
            entity.setName(getName());
        }
    }
}
