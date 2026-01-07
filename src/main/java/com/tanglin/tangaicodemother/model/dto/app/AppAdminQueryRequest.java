package com.tanglin.tangaicodemother.model.dto.app;

import com.tanglin.tangaicodemother.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 管理员应用查询请求
 *
 * @author
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AppAdminQueryRequest extends PageRequest implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 应用名称
     */
    private String appName;

    /**
     * 封面
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

    /**
     * 优先级
     */
    private Integer priority;

    /**
     * 创建人
     */
    private Long userId;

    /**
     * 删除标识
     */
    private Integer isDelete;

    private static final long serialVersionUID = 1L;
}
