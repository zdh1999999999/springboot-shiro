package com.zdh.shiro.controller;

import com.github.pagehelper.PageInfo;
import com.zdh.shiro.model.User;
import com.zdh.shiro.model.UserRole;
import com.zdh.shiro.service.UserRoleService;
import com.zdh.shiro.service.UserService;
import com.zdh.shiro.util.PasswordHelper;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
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
@RestController
@RequestMapping("/users")
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private UserRoleService userRoleService;

    @RequestMapping
    public Map<String,Object> getAll(User user, String draw,
                                     @RequestParam(required = false,defaultValue = "1") int start,
                                     @RequestParam(required = false,defaultValue = "10") int length){
        Map<String,Object> map=new HashMap<>();
        PageInfo<User> pageInfo = userService.selectByPage(user,start,length);
        System.out.println("pageInfo.getTotal():"+pageInfo.getTotal());
        map.put("draw",draw);
        map.put("recordsTotal",pageInfo.getTotal());
        map.put("recordsFiltered",pageInfo.getTotal());
        map.put("data", pageInfo.getList());
        return map;
    }

    /**
     * 添加用户角色
     * @param userRole
     * @return
     */
    @RequestMapping("/saveUserRoles")
    public String saveUserRoles(UserRole userRole){
        if (StringUtils.isEmpty(userRole.getUserid())) return "error";
        try{
            userRoleService.addUserRole(userRole);
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "fail";
        }
    }

    /**
     * 添加用户
     * @param user
     * @return
     */
    @RequestMapping(value = "/add")
    public String add(User user) {
        User u = userService.selectByUsername(user.getUsername());
        if(u != null)
            return "error";
        try {
            user.setEnable(1);
            PasswordHelper passwordHelper = new PasswordHelper();
            passwordHelper.encryptPassword(user);
            userService.save(user);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete")
    public String delete(Integer id){
        try{
            userService.delUser(id);
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "fail";
        }
    }


}
