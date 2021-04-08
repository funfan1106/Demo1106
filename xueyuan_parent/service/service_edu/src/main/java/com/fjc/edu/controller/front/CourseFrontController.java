package com.fjc.edu.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fjc.commonutils.R;
import com.fjc.commonutils.ordervo.CourseWebVoOrder;
import com.fjc.edu.entity.Course;
import com.fjc.edu.entity.Teacher;
import com.fjc.edu.entity.chapter.ChapterVo;
import com.fjc.edu.entity.frontvo.CourseQueryVo;
import com.fjc.edu.entity.frontvo.CourseWebVo;
import com.fjc.edu.service.ChapterService;
import com.fjc.edu.service.CourseService;
import com.fjc.edu.service.TeacherService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @program: guli_parent
 * @description:
 * @author: Mr.Fan
 * @create: 2021-03-25 16:26
 **/
@RestController
@CrossOrigin
@RequestMapping("/edu/courseFront")
public class CourseFrontController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private ChapterService chapterService;

    @ApiOperation(value = "分页课程列表")
    @PostMapping(value = "{page}/{limit}")
    public R pageList(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "courseQuery", value = "查询对象", required = false)
            @RequestBody(required = false) CourseQueryVo courseQuery) {

        Page<Course> pageParam = new Page<Course>(page, limit);
        Map<String, Object> map = courseService.pageListWeb(pageParam, courseQuery);
        return R.ok().data(map);
    }


    @ApiOperation(value = "根据ID查询课程")
    @GetMapping("getById/{courseId}")
    public R getById(
            @ApiParam(name = "courseId", value = "课程ID", required = true)
            @PathVariable String courseId) {
        //查询课程信息和讲师信息
        CourseWebVo courseWebVo = courseService.selectInfoWebById(courseId);
        //查询当前课程的章节信息
        List<ChapterVo> chapterVoList = chapterService.getChapterVideoBycourseId(courseId);
        return R.ok().data("course", courseWebVo).data("chapterVoList", chapterVoList);
    }

    //根据课程id查询课程信息
    @GetMapping("getCourseInfoDto/{courseId}")
    public CourseWebVoOrder getCourseInfoDto(@PathVariable String courseId) {
        CourseWebVo courseInfo1 = courseService.selectInfoWebById(courseId);
        CourseWebVoOrder courseInfo2 = new CourseWebVoOrder();
        BeanUtils.copyProperties(courseInfo1, courseInfo2);
        return courseInfo2;
    }
}
