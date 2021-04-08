package com.fjc.order.service;

import com.fjc.order.entity.TOrder;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-03-27
 */
public interface TOrderService extends IService<TOrder> {

    String saveOrder(String courseId, String memberIdByJwtToken);
}
