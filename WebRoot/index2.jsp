<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>CSGO出库存  DOTA2出库存</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="CSGO出库存  DOTA2出库存">
		<meta http-equiv="description" content="CSGO出库存  DOTA2出库存">
		<script type="text/javascript" src="dwr/engine.js"></script>
		<script type="text/javascript" src="dwr/util.js"></script>		
		<script type="text/javascript" src="dwr/interface/TmpService.js"></script>
		<script src="scripts/artDialog4.1.7/artDialog.js?skin=simple"></script>
		<script src="http://pv.sohu.com/cityjson?ie=utf-8"></script>  
		<link rel="stylesheet" type="text/css" href="<%=path %>/css/chosen.css">
		<link rel="stylesheet" type="text/css" href="<%=path %>/css/font.css">
		<link rel="stylesheet" type="text/css" href="<%=path %>/css/main.css">
		<link rel="stylesheet" type="text/css" href="<%=path %>/css/slimbox.css">
	<script type="text/javascript">	
	var flag=-1;
	var Bprice=[];
	
	window.onload=function(){ 
		document.getElementById("fetch").disabled=false;	
		calcI();
	}
	function aftertaxI(){
		if(flag==0)
		{
			document.getElementById("aftertaxprice").value="显示税后"
			var pNode=document.getElementById('myDiv').getElementsByTagName('li');
			 for( var i=0; i<pNode.length; i++){
		       pNode[i].getElementsByTagName('span')[0].innerHTML=Bprice[i];
		       }
			 flag=1;
		}
		else if(flag==-1 |flag==1)
		{
			if(flag==-1)
			{
				getbeforeprice();
			}
			document.getElementById("aftertaxprice").value="显示税前"
			getTableContent();
			flag=0;
		}
	}
	function getbeforeprice(){
		var pNode=document.getElementById('myDiv').getElementsByTagName('li');
		for( var i=0; i<pNode.length; i++){
	    	Bprice[i]=pNode[i].getElementsByTagName('span')[0].innerHTML;
	        }
	}
	function getTableContent(){
		  var pNode=document.getElementById('myDiv').getElementsByTagName('li');
		  for( var i=0; i<pNode.length; i++){
	            var price=pNode[i].getElementsByTagName('span')[0].innerHTML;
	            var pricearr=price.split("$");
	            price=pricearr[1];
	            if(price>0)
	            pNode[i].getElementsByTagName('span')[0].innerHTML="$"+gettaxprice(price).toFixed(2);
	            }
		}
	function gettaxprice(price){
        var saleprice = price;
        var  dota2fee = Math.floor(saleprice * 1000 *2 / 23 / 10);
        if (dota2fee <=1)
        {
            dota2fee = 0.01;
        }
        else
        {
            dota2fee =dota2fee / 100;
        }
        var vfee = Math.floor(saleprice * 1000 /23/ 10);
        if (vfee <=1)
        {
            vfee = 0.01;
        }
        else
        {
            vfee = vfee / 100;
        }
        saleprice = saleprice - vfee-dota2fee;
        return saleprice;
	}

	function appInfo(){  
	    var browser = {appname: 'unknown', version: 0},  
	        userAgent = window.navigator.userAgent.toLowerCase();  
	    if ( /(msie|firefox|opera|chrome|netscape)\D+(\d[\d.]*)/.test( userAgent ) ){  
	        browser.appname = RegExp.$1;  
	        browser.version = RegExp.$2;  
	    } else if ( /version\D+(\d[\d.]*).*safari/.test( userAgent ) ){ // safari  
	        browser.appname = 'safari';  
	        browser.version = RegExp.$2;  
	    }  
	    return browser;  
	}  
	
		function checkNum(){
			if(document.getElementById("pageNum").value>document.getElementById("maxPage").value){
				alert("请输入1到"+document.getElementById("maxPage").value+"之间的数字");	
				return false;
			}
			return true;
		}
		function changeValue(my){
			document.getElementById("steamId").value;
			return true;
		}
		function fetchI(){
			var steamId=$("steamId").value;
			var type=$("type").value;
			var isGuoFuId=$("isGuoFuId").checked;
			document.getElementById("fetch").disabled=true;	
			document.getElementById("info").innerHTML="开始进行数据采集,请稍等";
			var ip=returnCitySN["cip"];
			var city=returnCitySN["cname"];
			var browser=appInfo();
			TmpService.fetchData(steamId,type,isGuoFuId,ip,city,browser.appname+browser.version,fetchDataFinished); 		
		}
		function fetchDataFinished(data){
			document.getElementById("info").innerHTML=data;
			if(data=='数据采集完毕'){
				document.getElementById("goodsForm").submit();
			}	else{
				document.getElementById("fetch").disabled=false;	
			}		
		}
		
		function hiddenvalueI(){
			if(document.getElementById("rmbvalue").style.display=="block")
			{
				document.getElementById("rmbvalue").style.display="none";
				document.getElementById("hiddenvalue").value="显示估价";
			}
			else
			{
				document.getElementById("rmbvalue").style.display="block";
				document.getElementById("hiddenvalue").value="隐藏估价";
			}	
		}
	 function calcI(){
		 var p1=document.getElementById("p1").value;
		 var p2=document.getElementById("p2").value;
		 var p3=document.getElementById("p3").value;
		 var p4=document.getElementById("p4").value;
		 
		 var b1=document.getElementById("b1").value;
		 var b2=document.getElementById("b2").value;
		 var b3=document.getElementById("b3").value;
		 var b4=document.getElementById("b4").value;
		 if (isNaN(b1)||isNaN(b2)||isNaN(b3)||isNaN(b4)){
			 alert("请输入有效数字!");
		 }
		 var newPrice1=p1*b1;	
		 var newPrice2=p2*b2;
		 var newPrice3=p3*b3;
		 var newPrice4=p4*b4;		
		 document.getElementById("salePrice").innerHTML=(newPrice1+newPrice2+newPrice3+newPrice4).toFixed(2);				 
	 }
	 
	 function showdes(des){
		 var x=document.getElementById(des).innerHTML;
		 art.dialog({
			 	follow:document.getElementById('img'+des),
			 	width:200,
			 	padding:0,
			    content: x
		});
	 }
	 
	function closedes(){
		var list = art.dialog.list;
		for (var i in list) {
		    list[i].close();
		};
	}
	 
	</script>
	</head>

	<body bgcolor="#D3DBEE">
	<br>
	<br>
	    <div style="margin:0 auto;text-align:center;width:82%;float:left">
		<div>
		<div style="width:20%;float:left">
		<!-- 左上角留白-->
  &nbsp;
		</div>
		<div style="width:80%;text-align:center;float:left">
		<form id="goodsForm" action="goods.do?operate=toDotaManager" method="post">
	  <span>库存类型:</span>
	  <select name="type" id="type" class="chosen-select dropdown">
	   	<c:if test="${not empty type}">
		  	<c:if test="${type eq 570}">
		  		<option value="730">CS:GO</option>
		  		<option value="570" selected="selected">DOTA2</option>
		  	</c:if>
		  	<c:if test="${type eq 730}">
		  		<option value="730" selected="selected">CS:GO</option>
		  		<option value="570" >DOTA2</option>
		  	</c:if>
		</c:if>
	  	<c:if test="${empty type}">
	  		<option value="730" >CS:GO</option>
	  		<option value="570" selected="selected">DOTA2</option>
	  	</c:if>
	  </select>
	SteamId:<input type="text" id="steamId" value="${steamId }" name="steamId"/>
	<input type="checkbox" name="isGuoFuId" id="isGuoFuId" balue="aaa"/>国服ID
	<input type="button" value="点击查询库存" id="fetch" onclick="fetchI()" class="button-blue" style="float:none"/>
	<br>
	<span id="info"></span>
	&nbsp;&nbsp;
	</form>	
	</div>
</div>
<div>
	<div>
		<div style="float:left;width:20%">
			<!-- 左侧广告位 -->
			&nbsp;
			<!-- <div style="margin-top:80px;margin-left:15%">
			<a target="_blank" href="http://xiaoweigame.taobao.com">
			<img width="200px" alt="小薇电玩Dota2店铺" src="img/taobao.jpg"/>
			</a>
			</div> -->
		</div>	
	</div>
		<logic:notEmpty name="goods" scope="request">	
		<div>
			<input type="hidden" id="total_price" value="${total_price }"/>
			<br>
			<span style="color:red" id="info"></span>
			<br>
			<input type="hidden" id="maxPage" name="maxPage" value="${maxPage }" >			
			<div id="myDiv" style="float:left;width:80%">
				<div id="totalInfo">
				<span>Total:</span><span style="color:red;font-weight:bold;">$${total_price }</span><span>(</span>${count }<span> items)</span>
				<span>税后价格:<span style="color:red;font-weight:bold;">$${afterTaxPrice }</span></span>
				</div>
				<br>	
				<c:if test="${noPriceCount>0}">
					<span>库存有<span style="color:red;font-weight:bold;">${noPriceCount }件</span>物品未能查询到价格,请稍后...</span><br>
				</c:if>									
				<span id="rmbvalue">库存收购估价:<span style="color:red;font-weight:bold;font-size:30px" id="salePrice" ><%-- ￥${salePrice } --%></span>,有意出售请联系<a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=454536775&site=qq&menu=yes"><img border="0" src="http://wpa.qq.com/pa?p=2:454536775:51" alt="如有出售意愿，可联系QQ" title="如有出售意愿，可联系QQ"/></a>
				或添加Steam好友<a href="steam://friends/add/76561198070173809"><img border="0" src="img/steam.ico"  alt="如有出售意愿，可添加Steam好友" title="如有出售意愿，可添加Steam好友"></img></a>
				</span>
				<br>
				<div>
				&nbsp
				<ul id="imglist" class="imglist" style="margin:0 auto; width:740px; overflow:hidden;background-color:#111;border-radius:5px;text-align:center;">
				<logic:iterate id="good" name="goods">				
						<li style="margin-top:5px;float:left; padding-left:1px;" >
								<%-- <span id="span_count">X${good.wpnum }</span> --%>
								<c:if test="${not empty good.iconBase64}">
								<div style="border:5px solid #${good.nameColor}">
									<img style="display:block; width:62px; height:62px;vertical-align:middle;" id="img${good.id }"  onmouseout="closedes()" onmousemove="showdes('${good.id }')" src="data:image/png;base64,${good.iconBase64 }"></img>
								</div>
								</c:if>
								<c:if test="${empty good.iconBase64}">
								<div style="border:5px solid #${good.nameColor}">
								<%-- title="${good.name }" --%> 
									<img style="display:block; width:62px; height:62px;vertical-align:middle;" id="img${good.id }" onmouseout="closedes()" onmousemove="showdes('${good.id }')" src="${good.imgurl }"></img>
									</div>
								</c:if>	
								<c:if test="${good.price=='999'}">
									<span style="font-size:9px;display:block; line-height:30px; color: #5BD;width:62px;">$0</span>		
								</c:if>									
								<c:if test="${good.price!='999'}">
									<span style="font-size:9px;display:block; line-height:30px; color: #5BD;width:62px;">$${good.price}</span>		
								</c:if>													
							</li>	
							<!-- 遍历descriptions -->	
								<div style="display:none">		
								<div id="${good.id }" style="font-size:5px;background: #9d9d9d;text-align: left;">
								<div style="font-size:5px;background: #9d9d9d;text-align: left;">
								<span style="font-weight:bold;">${good.name }</span>
									<br>
									${good.desctype }
									<br>	
									<c:if test="${not empty good.descriptions}">			
									<c:forEach var="subList" items="${good.descriptions}">
           								<span >${subList }</span>
										<br>
      						 		 </c:forEach>	
      						 		 </c:if>
      						 	</div>
      						 	</div>
      						  </div>														
				</logic:iterate>
				</ul>
				</div>
			
			<br>
			<br>
			<table align="center"  cellpadding="10" cellspacing="0">

				<tr height="10px">
					
					<td>
					<c:if test="${pageNum eq 1 }">
						<a>首页</a> <a>上一页</a> 
					</c:if>
					<c:if test="${pageNum gt 1 }">
						<a  href="goods.do?operate=toDotaManager&type=${type}&steamId=${steamId}" class="button-blue" style="float:none">首页</a>
						<a  href="goods.do?operate=toDotaManager&pageNum=${pageNum-1 }&type=${type}&steamId=${steamId}" class="button-blue" style="float:none">上一页</a>
					</c:if>
					<c:if test="${pageNum eq maxPage }">
						<a>下一页</a> <a>尾页</a> 
					</c:if>
					<c:if test="${pageNum lt maxPage }">
						<a href="goods.do?operate=toDotaManager&pageNum=${pageNum+1 }&type=${type}&steamId=${steamId}" class="button-blue" style="float:none">下一页</a>
						<a href="goods.do?operate=toDotaManager&pageNum=${maxPage }&type=${type}&steamId=${steamId}" class="button-blue" style="float:none">尾页</a>
					</c:if>
					</td>
					<td >
						 第<input type="text" style="width: 20px" maxlength="2"  name="pageNum" value="${pageNum }" id="pageNum" >/${maxPage }页 
						 <input type="submit" value="跳转" onClick="return checkNum()" class="button-blue" style="float:none" />
					</td>
				</tr>
			</table>
			</div>
			</div>
		</logic:notEmpty>
		</div>
		<br>
		<br>
	</div>

	<div style="float:left;width:18%;">
	<%-- <logic:notEmpty name="user" scope="session">
			<a href="<%=path%>/user.do?operate=show&uid=${user.id}">欢迎您,<span style="color:red;font-size:20px">${user.personaname }</span>	
			<!-- </a> -->
			<br>
			<a href="goods.do?operate=logout" class="button-blue" style="float:none">退出登录</a>
			<br>
			<br>				
	</logic:notEmpty>
	<logic:empty name="user" scope="session">
			<span style="color:red;">登陆后自动填写steamID</span>		
	<br>
	<a href="<%=path %>/goods.do?operate=login" style="text-decoration:none;" target="_self">
	<img src="https://steamcommunity-a.akamaihd.net/public/images/signinthroughsteam/sits_small.png" width="154" height="23" border="0">
	</a>
	<br>
	</logic:empty> --%>	
	<logic:notEmpty name="goods" scope="request">
	<br>			
	 当前估价标准:
	 <input type="hidden" id="p1" value="${price_object[0] }">
	 <input type="hidden" id="p2" value="${price_object[1] }">
	 <input type="hidden" id="p3" value="${price_object[2] }">
	 <input type="hidden" id="p4" value="${price_object[3] }">
	 <table border="1">
		<tr>
		  <th>区间</th>
		  <th>数量</th>
		  <th>总价</th>
		  <th >比例</th>
		</tr>   
		<tr>
		  <td style="width:50px">0-0.2</td>
		  <td>${qujian_sum_object[0] }</td>
		  <td id="np1">$${price_object[0] }</td>
		  <c:if test="${type eq 570}">
		 	 <td ><input type="text" id="b1" value="1" style="width:40px"/></td>
		  </c:if>
		  <c:if test="${type eq 730}">
		 	 <td ><input type="text" id="b1" value="1" style="width:40px"/></td>
		  </c:if>
		  
		</tr>
		<tr>
		  <td style="width:50px">0.2-1</td>
		  <td>${qujian_sum_object[1] }</td>
		  <td id="np2">$${price_object[1] }</td>
		  
		  <c:if test="${type eq 570}">
		 	 <td ><input type="text" id="b2" value="2" style="width:40px"/></td>
		  </c:if>
		  <c:if test="${type eq 730}">
		 	 <td ><input type="text" id="b2" value="2" style="width:40px"/></td>
		  </c:if>
		</tr>
		<tr>
		  <td style="width:50px">1-10</td>
		  <td>${qujian_sum_object[2] }</td>
		  <td id="np3">$${price_object[2] }</td>
		  
		  <c:if test="${type eq 570}">
		 	 <td ><input type="text" id="b3" value="3.9" style="width:40px"/></td>
		  </c:if>
		  <c:if test="${type eq 730}">
		 	 <td ><input type="text" id="b3" value="4" style="width:40px"/></td>
		  </c:if>
		</tr>
		<tr>
		  <td style="width:50px">>10</td>
		  <td>${qujian_sum_object[3] }</td>
		  <td id="np4">$${price_object[3] }</td>
		 
		   <c:if test="${type eq 570}">
		 	 <td ><input type="text" id="b4" value="4.3" style="width:40px"/></td>
		  </c:if>
		  <c:if test="${type eq 730}">
		 	 <td ><input type="text" id="b4" value="4" style="width:40px"/></td>
		  </c:if>
		</tr>
	</table>
	<br>
	<input type="button"  value="隐藏估价" id="hiddenvalue" onclick="hiddenvalueI()" class="button-blue" style="float:none;margin:0;width:65" />	
	<input type="button"  value="显示税后" id="aftertaxprice" onclick="aftertaxI()" class="button-blue" style="float:none;margin:0;width:65" />
	<input type="button"  value="计算估价" id="calc" onclick="calcI()" class="button-blue" style="float:none;margin:0;width:65"/>	
	</logic:notEmpty>	
	<br>
	<a href="steam://friends/add/76561198070173809">添加Steam好友<img border="0" src="img/steam.ico"  alt="如有出售意愿，可添加Steam好友" title="如有出售意愿，可添加Steam好友"></img></a>
	<br>
	<br>
	<a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=454536775&site=qq&menu=yes"><img border="0" src="http://wpa.qq.com/pa?p=2:454536775:51" alt="如有出售意愿，可联系QQ" title="如有出售意愿，可联系QQ"/></a>	
	<br>
	<br>
	<a target="_blank" href="http://shang.qq.com/wpa/qunwpa?idkey=b645d0b9c6e5a9554365ee660f96a54b5c5be9c7c0d13a63b049858b59df263a"><img border="0" src="http://pub.idqqimg.com/wpa/images/group.png" alt="DOTA2/CSGO收库存网总群" title="DOTA2/CSGO收库存网总群"></a>
	<br>
	<!-- <a target="_blank" href="http://tufu888.taobao.com">
	<img  src="img/ad.png" width="200px" alt="屠夫888dota2饰品"/>
	</a> -->
	</div>
	</body>
</html>
