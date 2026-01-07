package com.tanglin.tangaicodemother.model.dto.user;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: tang-ai-code-mother
 * @ClassName UserLoginRequest
 * @description:
 * @author: TSL
 * @create: 2025-12-29 15:18
 * @Version 1.0
 **/
@Data
public class UserLoginRequest implements Serializable {

    private static final long serialVersionUID = 3191241716373120793L;

    /**
     * 账号
     */
    private String userAccount;

    /**
     * 密码
     */
    private String userPassword;
}
