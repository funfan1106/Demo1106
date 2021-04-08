package com.fjc.edu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fjc.edu.entity.Subject;
import com.fjc.edu.service.SubjectService;
import com.fjc.edu.entity.excel.SubjectData;


/**
 * @program: guli_parent
 * @description: subject的监听器
 * @author: Mr.Fan
 * @create: 2021-02-25 18:44
 **/
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    //因为SubjectExcelListener不能交给spring管理只能new对象，不能进行注入
    //不能实现数据库的操作
    public SubjectService subjectService;

    //有参构造
    public  SubjectExcelListener(SubjectService subjectService){
        this.subjectService = subjectService;
    }


    //一行行的进行读取
    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if (subjectData == null) {
            System.out.println("文件数据为空");
        }

        //判断一级分类是否重复
        Subject existOneSubject = this.existOneSubject(subjectService,subjectData.getOneSubjectName());
        if(existOneSubject==null){//如果等于空没出现过要添加
             existOneSubject = new Subject();
             existOneSubject.setParentId("0");
             existOneSubject.setTitle(subjectData.getOneSubjectName());//一级分类名称
            subjectService.save(existOneSubject);
        }

        //判断二级分类是否重复
        String pid = existOneSubject.getId();

        Subject existTwoSubject = this.existTwoSubject(subjectService,subjectData.getTwoSubjectName(),pid);
        if (existTwoSubject==null){
            existTwoSubject = new Subject();
            existTwoSubject.setParentId(pid);
            existTwoSubject.setTitle(subjectData.getTwoSubjectName());
            subjectService.save(existTwoSubject);
        }
    }
    //一行行读取 每次读取两个值 第一个值是一级分类，第二个值是二级分类
        // 判断一级分类不能重复添加
        private Subject existOneSubject(SubjectService subjectService,String name) {
            QueryWrapper<Subject> wrapper = new QueryWrapper<>();
            wrapper.eq("title",name);
            wrapper.eq("parent_id","0");
            Subject oneSubject = subjectService.getOne(wrapper);
            return oneSubject;
        }
        //判断二级分类不能重复添加
        private Subject existTwoSubject(SubjectService subjectService,String name,String pid) {
            QueryWrapper<Subject> wrapper = new QueryWrapper<>();
            wrapper.eq("title",name);
            wrapper.eq("parent_id",pid);
            Subject twoSubject = subjectService.getOne(wrapper);
            return twoSubject;
        }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
