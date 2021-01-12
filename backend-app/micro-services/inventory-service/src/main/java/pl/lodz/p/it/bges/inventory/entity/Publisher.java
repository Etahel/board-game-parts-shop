package pl.lodz.p.it.bges.inventory.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(name = "publishers")
public class Publisher extends InventoryEntity {

    @Column(name = "name", nullable = false)
    private String name;
}
