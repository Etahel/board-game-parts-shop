package pl.lodz.p.it.bges.inventory.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.lodz.p.it.bges.core.definitions.Views;
import pl.lodz.p.it.bges.inventory.entity.BoardGame;
import pl.lodz.p.it.bges.inventory.entity.Tag;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BoardGameDto extends InventoryDto<BoardGame> {

    @JsonView(Views.Basic.class)
    private String title;
    @JsonView(Views.Normal.class)
    private String description;
    @JsonView(Views.Basic.class)
    private List<String> tags;
    @JsonView(Views.Basic.class)
    private Integer year;
    @JsonView(Views.Normal.class)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Boolean active;


    public BoardGameDto(BoardGame boardGame) {
        super(boardGame);
    }

    @Override
    public void fillProperties(BoardGame entity) {
        super.fillProperties(entity);
        setTitle(entity.getTitle());
        setDescription(entity.getDescription());
        setYear(entity.getYear());
        setActive(entity.getActive());
        tags = new ArrayList<>();
        for (Tag tag : entity.getTags()) {
            tags.add(tag.getName());
        }
    }

    @Override
    public void putProperties(BoardGame entity) {
        super.putProperties(entity);
        entity.setTitle(getTitle());
        entity.setDescription(getDescription());
        entity.setYear(getYear());
    }

    @Override
    public void patchProperties(BoardGame entity) {
        super.patchProperties(entity);
        if (getTitle() != null) {
            entity.setTitle(getTitle());
        }
        if (getDescription() != null) {
            entity.setDescription(getDescription());
        }
        if (getYear() != null) {
            entity.setYear(getYear());
        }
    }
}
