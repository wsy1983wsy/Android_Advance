package com.netease.modular;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.netease.common.base.BaseActivity;
import com.netease.common.utils.Cons;
import com.netease.modular.annoation.Arouter;
import com.netease.modular.annoation.model.RouterBean;
import com.netease.modular.arouter.api.ArouterLoadGroup;
import com.netease.modular.arouter.api.ArouterLoadPath;
import com.netease.modular.order.Order_MainActivity;
import com.netease.modular.personal.Personal_MainActivity;
import com.netease.modular.test.ARouter$$Group$$order;

import java.util.Map;

@Arouter(path = "/app/MainActivity")
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (BuildConfig.isRelease) {
            Log.e(Cons.TAG, "当前为：集成化模式，除app可运行，其他子模块都是Android Library");
        } else {
            Log.e(Cons.TAG, "当前为：组件化模式，app/order/personal子模块都可独立运行");
        }
    }

    public void jumpOrder(View view) {
//        Intent intent = new Intent(this, Order_MainActivity.class);
//        intent.putExtra("name", "simon");
//        startActivity(intent);
        ArouterLoadGroup arouterLoadGroup = new ARouter$$Group$$order();
        Map<String, Class<? extends ArouterLoadPath>> groupMap = arouterLoadGroup.loadGroup();
        Class<? extends ArouterLoadPath> clazz = groupMap.get("order");
        try {
            ArouterLoadPath path = clazz.newInstance();
            Map<String, RouterBean> pathMap = path.loadPath();
            RouterBean bean = pathMap.get("/order/Order_MainActivity");
            if (bean != null) {
                Intent intent = new Intent(this, bean.getClazz());
                intent.putExtra("name", "simon");
                startActivity(intent);
            }
        } catch (Throwable e) {
        }
    }

    public void jumpPersonal(View view) {
//        Intent intent = new Intent(this, Personal_MainActivity.class);
//        intent.putExtra("name", "simon");
//        startActivity(intent);
    }
}
