<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<title>收藏</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<link rel="stylesheet" type="text/css" href="styles.css">
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>

<script type="text/javascript">
	
	function add(){ //js中有三种对象：js内置对象（Date,Array,eval,） 、dom(文档对象模型) 、 BOM(浏览器模型) 
		//showModalDialog是js中的一种窗口，可以回传值
		var r=window.showModalDialog(
			'favoriteAdd.html',
			'newwindow',
			'height=300, width=400, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no'
		);
	//添加成功
		alert(r);
		if(r&&r.code==1){
			init();
		}
	}
	
	function showTags(type){
		$.ajax({
			type:"post",
			url:"favorite.action",
			data:"op=findAllTag",
			dataType:"json",
			success:function(data){
				if(type==1){ //显示所有标签到左边树
					$("#taglink").html('');
					$.each(data.obj,function(i,entity){
						$("#taglink").append(
								"<tr><td class='selected_label'><a href=javascript:findAllFavorite('"+entity.tname+"')>"+entity.tname+"</a></td></tr>"                 
						);
					});
				}else{
					$("#content_links").html('');
					$.each(data.obj,function(i,entity){
						var fontsize=entity.tcount/2+3;//字体大小
						$("#content_links").append(
								"<font size='"+fontsize+"'><a href=javascript:findAllFavorite('"+entity.tname+"')>"+entity.tname+"</a></font>&nbsp&nbsp;"                 
						);
					});
				}
			}
		});
	}
	
	function init(){
		showTags(1); //左边显示标签
		findAllFavorite('全部'); //显示全部链接
	}
	
	function findAllFavorite(type){
		var typeval='op=findFavorite&tname='+encodeURI(type);  //encodeURI 中文编码
		var urlstring="favorite.action";
		$.ajax({
			
			type:"post",
			url:urlstring,
			data:typeval,
			dataType:"json",
			success:function(data){
				$("#content_links").html('');
				$.each(
					data.obj,
					function(i,entity){
						var result="<div style='padding:6px 10px'>";
						result+="<div><a href='http://"+entity.furl+"' style='color:blue;font-size:18px;' target='_bank'>"+entity.flabel+"</a></div>";
						result+="<div style='color:green;font-size:14px;'>"+entity.furl+"</div></div>";
						$("#content_links").append(result);
					}
				);
			}
		})
	}
	
	$(function(){
		init();
	});
	
	
</script>
</head>
<body>
	<form name="favForm" method="post"
		action="/cang/fav.do;jsessionid=FF8C0487A99814B144B07E5633524A0F">
		<input type="hidden" name="op" value="toList" />

		<div class="banner" valign="top">收藏</div>

		<table cellspacing="0" cellpadding="0" border="0">
			<tr>
				<td style="text-align: right;" valign="top">
					<!-- 左边Tag列表 -->
					<div class="left_labels">


						<table class="labels_table" cellspacing="0" cellpadding="0"
							border="0">
							<tr>
								<td><a href="#" onclick="add();" style="font-weight: bold;">添加书签</a></td>
							</tr>
							<tr>
								<td class="selected_label"><a
									href="javascript:findAllFavorite('全部');">全部</a></td>
							</tr>
							<tr>
								<td><a href="javascript:findAllFavorite('未分类')">未分类</a></td>
							</tr>

							<tr>
								<td>
									<table>
										<tbody id="taglink">
											<!-- tag列表 -->
										</tbody>
									</table>
								</td>
							</tr>

							<tr>
								<td><a style="font-weight: bold;" href="javascript:showTags(2)">云图</a>
								</td>
							</tr>
						</table>
					</div>
				</td>
				<td>
					<!-- 右边fav内容 -->
					<div class="content_links" id="content_links"></div>

				</td>
			</tr>
		</table>
	</form>
</body>
</html>