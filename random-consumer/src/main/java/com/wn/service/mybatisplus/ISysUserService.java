package com.wn.service.mybatisplus;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wn.entity.SysUser;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 王宁
 * @since 2023-03-05
 */
public interface ISysUserService extends IService<SysUser> {
    /**
     * 获取用户名集合,并按笔画排序
     *
     * @param
     * @author 王宁 2023/3/22
     */
    List<String> getUserNameList(Date date);
}
