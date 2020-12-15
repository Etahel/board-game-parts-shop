package pl.lodz.p.it.bges.inventory.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(name = "tags")
public class Tag extends InventoryEntity {

    @Column(name = "name", nullable = false, length = 10)
    private String name;

    @Column(name = "description", length = 255)
    private String description;
}
