// pages/first/first.js
const app = getApp();
const common = require('../common/common.js');
Page({

  /**
   * 页面的初始数据
   */
  data: {
    bmarray:[],
    jqbmstr:'点击以选择部门',
    islogin:wx.getStorageSync('islogin')
  },
  bindPickerChange:function(e){
    this.setData({
      jqbmstr:this.data.bmarray[e.detail.value]
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    var islogin = wx.getStorageSync('islogin');
    if(islogin!=null){
      that.setData({
        islogin:islogin
      })
    }
    wx.request({
      url: common.host+'/getbmarray',
      data: {
        lfrywxid:islogin.id
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
  },
  upuserinfo:function(e){
    var that = this;
    var err="";
    if(e.detail.value.jqbm=="点击以选择部门"){
      err="所在部门不能为空";
    };
    if(e.detail.value.khxm==""){
      err="姓名不能为空";
    };
    if(err==""){
      console.log(that.data.islogin);
      wx.request({
        url: common.host+'/upuserinfo',
        data: {
          realname:e.detail.value.jqbm+'-'+e.detail.value.khxm,
          id:that.data.islogin.id
        },
        success (res) {
          console.log(res);
          that.setData({
            instrue:res.data
          })
          if(that.data.instrue){
            wx.clearStorage({
              success(res){
                wx.showToast({
                  title: '修改成功请重新登录',
                  icon: 'success',
                  duration: 2000
                })
                wx.redirectTo({
                  url: '/pages/index/index'
                })
              }
            })  
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