package com.wsy.database.subdb;


import com.wsy.database.User;
import com.wsy.database.db.BaseDaoFactory;
import com.wsy.database.db.UserDao;

import java.io.File;

// 用来产生私有数据库存放的位置
public enum PrivateDatabaseEnums {

    database("");

    private String value;

    PrivateDatabaseEnums(String value) {

    }

    public String getValue() {
        UserDao userDao = BaseDaoFactory.getInstance().getBaseDao(UserDao.class, User.class);
        if (userDao != null) {
            User curUser = userDao.getCurrentUser();
            if (curUser != null) {
                File file = new File("data/data/com.wsy.database/");
                if (!file.exists()) {
                    file.mkdirs();
                }
                return file.getAbsolutePath() + "/u_" + curUser.getId() + "_private.db";
            }
        }
        return null;
    }

}
