package pl.lodz.p.it.bges.inventory.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "board_games")
public class BoardGame extends InventoryEntity {

    @Column(name = "title", nullable = false, length = 30)
    private String title;

    @Column(name = "description", length = 255)
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "boardgames_tags",
            joinColumns = {@JoinColumn(name = "board_game_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id")}
    )
    private List<Tag> tags = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "boardGame", orphanRemoval = true)
    private List<Element> elements = new ArrayList<>();

    @Column(name = "year")
    private Integer year;

    @Column(name = "active")
    private Boolean active = Boolean.TRUE;

    @Column(name = "min_players")
    private Integer minPlayers;

    @Column(name = "max_players")
    private Integer maxPlayers;

    @Column(name = "author")
    private String author;

    @Column(name = "photo_url")
    private String photoUrl;

    @JoinColumn(name = "publisher_id")
    @ManyToOne
    private Publisher publisher;
}

