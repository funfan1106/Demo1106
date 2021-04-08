package com.fjc.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fjc.commonutils.R;
import com.fjc.edu.entity.Chapter;
import com.fjc.edu.entity.Video;
import com.fjc.edu.entity.chapter.ChapterVo;
import com.fjc.edu.entity.chapter.VideoVo;
import com.fjc.edu.mapper.ChapterMapper;
import com.fjc.edu.service.ChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fjc.edu.service.VideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-02-28
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {

    @Autowired
    private VideoService videoService;

    @Override
    public List<ChapterVo> getChapterVideoBycourseId(String courseId) {
        //1.根据课程id 查询所有章节
        QueryWrapper<Chapter> chapterQueryWrapper = new QueryWrapper<>();
        chapterQueryWrapper.eq("course_id",courseId);
        List<Chapter> eduChapterList = baseMapper.selectList(chapterQueryWrapper);

        //2.根据课程id 查询所有小节
        QueryWrapper<Video> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("course_id",courseId);
        List<Video> eduVideoList = videoService.list(videoQueryWrapper);

        //创建集合封装所有数据
        List<ChapterVo> finalList = new ArrayList<>();

        //3.遍历查询章节list集合 然后封装
        for(int i=0;i<eduChapterList.size();i++){
            //每个章节
            Chapter chapter = eduChapterList.get(i);
            //把chapter对象放入chaptervo对象中
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(chapter,chapterVo);
            //把chaptervo放入最终集合中
            finalList.add(chapterVo);

            //创建集合用于封装章节的小节
            List<VideoVo> videoList = new ArrayList<>();

            //4.遍历查询小节list集合 然后封装
            for(int m=0;m<eduVideoList.size();m++){
                //得到每个小节
                Video video =eduVideoList.get(m);
                //判断小节里面的chapterid和章节的id是否相等
                if(video.getChapterId().equals(chapter.getId())){
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(video,videoVo);
                    //放到小节封装集合
                    videoList.add(videoVo);
                }
            }
            //把封装后的小节放入章节对象中
            chapterVo.setChildren(videoList);
        }

        return finalList;
    }

    //删除章节
    @Override
    public boolean deleteChapter(String chapterId) {
        QueryWrapper<Video> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id", chapterId);
        int count = videoService.count(wrapper);
        if (count > 0) {
            //查询出小节 不进行删除
            System.out.println("不能删除");
        }
            int result = baseMapper.deleteById(chapterId);
            return result>0;

    }

    @Override
    public void removeChapterByCourseId(String courseId) {
        QueryWrapper<Chapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        baseMapper.delete(wrapper);

    }
}
