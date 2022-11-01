package org.zerock.mreview.dto;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
public class PageResultDTO<DTO, EN> {
    //DTO리스트
    private List<DTO> list;
    private PageDTO page;


    public PageResultDTO(Page<EN> result, Function<EN, DTO> fn) {
        list = result.stream().map(fn).collect(Collectors.toList());
        page = new PageDTO(result.getPageable(), result.getTotalPages());
    }
}
