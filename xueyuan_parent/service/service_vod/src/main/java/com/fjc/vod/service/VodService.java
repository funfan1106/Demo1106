package com.fjc.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VodService {
    String uploadVideoAly(MultipartFile file);

    void removeVideo(String id);

    //删除多个视频（课程删除中使用）
    void removeMoreVideos(List<String> videoIdList);
}
