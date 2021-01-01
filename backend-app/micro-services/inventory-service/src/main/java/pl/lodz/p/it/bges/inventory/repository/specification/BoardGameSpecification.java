package pl.lodz.p.it.bges.inventory.repository.specification;

import org.springframework.data.jpa.domain.Specification;
import pl.lodz.p.it.bges.inventory.criteria.BoardGameCriteria;
import pl.lodz.p.it.bges.inventory.entity.BoardGame;
import pl.lodz.p.it.bges.inventory.entity.BoardGame_;
import pl.lodz.p.it.bges.inventory.entity.Tag_;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

//todo: test
public class BoardGameSpecification {
    public static Specification<BoardGame> getBoardGameSpecification(BoardGameCriteria boardGameCriteria) {
        return new Specification<BoardGame>() {

            @Override
            public Predicate toPredicate(Root<BoardGame> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (boardGameCriteria == null) {
                    return cb.conjunction();
                } else {
                    if (boardGameCriteria.getTagNames() != null) {
                        for (String name : boardGameCriteria.getTagNames())
                            predicates.add(cb.equal(root.join(BoardGame_.TAGS).get(Tag_.NAME), name));
                    }
                    if (boardGameCriteria.getTitle() != null) {
                        predicates.add(cb.like(root.get(BoardGame_.title), "%" + boardGameCriteria.getTitle() + "%"));
                    }
                }

                return cb.and(predicates.toArray(Predicate[]::new));
            }

        };
    }

    public static Specification<BoardGame> getActiveSpecification() {
        return new Specification<BoardGame>() {

            @Override
            public Predicate toPredicate(Root<BoardGame> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get(BoardGame_.active), Boolean.TRUE);
            }

        };
    }


}

