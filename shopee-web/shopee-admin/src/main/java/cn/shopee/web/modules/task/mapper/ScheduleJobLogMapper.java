package cn.shopee.web.modules.task.mapper;

import cn.shopee.web.modules.task.entity.ScheduleJobLog;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* All rights Reserved, Designed By www.shopee.cn
*
* @version V1.0
* @package cn.shopee.web.modules.task.mapper
* @title: 任务日志数据库控制层接口
* @description: 任务日志数据库控制层接口
* @author: HuLiang
* @date: 2018-09-17 14:25:18
* @copyright: 2018 www.shopee.cn Inc. All rights reserved.
*/
@Mapper
public interface ScheduleJobLogMapper extends BaseMapper<ScheduleJobLog> {

}