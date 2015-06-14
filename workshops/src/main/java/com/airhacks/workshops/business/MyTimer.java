package com.airhacks.workshops.business;

import com.airhacks.workshops.business.mybusiness.boundary.MyRepository;
import com.airhacks.workshops.business.mybusiness.entity.RootEntity;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;

@Singleton
public class MyTimer {

    @Inject
    private MyRepository myRepository;

    @Schedule(second = "*/10", minute = "*", hour = "*", persistent = true)
    public void automaticTimeout() {
        System.out.println("automatic timeout");

        RootEntity rootEntity = myRepository.find(2);
        System.out.println("rootEntity = " + rootEntity);
    }
}
