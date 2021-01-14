package pl.lodz.p.it.bges.inventory.criteria;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.lodz.p.it.bges.inventory.entity.ElementCategory;

@Getter
@Setter
@NoArgsConstructor
public class ElementCriteria {
    ElementCategory elementCategory;
    String name;
    String description;
}
