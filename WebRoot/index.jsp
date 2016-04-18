<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Dota2库存</title>
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
	ul.imglist li{ float:left; padding:1px 2px;} 
	ul.imglist li img{ display:block; width:62px; height:62px} 
	ul.imglist li span{ display:block; line-height:30px; } 
	</style>
	<script type="text/javascript">
	window.onload=function(){ 
		var steamId=$("steamId").value;
		var type=$("type").value;
		GoodsService.fetchData(steamId,type,fetchDataFinished); 
	}
	function fetch(){
		var steamId=$("steamId").value;
		var type=$("type").value;
		GoodsService.fetchData(steamId,type,fetchDataFinished); 
	}
	function fetchDataFinished(data){
		console.log(data);
		if (!data && typeof(data)!="undefined" && data!=0)
		{
		    alert("没有数据!");
		    return;
		}
		var total=0;
		var ul=document.getElementById("imglist");
		if(ul){
			document.getElementById("imglist").remove();
		}
		for(var i=0;i<data.length;i++){  
	         addRow(data[i].iconBase64,data[i].name,data[i].price);   // 表格添加一行
	         if(data[i].price){
		         var price=data[i].price.replace(/[$]/, "").replace(/USD/,"").replace(/ /,"");
		         total+=parseFloat(price);
	         }
	        }
		$("totalInfo").innerHTML="Total: $"+total+" ("+data.length+" items)";
	}
	function addRow(icon,name,price){        
	     	   
		   var div=document.getElementById("myDiv");
		   var ul=document.getElementById("imglist");
		   if(!ul)
	       ul=document.createElement("ul"); 
	       ul.id="imglist";
	       ul.className ="imglist";
	       var li= document.createElement("li"); 
	       var img = document.createElement("img"); 
	       img.style="vertical-align:middle;";
	       img.src="data:image/png;base64,"+icon;
	       img.title=name;
	       var span=document.createElement("span");
	       span.style="color: #5BD;";
	       span.innerHTML=price;
	       li.style="border:2px solid rgb(77, 116, 85);";
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
	  	<option value="730" selected="selected">DOTA2</option>
	  </select>
	SteamId:<input type="text" value="76561198070173809" id="steamId"/>
	<input type="button" value="fetch" onclick="fetch()"/>
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
