package com.tanglin.tangaicodemother.service;

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.service.IService;
import com.tanglin.tangaicodemother.model.dto.app.AppAddRequest;
import com.tanglin.tangaicodemother.model.dto.app.AppAdminQueryRequest;
import com.tanglin.tangaicodemother.model.dto.app.AppAdminUpdateRequest;
import com.tanglin.tangaicodemother.model.dto.app.AppFeaturedQueryRequest;
import com.tanglin.tangaicodemother.model.dto.app.AppMyQueryRequest;
import com.tanglin.tangaicodemother.model.dto.app.AppUpdateMyRequest;
import com.tanglin.tangaicodemother.model.entity.App;
import com.tanglin.tangaicodemother.model.entity.User;
import com.tanglin.tangaicodemother.model.vo.AppVO;

/**
 * 应用 服务层。
 *
 * @author tangshilin
 */
public interface AppService extends IService<App> {

    /**
     * 用户创建应用
     */
    Long createApp(AppAddRequest appAddRequest, User loginUser);

    /**
     * 用户更新应用
     */
    boolean updateMyApp(AppUpdateMyRequest updateRequest, User loginUser);

    /**
     * 用户删除自己的应用
     */
    boolean deleteMyApp(Long appId, User loginUser);

    /**
     * 查询自己的应用详情
     */
    AppVO getMyAppVO(Long appId, User loginUser);

    /**
     * 分页查询自己的应用
     */
    Page<AppVO> listMyAppVOByPage(AppMyQueryRequest queryRequest, User loginUser);

    /**
     * 分页查询精选应用
     */
    Page<AppVO> listFeaturedAppVOByPage(AppFeaturedQueryRequest queryRequest);

    /**
     * 管理员删除应用
     */
    boolean adminDeleteApp(Long appId);

    /**
     * 管理员更新应用
     */
    boolean adminUpdateApp(AppAdminUpdateRequest updateRequest);

    /**
     * 管理员或公共查看应用
     */
    AppVO getAppVOById(Long appId);

    /**
     * 管理员分页查询应用
     */
    Page<AppVO> listAdminAppVOByPage(AppAdminQueryRequest queryRequest);
}
