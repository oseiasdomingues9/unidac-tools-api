package com.unidac.tools.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class FilterDTO {

       private String dateStart;
       private String dateEnd;
       private Integer page;
       private Integer rows;
       private String sortField;
       private Long sortOrder;
}


