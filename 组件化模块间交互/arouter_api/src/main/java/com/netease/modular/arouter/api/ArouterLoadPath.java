package com.netease.modular.arouter.api;


import com.netease.modular.annoation.model.RouterBean;

import java.util.Map;

/**
 * 路由组G肉片对应的详细Path 加载数据接口
 * 比如app 分组对应有哪些类需要加载
 */
public interface ArouterLoadPath {
    Map<String, RouterBean> loadPath();
}
