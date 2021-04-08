package com.fjc.edu.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fjc.commonutils.R;
import com.fjc.edu.entity.Course;
import com.fjc.edu.entity.Teacher;
import com.fjc.edu.service.CourseService;
import com.fjc.edu.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: guli_parent
 * @description: 前台页面显示
 * @author: Mr.Fan
 * @create: 2021-03-16 19:16
 **/
@RestController
@RequestMapping("/edu/bannerfront")
@CrossOrigin
public class IndexFrontController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private TeacherService teacherService;

    //查询前八条课程 和前四条讲师
    @GetMapping("index")
    public R index(){
        //查询前八条课程
        QueryWrapper<Course> courseQueryWrapper = new QueryWrapper<>();
        courseQueryWrapper.orderByDesc("id");
        courseQueryWrapper.last("limit 8");

        List<Course> courseList = courseService.list(courseQueryWrapper);
        //查询前四条讲师
        QueryWrapper<Teacher> teacherQueryWrapper = new QueryWrapper<>();
        teacherQueryWrapper.orderByDesc("id");
        teacherQueryWrapper.last("limit 4");

        List<Teacher> teacherList = teacherService.list(teacherQueryWrapper);

        return R.ok().data("courseList",courseList).data("teacherList",teacherList);

    }


}
