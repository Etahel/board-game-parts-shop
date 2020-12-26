package pl.lodz.p.it.bges.inventory.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import pl.lodz.p.it.bges.core.definitions.Views;
import pl.lodz.p.it.bges.inventory.entity.BoardGame;
import pl.lodz.p.it.bges.inventory.entity.Tag;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BoardGameDto extends InventoryDto<BoardGame> {

    @JsonView(Views.Basic.class)
    private String title;
    @JsonView(Views.Normal.class)
    private String description;
    @JsonView(Views.Basic.class)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<String> tags = new ArrayList<>();
    @Column(name = "year")
    @JsonView(Views.Basic.class)
    private Integer year;

    @Override
    public void fillProperties(BoardGame entity) {
        super.fillProperties(entity);
        setTitle(entity.getTitle());
        setDescription(entity.getDescription());
        setYear(entity.getYear());
        for (Tag tag : entity.getTags()) {
            tags.add(tag.getName());
        }
    }

    @Override
    public void putProperties(BoardGame entity) {
        super.putProperties(entity);
    }

    @Override
    public void patchProperties(BoardGame entity) {
        super.patchProperties(entity);
    }
}
