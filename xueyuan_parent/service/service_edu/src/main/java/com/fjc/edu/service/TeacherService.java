package com.fjc.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fjc.edu.entity.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-01-25
 */
//继承的接口 mp已经把方法都封装完毕
public interface TeacherService extends IService<Teacher> {

    Map<String, Object> pageListWeb(Page<Teacher> pageParam);
}
