package com.infosys.coocking.model.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PagingRequest {

    private int start;

    private int size;

    private List<SearchCriteria> params;

}
