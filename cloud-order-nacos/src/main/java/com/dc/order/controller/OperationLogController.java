package com.dc.order.controller;

import com.dc.order.annotation.OperationLog;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
        Sort startTime = Sort.by(Sort.Direction.DESC, "startTime");
        Query query = new Query().with(PageRequest.of(0,5,startTime).next());
        return mongoTemplate.find(query, OperationLog.class);
    }

}
