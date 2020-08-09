package com.wsy.design.pattern.adapter;

import com.wsy.design.pattern.adapter.charge.ChinaCharge;
import com.wsy.design.pattern.adapter.charge.USACharge;

public class ChinaAdapter extends ChinaCharge {
    private USACharge usaCharge;

    public ChinaAdapter(USACharge usaCharge) {
        this.usaCharge = usaCharge;
    }

    @Override
    public void chinaCharge() {
        if (usaCharge.usaCharge() == 220) {
            System.out.println("符合中国220V使用标准");
        } else {
            System.out.println("不符合中国220V使用标准，电器会少辉");
        }
    }
}
