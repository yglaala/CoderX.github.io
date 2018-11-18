package com.bmsoft.canteensystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class EmpConsumeDto {

    private String deptName;
    private String empNo;
    private String empName;
    private Float empTotalPrice;
}
