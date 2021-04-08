package com.fjc.edu.entity.chapter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: guli_parent
 * @description:
 * @author: Mr.Fan
 * @create: 2021-03-01 17:15
 **/
@Data
public class ChapterVo {

    private String id;
    private String title;

    private List<VideoVo> children = new ArrayList<>();
}
