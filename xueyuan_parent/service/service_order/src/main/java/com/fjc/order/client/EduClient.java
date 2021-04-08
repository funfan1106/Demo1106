package com.fjc.order.client;

import com.fjc.commonutils.ordervo.CourseWebVoOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient("service-edu")
public interface EduClient {
    //根据课程id查询课程信息
    @GetMapping("/edu/courseFront/getCourseInfoDto/{courseId}")
    public CourseWebVoOrder getCourseInfoDto(@PathVariable String courseId);
}
