package com.tanglin.tangaicodemother.ai.config;

import com.tanglin.tangaicodemother.ai.service.AiCodeGeneratorService;
import com.tanglin.tangaicodemother.ai.config.properties.OpenAiChatModelProperties;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import dev.langchain4j.service.AiServices;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.time.Duration;

/**
 * @program: tang-ai-code-mother
 * @ClassName AiCodeGeneratorServiceFactory
 * @description:
 * @author: TSL
 * @create: 2025-12-30 16:39
 * @Version 1.0
 **/
@Configuration
@EnableConfigurationProperties(OpenAiChatModelProperties.class)
public class AiCodeGeneratorServiceFactory {

    @Bean
    public ChatModel chatModel(OpenAiChatModelProperties properties) {
        OpenAiChatModel.OpenAiChatModelBuilder builder = OpenAiChatModel.builder()
                .apiKey(properties.getApiKey())
                .modelName(properties.getModelName())
                .timeout(Duration.ofSeconds(120));

        if (StringUtils.hasText(properties.getBaseUrl())) {
            builder.baseUrl(properties.getBaseUrl());
        }
        if (properties.getLogRequests() != null) {
            builder.logRequests(properties.getLogRequests());
        }
        if (properties.getLogResponses() != null) {
            builder.logResponses(properties.getLogResponses());
        }
        if (properties.getStrictJsonSchema() != null) {
            builder.strictJsonSchema(properties.getStrictJsonSchema());
        }
        if (properties.getResponseFormat() != null) {
            builder.responseFormat(properties.getResponseFormat());
        }

        return builder.build();
    }

    @Bean
    public StreamingChatModel streamingChatModel(OpenAiChatModelProperties properties) {
        OpenAiStreamingChatModel.OpenAiStreamingChatModelBuilder builder = OpenAiStreamingChatModel.builder()
                .apiKey(properties.getApiKey())
                .modelName(properties.getModelName())
                .timeout(Duration.ofSeconds(120));

        if (StringUtils.hasText(properties.getBaseUrl())) {
            builder.baseUrl(properties.getBaseUrl());
        }
        if (properties.getLogRequests() != null) {
            builder.logRequests(properties.getLogRequests());
        }
        if (properties.getLogResponses() != null) {
            builder.logResponses(properties.getLogResponses());
        }
        if (properties.getStrictJsonSchema() != null) {
            builder.strictJsonSchema(properties.getStrictJsonSchema());
        }
        if (properties.getResponseFormat() != null) {
            builder.responseFormat(properties.getResponseFormat());
        }

        return builder.build();
    }

    @Bean
    public AiCodeGeneratorService aiCodeGeneratorService(ChatModel chatModel,
                                                         StreamingChatModel streamingChatModel) {
        return AiServices.builder(AiCodeGeneratorService.class)
                .chatModel(chatModel)
                .streamingChatModel(streamingChatModel)
                .build();
    }
}
