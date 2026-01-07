package com.tanglin.tangaicodemother.model.dto.user;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: tang-ai-code-mother
 * @ClassName UserRegisterRequest
 * @description:
 * @author: TSL
 * @create: 2025-12-29 14:37
 * @Version 1.0
 **/
@Data
public class UserRegisterRequest implements Serializable {

    private static final long serialVersionUID = 3191241716373120793L;

    /**
     * 账号
     */
    private String userAccount;

    /**
     * 密码
     */
    private String userPassword;

    /**
     * 确认密码
     */
    private String checkPassword;
}

