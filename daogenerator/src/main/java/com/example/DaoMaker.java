package com.example;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

public class DaoMaker {
    public static void main(String[] args){
        //生成数据库实体类 XXentity,对应的是数据库的表
        Schema schema=new Schema(1,"com.child.entity");
        addChild(schema);
        schema.setDefaultJavaPackageDao("com.child.dao");
        try {
            new DaoGenerator().generateAll(schema,"D:\\work\\workspace\\CircularFloatingActionMenu-master\\KidLauncher\\app\\src\\main\\java-gen");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //创建数据库的表
    private static  void addChild(Schema schema){
        Entity entity=schema.addEntity("Child");
        entity.addIdProperty();//主键 int 可自增长
        entity.addStringProperty("name");
        entity.addIntProperty("sex");
        entity.addIntProperty("age");
        entity.addStringProperty("birthday");
        entity.addIntProperty("head_pic");
        entity.addStringProperty("game_app");
        entity.addStringProperty("book_app");
        entity.addStringProperty("animation_app");
        entity.addStringProperty("learn_app");
    }
}
