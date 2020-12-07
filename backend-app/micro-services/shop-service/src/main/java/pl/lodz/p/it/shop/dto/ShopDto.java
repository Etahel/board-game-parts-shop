package pl.lodz.p.it.shop.dto;

import lombok.Getter;
import lombok.Setter;
import pl.lodz.p.it.shop.entity.ShopEntity;

@Setter
@Getter
public class ShopDto<T extends ShopEntity> {

    ShopDto() {
    }

    ;

    ShopDto(T entity) {

    }


}
