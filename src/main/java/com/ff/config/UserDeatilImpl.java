package com.ff.config;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ff.mapper.ResourcemMapper;
import com.ff.pojo.Admin;
import com.ff.pojo.Resource;
import com.ff.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author FF
 * @date 2021/12/11
 * @TIME:14:11
 */
@Component
public class UserDeatilImpl implements UserDetailsService {

    @Autowired
    private AdminService adminService;

    @Autowired
    private ResourcemMapper resourcemMapper;
    //内置的login登录方法
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //1.通过用户名查询用户对象
        Admin admin = adminService.getOne(Wrappers.<Admin>lambdaQuery().eq(Admin::getLoginName, username));
        // 判断
        if(admin == null){
            throw  new RuntimeException("该用户不存在。。。。");
        }
        // 2. 把容器交给容器自己取判断能否登录
        /**
         * 参数1：登录得用户名
         * 参数2：数据库得用户密
         * 参数3：该用户得权限集合
         * 告诉security使用的什么加密方式
         */
        ArrayList<SimpleGrantedAuthority> list = new ArrayList<>();


        List<Resource> resourceByName = resourcemMapper.findResourceByName(username);
        for (Resource resource : resourceByName) {
            list.add(new SimpleGrantedAuthority(resource.getResKey()));
        }

        User user = new User(admin.getLoginName(), admin.getPassword(), list);
        return user;
    }
}
