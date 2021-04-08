package com.fjc.ucenter.service;

import com.fjc.ucenter.entity.Member;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fjc.ucenter.entity.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-03-19
 */
public interface MemberService extends IService<Member> {

    String login(Member member);

    void register(RegisterVo registerVo);

    Member getByOpenid(String openid);

    Integer countRegisterByDay(String day);
}
