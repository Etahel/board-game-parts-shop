package pl.lodz.p.it.bges.shop.repository.specification;

import org.springframework.data.jpa.domain.Specification;
import pl.lodz.p.it.bges.shop.criteria.ClientCriteria;
import pl.lodz.p.it.bges.shop.entity.Client;
import pl.lodz.p.it.bges.shop.entity.Client_;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ClientSpecification {

    public static Specification<Client> getCriteriaSpecification(ClientCriteria clientCriteria) {
        return new Specification<Client>() {

            @Override
            public Predicate toPredicate(Root<Client> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (clientCriteria == null) {
                    return cb.conjunction();
                } else {
                    if (clientCriteria.getId() != null) {
                        predicates.add(cb.equal(root.get(Client_.id), clientCriteria.getId()));
                    }
                    if (clientCriteria.getFirstName() != null && !clientCriteria.getFirstName().isBlank()) {
                        predicates.add(cb.like(cb.lower(root.get(Client_.firstName)), "%" + clientCriteria.getFirstName().toLowerCase() + "%"));
                    }
                    if (clientCriteria.getLastName() != null && !clientCriteria.getLastName().isBlank()) {
                        predicates.add(cb.like(cb.lower(root.get(Client_.lastName)), "%" + clientCriteria.getLastName().toLowerCase() + "%"));
                    }
                }

                return cb.and(predicates.toArray(Predicate[]::new));
            }
        };
    }
}
