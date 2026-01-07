package com.tanglin.tangaicodemother.ai.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration binding for LangChain4j OpenAI chat model.
 */
@ConfigurationProperties(prefix = "langchain4j.open-ai.chat-model")
public class OpenAiChatModelProperties {

    private String baseUrl;
    private String apiKey;
    private String modelName;
    private Boolean logRequests;
    private Boolean logResponses;
    private Boolean strictJsonSchema;
    private String responseFormat;

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public Boolean getLogRequests() {
        return logRequests;
    }

    public void setLogRequests(Boolean logRequests) {
        this.logRequests = logRequests;
    }

    public Boolean getLogResponses() {
        return logResponses;
    }

    public void setLogResponses(Boolean logResponses) {
        this.logResponses = logResponses;
    }

    public Boolean getStrictJsonSchema() {
        return strictJsonSchema;
    }

    public void setStrictJsonSchema(Boolean strictJsonSchema) {
        this.strictJsonSchema = strictJsonSchema;
    }

    public String getResponseFormat() {
        return responseFormat;
    }

    public void setResponseFormat(String responseFormat) {
        this.responseFormat = responseFormat;
    }

}
