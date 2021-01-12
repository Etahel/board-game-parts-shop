package pl.lodz.p.it.bges.inventory.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "elements")
public class Element extends InventoryEntity {

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "photo_url")
    private String photoUrl;

    @JoinColumn(name = "stock_id", nullable = false, unique = true)
    @OneToOne(cascade = CascadeType.PERSIST)
    private Stock stock = new Stock();

    @Column(name = "category", nullable = false)
    private ElementCategory elementCategory;

    @JoinColumn(name = "board_game_id", nullable = false, unique = true)
    @ManyToOne
    private BoardGame boardGame;

    @Column(name = "active")
    private Boolean active = Boolean.TRUE;

}
