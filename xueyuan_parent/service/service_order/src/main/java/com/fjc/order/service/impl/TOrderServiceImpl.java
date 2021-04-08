package com.fjc.order.service.impl;

import com.fjc.commonutils.ordervo.CourseWebVoOrder;
import com.fjc.commonutils.ordervo.MemberOrder;
import com.fjc.order.client.EduClient;
import com.fjc.order.client.MemberClient;
import com.fjc.order.entity.TOrder;
import com.fjc.order.mapper.TOrderMapper;
import com.fjc.order.service.TOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fjc.order.utils.OrderNoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-03-27
 */
@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements TOrderService {

    @Autowired
    private EduClient eduClient;

    @Autowired
    private MemberClient memberClient;

    @Override
    public String saveOrder(String courseId, String memberIdByJwtToken) {

        //远程调用课程服务，根据用户id获取用户信息
        MemberOrder memberOrder = memberClient.getInfoUc(memberIdByJwtToken);

        //远程调用课程服务，根据课程id获取课程信息
        CourseWebVoOrder courseWebVoOrder = eduClient.getCourseInfoDto(courseId);

        //创建order对象 想order对象里面设置值
        TOrder order = new TOrder();
        order.setOrderNo(OrderNoUtil.getOrderNo());
        order.setCourseId(courseId);
        order.setCourseTitle(courseWebVoOrder.getTitle());
        order.setCourseCover(courseWebVoOrder.getCover());
        order.setTeacherName(courseWebVoOrder.getTeacherName());
        order.setTotalFee(courseWebVoOrder.getPrice());
        order.setMemberId(memberIdByJwtToken);
        order.setMobile(memberOrder.getMobile());
        order.setNickname(memberOrder.getNickname());
        order.setStatus(0);
        order.setPayType(1);

        baseMapper.insert(order);

        return order.getOrderNo();
    }
}
