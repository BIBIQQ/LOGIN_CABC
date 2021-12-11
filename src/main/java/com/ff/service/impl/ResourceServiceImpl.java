package com.ff.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ff.mapper.ResourcemMapper;
import com.ff.pojo.Resource;
import com.ff.service.ResourceService;
import org.springframework.stereotype.Service;
import sun.net.ResourceManager;

/**
 * @author FF
 * @date 2021/12/11
 * @TIME:15:44
 */
@Service
public class ResourceServiceImpl  extends ServiceImpl<ResourcemMapper, Resource> implements ResourceService {
}
