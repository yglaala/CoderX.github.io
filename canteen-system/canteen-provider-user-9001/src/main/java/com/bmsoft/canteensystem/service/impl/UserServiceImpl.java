package com.bmsoft.canteensystem.service.impl;
/**
 * USER-PROVIDER   UserServiceImpl层
 * @Author liugaoyang
 */


import com.bmsoft.canteensystem.entity.Emp;
import com.bmsoft.canteensystem.entity.Shop;
import com.bmsoft.canteensystem.entity.User;
import com.bmsoft.canteensystem.mapper.EmpMapper;
import com.bmsoft.canteensystem.mapper.ShopMapper;
import com.bmsoft.canteensystem.mapper.UserMapper;
import com.bmsoft.canteensystem.service.UserService;
import com.bmsoft.canteensystem.util.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private ShopMapper shopMapper;

    private Msg msg;

    /**
     * 注册添加用户
     * @param user 获取用户信息：这里主要获取userPwd 密码
     * @param empNo 员工号
     * @return
     */
    @Override
    public Msg register(User user, String empNo) {
        //判断用户是否存在
        if (userMapper.findByEmpNo(empNo)==null) {
            Emp emp = empMapper.findById(empNo);
            //判断员工部门id是否为1（1是食堂员工，将被设置角色1管理员角色）
            if (emp.getDept().getDeptId() == 1) {
                user.setRoleId(1);
            } else {
                user.setRoleId(2);
            }
            //将员工信息存到user对象中，为存入session准备
            user.setEmp(emp);
            //将用户信息添加到数据库
            userMapper.add(user);
            return Msg.success().setMsg("注册成功");
        }
        return Msg.fail().setMsg("用户已存在");
    }


    /**
     * 登录验证逻辑
     * @param empNo 员工号
     * @param userPwd 密码
     * @return
     */
    @Override
    public Msg login(String empNo,String userPwd) {
        User userInfo = userMapper.cascadingFindByEmpNo(empNo);
        //判断是否用该用户信息
        if (userInfo != null){
            //判断密码是否正确
            if (userPwd.equals(userInfo.getUserPwd())){
                //将一些保密信息去掉
                userInfo.setUserPwd(null);
                userInfo.setEmp(userInfo.getEmp().setEmpNumberId(null).setEmpPicPath(null));
                msg = Msg.success().setMsg("验证成功，正在登录。。。").add("userInfo",userInfo);
                //判断是否有店铺；没有店铺添加标识0、有店铺添加标识1
                Shop userShopInfo =  shopMapper.findByUserId(userInfo.getUserId());
                if (userShopInfo==null){
                    msg.add("isExistShop",0);
                    return msg;
                }
                msg.add("isExistShop",1);
                msg.add("userShopInfo",userShopInfo);
                return msg;
            }
            msg = Msg.fail().setMsg("密码错误");
            return msg;
        }
        msg = Msg.fail().setMsg("没有该用户，请先注册");
        return msg;
    }


}
