package com.wsy.design.pattern.adapter;

import com.wsy.design.pattern.adapter.charge.ChinaCharge;

public class AdapterMain {
    public static void main(String[] args) {
        ChinaCharge chinaCharge = new ChinaAdapter(new USA());
        chinaCharge.chinaCharge();
    }
}
