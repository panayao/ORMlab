package GuestBookDatabase;

import java.util.List;
import GuestBookDatabase.DaoSession;
import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table GuestList.
 */
public class GuestList {

    private Long id;
    private java.util.Date date;
    private long guestId;

    /** Used to resolve relations */
    private DaoSession daoSession;

    /** Used for active entity operations. */
    private GuestListDao myDao;

    private Guest guest;
    private Long guest__resolvedKey;

    private List<Guest> GuestList;

    public GuestList() {
    }

    public GuestList(Long id) {
        this.id = id;
    }

    public GuestList(Long id, java.util.Date date, long guestId) {
        this.id = id;
        this.date = date;
        this.guestId = guestId;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getGuestListDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public java.util.Date getDate() {
        return date;
    }

    public void setDate(java.util.Date date) {
        this.date = date;
    }

    public long getGuestId() {
        return guestId;
    }

    public void setGuestId(long guestId) {
        this.guestId = guestId;
    }

    /** To-one relationship, resolved on first access. */
    public Guest getGuest() {
        if (guest__resolvedKey == null || !guest__resolvedKey.equals(guestId)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            GuestDao targetDao = daoSession.getGuestDao();
            guest = targetDao.load(guestId);
            guest__resolvedKey = guestId;
        }
        return guest;
    }

    public void setGuest(Guest guest) {
        if (guest == null) {
            throw new DaoException("To-one property 'guestId' has not-null constraint; cannot set to-one to null");
        }
        this.guest = guest;
        guestId = guest.getId();
        guest__resolvedKey = guestId;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public synchronized List<Guest> getGuestList() {
        if (GuestList == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            GuestDao targetDao = daoSession.getGuestDao();
            GuestList = targetDao._queryGuestList_GuestList(id);
        }
        return GuestList;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    public synchronized void resetGuestList() {
        GuestList = null;
    }

    /** Convenient call for {@link AbstractDao#delete(Object)}. Entity must attached to an entity context. */
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.delete(this);
    }

    /** Convenient call for {@link AbstractDao#update(Object)}. Entity must attached to an entity context. */
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.update(this);
    }

    /** Convenient call for {@link AbstractDao#refresh(Object)}. Entity must attached to an entity context. */
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.refresh(this);
    }

}
