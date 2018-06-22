package com.zw.yzk.component.common.greendao.helper;

import com.zw.yzk.component.common.greendao.DaoManager;
import com.zw.yzk.component.common.greendao.entity.User;
import com.zw.yzk.component.common.greendao.entity.UserDao;

import java.util.List;

/**
 * 管理user表
 */
public class UserHelper {

    /**
     * 检查是否有登录用户存在
     *
     * @return true: 有，false：没有
     */
    public static boolean checkLogin() {
        return getLoginUser() != null;
    }

    /**
     * 获取登录用户
     *
     * @return 已经登录用户
     */
    public static User getLoginUser() {
        List<User> users = DaoManager.getInstance()
                .getUserDao().queryBuilder()
                .where(UserDao.Properties.Login.eq(true))
                .build().list();
        if (users.isEmpty()) {
            return null;
        }
        return users.get(0);
    }

    /**
     * 存储用户信息
     *
     * @param user 用户信息
     */
    public static void saveLoginUser(User user) {
        if (user == null) {
            return;
        }
        List<User> users = DaoManager.getInstance()
                .getUserDao().queryBuilder()
                .where(UserDao.Properties.UserId.eq(user.getUserId()))
                .build().list();
        if (users.isEmpty()) {
            DaoManager.getInstance().getUserDao().insert(user);
        } else {
            user.setId(users.get(0).getId());
            DaoManager.getInstance().getUserDao().insertOrReplace(user);
        }
    }

    /**
     * 退出登录
     */
    public static void logout() {
        List<User> users = DaoManager.getInstance()
                .getUserDao().queryBuilder()
                .where(UserDao.Properties.Login.eq(true))
                .build().list();
        for (User user : users) {
            user.setLogin(false);
            DaoManager.getInstance().getUserDao().insertOrReplace(user);
        }
    }

}
