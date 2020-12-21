// pages/lfform/lfform.js
const app = getApp();
const config = require('../conf/conf.js');
Page({

  /**
   * 页面的初始数据
   */
  data: {
    bmarray:[],
    jqbmstr:'点击以选择部门',
    lfrqstr:'点击以选择日期',
    instrue:null,
    islogin:null
  },
  bindPickerChange:function(e){
    this.setData({
      jqbmstr:this.data.bmarray[e.detail.value]
    })
  },
  bindDateChange:function(e){
    this.setData({
      lfrqstr:e.detail.value
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
      var islogin = wx.getStorageSync('islogin');
      if(islogin!=null){
      wx.request({
        url: config.host+'/getbmarray',
        data: {
          lfrywxid:islogin.userinfo.id
        },
        success (res) {
          console.log(res);
          that.setData({
            bmarray:res.data
          })
        },
        fail(res){
          console.log(res);
        }
      });
    }
      that.setData({
          islogin:islogin
      });
  },
  sublfform:function(e){
    var that = this;
    var err="";
    if(e.detail.value.lfrq=="点击以选择日期"){
      err="来访日期不能为空";
    };
    if(e.detail.value.khxm==""){
      err="客户姓名不能为空";
    };
    if(e.detail.value.rcsy==""){
      err="入场事由不能为空";
    };
    if (e.detail.value.idcard == "") {
      err = "身份证号不能为空";
    };
    if(e.detail.value.jqbm=="点击以选择部门"){
      err="接洽部门不能为空";
    };
    if(e.detail.value.jqr==""){
      err="接洽人不能为空";
    };
    if(e.detail.value.sjh!="") {
      var myreg = /^(((13[0-9]{1})|(14[0-9]{1})|(17[0-9]{1})|(15[0-3]{1})|(15[4-9]{1})|(18[0-9]{1})|(199))+\d{8})$/;
      if (e.detail.value.sjh.length != 11) {
        err = "请输入11位手机号码！";
      } 
      if (!myreg.test(e.detail.value.sjh)) {
        err = "请输入有效的手机号码！";
      }
  }
    if(err==""){
    wx.request({
      url: config.host+'/inslfdjb',
      data: {
        cph: e.detail.value.cph,
        jqbm:e.detail.value.jqbm,
        jqr:e.detail.value.jqr,
        khdw:e.detail.value.khdw,
        khxm:e.detail.value.khxm,
        idcard: e.detail.value.idcard,
        lfrq:e.detail.value.lfrq,
        rcsy:e.detail.value.rcsy,
        sjh:e.detail.value.sjh,
        uid:that.data.islogin.userinfo.id
      },
      success (res) {
        console.log(res);
        that.setData({
          instrue:res.data
        })
        if(that.data.instrue){
          var a = setTimeout(function(){
            wx.switchTab({
              url: '/pages/index/index',
              success(res){
                let page = getCurrentPages().pop();
                if(page == undefined || page == null){
                      return
                }
                page.onLoad();
          }
            });
            clearTimeout(a);
          } ,1500);
        }
      },
      fail(res){
        console.log(res);
      }
    });
    }
    else{
      wx.showToast({
        title: err,
        icon: 'none',
        duration: 2000
      })
    }
  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})