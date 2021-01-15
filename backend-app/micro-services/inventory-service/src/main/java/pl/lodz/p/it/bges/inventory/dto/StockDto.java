package pl.lodz.p.it.bges.inventory.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import pl.lodz.p.it.bges.core.definitions.Views;
import pl.lodz.p.it.bges.inventory.entity.Stock;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
public class StockDto extends InventoryDto<Stock> {

    @JsonView(Views.Basic.class)
    @Min(0)
    @Max(1000000)
    private Integer stockSize;
    @JsonView({Views.Basic.class})
    private Boolean available;
    @JsonView(Views.Basic.class)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Min(-1000000)
    @Max(1000000)
    private Integer stockChange;

    public StockDto() {
    }

    ;

    public StockDto(Stock stock) {
        super(stock);
    }

    @Override
    public void patchProperties(Stock entity) {
        super.patchProperties(entity);
        if (getStockSize() != null) {
            entity.setStockSize(getStockSize());
        }
        if (getAvailable() != null) {
            entity.setAvailable(getAvailable());
        }
    }

    @Override
    public void fillProperties(Stock entity) {
        super.fillProperties(entity);
        setStockSize(entity.getStockSize());
        setAvailable(entity.getAvailable());
    }

}
