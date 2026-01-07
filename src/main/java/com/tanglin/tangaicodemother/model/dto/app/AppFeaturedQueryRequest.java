package com.tanglin.tangaicodemother.model.dto.app;

import com.tanglin.tangaicodemother.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 精选应用分页查询请求
 *
 * @author
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AppFeaturedQueryRequest extends PageRequest implements Serializable {

    /**
     * 应用名称（模糊）
     */
    private String appName;

    private static final long serialVersionUID = 1L;
}
