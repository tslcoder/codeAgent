package com.tanglin.tangaicodemother.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: tang-ai-code-mother
 * @ClassName DeleteRequest
 * @description:
 * @author: TSL
 * @create: 2025-12-29 10:42
 * @Version 1.0
 **/
@Data
public class DeleteRequest implements Serializable {

    /**
     * id
     */
    private Long id;

    private static final long serialVersionUID = 1L;
}
