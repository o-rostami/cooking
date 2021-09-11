package com.infosys.coocking.specification;

import com.infosys.coocking.model.dto.SearchCriteria;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
public class BaseSpecification<E> implements Specification<E> {

    private List<SearchCriteria> params;

    @Override
    public Predicate toPredicate(Root<E> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if(Objects.nonNull(params) && !params.isEmpty()) {
            params.forEach(param -> {
                switch (param.getOperation()) {
                    case EQUAL:
                        predicates.add(criteriaBuilder.equal(relationalGetPath(root, param.getKey()), param.getValue()));
                        break;
                    case IN:
                        predicates.add(criteriaBuilder.in(relationalGetPath(root, param.getKey())).value(param.getValue()));
                        break;
                    case NOT_IN:
                        predicates.add(criteriaBuilder.not(relationalGetPath(root, param.getKey())).in(param.getValue()));
                        break;
                    case LIKE:
                        predicates.add(criteriaBuilder.like(criteriaBuilder.lower(relationalGetPath(root, param.getKey())),
                                "%" + param.getValue().toString().toLowerCase() + "%"));
                        break;
                    case LESS_THAN:
                        if (relationalGetPath(root, param.getKey()).getJavaType() == LocalDate.class) {
                            LocalDate inputDate = this.convertStrToDate(param.getValue().toString());
                            predicates.add(criteriaBuilder.lessThan(relationalGetPath(root, param.getKey()), inputDate));
                            break;
                        }
                        predicates.add(criteriaBuilder.lessThan(relationalGetPath(root, param.getKey()),
                                param.getValue().toString()));
                        break;
                    case NOT_EQUAL:
                        predicates.add(criteriaBuilder.notEqual(relationalGetPath(root, param.getKey()), param.getValue()));
                        break;
                    case GREATER_THAN:
                        if (relationalGetPath(root, param.getKey()).getJavaType() == LocalDate.class) {
                            LocalDate inputDate = this.convertStrToDate(param.getValue().toString());
                            predicates.add(criteriaBuilder.greaterThan(relationalGetPath(root, param.getKey()), inputDate));
                            break;
                        }
                        predicates.add(criteriaBuilder.greaterThan(relationalGetPath(root, param.getKey()),
                                param.getValue().toString()));
                        break;
                    case LESS_THAN_OR_EQUAL_TO:
                        if (relationalGetPath(root, param.getKey()).getJavaType() == LocalDate.class) {
                            LocalDate inputDate = this.convertStrToDate(param.getValue().toString());
                            predicates.add(criteriaBuilder.lessThanOrEqualTo(relationalGetPath(root, param.getKey()), inputDate));
                            break;
                        }
                        predicates.add(criteriaBuilder.lessThanOrEqualTo(relationalGetPath(root, param.getKey()),
                                param.getValue().toString()));
                        break;
                    case GREATER_THAN_OR_EQUAL_TO:
                        if (relationalGetPath(root, param.getKey()).getJavaType() == LocalDate.class) {
                            LocalDate inputDate = this.convertStrToDate(param.getValue().toString());
                            predicates.add(criteriaBuilder.greaterThanOrEqualTo(relationalGetPath(root, param.getKey()), inputDate));
                            break;
                        }
                        predicates.add(criteriaBuilder.greaterThanOrEqualTo(relationalGetPath(root, param.getKey()),
                                param.getValue().toString()));
                        break;
                    default:
                        break;
                }
            });
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private LocalDate convertStrToDate(String date) {
        String[] strDate = date.split("-");
        return LocalDate.of(
                Integer.parseInt(strDate[0]),   // year
                Integer.parseInt(strDate[1]),   // month
                Integer.parseInt(strDate[2])    // day
        );
    }

    public Path relationalGetPath(Root root, String stringPath) {
        if (!stringPath.contains(".")) {
            return root.get(stringPath);
        }
        final String[] relationalPaths = stringPath.split("\\.");
        Path sequencedPath = root;
        for (String path : relationalPaths) {
            sequencedPath = sequencedPath.get(path);
        }
        return sequencedPath;
    }

}
