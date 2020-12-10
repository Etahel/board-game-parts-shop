package pl.lodz.p.it.bges.shop.dto;

import lombok.Getter;
import lombok.Setter;
import pl.lodz.p.it.bges.core.definitions.Dto;
import pl.lodz.p.it.bges.shop.entity.ShopEntity;
import pl.lodz.p.it.bges.shop.util.CryptoUtil;

import java.math.BigInteger;

@Setter
@Getter
public abstract class ShopDto<T extends ShopEntity> implements Dto<T> {

    private Long id;
    private BigInteger version;


    public ShopDto() {
    }

    ;

    public ShopDto(T entity) {
        fillProperties(entity);
    }


    @Override
    public void fillProperties(T entity) {
        this.id = entity.getId();
        this.version = encodeVersion(entity.getVersion());
    }

    @Override
    public void putProperties(T entity) {
        entity.setVersion(decodeVersion(getVersion()));
    }

    @Override
    public void patchProperties(T entity) {
        entity.setVersion(decodeVersion(getVersion()));
    }

    private BigInteger encodeVersion(Long version) {
        return CryptoUtil.getInstance().encrypt(BigInteger.valueOf(version));
    }

    private Long decodeVersion(BigInteger version) {
        return CryptoUtil.getInstance().decrypt(version).longValue();
    }
}