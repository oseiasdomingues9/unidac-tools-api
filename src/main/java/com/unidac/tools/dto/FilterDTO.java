package com.unidac.tools.dto;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class FilterDTO {

       private Date dateStart;
       private Date dateEnd;
       private Integer page;
       private Integer rows;
       private String sortField;
       private Long sortOrder;
}


