package com.example.hzxie.itaszkcatalog.zkwebservice;

/**
 * Created by hzxie on 2016/10/13.
 */
public interface IServicePublisher {
    void RegisterObserver(IServiceObserver observer);
    void DisAttach();
    void CallIsConnected();
    void CallState();
    void CallSelect(String sql);
    void CallInsert(String sql);
    void CallUpdate(String sql);
    void CallDelete(String sql);
}
