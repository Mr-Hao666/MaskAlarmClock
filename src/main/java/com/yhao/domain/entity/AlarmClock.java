package com.yhao.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("主题闹钟信息")
public class AlarmClock implements Serializable {
    private Integer id;

    @ApiModelProperty("中文名称")
    private String nameCh;

    @ApiModelProperty("英文名称")
    private String nameEn;

    @ApiModelProperty("图片")
    private String icon;

    @ApiModelProperty("背景图")
    private String background;

    @ApiModelProperty("音乐")
    private String music;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameCh() {
        return nameCh;
    }

    public void setNameCh(String nameCh) {
        this.nameCh = nameCh;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", nameCh=").append(nameCh);
        sb.append(", nameEn=").append(nameEn);
        sb.append(", icon=").append(icon);
        sb.append(", background=").append(background);
        sb.append(", music=").append(music);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}