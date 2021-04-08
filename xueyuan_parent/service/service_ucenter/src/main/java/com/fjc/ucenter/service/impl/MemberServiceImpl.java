package com.fjc.ucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fjc.commonutils.JwtUtils;
import com.fjc.ucenter.entity.Member;
import com.fjc.ucenter.entity.RegisterVo;
import com.fjc.ucenter.mapper.MemberMapper;
import com.fjc.ucenter.service.MemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fjc.ucenter.utils.MD5;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-03-19
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    @Override
    public String login(Member member) {
        //获取登录的手机号和密码
        String mobile = member.getMobile();
        String password = member.getPassword();

        //判断手机号非空
        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)){
            try {
                throw  new Exception("登录失败！");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //判断手机号是否正确
        QueryWrapper<Member> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        Member mobileMember = baseMapper.selectOne(wrapper);
        //判断查询对象是否为空
        if (mobileMember == null){
            try {
                throw new Exception("对象为空 登录失败！");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //判断密码
        //数据库中的密码是加密的 所以要将自己输入的密码先进行加密
        if(!MD5.encrypt(password).equals(mobileMember.getPassword())){
            try {
                throw new Exception("密码错误 登录失败");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //判断用户是否被禁用
        if(mobileMember.getIsDisabled()){
            try {
                throw new Exception("该用户已被禁用");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //登录成功
        //使用jwt生成token字符串
        String jwtToken = JwtUtils.getJwtToken(mobileMember.getId(),mobileMember.getNickname());
        return jwtToken;
    }

    @Override
    public void register(RegisterVo registerVo) {
        //获取注册信息，进行校验
        String nickname = registerVo.getNickname();
        String mobile = registerVo.getMobile();
        String password = registerVo.getPassword();

        //判断注册条件非空
        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password) ||StringUtils.isEmpty(nickname)){
            try {
                throw  new Exception("有注册信息为空 登录失败！");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //校正验证码 因为个人申请不下来先 to Do

        //查重（手机号）
        QueryWrapper<Member> wrapper = new QueryWrapper<>();
        Integer count = baseMapper.selectCount(wrapper);
        if (count.intValue()>0){
            System.out.println("注册信息重复 登录失败");
        }

        //将信息植入数据库
        Member member = new Member();
        member.setNickname(nickname);
        member.setMobile(registerVo.getMobile());
        member.setPassword(MD5.encrypt(password));
        member.setIsDisabled(false);
        member.setAvatar("http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132");
        baseMapper.insert(member);



    }

    @Override
    public Member getByOpenid(String openid) {

        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("openid", openid);

        Member member = baseMapper.selectOne(queryWrapper);
        return member;
    }

    @Override
    public Integer countRegisterByDay(String day) {
        return baseMapper.selectRegisterCount(day);
    }
}
