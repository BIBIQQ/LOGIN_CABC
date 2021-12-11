package com.ff.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ff.pojo.Resource;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author FF
 * @date 2021/12/11
 * @TIME:15:43
 */
public interface ResourcemMapper  extends BaseMapper<Resource> {

    @Select("SELECT * FROM tb_resource WHERE id in (SELECT resource_id FROM tb_role_resource WHERE role_id in " +
            "( SELECT role_id FROM tb_admin_role WHERE admin_id = ( SELECT id FROM tb_admin WHERE login_name =#{userName})))")
    List<Resource> findResourceByName(String userName);
}
