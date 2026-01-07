package com.tanglin.tangaicodemother.core;

import com.tanglin.tangaicodemother.ai.enums.CodeGenTypeEnum;
import com.tanglin.tangaicodemother.exception.BusinessException;
import com.tanglin.tangaicodemother.exception.ErrorCode;

/**
 * @program: tang-ai-code-mother
 * @ClassName CodeParserExecutor
 * @description:
 * @author: TSL
 * @create: 2026-01-07 10:45
 * @Version 1.0
 **/

public class CodeParserExecutor {

    private static final HtmlCodeParser htmlCodeParser = new HtmlCodeParser();

    private static final MultiFileCodeParser multiFileCodeParser = new MultiFileCodeParser();

    /**
     * 执行代码解析
     *
     * @param codeContent 代码内容
     * @param codeGenType 代码生成类型
     * @return 解析结果（HtmlCodeResult 或 MultiFileCodeResult）
     */
    public static Object executeParser(String codeContent, CodeGenTypeEnum codeGenType) {
        return switch (codeGenType) {
            case HTML -> htmlCodeParser.parseCode(codeContent);
            case MULTI_FILE -> multiFileCodeParser.parseCode(codeContent);
            default -> throw new BusinessException(ErrorCode.SYSTEM_ERROR, "不支持的代码生成类型: " + codeGenType);
        };
    }
}
