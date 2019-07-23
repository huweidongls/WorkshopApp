package com.jingna.workshopapp.net;

/**
 * Created by Administrator on 2019/6/18.
 */

public class NetUrl {
    public static final String BASE_URL = "http://192.168.2.103:8080/";
    //APP会员登录接口
    public static final String MemUserloginAPP = "MemUser/loginAPP";
    //发送短信请求
    public static final String MemUsersendMessage = "MemUser/sendMessage";
    //用户注册接口
    public static final String MemUseraddMember = "MemUser/addMember";
    //匹配验证码是否正确
    public static final String MemUsermatchCode = "MemUser/matchCode";
    //通过手机验证码设置新密码
    public static final String MemUserretrievePassword = "MemUser/retrievePassword";
    //新增个人详情
    public static final String MemUsertoUpdate = "MemUser/toUpdate";
    //通过ID查询会员
    public static final String MemUsergetOne = "MemUser/getOne";
    //查询该用户绑定的银行卡
    public static final String MemBankCardqueryList = "MemBankCard/queryList";
    //新增银行卡
    public static final String MemBankCardinsertBankCard = "MemBankCard/insertBankCard";
    //移除银行卡
    public static final String MemBankCardtoDelete = "MemBankCard/toDelete";
    //团队管理
    public static final String MemUserteamManagement = "MemUser/teamManagement";
    //显示所有轮播图和分类图标接口
    public static final String IndexPageApifindBanner = "IndexPageApi/findBanner";
}
