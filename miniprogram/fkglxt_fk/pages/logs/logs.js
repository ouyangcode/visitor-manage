// pages/logs/logs.js
const app = getApp();
const config = require('../conf/conf.js');
Page({
  /**
   * 页面的初始数据
   */
  data: {
    logs:null,
    islogin:null
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var islogin = wx.getStorageSync("islogin");
    if(islogin.userinfo!=null){
    var that = this;
      that.setData({
        islogin:islogin
      });
    wx.request({
      url: config.host+'/getUserlogs',
      data: {
        lfrywxid: islogin.userinfo.id
      },
      success (res) {
        that.setData({
          logs:res.data.lfdjbs
        });
      },
      fail(res){
        console.log(res);
      }
    })
  }
  },
 logout:function(e){
  wx.clearStorage({
    success(res){
      wx.showToast({
        title: '清除成功',
        icon: 'success',
        duration: 2000
      })
      wx.redirectTo({
        url: '/pages/login/login'
      })
    }
  })
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