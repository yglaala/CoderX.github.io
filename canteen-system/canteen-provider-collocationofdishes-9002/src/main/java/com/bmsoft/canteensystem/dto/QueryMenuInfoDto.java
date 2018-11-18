package com.bmsoft.canteensystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author liugaoyang
 * @Date 2018/10/21 09:25
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class QueryMenuInfoDto implements Serializable {
    private String matchMenuTime;
    private Date matchMenuDate;
    private Integer shopId;
    private String shopName;
}
