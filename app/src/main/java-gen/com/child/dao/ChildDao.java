package com.child.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.child.entity.Child;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "CHILD".
*/
public class ChildDao extends AbstractDao<Child, Long> {

    public static final String TABLENAME = "CHILD";

    /**
     * Properties of entity Child.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
        public final static Property Sex = new Property(2, Integer.class, "sex", false, "SEX");
        public final static Property Age = new Property(3, Integer.class, "age", false, "AGE");
        public final static Property Birthday = new Property(4, String.class, "birthday", false, "BIRTHDAY");
        public final static Property Head_pic = new Property(5, Integer.class, "head_pic", false, "HEAD_PIC");
        public final static Property Game_app = new Property(6, String.class, "game_app", false, "GAME_APP");
        public final static Property Book_app = new Property(7, String.class, "book_app", false, "BOOK_APP");
        public final static Property Animation_app = new Property(8, String.class, "animation_app", false, "ANIMATION_APP");
        public final static Property Learn_app = new Property(9, String.class, "learn_app", false, "LEARN_APP");
    }


    public ChildDao(DaoConfig config) {
        super(config);
    }
    
    public ChildDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"CHILD\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"NAME\" TEXT," + // 1: name
                "\"SEX\" INTEGER," + // 2: sex
                "\"AGE\" INTEGER," + // 3: age
                "\"BIRTHDAY\" TEXT," + // 4: birthday
                "\"HEAD_PIC\" INTEGER," + // 5: head_pic
                "\"GAME_APP\" TEXT," + // 6: game_app
                "\"BOOK_APP\" TEXT," + // 7: book_app
                "\"ANIMATION_APP\" TEXT," + // 8: animation_app
                "\"LEARN_APP\" TEXT);"); // 9: learn_app
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"CHILD\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Child entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        Integer sex = entity.getSex();
        if (sex != null) {
            stmt.bindLong(3, sex);
        }
 
        Integer age = entity.getAge();
        if (age != null) {
            stmt.bindLong(4, age);
        }
 
        String birthday = entity.getBirthday();
        if (birthday != null) {
            stmt.bindString(5, birthday);
        }
 
        Integer head_pic = entity.getHead_pic();
        if (head_pic != null) {
            stmt.bindLong(6, head_pic);
        }
 
        String game_app = entity.getGame_app();
        if (game_app != null) {
            stmt.bindString(7, game_app);
        }
 
        String book_app = entity.getBook_app();
        if (book_app != null) {
            stmt.bindString(8, book_app);
        }
 
        String animation_app = entity.getAnimation_app();
        if (animation_app != null) {
            stmt.bindString(9, animation_app);
        }
 
        String learn_app = entity.getLearn_app();
        if (learn_app != null) {
            stmt.bindString(10, learn_app);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Child entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        Integer sex = entity.getSex();
        if (sex != null) {
            stmt.bindLong(3, sex);
        }
 
        Integer age = entity.getAge();
        if (age != null) {
            stmt.bindLong(4, age);
        }
 
        String birthday = entity.getBirthday();
        if (birthday != null) {
            stmt.bindString(5, birthday);
        }
 
        Integer head_pic = entity.getHead_pic();
        if (head_pic != null) {
            stmt.bindLong(6, head_pic);
        }
 
        String game_app = entity.getGame_app();
        if (game_app != null) {
            stmt.bindString(7, game_app);
        }
 
        String book_app = entity.getBook_app();
        if (book_app != null) {
            stmt.bindString(8, book_app);
        }
 
        String animation_app = entity.getAnimation_app();
        if (animation_app != null) {
            stmt.bindString(9, animation_app);
        }
 
        String learn_app = entity.getLearn_app();
        if (learn_app != null) {
            stmt.bindString(10, learn_app);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Child readEntity(Cursor cursor, int offset) {
        Child entity = new Child( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // name
            cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2), // sex
            cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3), // age
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // birthday
            cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5), // head_pic
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // game_app
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // book_app
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // animation_app
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9) // learn_app
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Child entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setSex(cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2));
        entity.setAge(cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3));
        entity.setBirthday(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setHead_pic(cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5));
        entity.setGame_app(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setBook_app(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setAnimation_app(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setLearn_app(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Child entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Child entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Child entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
