package com.dc.order.annotation;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @PACKAGE_NAME: com.dc.order.annotation
 * @NAME: OperationLog
 * @USER: 25738
 * @DATE: 2021/4/28
 **/
@Data
@Builder
public class OperationLog {
    private String uri;
    private String name;
    private String functionName;
    private String ip;
    private Date startTime;
    private Long responseTime;
}
