package com.fjc.edu.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: guli_parent
 * @description: 一级分类的实体类
 * @author: Mr.Fan
 * @create: 2021-02-27 20:15
 **/
@Data
public class OneSubject {

    private String id;

    private String title;

    private List<TwoSubject> children = new ArrayList<>();
}
