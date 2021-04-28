package com.dc.order.controller;

import com.dc.order.annotation.ActionLogs;
import com.dc.order.annotation.OperationLog;
import com.dc.order.entity.Order;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @PACKAGE_NAME: com.dc.order.controller
 * @NAME: OperationLogController
 * @USER: 25738
 * @DATE: 2021/4/28
 **/
@RestController
public class OperationLogController {

    @Autowired
    MongoTemplate mongoTemplate;

    @ActionLogs
    @RequestMapping("/log")
    public Object queryList(){
        Sort startTime = Sort.by(Sort.Direction.DESC, "startTime");
        Query query = new Query().with(PageRequest.of(0,5,startTime).next());
        return mongoTemplate.find(query, OperationLog.class);
    }

}
