package com.bmsoft.canteensystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class OrderMenuDto {

    private Float menuPrice;
    private String menuName;
    private Integer menuNum;
}
