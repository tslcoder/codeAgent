//package com.tanglin.tangaicodemother.ai;
//
//import com.tanglin.tangaicodemother.ai.model.HtmlCodeResult;
//import com.tanglin.tangaicodemother.ai.model.MultiFileCodeResult;
//import com.tanglin.tangaicodemother.ai.service.AiCodeGeneratorService;
//import jakarta.annotation.Resource;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
///**
// * @Author: TSL
// * @Date: 2025/12/30 16:45
// * @Param:
// * @Return:
// * @Description:
// **/
//@SpringBootTest
//class AiCodeGeneratorServiceTest {
//
//    @Resource
//    private AiCodeGeneratorService aiCodeGeneratorService;
//
//    @Test
//    void generateHtmlCode() {
//        HtmlCodeResult result = aiCodeGeneratorService.generateHtmlCode("做个程序员鱼皮的工作记录小工具");
//        Assertions.assertNotNull(result);
//    }
//
//    @Test
//    void generateMultiFileCode() {
//        MultiFileCodeResult multiFileCode = aiCodeGeneratorService.generateMultiFileCode("做个程序员鱼皮的留言板");
//        Assertions.assertNotNull(multiFileCode);
//    }
//
//}
