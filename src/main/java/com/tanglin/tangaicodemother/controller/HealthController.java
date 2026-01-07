package com.tanglin.tangaicodemother.controller;

import com.tanglin.tangaicodemother.common.BaseResponse;
import com.tanglin.tangaicodemother.common.ResultUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: tang-ai-code-mother
 * @ClassName HealthController
 * @description:
 * @author: TSL
 * @create: 2025-12-29 10:34
 * @Version 1.0
 **/
@RequestMapping("/health")
@RestController
public class HealthController {

    @GetMapping("/")
    public BaseResponse<String> healthCheck() {
        return ResultUtils.success("I'm OK!");
    }

}
