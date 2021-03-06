package cn.shopee.web.modules.sys.service;

import cn.shopee.common.mybatis.mvc.service.ICommonService;
import cn.shopee.web.modules.sys.entity.UserRole;

/**
 * 
 * All rights Reserved, Designed By www.shopee.cn
 * 
 * @title: IUserRoleService.java
 * @package cn.shopee.web.modules.sys.service
 * @description: 角色
 * @author: HuLiang
 * @date: 2017年7月11日 下午9:21:20
 * @version V1.0
 * @copyright: 2017 www.shopee.cn Inc. All rights reserved.
 *
 */
public interface IUserRoleService extends ICommonService<UserRole> {

    void insert(String uid,String roleCode);

    void insertByRoleId(String uid,String roleId);

    void deleteUserRole(String uid, String roleId);
}
