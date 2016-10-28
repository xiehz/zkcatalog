package com.example.hzxie.itaszkcatalog;

import com.example.hzxie.itaszkcatalog.zkwebservice.IServicePublisher;
import com.example.hzxie.itaszkcatalog.zkwebservice.WebservicePublisher;

/**
 * Created by hzxie on 2016/10/13.
 * 饿汉模式
 */
public class OperationObjectManager {
    private OperationObjectManager()
    {

    }
    private static final OperationObjectManager Instance = new OperationObjectManager();

    public static OperationObjectManager GetInstance()
    {
        return Instance;
    }
    public IServicePublisher webserver;
}
