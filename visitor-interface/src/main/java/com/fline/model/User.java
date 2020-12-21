package com.fline.model;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private Integer id;

    private String openid; // 微信openid

    private Integer code;  // 是否为内部人员（0不是，1是）

    private String realname;  //真实姓名

    private Date zcsj;  // 注册时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public Date getZcsj() {
        return zcsj;
    }

    public void setZcsj(Date zcsj) {
        this.zcsj = zcsj;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", openid=").append(openid);
        sb.append(", code=").append(code);
        sb.append(", realname=").append(realname);
        sb.append(", zcsj=").append(zcsj);
        sb.append("]");
        return sb.toString();
    }
}