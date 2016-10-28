package com.example.hzxie.itaszkcatalog.zkwebservice;

/**
 * Created by hzxie on 2016/10/13.
 */
public interface IServiceObserver {
    void OnIsConnected(Boolean connected);
    void OnState(int state);
    void OnSelect(String result);
    void OnInsert(Boolean result);
    void OnUpdate(Boolean update);
    void OnDelete(Boolean delete);
}
