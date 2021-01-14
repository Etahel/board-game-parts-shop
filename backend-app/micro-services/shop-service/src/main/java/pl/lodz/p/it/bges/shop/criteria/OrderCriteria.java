package pl.lodz.p.it.bges.shop.criteria;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import pl.lodz.p.it.bges.shop.entity.OrderStatus;

import java.time.LocalDate;

@Setter
@Getter
public class OrderCriteria extends ShopCriteria {

    private OrderStatus status;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateStart;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateEnd;
    private Double minValue;
    private Double maxValue;
    private String lastName;
    private String username;


}
