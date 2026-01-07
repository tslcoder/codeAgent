package com.tanglin.tangaicodemother.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.tanglin.tangaicodemother.constant.AppConstant;
import com.tanglin.tangaicodemother.exception.BusinessException;
import com.tanglin.tangaicodemother.exception.ErrorCode;
import com.tanglin.tangaicodemother.exception.ThrowUtils;
import com.tanglin.tangaicodemother.mapper.AppMapper;
import com.tanglin.tangaicodemother.model.dto.app.AppAddRequest;
import com.tanglin.tangaicodemother.model.dto.app.AppAdminQueryRequest;
import com.tanglin.tangaicodemother.model.dto.app.AppAdminUpdateRequest;
import com.tanglin.tangaicodemother.model.dto.app.AppFeaturedQueryRequest;
import com.tanglin.tangaicodemother.model.dto.app.AppMyQueryRequest;
import com.tanglin.tangaicodemother.model.dto.app.AppUpdateMyRequest;
import com.tanglin.tangaicodemother.model.entity.App;
import com.tanglin.tangaicodemother.model.entity.User;
import com.tanglin.tangaicodemother.model.vo.AppVO;
import com.tanglin.tangaicodemother.service.AppService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 应用 服务层实现。
 *
 * @author tangshilin
 */
@Service
public class AppServiceImpl extends ServiceImpl<AppMapper, App> implements AppService {

    private static final Set<String> ADMIN_SORT_FIELDS = Set.of("id", "appName", "cover", "initPrompt",
            "codeGenType", "deployKey", "priority", "userId", "editTime", "createTime", "updateTime");

    @Override
    public Long createApp(AppAddRequest appAddRequest, User loginUser) {
        ThrowUtils.throwIf(appAddRequest == null, ErrorCode.PARAMS_ERROR);
        ThrowUtils.throwIf(loginUser == null || loginUser.getId() == null, ErrorCode.NOT_LOGIN_ERROR);
        String appName = appAddRequest.getAppName();
        String initPrompt = appAddRequest.getInitPrompt();
        ThrowUtils.throwIf(StrUtil.isBlank(appName), ErrorCode.PARAMS_ERROR, "应用名称不能为空");
        ThrowUtils.throwIf(StrUtil.isBlank(initPrompt), ErrorCode.PARAMS_ERROR, "initPrompt 不能为空");
        App app = new App();
        BeanUtil.copyProperties(appAddRequest, app);
        app.setUserId(loginUser.getId());
        app.setPriority(app.getPriority() == null ? 0 : app.getPriority());
        app.setEditTime(LocalDateTime.now());
        boolean result = this.save(app);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return app.getId();
    }

    @Override
    public boolean updateMyApp(AppUpdateMyRequest updateRequest, User loginUser) {
        ThrowUtils.throwIf(updateRequest == null || updateRequest.getId() == null, ErrorCode.PARAMS_ERROR);
        ThrowUtils.throwIf(loginUser == null || loginUser.getId() == null, ErrorCode.NOT_LOGIN_ERROR);
        String appName = updateRequest.getAppName();
        ThrowUtils.throwIf(StrUtil.isBlank(appName), ErrorCode.PARAMS_ERROR, "应用名称不能为空");
        App dbApp = this.getById(updateRequest.getId());
        ThrowUtils.throwIf(dbApp == null, ErrorCode.NOT_FOUND_ERROR);
        ThrowUtils.throwIf(!loginUser.getId().equals(dbApp.getUserId()), ErrorCode.NO_AUTH_ERROR);
        App updateEntity = new App();
        updateEntity.setId(updateRequest.getId());
        updateEntity.setAppName(appName);
        updateEntity.setEditTime(LocalDateTime.now());
        boolean result = this.updateById(updateEntity);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return true;
    }

    @Override
    public boolean deleteMyApp(Long appId, User loginUser) {
        ThrowUtils.throwIf(appId == null || appId <= 0, ErrorCode.PARAMS_ERROR);
        ThrowUtils.throwIf(loginUser == null || loginUser.getId() == null, ErrorCode.NOT_LOGIN_ERROR);
        App dbApp = this.getById(appId);
        ThrowUtils.throwIf(dbApp == null, ErrorCode.NOT_FOUND_ERROR);
        ThrowUtils.throwIf(!loginUser.getId().equals(dbApp.getUserId()), ErrorCode.NO_AUTH_ERROR);
        boolean result = this.removeById(appId);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return true;
    }

    @Override
    public AppVO getMyAppVO(Long appId, User loginUser) {
        ThrowUtils.throwIf(appId == null || appId <= 0, ErrorCode.PARAMS_ERROR);
        ThrowUtils.throwIf(loginUser == null || loginUser.getId() == null, ErrorCode.NOT_LOGIN_ERROR);
        App dbApp = this.getById(appId);
        ThrowUtils.throwIf(dbApp == null, ErrorCode.NOT_FOUND_ERROR);
        ThrowUtils.throwIf(!loginUser.getId().equals(dbApp.getUserId()), ErrorCode.NO_AUTH_ERROR);
        return convertToAppVO(dbApp);
    }

    @Override
    public Page<AppVO> listMyAppVOByPage(AppMyQueryRequest queryRequest, User loginUser) {
        ThrowUtils.throwIf(queryRequest == null, ErrorCode.PARAMS_ERROR);
        ThrowUtils.throwIf(loginUser == null || loginUser.getId() == null, ErrorCode.NOT_LOGIN_ERROR);
        long pageNum = queryRequest.getPageNum();
        if (pageNum <= 0) {
            pageNum = 1;
        }
        long pageSize = queryRequest.getPageSize();
        if (pageSize > AppConstant.MAX_PAGE_SIZE) {
            pageSize = AppConstant.MAX_PAGE_SIZE;
        }
        if (pageSize <= 0) {
            pageSize = AppConstant.MAX_PAGE_SIZE;
        }
        QueryWrapper queryWrapper = QueryWrapper.create()
                .eq("userId", loginUser.getId())
                .like("appName", queryRequest.getAppName())
                .orderBy("updateTime", false);
        Page<App> appPage = this.page(Page.of(pageNum, pageSize), queryWrapper);
        return buildAppVOPage(appPage);
    }

    @Override
    public Page<AppVO> listFeaturedAppVOByPage(AppFeaturedQueryRequest queryRequest) {
        ThrowUtils.throwIf(queryRequest == null, ErrorCode.PARAMS_ERROR);
        long pageNum = queryRequest.getPageNum();
        if (pageNum <= 0) {
            pageNum = 1;
        }
        long pageSize = queryRequest.getPageSize();
        if (pageSize > AppConstant.MAX_PAGE_SIZE || pageSize <= 0) {
            pageSize = AppConstant.MAX_PAGE_SIZE;
        }
        QueryWrapper queryWrapper = QueryWrapper.create()
                .gt("priority", 0)
                .like("appName", queryRequest.getAppName())
                .orderBy("priority", false)
                .orderBy("updateTime", false);
        Page<App> appPage = this.page(Page.of(pageNum, pageSize), queryWrapper);
        return buildAppVOPage(appPage);
    }

    @Override
    public boolean adminDeleteApp(Long appId) {
        ThrowUtils.throwIf(appId == null || appId <= 0, ErrorCode.PARAMS_ERROR);
        App dbApp = this.getById(appId);
        ThrowUtils.throwIf(dbApp == null, ErrorCode.NOT_FOUND_ERROR);
        boolean result = this.removeById(appId);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return true;
    }

    @Override
    public boolean adminUpdateApp(AppAdminUpdateRequest updateRequest) {
        ThrowUtils.throwIf(updateRequest == null || updateRequest.getId() == null, ErrorCode.PARAMS_ERROR);
        App dbApp = this.getById(updateRequest.getId());
        ThrowUtils.throwIf(dbApp == null, ErrorCode.NOT_FOUND_ERROR);
        App updateEntity = new App();
        BeanUtil.copyProperties(updateRequest, updateEntity);
        updateEntity.setEditTime(LocalDateTime.now());
        boolean result = this.updateById(updateEntity);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return true;
    }

    @Override
    public AppVO getAppVOById(Long appId) {
        ThrowUtils.throwIf(appId == null || appId <= 0, ErrorCode.PARAMS_ERROR);
        App app = this.getById(appId);
        ThrowUtils.throwIf(app == null, ErrorCode.NOT_FOUND_ERROR);
        return convertToAppVO(app);
    }

    @Override
    public Page<AppVO> listAdminAppVOByPage(AppAdminQueryRequest queryRequest) {
        ThrowUtils.throwIf(queryRequest == null, ErrorCode.PARAMS_ERROR);
        long pageNum = queryRequest.getPageNum();
        if (pageNum <= 0) {
            pageNum = 1;
        }
        long pageSize = queryRequest.getPageSize();
        if (pageSize <= 0) {
            pageSize = 10;
        }
        Page<App> appPage = this.page(Page.of(pageNum, pageSize), buildAdminQueryWrapper(queryRequest));
        return buildAppVOPage(appPage);
    }

    private QueryWrapper buildAdminQueryWrapper(AppAdminQueryRequest queryRequest) {
        QueryWrapper queryWrapper = QueryWrapper.create()
                .eq("id", queryRequest.getId())
                .eq("userId", queryRequest.getUserId())
                .eq("priority", queryRequest.getPriority())
                .eq("isDelete", queryRequest.getIsDelete())
                .like("appName", queryRequest.getAppName())
                .like("cover", queryRequest.getCover())
                .like("initPrompt", queryRequest.getInitPrompt())
                .like("codeGenType", queryRequest.getCodeGenType())
                .like("deployKey", queryRequest.getDeployKey());
        String sortField = queryRequest.getSortField();
        String sortOrder = queryRequest.getSortOrder();
        if (StrUtil.isNotBlank(sortField) && ADMIN_SORT_FIELDS.contains(sortField)) {
            queryWrapper.orderBy(sortField, "ascend".equalsIgnoreCase(sortOrder));
        } else {
            queryWrapper.orderBy("priority", false).orderBy("updateTime", false);
        }
        return queryWrapper;
    }

    private Page<AppVO> buildAppVOPage(Page<App> appPage) {
        if (appPage == null) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
        Page<AppVO> voPage = new Page<>(appPage.getPageNumber(), appPage.getPageSize(), appPage.getTotalRow());
        voPage.setRecords(getAppVOList(appPage.getRecords()));
        return voPage;
    }

    private List<AppVO> getAppVOList(List<App> appList) {
        if (CollUtil.isEmpty(appList)) {
            return new ArrayList<>();
        }
        return appList.stream().map(this::convertToAppVO).collect(Collectors.toList());
    }

    private AppVO convertToAppVO(App app) {
        if (app == null) {
            return null;
        }
        AppVO appVO = new AppVO();
        BeanUtil.copyProperties(app, appVO);
        return appVO;
    }
}
