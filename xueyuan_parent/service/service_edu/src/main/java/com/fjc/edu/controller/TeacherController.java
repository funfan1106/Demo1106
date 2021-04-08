package com.fjc.edu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fjc.commonutils.R;
import com.fjc.edu.entity.Teacher;
import com.fjc.edu.entity.vo.TeacherQuery;
import com.fjc.edu.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-01-25
 */
@Api(description="讲师管理")
@RestController //是@ResponseBody：负责返回json数据 @Controller交给spring去管理 两者合体
@RequestMapping("/edu/teacher")//最后访问路径
@CrossOrigin //解决跨域问题
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    //查询所有
    @ApiOperation(value = "查询所有讲师列表")
    @GetMapping("findAll")
    public R findAll(){
        //调用service的方法
        List<Teacher> list = teacherService.list(null);
        return R.ok().data("items",list);
    }

    //逻辑删除讲师
    @ApiOperation(value = "逻辑删除讲师")
    @DeleteMapping("{id}")
    public R removeTeacher(@ApiParam(name = "id", value = "讲师ID", required = true) @PathVariable String id){
        boolean flag =teacherService.removeById(id);
        if(flag){
            return R.ok();
        }else {
            return R.error();
        }
    }

    //分页查询讲师
    @ApiOperation(value = "分页查询讲师")
    @GetMapping("pageTeacher/{current}/{limit}")
    public R pageListTeacher(@ApiParam(name = "current",value = "当前页",required = true)
                             @PathVariable long current,
                             @ApiParam(name = "limit", value = "每页记录数", required = true)
                             @PathVariable long limit){
        //创建page对象
        Page<Teacher> pageTeacher = new Page<>(current,limit);
        //调用方法实现
        teacherService.page(pageTeacher,null);
        //获取list集合
        List<Teacher> records = pageTeacher.getRecords();
        //获取记录数
        long total = pageTeacher.getTotal();

        return R.ok().data("total",total).data("records",records);

    }

    //分页条件查询
    @ApiOperation(value = "条件分页查询")
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public R pageTeacherCondition(@ApiParam(name = "current",value = "当前页",required = true)
                                      @PathVariable long current,
                                  @ApiParam(name = "limit", value = "每页记录数", required = true)
                                      @PathVariable long limit,
                                  @RequestBody(required = false)TeacherQuery teacherQuery){
        //创建page对象
        Page<Teacher> pageTeacher = new Page<>(current,limit);
        //构建条件 判断条件是否为空 如果不为空则是拼接条件
        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
        String name = teacherQuery.getName();
        /*Integer level = teacherQuery.getLevel();*/
        //要Integer转一下String 满足isEmpty方法
        //String leveal1 = level.toString();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        
        if(!StringUtils.isEmpty(name) ){
            wrapper.like("name",name);
        }
/*        if (!StringUtils.isEmpty(leveal) ) {
            wrapper.eq("level", level);
        }*/
        if(!StringUtils.isEmpty(begin)){
            wrapper.ge("gmt_create",begin);
        }
        if(!StringUtils.isEmpty(end)){
            wrapper.le("gmt_create",end);
        }
        //排序
        wrapper.orderByDesc("gmt_create");


        //调用方法实现
        teacherService.page(pageTeacher,wrapper);
        //获取记录数
        long total = pageTeacher.getTotal();
        //获取list集合
        List<Teacher> records = pageTeacher.getRecords();

        return R.ok().data("total",total).data("records",records);

    }

    //添加讲师
    @ApiOperation(value = "添加讲师")
    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody Teacher teacher){
        boolean save = teacherService.save(teacher);
        if(save){
            return R.ok();
        } else {
            return R.error();
        }
    }

    //根据讲师ID查询
    @ApiOperation(value = "根据讲师ID查询")
    @GetMapping("getTeacher/{id}")
    public R getTeacher(@PathVariable String id){
        Teacher teacher = teacherService.getById(id);
        return R.ok().data("teacher",teacher);
    }

    //修改讲师
    @ApiOperation(value = "修改讲师")
    @PostMapping("updateTeacher")
    public R updateTeacher(@RequestBody Teacher teacher){
        boolean flag = teacherService.updateById(teacher);
        if(flag) {
            return R.ok();
        }else {
            return R.error();
        }
    }
}

