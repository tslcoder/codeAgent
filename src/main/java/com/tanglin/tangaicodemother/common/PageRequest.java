package com.tanglin.tangaicodemother.common;

import lombok.Data;

/**
 * @program: tang-ai-code-mother
 * @ClassName PageRequest
 * @description:
 * @author: TSL
 * @create: 2025-12-29 10:42
 * @Version 1.0
 **/
@Data
public class PageRequest {

    /**
     * 当前页号
     */
    private int pageNum = 1;

    /**
     * 页面大小
     */
    private int pageSize = 10;

    /**
     * 排序字段
     */
    private String sortField;

    /**
     * 排序顺序（默认降序）
     */
    private String sortOrder = "descend";
}
