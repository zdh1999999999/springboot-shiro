package com.zdh.shiro.mapper;

import com.zdh.shiro.model.Resources;
import com.zdh.shiro.util.MyMapper;

import java.util.List;
import java.util.Map;

/**
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┃
 * 　　　　┃　　　┃
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * 神兽保佑
 * author:zdh
 * date:2018/3/23
 */
public interface ResourcesMapper extends MyMapper<Resources> {
    List<Resources> queryAll();

    List<Resources> loadUserResources(Map<String,Object> map);

    List<Resources> queryResourcesListWithSelected(Integer rid);
}
