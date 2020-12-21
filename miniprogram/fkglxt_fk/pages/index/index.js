// pages/login/login.js
const app = getApp();
const config = require('../conf/conf.js');
Page({
  /**
   * 页面的初始数据
   */
  data: {
    islogin: null,
    timestr: '程序异常',
    spcode: 0,
    newest: null
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

    var that = this;
    var timestamp = Date.parse(new Date());
    timestamp = timestamp / 1000;
    //获取当前时间
    var n = timestamp * 1000;
    var date = new Date(n);
    //时
    var h = date.getHours();
    var timestr = '程序异常';
    if (h > 0 && h < 5) {
      timestr = '夜深了！'
    } else if (h < 11) {
      timestr = '上午好！'
    } else if (h < 14) {
      timestr = '中午好！'
    } else if (h < 18) {
      timestr = '下午好！'
    } else if (h < 22) {
      timestr = '晚上好！'
    } else {
      timestr = '夜深了！'
    }
    that.setData({
      timestr: timestr,
      islogin: wx.getStorageSync("islogin")
    })

    //发起网络请求
    wx.request({
      url: config.host+'/getNewest',
      data: {
        lfrywxid: that.data.islogin.userinfo.id
      },
      success(res) {
        that.setData({
          newest: res.data.newest
        });
      },
      fail(res) {
      
      }
    })
  },
  reflush:function(e){
    var that = this;
    wx.request({
      url: config.host+'/getNewest',
      data: {
        lfrywxid: that.data.islogin.userinfo.id
      },
      success(res) {
        that.setData({
          newest: res.data.newest
        });
      },
      fail(res) {
 
      }
    })
  },
  new_fksq: function (e) {
    wx.navigateTo({
      url: '/pages/lfform/lfform',
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