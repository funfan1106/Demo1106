package com.fjc.edu.service;

import com.fjc.edu.entity.Chapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fjc.edu.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-02-28
 */
public interface ChapterService extends IService<Chapter> {

    List<ChapterVo> getChapterVideoBycourseId(String courseId);

    //删除章节
    boolean deleteChapter(String chapterId);

    void removeChapterByCourseId(String courseId);
}
