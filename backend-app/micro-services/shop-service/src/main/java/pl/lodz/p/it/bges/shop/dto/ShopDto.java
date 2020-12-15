package pl.lodz.p.it.bges.shop.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import org.springframework.dao.OptimisticLockingFailureException;
import pl.lodz.p.it.bges.core.definitions.Dto;
import pl.lodz.p.it.bges.shop.entity.ShopEntity;
import pl.lodz.p.it.bges.shop.util.CryptoUtil;

import java.math.BigInteger;

@Setter
@Getter
public abstract class ShopDto<T extends ShopEntity> implements Dto<T> {

    @JsonView(Views.Id.class)
    private Long id;

    @JsonView(Views.Base.class)
    private BigInteger version;


    public ShopDto() {
        init();
    }

    ;

    public ShopDto(T entity) {
        init();
        fillProperties(entity);
    }

    public void init() {

    }


    @Override
    public void fillProperties(T entity) {
        if (entity == null)
            return;
        this.id = entity.getId();
        this.version = encodeVersion(entity.getVersion());
    }

    @Override
    public void putProperties(T entity) {
        if (entity == null)
            return;
        if (entity.getVersion() != null) {
            checkVersion(entity.getVersion(), getVersion());
        }
    }

    @Override
    public void patchProperties(T entity) {
        if (entity == null)
            return;
        checkVersion(entity.getVersion(), getVersion());
    }

    protected void checkVersion(Long v1, BigInteger v2) {
        if (!v1.equals(decodeVersion(v2))) {
            throw new OptimisticLockingFailureException("DTO with invalid version");
        }
    }

    protected BigInteger encodeVersion(Long version) {
        return CryptoUtil.getInstance().encrypt(BigInteger.valueOf(version));
    }

    protected Long decodeVersion(BigInteger version) {
        if (version != null) {
            return CryptoUtil.getInstance().decrypt(version).longValue();
        } else {
            return 0L;
        }
    }

}
