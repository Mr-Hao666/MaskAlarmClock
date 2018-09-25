package com.yhao.enums;

import com.yhao.domain.entity.FaceScore;

/**
 * @author 杨浩
 * @create 2018-09-20 12:23
 **/
public enum FaceScoreStyle {
    WATER(1,"补水"),
    WHITE(2,"美白"),
    COMPACT(3,"紧致"),
    MOIST(4,"滋润"),
    ALARM(5,"闹钟"),
    COMMENT(6,"点评");

    FaceScoreStyle(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    private int value;
    private String desc;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
