package com.tanglin.tangaicodemother.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.mybatisflex.core.paginate.Page;
import com.tanglin.tangaicodemother.annotation.AuthCheck;
import com.tanglin.tangaicodemother.common.BaseResponse;
import com.tanglin.tangaicodemother.common.DeleteRequest;
import com.tanglin.tangaicodemother.common.ResultUtils;
import com.tanglin.tangaicodemother.constant.UserConstant;
import com.tanglin.tangaicodemother.exception.ErrorCode;
import com.tanglin.tangaicodemother.exception.ThrowUtils;
import com.tanglin.tangaicodemother.model.dto.app.AppAddRequest;
import com.tanglin.tangaicodemother.model.dto.app.AppAdminQueryRequest;
import com.tanglin.tangaicodemother.model.dto.app.AppAdminUpdateRequest;
import com.tanglin.tangaicodemother.model.dto.app.AppFeaturedQueryRequest;
import com.tanglin.tangaicodemother.model.dto.app.AppMyQueryRequest;
import com.tanglin.tangaicodemother.model.dto.app.AppUpdateMyRequest;
import com.tanglin.tangaicodemother.model.entity.User;
import com.tanglin.tangaicodemother.model.vo.AppVO;
import com.tanglin.tangaicodemother.service.AppService;
import com.tanglin.tangaicodemother.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * 应用 控制层。
 *
 * @author
 */
@RestController
@RequestMapping("/app")
public class AppController {

    @Resource
    private AppService appService;

    @Resource
    private UserService userService;

    /**
     * 用户创建应用
     */
    @PostMapping("/add")
    public BaseResponse<Long> addApp(@RequestBody AppAddRequest appAddRequest, HttpServletRequest request) {
        ThrowUtils.throwIf(appAddRequest == null, ErrorCode.PARAMS_ERROR);
        User loginUser = userService.getLoginUser(request);
        Long appId = appService.createApp(appAddRequest, loginUser);
        return ResultUtils.success(appId);
    }

    /**
     * 用户更新应用（仅支持更新名称）
     */
    @PostMapping("/my/update")
    public BaseResponse<Boolean> updateMyApp(@RequestBody AppUpdateMyRequest updateMyRequest, HttpServletRequest request) {
        ThrowUtils.throwIf(updateMyRequest == null, ErrorCode.PARAMS_ERROR);
        User loginUser = userService.getLoginUser(request);
        boolean result = appService.updateMyApp(updateMyRequest, loginUser);
        return ResultUtils.success(result);
    }

    /**
     * 用户删除自己的应用
     */
    @PostMapping("/my/delete")
    public BaseResponse<Boolean> deleteMyApp(@RequestBody DeleteRequest deleteRequest, HttpServletRequest request) {
        ThrowUtils.throwIf(deleteRequest == null || deleteRequest.getId() == null, ErrorCode.PARAMS_ERROR);
        User loginUser = userService.getLoginUser(request);
        boolean result = appService.deleteMyApp(deleteRequest.getId(), loginUser);
        return ResultUtils.success(result);
    }

    /**
     * 用户根据 id 查看应用详情
     */
    @GetMapping("/my/get")
    public BaseResponse<AppVO> getMyApp(@RequestParam("id") Long id, HttpServletRequest request) {
        ThrowUtils.throwIf(id == null || id <= 0, ErrorCode.PARAMS_ERROR);
        User loginUser = userService.getLoginUser(request);
        AppVO appVO = appService.getMyAppVO(id, loginUser);
        return ResultUtils.success(appVO);
    }

    /**
     * 用户分页查询自己的应用
     */
    @PostMapping("/my/list/page")
    public BaseResponse<Page<AppVO>> listMyAppByPage(@RequestBody AppMyQueryRequest queryRequest, HttpServletRequest request) {
        ThrowUtils.throwIf(queryRequest == null, ErrorCode.PARAMS_ERROR);
        User loginUser = userService.getLoginUser(request);
        Page<AppVO> page = appService.listMyAppVOByPage(queryRequest, loginUser);
        return ResultUtils.success(page);
    }

    /**
     * 查询精选应用
     */
    @PostMapping("/featured/list/page")
    public BaseResponse<Page<AppVO>> listFeaturedAppByPage(@RequestBody AppFeaturedQueryRequest queryRequest) {
        ThrowUtils.throwIf(queryRequest == null, ErrorCode.PARAMS_ERROR);
        Page<AppVO> page = appService.listFeaturedAppVOByPage(queryRequest);
        return ResultUtils.success(page);
    }

    /**
     * 管理员删除应用
     */
    @PostMapping("/admin/delete")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> adminDeleteApp(@RequestBody DeleteRequest deleteRequest) {
        ThrowUtils.throwIf(deleteRequest == null || deleteRequest.getId() == null, ErrorCode.PARAMS_ERROR);
        boolean result = appService.adminDeleteApp(deleteRequest.getId());
        return ResultUtils.success(result);
    }

    /**
     * 管理员更新应用
     */
    @PostMapping("/admin/update")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> adminUpdateApp(@RequestBody AppAdminUpdateRequest updateRequest) {
        ThrowUtils.throwIf(updateRequest == null, ErrorCode.PARAMS_ERROR);
        boolean result = appService.adminUpdateApp(updateRequest);
        return ResultUtils.success(result);
    }

    /**
     * 管理员查看应用详情
     */
    @GetMapping("/admin/get")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<AppVO> adminGetApp(@RequestParam("id") Long id) {
        ThrowUtils.throwIf(id == null || id <= 0, ErrorCode.PARAMS_ERROR);
        AppVO appVO = appService.getAppVOById(id);
        return ResultUtils.success(appVO);
    }

    /**
     * 管理员分页查询应用
     */
    @PostMapping("/admin/list/page")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Page<AppVO>> adminListAppByPage(@RequestBody AppAdminQueryRequest queryRequest) {
        ThrowUtils.throwIf(queryRequest == null, ErrorCode.PARAMS_ERROR);
        Page<AppVO> page = appService.listAdminAppVOByPage(queryRequest);
        return ResultUtils.success(page);
    }

    /**
     * 应用聊天生成代码（流式 SSE）
     *
     * @param appId   应用 ID
     * @param message 用户消息
     * @param request 请求对象
     * @return 生成结果流
     */
    @GetMapping(value = "/chat/gen/code", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<String>> chatToGenCode(@RequestParam Long appId,
                                                       @RequestParam String message,
                                                       HttpServletRequest request) {
        // 参数校验
        ThrowUtils.throwIf(appId == null || appId <= 0, ErrorCode.PARAMS_ERROR, "应用ID无效");
        ThrowUtils.throwIf(StrUtil.isBlank(message), ErrorCode.PARAMS_ERROR, "用户消息不能为空");
        // 获取当前登录用户
        User loginUser = userService.getLoginUser(request);
        // 调用服务生成代码（流式）
        Flux<String> contentFlux = appService.chatToGenCode(appId, message, loginUser);
        // 转换为 ServerSentEvent 格式
        return contentFlux
                .map(chunk -> {
                    // 将内容包装成JSON对象
                    Map<String, String> wrapper = Map.of("d", chunk);
                    String jsonData = JSONUtil.toJsonStr(wrapper);
                    return ServerSentEvent.<String>builder()
                            .data(jsonData)
                            .build();
                })
                .concatWith(Mono.just(
                        // 发送结束事件
                        ServerSentEvent.<String>builder()
                                .event("done")
                                .data("")
                                .build()
                ));
    }



}
