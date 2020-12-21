// pages/login/login.js
const app = getApp();
const config = require('../conf/conf.js');
Page({

  /**
   * 页面的初始数据
   */
  data: {
      islogin:wx.getStorageSync("islogin")
  },
  
  login:function(e){
    var that = this;
    wx.login({
      success (res) {
        if (res.code) {
          //发起网络请求
          wx.request({
            url: config.host+'/vxlogin',
            data: {
              code: res.code
            },
            success (res) {
              that.setData({
                islogin:res.data
              });
              wx.setStorageSync("islogin",that.data.islogin);
              wx.switchTab({
                url: '/pages/index/index',
                success(res){
                  let page = getCurrentPages().pop();
                  if(page == undefined || page == null){
                        return
                  }
                  page.onLoad();
            }
              })
            },
            fail(res){
              console.log(res);
            }
          })
        } else {
          console.log('登录失败！' + res.errMsg)
        }
      }
    })
  },
  getUserinfo:function(e){
   console.log(e.detail.userInfo);
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var islogin = wx.getStorageSync("islogin")
    if(islogin)
    wx.switchTab({
      url: '/pages/index/index'
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