package com.tanglin.tangaicodemother.ai.model;

import dev.langchain4j.model.output.structured.Description;
import lombok.Data;

/**
 * @program: tang-ai-code-mother
 * @ClassName MultiFileCodeResult
 * @description:
 * @author: TSL
 * @create: 2025-12-30 17:21
 * @Version 1.0
 **/
@Description("生成多个代码文件的结果")
@Data
public class MultiFileCodeResult {

    @Description("HTML代码")
    private String htmlCode;

    @Description("CSS代码")
    private String cssCode;

    @Description("JS代码")
    private String jsCode;

    @Description("生成代码的描述")
    private String description;
}


