package pl.lodz.p.it.bges.shop.repository.specification;

import org.springframework.data.jpa.domain.Specification;
import pl.lodz.p.it.bges.shop.criteria.OrderCriteria;
import pl.lodz.p.it.bges.shop.entity.Client;
import pl.lodz.p.it.bges.shop.entity.Client_;
import pl.lodz.p.it.bges.shop.entity.Order;
import pl.lodz.p.it.bges.shop.entity.Order_;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class OrderSpecification {

    public static Specification<Order> getCriteriaSpecification(OrderCriteria orderCriteria) {
        return new Specification<Order>() {

            @Override
            public Predicate toPredicate(Root<Order> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (orderCriteria == null) {
                    return criteriaBuilder.conjunction();
                } else {
                    if (orderCriteria.getId() != null) {
                        predicates.add(criteriaBuilder.equal(root.get(Order_.id), orderCriteria.getId()));
                    }
                    if (orderCriteria.getStatus() != null) {
                        predicates.add(criteriaBuilder.equal(root.get(Order_.status), orderCriteria.getStatus()));
                    }
                }

                return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
            }
        };
    }

    public static Specification<Order> getOwnerSpecification(String username) {
        return new Specification<Order>() {

            @Override
            public Predicate toPredicate(Root<Order> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {
                Join<Order, Client> clientJoin = root.join(Order_.client);
                return criteriaBuilder.equal(clientJoin.get(Client_.username), username);
            }
        };
    }


}