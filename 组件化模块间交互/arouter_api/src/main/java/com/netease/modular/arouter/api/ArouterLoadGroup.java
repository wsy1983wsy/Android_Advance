package com.netease.modular.arouter.api;

import java.util.Map;

/**
 * 路由组Group对外提供加载数据接口
 */
public interface ArouterLoadGroup {
    Map<String, Class<? extends ArouterLoadPath>> loadGroup();
}
