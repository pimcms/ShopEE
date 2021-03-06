package cn.shopee.web.modules.monitor.service.impl;

import cn.shopee.common.mybatis.mvc.service.impl.CommonServiceImpl;
import cn.shopee.web.modules.monitor.entity.LoginLog;
import cn.shopee.web.modules.monitor.mapper.LoginLogMapper;
import cn.shopee.web.modules.monitor.service.ILoginLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
* All rights Reserved, Designed By www.shopee.cn
*
* @version V1.0
* @package cn.shopee.web.modules.sys.service.impl
* @title: 登陆日志服务实现
* @description: 登陆日志服务实现
* @author: sys
* @date: 2018-09-28 11:31:36
* @copyright: 2018 www.shopee.cn Inc. All rights reserved.
*/
@Transactional
@Service("loginlogService")
public class LoginLogServiceImpl  extends CommonServiceImpl<LoginLogMapper,LoginLog> implements ILoginLogService {

}