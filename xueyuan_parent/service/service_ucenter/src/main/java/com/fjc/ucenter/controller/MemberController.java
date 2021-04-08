package com.fjc.ucenter.controller;


import com.fjc.commonutils.JwtUtils;
import com.fjc.commonutils.R;
import com.fjc.commonutils.ordervo.MemberOrder;
import com.fjc.ucenter.entity.Member;
import com.fjc.ucenter.entity.RegisterVo;
import com.fjc.ucenter.service.MemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-03-19
 */
@RestController
@RequestMapping("/ucenter/member")
@CrossOrigin
public class MemberController {

    @Autowired
    private MemberService  memberService;

    //登录
    @PostMapping("login")
    public R loginUser(@RequestBody Member member){
        //返回token值 使用jwt生成
        String token = memberService.login(member);
        return R.ok().data("token",token);
    }

    //注册
    @PostMapping("register")
    public R register(@RequestBody RegisterVo registerVo){
        memberService.register(registerVo);
        return R.ok();
    }

    //通过token得到用户信息
    @GetMapping("getMemberInfo")
    public R getMemberInfo(HttpServletRequest request){
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        Member member = memberService.getById(memberId);

        return R.ok().data("userInfo",member);
    }

    //根据用户id获取用户信息
    @PostMapping("getInfoUc/{id}")
    public MemberOrder getInfoUc(@PathVariable String id){
        Member member = memberService.getById(id);
        MemberOrder memberOrder = new MemberOrder();
        BeanUtils.copyProperties(member,memberOrder);
        return memberOrder;
    }

    //统计该时间内的登陆人数
    @GetMapping(value = "countregister/{day}")
    public R registerCount(@PathVariable String day){
        Integer count = memberService.countRegisterByDay(day);
        return R.ok().data("countRegister", count);
    }



}

