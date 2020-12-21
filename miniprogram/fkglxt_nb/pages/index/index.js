//index.js
//获取应用实例
const app = getApp()
const common = require("../common/common.js")

Page({
  data: {
    islogin:wx.getStorageSync('islogin'),
    info:""
  },

  onLoad: function () {
    var that = this;
    var islogin = wx.getStorageSync('islogin');
    if(islogin){
      wx.switchTab({
        url:'../fwdt/fwdt'
      });
    }
  },
  zhmm:function(){
    wx.navigateTo({
      url: '../zhmm/zhmm',
    })
  },
  reguser:function(){
    wx.navigateTo({
      url: '../reguser/reguser',
    })
  },

  login:function(e){
    var that = this;
    var host = common.host;
    var username = e.detail.value.username;
    var password = e.detail.value.password;
    if(username==null||username==""){
      that.setData({
        info:"请输入用户名"
      })
      return;
    }
    if(password==null||password==""){
      that.setData({
        info:"请输入密码"
      })
      return;
    }
    wx.request({
      url: host + '/wxuserlogin',
      data:{
        username:username,
        password:password
      },
      success(res){
        var code = res.data.code;
        if(code==0){
          wx.showToast({
            title: res.data.msg,
            icon: 'success',
            duration: 2000
          });
          that.setData({
            islogin:res.data.user
          });
          wx.setStorageSync("islogin",res.data.user);
          wx.switchTab({
            url:'../fwdt/fwdt'
          })
          return;
        }
        else{
          wx.showToast({
            title: res.data.msg,
            icon: 'none',
            duration: 2000
          });
          return;
        }
      }
    })
  },
  qywxlogin:function(){
    var that = this;
    var host = common.host;
    wx.qy.login({
      success: function(res) {
        if (res.code) {
          //发起网络请求
          wx.request({
            url: host+'/qyvxlogin',
            data: {
              code: res.code
            },
            success(e){
              if(e.data.code==0){
                wx.showToast({
                  title: '登陆成功',
                  icon: 'success',
                  duration: 2000
                });
                that.setData({
                  islogin:e.data.user
                });
                wx.setStorageSync("islogin",e.data.user);
                wx.switchTab({
                  url:'../fwdt/fwdt'
                })
                return;
              }
              else{
                wx.showToast({
                  title: '未绑定微信，请注册',
                  icon: 'none',
                  duration: 2000
                });
                wx.navigateTo({
                  url: '../reguser/reguser',
                });
                return;
              }
            }
          })
        } else {
          console.log('登录失败！' + res.errMsg)
        }
      }
    });
  }
})
