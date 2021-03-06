package com.child.dao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.child.entity.Child;

import com.child.dao.ChildDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig childDaoConfig;

    private final ChildDao childDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        childDaoConfig = daoConfigMap.get(ChildDao.class).clone();
        childDaoConfig.initIdentityScope(type);

        childDao = new ChildDao(childDaoConfig, this);

        registerDao(Child.class, childDao);
    }
    
    public void clear() {
        childDaoConfig.clearIdentityScope();
    }

    public ChildDao getChildDao() {
        return childDao;
    }

}
