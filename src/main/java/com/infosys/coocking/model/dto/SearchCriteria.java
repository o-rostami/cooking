package com.infosys.coocking.model.dto;

import com.infosys.coocking.model.enums.SearchOperation;
import com.infosys.coocking.model.enums.SortOperation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchCriteria {
    private String key;
    private SearchOperation operation;
    private Object value;
    private SortOperation sortOperation;


}
