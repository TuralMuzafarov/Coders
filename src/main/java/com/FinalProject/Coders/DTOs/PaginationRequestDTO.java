package com.FinalProject.Coders.DTOs;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


import java.util.Objects;

@Getter
@Setter
public class PaginationRequestDTO {
    private Integer pageNo = 0;
    private Integer pageSize = 10;

    public Pageable getPageable(PaginationRequestDTO dto)
    {
        Integer page = Objects.nonNull(dto.pageNo) ? dto.pageNo : this.pageNo;
        Integer size = Objects.nonNull(dto.pageSize) ? dto.pageSize : this.pageSize;

        return  PageRequest.of(page , size);
    }


}
