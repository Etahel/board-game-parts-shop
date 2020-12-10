package pl.lodz.p.it.bges.inventory.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import pl.lodz.p.it.bges.core.definitions.Entity;

import javax.persistence.*;

@Setter
@Getter
@MappedSuperclass
public abstract class InventoryEntity implements Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    @Setter(AccessLevel.PROTECTED)
    private Long id;

    @Version
    private Long version;

}
