package com.yhao.domain.entity;

import java.io.Serializable;

public class UserCollection implements Serializable {

    public UserCollection(Integer userId, Integer alarmClockId) {
        super();
        this.userId = userId;
        this.alarmClockId = alarmClockId;
    }

    private Integer id;

    private Integer userId;

    private Integer alarmClockId;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAlarmClockId() {
        return alarmClockId;
    }

    public void setAlarmClockId(Integer alarmClockId) {
        this.alarmClockId = alarmClockId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", alarmClockId=").append(alarmClockId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}