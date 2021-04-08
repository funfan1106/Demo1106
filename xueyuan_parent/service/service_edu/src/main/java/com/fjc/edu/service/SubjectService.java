package com.fjc.edu.service;

import com.fjc.edu.entity.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fjc.edu.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-02-26
 */
public interface SubjectService extends IService<Subject> {

    //添加课程分类
    void saveSubject(MultipartFile file, SubjectService subjectService);

    //得到课程分类列表
    List<OneSubject> getAllOneTwoSubject();
}
