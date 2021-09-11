package com.infosys.coocking.specification;

import com.infosys.coocking.model.dto.PagingRequest;
import com.infosys.coocking.model.dto.PagingResponse;
import com.infosys.coocking.model.dto.SearchCriteria;
import com.infosys.coocking.model.enums.SortOperation;
import com.infosys.coocking.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PaginationExecutor<E> {

    private PagingRequest pagingRequest;
    private Specification specification;
    private Specification compositeSpecification;
    private BaseRepository repository;
    private boolean isCompositeSpecification = false;


    public PaginationExecutor(PagingRequest pagingRequest, BaseRepository repository) {
        this.pagingRequest = pagingRequest;
        this.repository = repository;
        this.specification = new BaseSpecification<E>(pagingRequest.getParams());
    }

    public PaginationExecutor(PagingRequest pagingRequest, BaseRepository repository, Specification specification) {
        this.pagingRequest = pagingRequest;
        this.repository = repository;
        this.isCompositeSpecification = true;
        this.compositeSpecification = new BaseSpecification<E>(pagingRequest.getParams()).and(specification);
    }

    private Sort parsingSortRequest(PagingRequest pagingRequest) {
        List<Sort.Order> orders = new ArrayList<>();
        if (Objects.nonNull(pagingRequest.getParams()) && !pagingRequest.getParams().isEmpty())
            for (SearchCriteria searchCriteria : pagingRequest.getParams()) {
                if (Objects.nonNull(searchCriteria.getSortOperation()) && searchCriteria.getSortOperation().equals(SortOperation.ASC))
                    orders.add(Sort.Order.asc(searchCriteria.getKey()));
                else
                    orders.add(Sort.Order.desc(searchCriteria.getKey()));
            }
        return Sort.by(orders);
    }

    public PagingResponse execute() {

        Sort sort = this.parsingSortRequest(pagingRequest);
        Pageable pageable = PageRequest.of(this.pagingRequest.getStart() / this.pagingRequest.getSize(), this.pagingRequest.getSize(), sort);

        Page response = null;
        if (isCompositeSpecification) {
            response = repository.findAll(compositeSpecification, pageable);
        } else {
            response = repository.findAll(specification, pageable);
        }

        PagingResponse pagingResponse = new PagingResponse();
        pagingResponse.setCount(response.getTotalPages());
        pagingResponse.setSize((int) response.getTotalElements());
        pagingResponse.setStart(this.pagingRequest.getStart());
        pagingResponse.setData(response.getContent());

        return pagingResponse;
    }


}
