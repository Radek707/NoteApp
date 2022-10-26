package com.example.mynotes.models;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.DaoException;

@Entity
public class Note {
    @Id(autoincrement = true)
    private Long id;

    @NotNull
    private String title;
    private String details;
    boolean archived;
    private long idUser;

    @ToOne(joinProperty = "idUser")
    private User user;

    private long colorId;
    @ToOne(joinProperty = "colorId")
    private NoteColor color;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 363862535)
    private transient NoteDao myDao;

    @Generated(hash = 251390918)
    private transient Long user__resolvedKey;

    @Generated(hash = 2062663027)
    private transient Long color__resolvedKey;

    @Generated(hash = 967994577)
    public Note(Long id, @NotNull String title, String details, boolean archived, long idUser,
            long colorId) {
        this.id = id;
        this.title = title;
        this.details = details;
        this.archived = archived;
        this.idUser = idUser;
        this.colorId = colorId;
    }

    @Generated(hash = 1272611929)
    public Note() {
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @NotNull
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDetails() {
        return details;
    }
    public void setDetails(String details) {
        this.details = details;
    }

    public boolean getArchived() {
        return this.archived;
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1926603872)
    public User getUser() {
        long __key = this.idUser;
        if (user__resolvedKey == null || !user__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            UserDao targetDao = daoSession.getUserDao();
            User userNew = targetDao.load(__key);
            synchronized (this) {
                user = userNew;
                user__resolvedKey = __key;
            }
        }
        return user;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1939208832)
    public void setUser(@NotNull User user) {
        if (user == null) {
            throw new DaoException(
                    "To-one property 'idUser' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.user = user;
            idUser = user.getId();
            user__resolvedKey = idUser;
        }
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    public long getColorId() {
        return this.colorId;
    }

    public void setColorId(long colorId) {
        this.colorId = colorId;
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 290161855)
    public NoteColor getColor() {
        long __key = this.colorId;
        if (color__resolvedKey == null || !color__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            NoteColorDao targetDao = daoSession.getNoteColorDao();
            NoteColor colorNew = targetDao.load(__key);
            synchronized (this) {
                color = colorNew;
                color__resolvedKey = __key;
            }
        }
        return color;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1931615041)
    public void setColor(@NotNull NoteColor color) {
        if (color == null) {
            throw new DaoException(
                    "To-one property 'colorId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.color = color;
            colorId = color.getId();
            color__resolvedKey = colorId;
        }
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 799086675)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getNoteDao() : null;
    }
}
