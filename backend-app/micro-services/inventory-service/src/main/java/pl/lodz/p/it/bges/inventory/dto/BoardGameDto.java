package pl.lodz.p.it.bges.inventory.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.lodz.p.it.bges.core.definitions.Views;
import pl.lodz.p.it.bges.inventory.entity.BoardGame;
import pl.lodz.p.it.bges.inventory.entity.Tag;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BoardGameDto extends InventoryDto<BoardGame> {

    @JsonView(Views.Basic.class)
    @Size(min = 1, max = 50)
    private String title;
    @JsonView(Views.Normal.class)
    @Size(max = 2000)
    private String description;
    @JsonView(Views.Basic.class)
    private List<String> tags;
    @JsonView(Views.Basic.class)
    @Min(1900)
    @Max(2100)
    private Integer year;
    @JsonView(Views.Normal.class)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Boolean active;
    @JsonView(Views.Basic.class)
    @Min(1)
    @Max(20)
    private Integer minPlayers;
    @JsonView(Views.Basic.class)
    @Min(1)
    @Max(20)
    private Integer maxPlayers;
    @JsonView(Views.Basic.class)
    @Size(max = 60)
    private String author;
    @JsonView(Views.Basic.class)
    @Size(max = 2048)
    private String photoUrl;
    @JsonView(Views.Basic.class)
    @Size(max = 15)
    private String publisher;


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
        if (entity.getPublisher() != null) {
            setPublisher(entity.getPublisher().getName());
        }
        setAuthor(entity.getAuthor());
        setMinPlayers(entity.getMinPlayers());
        setMaxPlayers(entity.getMaxPlayers());
        setPhotoUrl(entity.getPhotoUrl());
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
        entity.setAuthor(getAuthor());
        entity.setMinPlayers(getMinPlayers());
        entity.setMaxPlayers(getMaxPlayers());
        entity.setPhotoUrl(getPhotoUrl());
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
        if (getAuthor() != null) {
            entity.setAuthor(getAuthor());
        }
        if (getMinPlayers() != null) {
            entity.setMinPlayers(getMinPlayers());
        }
        if (getMaxPlayers() != null) {
            entity.setMaxPlayers(getMaxPlayers());
        }
        if (getPhotoUrl() != null) {
            entity.setPhotoUrl(getPhotoUrl());
        }
    }
}
