package pl.lodz.p.it.bges.inventory.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import pl.lodz.p.it.bges.core.definitions.Views;
import pl.lodz.p.it.bges.inventory.entity.Element;
import pl.lodz.p.it.bges.inventory.entity.ElementCategory;

@Getter
@Setter
public class ElementDto extends InventoryDto<Element> {

    @JsonView(Views.Basic.class)
    private String name;

    @JsonView(Views.Normal.class)
    private String description;

    @JsonView(Views.Basic.class)
    private Double price;

    @JsonView(Views.Normal.class)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private StockDto stock;

    @JsonView(Views.Basic.class)
    private ElementCategory elementCategory;

    @Override
    public void fillProperties(Element entity) {
        super.fillProperties(entity);
        setName(entity.getName());
        setDescription(entity.getDescription());
        setPrice(entity.getPrice());
        setStock(new StockDto(entity.getStock()));
    }

    @Override
    public void putProperties(Element entity) {
        super.putProperties(entity);
        entity.setName(getName());
        entity.setDescription(getDescription());
        entity.setPrice(getPrice());
        entity.setElementCategory(getElementCategory());
    }

    @Override
    public void patchProperties(Element entity) {
        super.patchProperties(entity);
    }
}
