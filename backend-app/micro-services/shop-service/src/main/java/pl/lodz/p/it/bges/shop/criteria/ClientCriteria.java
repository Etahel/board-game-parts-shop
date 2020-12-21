package pl.lodz.p.it.bges.shop.criteria;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientCriteria extends ShopCriteria {

    private String username;
    private String firstName;
    private String lastName;

}
