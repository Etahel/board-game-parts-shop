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
                    if (orderCriteria.getDateStart() != null) {
                        predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(Order_.DATE), orderCriteria.getDateStart().atStartOfDay()));
                    }
                    if (orderCriteria.getDateEnd() != null) {
                        predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(Order_.DATE), orderCriteria.getDateEnd().atTime(23, 59, 59)));
                    }
                    if (orderCriteria.getMinValue() != null) {
                        predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(Order_.value), orderCriteria.getMinValue()));
                    }
                    if (orderCriteria.getMaxValue() != null) {
                        predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(Order_.value), orderCriteria.getMaxValue()));
                    }
                    if (orderCriteria.getUsername() != null) {
                        predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.join(Order_.CLIENT).get(Client_.USERNAME)), "%" + orderCriteria.getUsername().toUpperCase() + "%"));
                    }
                    if (orderCriteria.getLastName() != null) {
                        predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.join(Order_.CLIENT).get(Client_.LAST_NAME)), "%" + orderCriteria.getLastName().toUpperCase() + "%"));
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