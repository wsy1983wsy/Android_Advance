package com.wsy.design.pattern.adapter;

import com.wsy.design.pattern.adapter.charge.USACharge;

public class USA extends USACharge {
    @Override
    public int usaCharge() {
        System.out.println("工程师改造了电压");
        return 220;
    }
}
