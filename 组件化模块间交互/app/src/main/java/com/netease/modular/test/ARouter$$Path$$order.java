package com.netease.modular.test;

import com.netease.modular.annoation.model.RouterBean;
import com.netease.modular.arouter.api.ArouterLoadPath;
import com.netease.modular.order.Order_MainActivity;

import java.util.HashMap;
import java.util.Map;

public class ARouter$$Path$$order implements ArouterLoadPath {
    @Override
    public Map<String, RouterBean> loadPath() {
        Map<String, RouterBean> pathMap = new HashMap<>();
        pathMap.put("/order/Order_MainActivity",
                RouterBean.create(RouterBean.Type.ACTIVITY, Order_MainActivity.class,
                        "/order/Order_MainActivity", "order"));
        return pathMap;
    }
}
