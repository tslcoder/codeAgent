package com.tanglin.tangaicodemother.ai.service;

import com.tanglin.tangaicodemother.ai.model.HtmlCodeResult;
import com.tanglin.tangaicodemother.ai.model.MultiFileCodeResult;
import dev.langchain4j.service.SystemMessage;
import reactor.core.publisher.Flux;

/**
 * @Author: TSL
 * @Date: 2025/12/30 09:52
 * @Param:
 * @Return:
 * @Description:
 **/
public interface AiCodeGeneratorService {

    /**
     * 生成 HTML 代码（流式）
     *
     * @param userMessage 用户消息
     * @return 生成的代码结果
     */
    @SystemMessage(fromResource = "prompt/codegen-html-system-prompt.txt")
    Flux<String> generateHtmlCodeStream(String userMessage);

    /**
     * 生成多文件代码（流式）
     *
     * @param userMessage 用户消息
     * @return 生成的代码结果
     */
    @SystemMessage(fromResource = "prompt/codegen-multi-file-system-prompt.txt")
    Flux<String> generateMultiFileCodeStream(String userMessage);


}

