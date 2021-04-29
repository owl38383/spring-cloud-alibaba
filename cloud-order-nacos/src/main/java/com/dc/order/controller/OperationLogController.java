package com.dc.order.controller;

import cn.hutool.core.date.DateUtil;
import com.dc.order.annotation.OperationLog;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @PACKAGE_NAME: com.dc.order.controller
 * @NAME: OperationLogController
 * @USER: 25738
 * @DATE: 2021/4/28
 **/
@RestController
public class OperationLogController {

    @Resource
    MongoTemplate mongoTemplate;

    @RequestMapping("/log")
    public Object queryList(){
        Sort sort = Sort.by(Sort.Direction.DESC, "startTime");
        Pageable next = PageRequest.of(0, 5, sort).next();
        Query query = new Query().with(sort);
        List<OperationLog> operationLogs = mongoTemplate.find(query, OperationLog.class);
        operationLogs.forEach(s->{
            s.setStartTime(DateUtil.offsetHour(s.getStartTime(),8));
        });
        return operationLogs;
    }

}
