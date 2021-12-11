package com.ff.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ff.mapper.MenuMapper;
import com.ff.pojo.Menu;
import com.ff.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper,Menu> implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Map> findMenusByLoginName(String name) {

        List<Menu> list =  menuMapper.findMenusByLoginName(name);

        List<Map> menuListByParentId = findMenuListByParentId(list, "0");

        return menuListByParentId;
    }

    /**
     * 查询下级菜单
     * @param menuList
     * @param parentId
     * @return
     */
    private List<Map> findMenuListByParentId(List<Menu>  menuList,String parentId ){
        List<Map> mapList=new ArrayList<Map>();
        for( Menu menu:menuList  ){
            if(menu.getParentId().equals(parentId) ){
                Map map=new HashMap();
                map.put("path",menu.getId());
                map.put("title",menu.getName());
                map.put("icon",menu.getIcon());
                map.put("linkUrl",menu.getUrl());
                map.put("children", findMenuListByParentId(menuList,menu.getId())   );
                mapList.add(map);
            }
        }
        return mapList;
    }
}
