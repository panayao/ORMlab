package GuestBookDatabase;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.DaoConfig;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.IdentityScopeType;

import GuestBookDatabase.Guest;
import GuestBookDatabase.GuestList;

import GuestBookDatabase.GuestDao;
import GuestBookDatabase.GuestListDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig guestDaoConfig;
    private final DaoConfig guestListDaoConfig;

    private final GuestDao guestDao;
    private final GuestListDao guestListDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        guestDaoConfig = daoConfigMap.get(GuestDao.class).clone();
        guestDaoConfig.initIdentityScope(type);

        guestListDaoConfig = daoConfigMap.get(GuestListDao.class).clone();
        guestListDaoConfig.initIdentityScope(type);

        guestDao = new GuestDao(guestDaoConfig, this);
        guestListDao = new GuestListDao(guestListDaoConfig, this);

        registerDao(Guest.class, guestDao);
        registerDao(GuestList.class, guestListDao);
    }
    
    public void clear() {
        guestDaoConfig.getIdentityScope().clear();
        guestListDaoConfig.getIdentityScope().clear();
    }

    public GuestDao getGuestDao() {
        return guestDao;
    }

    public GuestListDao getGuestListDao() {
        return guestListDao;
    }

}