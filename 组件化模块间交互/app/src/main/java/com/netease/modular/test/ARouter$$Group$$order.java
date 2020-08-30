package com.netease.modular.test;

import com.netease.modular.arouter.api.ArouterLoadGroup;
import com.netease.modular.arouter.api.ArouterLoadPath;

import java.util.HashMap;
import java.util.Map;

public class ARouter$$Group$$order implements ArouterLoadGroup {
    @Override
    public Map<String, Class<? extends ArouterLoadPath>> loadGroup() {
        Map<String, Class<? extends ArouterLoadPath>> groupMap = new HashMap<>();
        groupMap.put("order", ARouter$$Path$$order.class);
        return groupMap;
    }
}
