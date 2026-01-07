package com.tanglin.tangaicodemother.ai.model;

import dev.langchain4j.model.output.structured.Description;
import lombok.Data;

/**
 * @program: tang-ai-code-mother
 * @ClassName HtmlCodeResult
 * @description:
 * @author: TSL
 * @create: 2025-12-30 17:21
 * @Version 1.0
 **/
@Description("生成 HTML 代码文件的结果")
@Data
public class HtmlCodeResult {

    @Description("HTML代码")
    private String htmlCode;

    @Description("生成代码的描述")
    private String description;
}
