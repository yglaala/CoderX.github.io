package com.bmsoft.canteensystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @Author liugaoyang
 * @Date 2018/10/21 21:02
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class MatchMenuInfoDto {
    private Integer menuId;
    private Date matchMenuDate;
    private String matchMenuTime;
}
