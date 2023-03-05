package com.wn.service.impl;

import com.wn.entity.SysUser;
import com.wn.mapper.SysUserMapper;
import com.wn.service.mybatisplus.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 王宁
 * @since 2023-03-05
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

}
