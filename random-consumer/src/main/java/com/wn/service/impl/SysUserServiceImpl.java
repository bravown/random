package com.wn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wn.entity.SysUser;
import com.wn.mapper.SysUserMapper;
import com.wn.service.mybatisplus.ISysUserService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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

    @Override
    public List<String> getUserNameList(Date date) {
        listObjs();
        return null;
    }
}
