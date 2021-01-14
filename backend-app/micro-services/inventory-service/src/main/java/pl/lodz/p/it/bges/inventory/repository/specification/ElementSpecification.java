package pl.lodz.p.it.bges.inventory.repository.specification;

import org.springframework.data.jpa.domain.Specification;
import pl.lodz.p.it.bges.inventory.criteria.ElementCriteria;
import pl.lodz.p.it.bges.inventory.entity.BoardGame;
import pl.lodz.p.it.bges.inventory.entity.BoardGame_;
import pl.lodz.p.it.bges.inventory.entity.Element;
import pl.lodz.p.it.bges.inventory.entity.Element_;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class ElementSpecification {
    public static Specification<Element> getCriteriaSpecification(ElementCriteria elementCriteria) {
        return new Specification<Element>() {

            @Override
            public Predicate toPredicate(Root<Element> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (elementCriteria == null) {
                    return cb.conjunction();
                } else {

                    if (elementCriteria.getName() != null) {
                        predicates.add(cb.like(cb.upper(root.get(Element_.name)), "%" + elementCriteria.getName().toUpperCase() + "%"));
                    }

                    if (elementCriteria.getDescription() != null) {
                        predicates.add(cb.like(cb.upper(root.get(Element_.description)), "%" + elementCriteria.getDescription().toUpperCase() + "%"));
                    }

                    if (elementCriteria.getElementCategory() != null) {
                        predicates.add(cb.equal(root.get(Element_.ELEMENT_CATEGORY), elementCriteria.getElementCategory()));
                    }
                }

                return cb.and(predicates.toArray(Predicate[]::new));
            }
        };
    }

    public static Specification<Element> getBoardGameSpecification(Long boardGameId) {
        return new Specification<Element>() {

            @Override
            public Predicate toPredicate(Root<Element> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {
                Join<Element, BoardGame> clientJoin = root.join(Element_.boardGame);
                return criteriaBuilder.equal(clientJoin.get(BoardGame_.id), boardGameId);
            }
        };
    }

    public static Specification<Element> getActiveSpecification() {
        return new Specification<Element>() {

            @Override
            public Predicate toPredicate(Root<Element> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get(Element_.active), Boolean.TRUE);
            }
        };
    }
}
