package com.accp.myoa.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Future;

import org.apache.commons.codec.binary.Base64;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.accp.myoa.entity.Goods;
import com.accp.myoa.entity.Tmp;
import com.accp.myoa.task.UpdateProxy;
import com.accp.myoa.web.action.GoodsAction;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

public class HttpUtil {

    private static String                                 ipUrl         = "http://qsrdk.daili666api.com/ip/?tid=556332115885300&num=1&filter=on&delay=5";
    private static String[]                               agents        = new String[] { "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_8; en-us) AppleWebKit/534.50 (KHTML, like Gecko) Version/5.1 Safari/534.50                               ",
                                                                                         "Mozilla/5.0 (Windows; U; Windows NT 6.1; en-us) AppleWebKit/534.50 (KHTML, like Gecko) Version/5.1 Safari/534.50                                        ",
                                                                                         "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0;                                                                                         ",
                                                                                         "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0; Trident/4.0)                                                                                         ",
                                                                                         "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.0)                                                                                                      ",
                                                                                         "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)                                                                                                      ",
                                                                                         "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.6; rv:2.0.1) Gecko/20100101 Firefox/4.0.1                                                                     ",
                                                                                         "Mozilla/5.0 (Windows NT 6.1; rv:2.0.1) Gecko/20100101 Firefox/4.0.1                                                                                     ",
                                                                                         "Opera/9.80 (Macintosh; Intel Mac OS X 10.6.8; U; en) Presto/2.8.131 Version/11.11                                                                       ",
                                                                                         "Opera/9.80 (Windows NT 6.1; U; en) Presto/2.8.131 Version/11.11                                                                                         ",
                                                                                         "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_0) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.56 Safari/535.11                                  ",
                                                                                         "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Maxthon 2.0)                                                                                         ",
                                                                                         "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; TencentTraveler 4.0)                                                                                 ",
                                                                                         "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)                                                                                                      ",
                                                                                         "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; The World)                                                                                           ",
                                                                                         "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Trident/4.0; SE 2.X MetaSr 1.0; SE 2.X MetaSr 1.0; .NET CLR 2.0.50727; SE 2.X MetaSr 1.0)            ",
                                                                                         "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; 360SE)                                                                                               ",
                                                                                         "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Avant Browser)                                                                                       ",
                                                                                         "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)" };

    private static ThreadLocal<List<Tmp>>                 threadtmpList;
    private static ThreadLocal<CopyOnWriteArrayList<Tmp>> threadmyList;

    public static void main(String[] args) throws Exception {
        setProxy();
    }

    /**
     * 判断字符串是否是数字
     * 
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static List<Tmp> fetchTmp(String streamId, String type,
                                     ThreadPoolTaskExecutor threadPoolTaskExecutor,                            
                                     HibernateTemplate hirbernate, int more,int xx) throws Exception {
        
        //只有前台第一次取数据才会初始化,否则保留已经存在的数据
        if(xx==0){
            threadtmpList=null;
            threadmyList=null;
            threadtmpList=new ThreadLocal<List<Tmp>>();
            threadmyList=new ThreadLocal<CopyOnWriteArrayList<Tmp>>();
        }
       
        CopyOnWriteArrayList<Tmp> myList = new CopyOnWriteArrayList<Tmp>();
        List<Tmp> tmpList = new ArrayList<Tmp>();
        String str1 = "http://steamcommunity.com/id/" + streamId + "/inventory/json/" + type
                      + "/2/";
        String str2 = "http://steamcommunity.com/profiles/" + streamId + "/inventory/json/" + type
                      + "/2/";
        String str = null;
        if (streamId.trim().length() == 17) {
            str = str2;
        } else {
            str = str1;
        }
        if (more != 0) {
            str += "?start=" + more + "&trading=1";
        } else {
            str += "?trading=1";
        }
        Tmp tmp = null;
        String icon_url_preix = "http://steamcommunity-a.akamaihd.net/economy/image/";
        //这个文件改造成先读取到本地文本,再读出来处理的方式
        //1、文件读到本地
        String fname = "d://" + streamId + (new Random().nextFloat()) + ".txt";
        DownHttpUtil.downloadFile(fname, str);
        //2、从本地加载文件
        int _5M = 1024 * 1024 * 5;
        File fin = new File(fname); //文件大小200M   
        FileChannel fcin = new RandomAccessFile(fin, "r").getChannel();
        ByteBuffer rBuffer = ByteBuffer.allocate(_5M);
        ByteBuffer wBuffer = ByteBuffer.allocateDirect(_5M);
        StringBuilder sBuilder = DownHttpUtil.readFileByLine(_5M, fcin, rBuffer, wBuffer);
        //3、加载好的文件交给json解析
        String jsondata = sBuilder.toString();
        JSONObject jsonObj;
        try {
            jsonObj = JSONObject.parseObject(jsondata);
        } catch (Exception e) {
            return null;
        }
        //1、判断返回json数据是否为true,否则是链接不对或者其他原因导致取不到数据
        if (jsonObj != null && jsonObj.get("success").toString().trim().equals("true")) {
            //获取数据项列表,准确数据列表,一项为一个数据
            Object jsonArray = jsonObj.get("rgInventory");
            LinkedHashMap<String, String> jsonMap = JSON.parseObject(jsonArray.toString(),
                new TypeReference<LinkedHashMap<String, String>>() {
                });
            for (Map.Entry<String, String> entry : jsonMap.entrySet()) {
                //保存键值
                JSONObject v = JSONObject.parseObject(entry.getValue());
                tmp=null;
                tmp = new Tmp();
                tmp.setClassid(v.getString("classid"));
                tmp.setInstanceid(v.getString("instanceid"));
                tmp.setSteamId(streamId);
                tmp.setType(type);
                tmp.setCreatetime(new Date());
                tmpList.add(tmp);
                tmp=null;
            }

            //处理价格问题
            Object priceArray = jsonObj.get("rgDescriptions");
            LinkedHashMap<String, String> priceMap = JSON.parseObject(priceArray.toString(),
                new TypeReference<LinkedHashMap<String, String>>() {
                });
            Collection<Future<?>> futures = new LinkedList<Future<?>>();
            myList.addAll(tmpList);
            for (Map.Entry<String, String> entry : priceMap.entrySet()) {
                muSunTask mytask = new muSunTask(entry, myList, tmpList, hirbernate, type);
                futures.add(threadPoolTaskExecutor.getThreadPoolExecutor().submit(mytask));
            }
            System.out.println("线程开启完毕，坐等结束"+(new Date()));
            int count = 0;
            for (Future<?> future : futures) {
                future.get();
                //System.out.println("任务：" + (count++) + " 结束了!");
            }

            //threadLocal变量设置
            if (threadmyList.get() == null) {
                threadmyList.set(myList);
            } else {
                threadmyList.get().addAll(myList);
            }
            if (threadtmpList.get() == null) {
                threadtmpList.set(tmpList);
            } else {
                threadtmpList.get().addAll(tmpList);
            }

            boolean moreOrNot = (boolean) jsonObj.get("more");
            //还有数据,把起始位置传过去
            if (moreOrNot) {
                int moreStart = Integer.parseInt(jsonObj.get("more_start").toString());
                fetchTmp(streamId, type, threadPoolTaskExecutor, hirbernate, moreStart,1);
            }

        } else {
            return null;
        }
        return new ArrayList<Tmp>(threadmyList.get());

    }

    /**
     * 获取字节流
     * 
     * @param inputStream
     * @return
     * @throws Exception
     */
    public static String readInputStream(InputStream inputStream) {
        byte[] encodeBase64 = null;
        try {
            byte[] buffer = new byte[1024];
            int len = -1;
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            while ((len = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }
            outputStream.close();
            inputStream.close();
            encodeBase64 = Base64.encodeBase64(outputStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(encodeBase64);
    }

    /**
     * 代理设置
     * 
     * @param host
     * @param port
     */
    public static void setProxy() {
        CopyOnWriteArrayList<String> list = UpdateProxy.ipList;
        //随机取一个
        int index = new Random().nextInt(list.size());
        String ip = list.get(index);
        String host = ip.split(":")[0];
        String port = ip.split(":")[1];
        /* System.out.println("正在使用代理ip:"+ip);*/
        System.setProperty("proxySet", "true");
        System.setProperty("proxyHost", host);
        System.setProperty("proxyPort", port);
    }

    /**
     * 1、返回字符串
     * 2、返回inputstream
     * 
     * @param urlStr
     * @param type
     * @return
     * @throws Exception
     */
    public static Object getJsonContent(String urlStr, int type) throws Exception {

        URL url = new URL(urlStr);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        // 设置连接属性
        int agentIndex = new Random().nextInt(agents.length);
        httpConn.setRequestProperty("User-Agent", agents[agentIndex].trim());
        httpConn.setConnectTimeout(3000);
        httpConn.setReadTimeout(3000);
        httpConn.setDoInput(true);
        httpConn.setRequestMethod("GET");
        // 获取相应码
        int respCode = httpConn.getResponseCode();
        if (respCode == 200) {
            if (type == 1 || type == 3) {
                return ConvertStream2Json(httpConn.getInputStream());
            } else if (type == 2) {
                return httpConn.getInputStream();
            }
        } else if (respCode == 429 && type == 3) {
            //后台线程才重试,用户体验好点
            setProxy();
            getJsonContent(urlStr, type);
        } else if (respCode == 500 && type == 3) {
            //后台线程才重试,用户体验好点
            setProxy();
            getJsonContent(urlStr, type);
        } else if (respCode == 503 && type == 3) {
            //后台线程才重试,用户体验好点
            setProxy();
            getJsonContent(urlStr, type);
        }
        return null;
    }

    private static String ConvertStream2Json(InputStream inputStream) {
        String jsonStr = "";
        // ByteArrayOutputStream相当于内存输出流
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[512];
        int len = 0;
        // 将输入流转移到内存输出流中
        try {
            while ((len = inputStream.read(buffer, 0, buffer.length)) != -1) {
                out.write(buffer, 0, len);
            }
            // 将内存流转换为字符串
            jsonStr = new String(out.toByteArray());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonStr;
    }
}

class muSunTask implements Runnable {
    private Map.Entry<String, String> entry;
    private CopyOnWriteArrayList<Tmp> myList;
    private List<Tmp>                 tmpList;
    private HibernateTemplate         hirbernate;
    private String                    icon_url_preix = "http://steamcommunity-a.akamaihd.net/economy/image/";
    private String                    type;
     
    
    /**
     * 
     */
    public muSunTask(Map.Entry<String, String> entry, CopyOnWriteArrayList<Tmp> myList,
                     List<Tmp> tmpList, HibernateTemplate hirbernate, String type) {
        this.entry = entry;
        this.myList = myList;
        this.tmpList = tmpList;
        this.hirbernate = hirbernate;
        this.type = type;
    }

    public void run() {
        JSONObject v = JSONObject.parseObject(entry.getValue());
        Goods gs = new Goods();
        String classId = v.getString("classid");
        String instanceId = v.getString("instanceid");
        String icon_url = v.getString("icon_url");
        String name = v.getString("market_hash_name");
        String market_hash_name = v.getString("market_hash_name");
        String marketable = v.getString("marketable");
        String nameColor = v.getString("name_color");
        String desctype=v.getString("type");
        //中文名获取
        String chineseName = v.getString("market_name");
        //特殊html标签过滤
        int j=chineseName.indexOf(">");
        if(j!=-1)
        {
            chineseName=chineseName.substring(j+1);
            chineseName=chineseName.replaceAll("'", ""); 
        }        
        gs.setClassid(classId);
        gs.setInstanceId(instanceId);
        gs.setName(name);
        gs.setType(type);
        gs.setNameColor(nameColor);
        gs.setLastupdatetime(new Date());

        //不可交易的直接返回
        if (marketable.equals("0")) {
            return;
        }
        
        //descriptions和tags处理
        String desArray = v.get("descriptions").toString();            
  
        //更新每个人商品hash名字和中文名
        for (Tmp tmp2 : myList) {
            if (tmp2.getClassid().equals(classId) && tmp2.getInstanceid().equals(instanceId)) {
                tmp2.setName(market_hash_name);
                tmp2.setChineseName(chineseName);
                tmp2.setDescriptions(desArray);
                tmp2.setDesctype(desctype);

            }
        }
        //判断该物品在物品库有木有,如果有就不处理,没有就存进去
        //新的逻辑是根据classId,instanceId取得名字,根据名字来匹配
        String n = GoodsAction.encode(market_hash_name);
        String hql = "from Goods where name='" + n + "'"+" and type='"+type+"'";
        List<Goods> g = hirbernate.find(hql);
        if (g.size() > 0) {
            //什么也不做,只是更新一下价格还有商品的颜色信息
            //触发器来完成这个工作
            for (Tmp tmp2 : myList) {
                if (tmp2.getClassid().equals(classId) && tmp2.getInstanceid().equals(instanceId)) {
                    tmp2.setPrice(g.get(0).getPrice());
                }
            }
            /*for (Goods goods : g) {
                goods.setClassid(classId);
                goods.setInstanceId(instanceId);
                goods.setName(name);
                goods.setType(type);
                goods.setNameColor(nameColor);
                hirbernate.update(goods);
            }*/

        } else {
            //读图片存到数据库
           byte[] imgByte = null;
            try {
                imgByte = ImageUtil.scale2(
                    (InputStream) HttpUtil.getJsonContent(icon_url_preix + icon_url, 2), 62, 62,
                    true);
                gs.setIconBase64(HttpUtil.readInputStream(new ByteArrayInputStream(imgByte)));
            } catch (Exception e1) {
                gs.setImgurl(icon_url_preix + v.get("icon_url"));
            }
            
            /*

            try {
                //组装查价格链接
                String priceUrl = "http://steamcommunity.com/market/priceoverview/?country=CN&currency=1&appid="
                                  + type + "&market_hash_name=";
                String hash_name_url = null;
                try {
                    hash_name_url = URLEncoder.encode(v.get("market_hash_name").toString(),
                        "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } //已经转空格为+了
                hash_name_url = hash_name_url.replace("+", "%20");
                priceUrl = priceUrl + hash_name_url;
                String pricedata = HttpUtil.getJsonContent(priceUrl, 1).toString();
                JSONObject pricejson = JSONObject.parseObject(pricedata);
                //lower_price
                if (pricejson != null
                    && pricejson.get("success").toString().trim().equals("true")) {

                    if (pricejson.get("lowest_price") != null) {
                        gs.setPrice(pricejson.get("lowest_price").toString().replace("$", "")
                            .replace("USD", ""));
                    } else if (pricejson.get("median_price") != null) {
                        gs.setPrice(pricejson.get("median_price").toString().replace("$", "")
                            .replace("USD", ""));
                    } else {
                        //取不到价格的作价999,后面显示的时候显示为0
                        gs.setPrice("999");
                    }
                }else if(pricejson != null
                    && pricejson.get("success").toString().trim().equals("false")){
                    gs.setPrice("999");
                }

            } catch (Exception e) {
                gs.setPrice("0.0");
            }
            for (Tmp t : myList) {
                if (t.getClassid().equals(gs.getClassid())
                    && t.getInstanceid().equals(gs.getInstanceId())) {
                    t.setPrice(gs.getPrice());
                }
            }*/
            gs.setPrice("0.0");           
            hirbernate.saveOrUpdate(gs);
            
        }
    }
}
