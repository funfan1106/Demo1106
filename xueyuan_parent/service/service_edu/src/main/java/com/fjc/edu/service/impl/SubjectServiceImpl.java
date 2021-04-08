package com.fjc.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fjc.edu.entity.subject.OneSubject;
import com.fjc.edu.entity.subject.TwoSubject;
import com.fjc.edu.service.SubjectService;
import com.fjc.edu.entity.Subject;
import com.fjc.edu.entity.excel.SubjectData;
import com.fjc.edu.listener.SubjectExcelListener;
import com.fjc.edu.mapper.SubjectMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-02-26
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {
    @Override
    public void saveSubject(MultipartFile file, com.fjc.edu.service.SubjectService subjectService) {
        try {
            //文件输入流
            InputStream in = file.getInputStream();
            //调用方法进行读取
            EasyExcel.read(in, SubjectData.class,new SubjectExcelListener(subjectService)).sheet().doRead();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<OneSubject> getAllOneTwoSubject() {
        //查询所有一级分类
        QueryWrapper<Subject> wrapperOne = new QueryWrapper<>();
        wrapperOne.eq("parent_id","0");

        List<Subject> oneSubjectList = baseMapper.selectList(wrapperOne);
        //查询所有二级分类
        QueryWrapper<Subject> wrapperTwo = new QueryWrapper<>();
        wrapperTwo.ne("parent_id","0");

        List<Subject> twoSubjectList = baseMapper.selectList(wrapperTwo);
        //创建list封装最终数据
        List<OneSubject> finalSubjectList = new ArrayList<>();
        //封装所有一级分类
        for(int i=0;i<oneSubjectList.size();i++){
            Subject subject = oneSubjectList.get(i);
            OneSubject oneSubject = new OneSubject();
            //利用BeanUtils 把前者的值赋值给后者
            BeanUtils.copyProperties(subject,oneSubject);

            finalSubjectList.add(oneSubject);

            //在一级分类下查询出所有的二级分类
            List<TwoSubject> twoFinalSubjectList = new ArrayList<>();
            for (int m=0;m<twoSubjectList.size();m++){
                //获取每个二级分类
                Subject tsubject = twoSubjectList.get(m);
                //判断二级分类的parentid和一级分类的id是否相等
                if(tsubject.getParentId().equals(subject.getId())){
                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(tsubject,twoSubject);
                    twoFinalSubjectList.add(twoSubject);
                }
            }
            //把一级下面的所有二级分类放进一级分类下面
            oneSubject.setChildren(twoFinalSubjectList);
        }

        return finalSubjectList;
    }


}


