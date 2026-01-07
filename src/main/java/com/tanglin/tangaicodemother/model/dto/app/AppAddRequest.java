package com.tanglin.tangaicodemother.model.dto.app;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户创建应用请求
 *
 * @author
 */
@Data
public class AppAddRequest implements Serializable {

    /**
     * 应用名称
     */
    private String appName;

    /**
     * 应用封面
     */
    private String cover;

    /**
     * 初始化 prompt
     */
    private String initPrompt;

    /**
     * 代码生成类型
     */
    private String codeGenType;

    /**
     * 部署标识
     */
    private String deployKey;

    private static final long serialVersionUID = 1L;
}
