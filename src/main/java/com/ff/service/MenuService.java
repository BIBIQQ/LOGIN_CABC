package com.ff.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ff.pojo.Menu;

import java.util.List;
import java.util.Map;

public interface MenuService extends IService<Menu> {


    List<Map> findMenusByLoginName(String name);
}
