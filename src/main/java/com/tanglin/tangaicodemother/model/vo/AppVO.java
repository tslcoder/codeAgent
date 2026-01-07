package com.tanglin.tangaicodemother.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 应用展示对象
 *
 * @author
 */
@Data
public class AppVO implements Serializable {

    private Long id;

    private String appName;

    private String cover;

    private String initPrompt;

    private String codeGenType;

    private String deployKey;

    private LocalDateTime deployedTime;

    private Integer priority;

    private Long userId;

    private LocalDateTime editTime;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;
}
