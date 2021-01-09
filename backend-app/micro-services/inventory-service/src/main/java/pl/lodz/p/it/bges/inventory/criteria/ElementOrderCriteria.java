package pl.lodz.p.it.bges.inventory.criteria;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ElementOrderCriteria {
    @Size(max = 10)
    List<Long> ids;
    Boolean active;
}
