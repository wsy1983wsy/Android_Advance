package com.wsy.design.pattern.mvp1;

import com.wsy.design.pattern.mvp1.model.ImageBean;

// View 层交互， Model层交互共同的需求
// 是一种契约，或者说合同
public interface DownloadContract {
    interface M {
        //P层告诉M层，需要做什么事情
        void requestDownloader(ImageBean imageBean) throws Exception;
    }

    interface PV {
        //V 层告诉P层，需要做什么事情
        void requestDownloader(ImageBean imageBean) throws Exception;

        //P层得到M层的结果返回，再通知V层
        void responseDownloadResult(boolean isSuccess, ImageBean imageBean);
    }
}
