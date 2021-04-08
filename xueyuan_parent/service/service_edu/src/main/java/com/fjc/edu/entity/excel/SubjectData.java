package com.fjc.edu.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @program: guli_parent
 * @description: 课程的Excel的实体类
 * @author: Mr.Fan
 * @create: 2021-02-25 18:23
 **/
@Data
public class SubjectData {

    @ExcelProperty(index = 0)
    private String oneSubjectName;

    @ExcelProperty(index = 1)
    private String twoSubjectName;
}
