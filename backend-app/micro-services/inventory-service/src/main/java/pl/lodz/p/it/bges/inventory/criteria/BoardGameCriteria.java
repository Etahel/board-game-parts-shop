package pl.lodz.p.it.bges.inventory.criteria;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BoardGameCriteria {
    List<String> tagNames;
    String title;
}
