package com.tanglin.tangaicodemother.core;

/**
 * @Author: TSL
 * @Date: 2026/1/7 10:45
 * @Param:
 * @Return:
 * @Description:
 **/

public interface CodeParser<T> {

    /**
     * 解析代码内容
     *
     * @param codeContent 原始代码内容
     * @return 解析后的结果对象
     */
    T parseCode(String codeContent);
}
