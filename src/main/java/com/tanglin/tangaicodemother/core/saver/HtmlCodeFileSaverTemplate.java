package com.tanglin.tangaicodemother.core.saver;

/**
 * @program: tang-ai-code-mother
 * @ClassName HtmlCodeFileSaverTemplate
 * @description:
 * @author: TSL
 * @create: 2026-01-09 14:35
 * @Version 1.0
 **/

import cn.hutool.core.util.StrUtil;
import com.tanglin.tangaicodemother.ai.enums.CodeGenTypeEnum;
import com.tanglin.tangaicodemother.ai.model.HtmlCodeResult;
import com.tanglin.tangaicodemother.exception.BusinessException;
import com.tanglin.tangaicodemother.exception.ErrorCode;

/**
 * HTML代码文件保存器
 *
 * @author yupi
 */
public class HtmlCodeFileSaverTemplate extends CodeFileSaverTemplate<HtmlCodeResult> {

    @Override
    protected CodeGenTypeEnum getCodeType() {
        return CodeGenTypeEnum.HTML;
    }

    @Override
    protected void saveFiles(HtmlCodeResult result, String baseDirPath) {
        // 保存 HTML 文件
        writeToFile(baseDirPath, "index.html", result.getHtmlCode());
    }

    @Override
    protected void validateInput(HtmlCodeResult result) {
        super.validateInput(result);
        // HTML 代码不能为空
        if (StrUtil.isBlank(result.getHtmlCode())) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "HTML代码内容不能为空");
        }
    }
}

