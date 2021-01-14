package pl.lodz.p.it.bges.inventory.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.lodz.p.it.bges.core.definitions.Views;
import pl.lodz.p.it.bges.inventory.entity.Element;
import pl.lodz.p.it.bges.inventory.entity.ElementCategory;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class ElementDto extends InventoryDto<Element> {

    @JsonView(Views.Basic.class)
    @Size(min = 1, max = 30)
    private String name;

    @JsonView(Views.Basic.class)
    @Size(max = 500)
    private String description;

    @JsonView(Views.Basic.class)
    @Min(0)
    private Double price;

    @JsonView(Views.Normal.class)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private StockDto stock;

    @JsonView(Views.Basic.class)
    private ElementCategory elementCategory;

    @JsonView(Views.Normal.class)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Boolean active;

    @JsonView(Views.Normal.class)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private BoardGameDto boardGame;

    @Size(max = 2048)
    private String photoUrl;

    public ElementDto(Element element) {
        super(element);
    }

    @Override
    public void fillProperties(Element entity) {
        super.fillProperties(entity);
        setName(entity.getName());
        setDescription(entity.getDescription());
        setPrice(entity.getPrice());
        setElementCategory(entity.getElementCategory());
        setActive(entity.getActive());
        setStock(new StockDto(entity.getStock()));
        setBoardGame(new BoardGameDto(entity.getBoardGame()));
        setPhotoUrl(entity.getPhotoUrl());
    }

    @Override
    public void putProperties(Element entity) {
        super.putProperties(entity);
        entity.setName(getName());
        entity.setDescription(getDescription());
        entity.setPrice(getPrice());
        entity.setElementCategory(getElementCategory());
        entity.setPhotoUrl(getPhotoUrl());
    }

    @Override
    public void patchProperties(Element entity) {
        super.patchProperties(entity);
        if (getName() != null) {
            entity.setName(getName());
        }
        if (getDescription() != null) {
            entity.setDescription(getDescription());
        }
        if (getPrice() != null) {
            entity.setPrice(getPrice());
        }
        if (getElementCategory() != null) {
            entity.setElementCategory(getElementCategory());
        }
        if (getPhotoUrl() != null) {
            entity.setPhotoUrl(getPhotoUrl());
        }
    }
}
