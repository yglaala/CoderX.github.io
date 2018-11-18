package com.bmsoft.canteensystem.dto;

import com.bmsoft.canteensystem.entity.MenuSug;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class OrderMenuSugDto {

    private String orderNo;
    private Integer menuId;
    private String menuName;
    private Float menuPrice;
    //private Integer menuNum;
    private String menuPropertiesCategoryName;
    private String menuPropertiesStyleName;
    private String menuPropertiesTasteName;
    private Integer menuSugId;
    private String sugContent;
    private Double menuRating;

    public OrderMenuSugDto setId(){

        if(this.menuSugId == null){
            menuSugId = 0;
        }
        return this;
    }

}
