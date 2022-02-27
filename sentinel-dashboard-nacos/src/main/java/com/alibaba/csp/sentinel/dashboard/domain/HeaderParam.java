package com.alibaba.csp.sentinel.dashboard.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * 外部系统header公共参数
 */
@Setter
@Getter
public class HeaderParam {

    public final static String HEADER_APP_CODE = "appCode";

    public final static String HEADER_TOKEN = "token";

    public final static String HEADER_REQUEST_ID = "distinctRequestId";

    private String appCode;

    private String token;

    private String distinctRequestId;
}
