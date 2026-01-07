package com.tanglin.tangaicodemother.service;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.service.IService;
import com.tanglin.tangaicodemother.model.dto.user.UserQueryRequest;
import com.tanglin.tangaicodemother.model.entity.User;
import com.tanglin.tangaicodemother.model.vo.LoginUserVO;
import com.tanglin.tangaicodemother.model.vo.UserVO;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

/**
 * 用户 服务层。
 *
 * @author tangshilin
 */
public interface UserService extends IService<User> {
    /**
     * 用户注册
     *
     * @param userAccount   用户账户
     * @param userPassword  用户密码
     * @param checkPassword 校验密码
     * @return 新用户 id
     */
    long userRegister(String userAccount, String userPassword, String checkPassword);

    String getEncryptPassword(String userPassword);

    /**
     * 获取脱敏的已登录用户信息
     *
     * @return
     */
    LoginUserVO getLoginUserVO(User user);

    /**
     * 用户登录
     *
     * @param userAccount  用户账户
     * @param userPassword 用户密码
     * @param request
     * @return 脱敏后的用户信息
     */
    LoginUserVO userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * 获取当前登录用户
     *
     * @param request
     * @return
     */
    User getLoginUser(HttpServletRequest request);

    /**
     * 用户注销
     *
     * @param request
     * @return
     */
    boolean userLogout(HttpServletRequest request);

    /**
     * 获取用户信息
     */
    UserVO getUserVO(User user);

    /**
     * 获取用户列表
     */
    List<UserVO> getUserVOList(List<User> userList);

    /**
     * 构造查询为QueryWrapper对象
     */
    QueryWrapper getQueryWrapper(UserQueryRequest userQueryRequest);

}
