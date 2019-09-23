package com.jingna.workshopapp.net;

/**
 * Created by Administrator on 2019/6/18.
 */

public class NetUrl {
    public static final String BASE_URL = "http://39.98.188.171:8089/";//231 http://192.168.2.103:80/
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
    //查询隐私政策
    public static final String PrivacyPolicyqueryList = "PrivacyPolicy/queryList";
    //查询所有声明接口
    public static final String ImportantStatementsqueryList = "ImportantStatements/queryList";
    //查询热门众筹
    public static final String AppCrowdFundingfindByPopular = "AppCrowdFunding/findByPopular";
    //查询热门众筹
    public static final String FunctionalFeedbacktoAdd = "FunctionalFeedback/toAdd";
    //车间详情
    public static final String AppShopCategorygetByCategoryId = "AppShopCategory/getByCategoryId";
    //车间订单下单配置详情
    public static final String AppOrderworkshopEquipment = "AppOrder/workshopEquipment";
    //车间提交订单
    public static final String AppOrderorderSubmission = "AppOrder/orderSubmission";
    //根据type修改用户是否收藏商品
    public static final String AppGoodsShopisFollow = "AppGoodsShop/isFollow";
    //根据用户ID查询用户所有收藏接口
    public static final String AppMemberCollectqueryList = "AppMemberCollect/queryList";
    //根据用户id和商品id删除收藏接口
    public static final String AppMemberCollecttoDelete = "AppMemberCollect/toDelete";
    //根据车间ID查询车间订单配置
    public static final String AppOrderorderConfiguration = "AppOrder/orderConfiguration";
    //根据委托订单ID数据回显
    public static final String AppOrderentrustedProcessingOrder = "AppOrder/entrustedProcessingOrder";
    //委托加工订单提交
    public static final String AppOrderwtjgOrderConfiguration = "AppOrder/wtjgOrderConfiguration";
    //根据众筹ID查询档位下单详情接口
    public static final String AppCrowdFundinggetGearPositionById = "AppCrowdFunding/getGearPositionById";
    //订单列表
    public static final String AppOrderActivityList = "/AppOrder/queryList";
    //订单去支付
    public  static final String AppOrderlistOrdersSubmitted = "/AppOrder/listOrdersSubmitted";
    //订单退款
    public static final String AppOrderorderRefund = "/AppOrder/orderRefund";
    //取消订单
    public static final String AppOrdercancellationOrder = "/AppOrder/cancellationOrder";
    //删除订单
    public static final String AppOrdertoDelete ="/AppOrder/toDelete";
    //订单详情
    public static final String AppOrderorderDetails="/AppOrder/orderDetails";
    //新增商品评价接口
    public static final String AppShopGoodsEvaluatetoUpdate ="/AppShopGoodsEvaluate/toUpdate";
    //订单收货
    public static final String AppOrderorderReceiving ="/AppOrder/orderReceiving";
    //佣金收益记录接口
    public static final String MemUserCommissionRevenuesSum ="/MemUser/CommissionRevenuesSum";
    //佣金提现申请接口
    public static final String AppMemberCommissionAudittoUpdate ="/AppMemberCommissionAudit/toUpdate";
    //根据用户ID查询该用户的可提现金额
    public static final String MemUsergetByUserMoney ="MemUser/getByUserMoney";
    //查询维修设备列表(售后服务)
    public static final String AppAfterSaleEquipmentqueryList = "/AppAfterSaleEquipment/queryList";
    //提交售后订单
    public static final String AfterSaleOrderafterSaleOrder = "/AfterSaleOrder/afterSaleOrder";
    //订单列表(售后)
    public static final String AfterSaleOrdergetByUserIdOrder = "/AfterSaleOrder/getByUserIdOrder";
    //删除订单(售后)
    public static final String AfterSaleOrdertoDeleteRepairOrder = "/AfterSaleOrder/toDeleteRepairOrder";
    //取消订单(售后)
    public static final String AfterSaleOrdergetByOrderRepairId = "/AfterSaleOrder/getByOrderRepairId";
    //去支付(售后)
    public static final String AfterSaleOrdergetByWxPay = "/AfterSaleOrder/getByWxPay";
    //订单详情售后
    public static final String AfterSaleOrdergetOneByOrderId = "/AfterSaleOrder/getOneByOrderId";
    //订单详情(售后去支付)
    public static final String AfterSaleOrdergetByWxPayDetails = "/AfterSaleOrder/getByWxPayDetails";
}
