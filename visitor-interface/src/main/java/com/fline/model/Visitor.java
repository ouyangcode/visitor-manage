package com.fline.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 来访登记表
 */
public class Visitor implements Serializable {
    private Integer id;

    private Date lfrq; //来访日期

    private String jcsj; //进厂时间

    private String djy; //登记员

    private String idcard; //身份证号

    private String khdw; //客户单位

    private String cph;  //车牌号

    private String khxm; //客户姓名

    private String rcsy;  //入场事由

    private String aqcs;  //安全措施

    private String zkwp;  //暂扣物品

    private String cwgbh;  //储物柜编号

    private String sjh;  //手机号

    private String jqbm;  //接洽部门

    private String jqr;  //接洽人

    private Date ccsj;  //出厂时间

    private Integer lfryvxid;  //来访人员微信用户id

    private Integer sfqylf;  //是否确认来访（0未确认，1已确认，2已拒绝）

    private Integer qrrvxid;  //确认人微信用户id

    private Integer sfjslc;  //是否结束流程(0未结束，1已结束)

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getLfrq() {
        return lfrq;
    }

    public void setLfrq(Date lfrq) {
        this.lfrq = lfrq;
    }

    public String getJcsj() {
        return jcsj;
    }

    public void setJcsj(String jcsj) {
        this.jcsj = jcsj == null ? null : jcsj.trim();
    }

    public String getDjy() {
        return djy;
    }

    public void setDjy(String djy) {
        this.djy = djy == null ? null : djy.trim();
    }

    public String getKhdw() {
        return khdw;
    }

    public void setKhdw(String khdw) {
        this.khdw = khdw == null ? null : khdw.trim();
    }

    public String getCph() {
        return cph;
    }

    public void setCph(String cph) {
        this.cph = cph == null ? null : cph.trim();
    }

    public String getKhxm() {
        return khxm;
    }

    public void setKhxm(String khxm) {
        this.khxm = khxm == null ? null : khxm.trim();
    }

    public String getRcsy() {
        return rcsy;
    }

    public void setRcsy(String rcsy) {
        this.rcsy = rcsy == null ? null : rcsy.trim();
    }

    public String getAqcs() {
        return aqcs;
    }

    public void setAqcs(String aqcs) {
        this.aqcs = aqcs == null ? null : aqcs.trim();
    }

    public String getZkwp() {
        return zkwp;
    }

    public void setZkwp(String zkwp) {
        this.zkwp = zkwp == null ? null : zkwp.trim();
    }

    public String getCwgbh() {
        return cwgbh;
    }

    public void setCwgbh(String cwgbh) {
        this.cwgbh = cwgbh == null ? null : cwgbh.trim();
    }

    public String getSjh() {
        return sjh;
    }

    public void setSjh(String sjh) {
        this.sjh = sjh == null ? null : sjh.trim();
    }

    public String getJqbm() {
        return jqbm;
    }

    public void setJqbm(String jqbm) {
        this.jqbm = jqbm == null ? null : jqbm.trim();
    }

    public String getJqr() {
        return jqr;
    }

    public void setJqr(String jqr) {
        this.jqr = jqr == null ? null : jqr.trim();
    }

    public Date getCcsj() {
        return ccsj;
    }

    public void setCcsj(Date ccsj) {
        this.ccsj = ccsj;
    }

    public Integer getLfryvxid() {
        return lfryvxid;
    }

    public void setLfryvxid(Integer lfryvxid) {
        this.lfryvxid = lfryvxid;
    }

    public Integer getSfqylf() {
        return sfqylf;
    }

    public void setSfqylf(Integer sfqylf) {
        this.sfqylf = sfqylf;
    }

    public Integer getQrrvxid() {
        return qrrvxid;
    }

    public void setQrrvxid(Integer qrrvxid) {
        this.qrrvxid = qrrvxid;
    }

    public Integer getSfjslc() {
        return sfjslc;
    }

    public void setSfjslc(Integer sfjslc) {
        this.sfjslc = sfjslc;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", lfrq=").append(lfrq);
        sb.append(", jcsj=").append(jcsj);
        sb.append(", djy=").append(djy);
        sb.append(", khdw=").append(khdw);
        sb.append(", cph=").append(cph);
        sb.append(", khxm=").append(khxm);
        sb.append(", rcsy=").append(rcsy);
        sb.append(", aqcs=").append(aqcs);
        sb.append(", zkwp=").append(zkwp);
        sb.append(", cwgbh=").append(cwgbh);
        sb.append(", sjh=").append(sjh);
        sb.append(", jqbm=").append(jqbm);
        sb.append(", jqr=").append(jqr);
        sb.append(", ccsj=").append(ccsj);
        sb.append(", lfryvxid=").append(lfryvxid);
        sb.append(", sfqylf=").append(sfqylf);
        sb.append(", qrrvxid=").append(qrrvxid);
        sb.append(", sfjslc=").append(sfjslc);
        sb.append(", idcard=").append(idcard);
        sb.append("]");
        return sb.toString();
    }
}