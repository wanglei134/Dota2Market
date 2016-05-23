<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Dota2库存数据采集页面</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">	
	<script type="text/javascript" src="dwr/engine.js"></script>
	<script type="text/javascript" src="dwr/util.js"></script>
	<script type="text/javascript" src="dwr/interface/GoodsService.js"></script>
	<style type="text/css">
	ul.imglist{ margin:0 auto; width:650px; overflow:hidden;background-color:#111;border-radius:5px;text-align:center;} 
	ul.imglist li{ float:left; padding:1px 2px;border:2px solid rgb(77, 116, 85);border:2px solid rgb(77, 116, 85);} 
	ul.imglist li img{ display:block; width:62px; height:62px;vertical-align:middle;} 
	ul.imglist li span{ display:block; line-height:30px; color: #5BD;width:62px;} 
	#span_count{width: 0;margin-top:-8px;margin-left: 1px;color: #5BD;}
	</style>
	<script type="text/javascript">
	/* window.onload=function(){ 
		var steamId=$("steamId").value;
		var type=$("type").value;
		GoodsService.fetchData(steamId,type,fetchDataFinished); 
	} */
	function fetch(){
		var steamId=$("steamId").value;
		var type=$("type").value;
		document.getElementsByTagName("input")[1].disabled=true;
		/* var ul=document.getElementById("imglist");
		if(ul){
			document.getElementById("imglist").remove();
		} */
		alert("开始进行数据采集,请稍等");
		GoodsService.fetchData(steamId,type,fetchDataFinished); 		
	}
	function fetchDataFinished(data){
		//console.log(data);
		alert(data);
		document.getElementsByTagName("input")[1].disabled=false;
		/*
		if (!data && typeof(data)!="undefined" && data!=0)
		{
		    alert("没有数据!");
		    return;
		}
		var total=0;
		var count_num=0;
		var ul=document.getElementById("imglist");
		if(ul){
			document.getElementById("imglist").remove();
		}
		for(var i=0;i<data.length;i++){  
	         // 表格添加一行
	         if(data[i].price){
	        	 addRow(data[i].iconBase64,data[i].name,data[i].price,data[i].wpnum);
		         var price=data[i].price;
		         total=accAdd(total,parseFloat(price));
		         count_num+=parseInt(data[i].wpnum);
	         }else{
	        	 addRow(data[i].iconBase64,data[i].name,"",data[i].wpnum);   
	         }
	        }
		$("totalInfo").innerHTML="Total: $"+total+" ("+count_num+" items)"; */
	}
	function accAdd(dataOne,dataTwo){ 
		var dataOneInt=dataOne.toString().split(".")[0]; 
		var dataOneFloat=""; 
		var dataTwoInt=dataTwo.toString().split(".")[0]; 
		var dataTwoFloat=""; 
		var lengthOne=0; 
		var lengthTwo=0; 
		var maxlength=0; 
		if(dataOne.toString().split(".").length==2){ 
		dataOneFloat=dataOne.toString().split(".")[1]; 
		lengthOne=dataOneFloat.toString().length; 
		} 
		if(dataTwo.toString().split(".").length==2){ 
		dataTwoFloat=dataTwo.toString().split(".")[1]; 
		lengthTwo=dataTwoFloat.toString().length; 

		} 
		maxLength=Math.max(lengthOne,lengthTwo); 
		for(var i=0;i<maxLength-lengthOne;i++){ 
		dataOneFloat+="0"; 
		} 
		for(var i=0;i<maxLength-lengthTwo;i++){ 
		dataTwoFloat+="0"; 
		} 
		/** 
		*对两个数据进行倍数放大 
		*使其都变为整数。因为整数计算 
		*比较精确。 
		*/ 
		var one=dataOneInt+""+dataOneFloat; 
		var two=dataTwoInt+""+dataTwoFloat; 
		//alert("dataOne:"+dataOne+" dataTwo:"+dataTwo +" one:"+one+" two:"+two); 
		/** 
		*数据扩大倍数后，经计算的到结果， 
		*然后在缩小相同的倍数 
		*进而得到正确的结果 
		*/ 
		var result= (Number(one)+Number(two))/Math.pow(10,maxLength); 
		return result; 
	} 
	function addRow(icon,name,price,count){        
	     	   
		   var div=document.getElementById("myDiv");
		   var ul=document.getElementById("imglist");
		   if(!ul)
	       ul=document.createElement("ul"); 
	       ul.id="imglist";
	       ul.className ="imglist";
	       var li= document.createElement("li"); 
	       var img = document.createElement("img"); 
	       img.src="data:image/png;base64,"+icon;
	       img.title=name;
	       var span=document.createElement("span");
	       span.innerHTML="$"+price;
	       var span_count=document.createElement("span");
	       span_count.id="span_count";
	       span_count.innerHTML="X"+count;
	       li.appendChild(span_count);
	       li.appendChild(img); 
	       li.appendChild(span);
           ul.appendChild(li);    
           div.appendChild(ul);
	    }  
	</script>
  </head>
  <body>
  <br>
  <br>
    <center>
	  Type:<select id="type">
	  	<option value="730">CS:GO</option>
	  	<option value="570" selected="selected">DOTA2</option>
	  </select>
	SteamId:<input type="text" value="76561198070173809" id="steamId"/>
	<input type="button" value="fetch" id="fetch" onclick="fetch()"/>
	<br>
	<br>
	<div id="totalInfo">
	</div>
	<br>
	<div id="myDiv" >
	</div>
	<table width="50%" id="mytable" border="1" bordercolor="whitesmoke">    
    </table>  
	</center>
  </body> 
</html>
