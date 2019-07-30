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
    //根据车间ID查询车间数据
    public static final String AppShopCategoryqueryChildList = "AppShopCategory/queryChildList";
    //查询显示所有故事
    public static final String AppShopStorysqueryList = "AppShopStorys/queryList";
    //查询委托加工所有类型
    public static final String AppShopWtjgfindAllWtjgType = "AppShopWtjg/findAllWtjgType";
    //查询委托加工设备(可根据设备类型ID查询)
    public static final String AppShopWtjgfindAllWtjgEquipment = "AppShopWtjg/findAllWtjgEquipment";
    //查询众筹类型
    public static final String AppCrowdFundinggetType = "AppCrowdFunding/getType";
    //根据类型ID查询众筹列表
    public static final String AppCrowdFundingfindAllByType = "AppCrowdFunding/findAllByType";
    //根据众筹ID查询众筹详情
    public static final String AppCrowdFundinggetById = "AppCrowdFunding/getById";
    //众筹的为你推荐(已上架和推荐的)
    public static final String AppCrowdFundingfindAllRecommend = "AppCrowdFunding/findAllRecommend";
}
