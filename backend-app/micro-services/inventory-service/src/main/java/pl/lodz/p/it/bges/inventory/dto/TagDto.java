package pl.lodz.p.it.bges.inventory.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.lodz.p.it.bges.core.definitions.Views;
import pl.lodz.p.it.bges.inventory.entity.Tag;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Getter
@Setter
@NoArgsConstructor
public class TagDto extends InventoryDto<Tag> {

    @JsonView(Views.Basic.class)
    @Size(max = 20)
    @NotBlank
    private String name;

    public TagDto(Tag tag) {
        super(tag);
    }

    @Override
    public void fillProperties(Tag entity) {
        super.fillProperties(entity);
        setName(entity.getName());
    }

    @Override
    public void putProperties(Tag entity) {
        super.putProperties(entity);
        entity.setName(getName());
    }

    @Override
    public void patchProperties(Tag entity) {
        super.patchProperties(entity);
        if (getName() != null && !getName().isBlank()) {
            entity.setName(getName());
        }
    }
}

