package com.accp.myoa.web.action;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.openid4java.association.AssociationException;
import org.openid4java.association.AssociationSessionType;
import org.openid4java.consumer.ConsumerException;
import org.openid4java.consumer.ConsumerManager;
import org.openid4java.consumer.InMemoryConsumerAssociationStore;
import org.openid4java.consumer.InMemoryNonceVerifier;
import org.openid4java.consumer.VerificationResult;
import org.openid4java.discovery.DiscoveryException;
import org.openid4java.discovery.DiscoveryInformation;
import org.openid4java.discovery.Identifier;
import org.openid4java.message.AuthRequest;
import org.openid4java.message.MessageException;
import org.openid4java.message.ParameterList;

import com.accp.myoa.entity.Goods;
import com.accp.myoa.entity.Tmp;
import com.accp.myoa.entity.Users;
import com.accp.myoa.entity.tmpGoods;
import com.accp.myoa.service.GoodsService;
import com.accp.myoa.service.TmpService;
import com.accp.myoa.service.UsersService;
import com.accp.myoa.util.GetUsersInfo;
import com.accp.myoa.util.TaxUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

public class GoodsAction extends DispatchAction {

    private static Map<String, String> referencesMap   = new HashMap<String, String>();
    public static final String         GOOGLE_ENDPOINT = "https://steamcommunity.com/openid";
    public final ConsumerManager       manager         = new ConsumerManager();
    private static String steamUserInfoUrl="http://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/?key=7179C3828113D400D1B6BA9B1CF852F3&steamids=";

    static {
        referencesMap.put("'", "''");
    }

    private UsersService usersService;

    /**
     * Setter method for property <tt>usersService</tt>.
     * 
     * @param usersService value to be assigned to property usersService
     */
    public void setUsersService(UsersService usersService) {
        this.usersService = usersService;
    }

    private TmpService tmpService;

    /**
     * Setter method for property <tt>tmpService</tt>.
     * 
     * @param tmpService value to be assigned to property tmpService
     */
    public void setTmpService(TmpService tmpService) {
        this.tmpService = tmpService;
    }

    private Goods goods;

    /**
     * Setter method for property <tt>goods</tt>.
     * 
     * @param goods value to be assigned to property goods
     */
    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    private GoodsService goodsService;

    public void setGoodsService(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    public ActionForward toIndividualInfo(ActionMapping mapping, ActionForm form,
                                          HttpServletRequest request,
                                          HttpServletResponse response) {
        return mapping.findForward("individualInfo");
    }

    //escape sql tag with the source code.      
    public static String encode(String source) {
        if (source == null)
            return "";

        StringBuffer sbuffer = new StringBuffer(source.length());

        for (int i = 0; i < source.length(); i++) {
            String c = source.substring(i, i + 1);

            //System.out.println("c=" + c);      
            //System.out.println(referencesMap.get(c));      

            if (referencesMap.get(c) != null) {
                sbuffer.append(referencesMap.get(c));
            } else {
                sbuffer.append(c);
            }
        }
        return sbuffer.toString();
    }

    public ActionForward callbackfunc(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws MessageException,
                                                                    DiscoveryException,
                                                                    AssociationException,
                                                                    URISyntaxException {

        try {
            ParameterList paList = new ParameterList(request.getParameterMap());
           DiscoveryInformation discovered = (DiscoveryInformation) request.getSession()
                .getAttribute("openid-disc");
            StringBuffer receivingURL = request.getRequestURL();
            String queryString = request.getQueryString();
            if (queryString != null && queryString.length() > 0) {
                receivingURL.append("?").append(request.getQueryString());
            }
            VerificationResult verification = manager.verify(receivingURL.toString(), paList,
                discovered);
            Identifier verified = verification.getVerifiedId();
            String identifier = verified.getIdentifier();
            HttpSession session=request.getSession();
            if (identifier != null) {
                String steamId =identifier.substring(identifier.lastIndexOf("/")+1).trim();
                Users u=GetUsersInfo.getInfo(steamUserInfoUrl+steamId);
                Users isDBExist=(Users) usersService.getResult("from Users where steamid="+u.getSteamid());
                if(isDBExist==null){
                    usersService.add(u);    
                }else{
                    isDBExist.setAvatar(u.getAvatar());
                    isDBExist.setAvatarfull(u.getAvatarfull());
                    isDBExist.setAvatarmedium(u.getAvatarmedium());
                    isDBExist.setLastlogoff(u.getLastlogoff());
                    isDBExist.setPersonaname(u.getPersonaname());
                    isDBExist.setPersonastate(u.getPersonastate());
                    isDBExist.setPersonastateflags(u.getPersonastateflags());
                    isDBExist.setPrimaryclanid(u.getPrimaryclanid());
                    isDBExist.setProfilestate(u.getProfilestate());
                    isDBExist.setProfileurl(u.getProfileurl());
                    usersService.update(isDBExist);
                }
                request.setAttribute("steamId", steamId);
                session.setAttribute("user", u);                  
            }
        } catch (Exception e) {
            return mapping.findForward("toDotaManager");
        }

        return mapping.findForward("toDotaManager");
    }
    
    public ActionForward logout(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                               HttpServletResponse response){
        
        HttpSession session=request.getSession();
        session.invalidate();
        return mapping.findForward("toDotaManager");
    }
    public ActionForward login(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                               HttpServletResponse response) throws MessageException,
                                                             DiscoveryException,
                                                             AssociationException,
                                                             ConsumerException, IOException {

        manager.setMaxAssocAttempts(0);
        manager.setAssociations(new InMemoryConsumerAssociationStore());
        manager.setNonceVerifier(new InMemoryNonceVerifier(5000));
        manager.setMinAssocSessEnc(AssociationSessionType.DH_SHA256);
        String returnUrl = "http://dota2chukucun.com/goods.do?operate=callbackfunc";
        List discoveries = manager.discover(GOOGLE_ENDPOINT);
        DiscoveryInformation discovered = manager.associate(discoveries);
        request.getSession().setAttribute("openid-disc", discovered);
        AuthRequest authReq = manager.authenticate(discovered, returnUrl);
        if (!discovered.isVersion2()) {
            response.sendRedirect(authReq.getDestinationUrl(true));
        } else {
            response.sendRedirect(authReq.getDestinationUrl(true));
        }
        return null;

    }

    public ActionForward toDotaManager(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request, HttpServletResponse response) {
        long begin=System.currentTimeMillis();
        float tprice = 0f;
        float salePrice = 0f;
        int pageNum = 1;
        String steamId = request.getParameter("steamId");
        String isGuoFuId = request.getParameter("isGuoFuId");
        if (steamId.contains("/id/")) {
            int index_a = steamId.indexOf("/id/");
            steamId = steamId.substring(index_a + 4);
            if (steamId.contains("/")) {
                steamId = steamId.split("/")[0];
            }
        } else if (steamId.contains("/profiles/")) {
            int index_a = steamId.indexOf("/profiles/");
            steamId = steamId.substring(index_a + 10);
            if (steamId.contains("/")) {
                steamId = steamId.split("/")[0];
            }
        }
        //判断是否是国服id
        if(isGuoFuId!=null){
           BigInteger bigInteger=new BigInteger(steamId);
           bigInteger=bigInteger.add(new BigInteger("76561197960265728"));
           steamId=bigInteger.toString();
        }
        String type = request.getParameter("type");
        if (request.getParameter("pageNum") != null) {
            pageNum = Integer.parseInt(request.getParameter("pageNum"));
        }
        String hql = "from Tmp t where t.name is not null and  t.steamId='" + steamId
                     + "' and t.type=" + type + " order by price*1 desc";
        List<Tmp> list = tmpService.findByPage(hql, 100, pageNum);

        //查询有多少个没有取到价格的
        String noPriceSql = "from Tmp t where t.name is not null and  t.steamId='" + steamId
                            + "' and t.type=" + type + " and t.price='0.0'";
        String noPriceCount = tmpService.getCount(noPriceSql) + "";
        //算总价    
        //需要计算总价,否则直接传
        tprice = 0f;
        String h = "select sum(replace(price,'999','0'))  From Tmp where steamId='" + steamId
                   + "' and type=" + type;
        Object o = tmpService.getPrice(h);
        if (o != null)
            tprice = Float.parseFloat(o.toString());

        String sql_all_price = "select "
                               + " sum(case when price*1.0>=0 and price*1.0<=0.2 then price end) as 'a',"
                               + " sum(case when price*1.0>0.2 and price*1.0<=1 then price end) as 'b',"
                               + " sum(case when price*1.0>1 and price*1.0<=10  then price end) as 'c',"
                               + " sum(case when price*1.0>10 and price*1.0<999  then price end) as 'd'"
                               + " from Tmp where steamId='" + steamId + "' and type=" + type;
        Object[] price_object = (Object[]) tmpService.getPrice(sql_all_price);      
        for (Object obj : price_object) {
            if (obj != null) {
                salePrice += Float.parseFloat(obj.toString());                 
            }
        }
       
        String qujian_sum_object_sql = "select "
                + " sum(case when price*1.0>=0 and price*1.0<=0.2 then '1' end) as 'a',"
                + " sum(case when price*1.0>0.2 and price*1.0<=1 then '1' end) as 'b',"
                + " sum(case when price*1.0>1 and price*1.0<=10  then '1' end) as 'c',"
                + " sum(case when price*1.0>10 and price*1.0<999 then '1' end) as 'd'"
                + " from Tmp where steamId='" + steamId + "' and type=" + type;
        Object[] qujian_sum_object = (Object[]) tmpService.getPrice(qujian_sum_object_sql);    
        
        //价格和数量格式化输出
       for(int j=0;j<qujian_sum_object.length;j++){
           if(qujian_sum_object[j]!=null){
               qujian_sum_object[j]=new DecimalFormat("0").format(Float.parseFloat(qujian_sum_object[j].toString()));
           }
       }
       
       for(int k=0;k<price_object.length;k++){
           if(k==0&&price_object[k]!=null){
               price_object[k]=new DecimalFormat("0.00").format(Float.parseFloat(price_object[k].toString())/1.0);
           }
           if(k==1&&price_object[k]!=null){
               price_object[k]=new DecimalFormat("0.00").format(Float.parseFloat(price_object[k].toString())/1.0);
           }
           if(k==2&&price_object[k]!=null){
               price_object[k]=new DecimalFormat("0.00").format(Float.parseFloat(price_object[k].toString())/1.0);
           }
           if(k==3&&price_object[k]!=null){
               price_object[k]=new DecimalFormat("0.00").format(Float.parseFloat(price_object[k].toString())/1.0);
           }
       }
        //这个是要返回给页面的数据
        List<tmpGoods> tmpGoodsList = new ArrayList<>();
        tmpGoods tg = null;
        //去查价格和图片信息
        for (Tmp t : list) {
            tg = new tmpGoods();
            String n = encode(t.getName());
            String h1 = "from Goods where name='" + n + "'"+" and type='"+type+"'";
            int c = goodsService.getCount(h1);
            List<Goods> g = goodsService.findByPage(h1, c, 1);
            tg.setIconBase64(g.get(0).getIconBase64());
            tg.setImgurl(g.get(0).getImgurl());
            //名字使用中文名
            tg.setName(t.getChineseName());
            tg.setPrice(g.get(0).getPrice());
            tg.setNameColor(g.get(0).getNameColor());
            tg.setDesctype(t.getDesctype()); 
            tg.setId(t.getId());
            //处理desclist
            JSONArray arr= JSON.parseArray(t.getDescriptions());    
            List<String> desTmpList=new ArrayList<String>();
            if(arr!=null)
            {
                for(int i=0;i<arr.size();i++){
                   Map map=(Map) arr.get(i);
                   desTmpList.add(map.get("value").toString());
                }
            }
            tg.setDescriptions(desTmpList);
            tmpGoodsList.add(tg);
        }

        String tp = new DecimalFormat("0.00").format(tprice);
        String afterTaxPrice=new DecimalFormat("0.00").format(TaxUtil.calcAfterTaxPrice(tprice+""));
        String salePriceStr = new DecimalFormat("0.00").format(salePrice);
        int countForPage = tmpService.getCount(hql);
        int maxPage = countForPage % 100 > 0 ? (countForPage / 100 + 1) : (countForPage / 100);
        request.setAttribute("steamId", steamId);
        request.setAttribute("goods", tmpGoodsList);
        request.setAttribute("type", type);
        request.setAttribute("count", countForPage);
        request.setAttribute("total_price", tp);
        request.setAttribute("pageNum", pageNum);
        request.setAttribute("maxPage", maxPage);
        request.setAttribute("noPriceCount", noPriceCount);
        request.setAttribute("salePrice", salePriceStr);
        request.setAttribute("qujian_sum_object", qujian_sum_object);
        request.setAttribute("price_object", price_object);
        request.setAttribute("afterTaxPrice", afterTaxPrice);
        long endTime=System.currentTimeMillis();
        System.out.println("组装数据耗时:"+(endTime-begin));
        return mapping.findForward("toDotaManager");
    }

}
