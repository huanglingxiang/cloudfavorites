<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>favoriteAdd.jsp</title>

<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="this is my page">
<meta http-equiv="content-type" content="text/html; charset=UTF-8">

<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript">
	$(function() {

		$("#addButton").bind("click", function() {
			$.ajax({
				type : "post",
				url : "favorite.action?op=addFavorite",
				data : $("#addForm").serialize(),
				dataType : "json",
				success : function(data) {
					window.returnValue = data; //将结果的json存到   window.returnValue
					window.close();
				}
			});
		});

	});
</script>

</head>

<body>
	<form id="addForm" method="post">
		链接名:<input type="text" name="flabel" id="flabel" /><br /> 地址:<input
			type="text" name="furl" id="furl" /> <br /> 类别:<input type="text"
			name="ftags" id="ftags" /><br /> 描述:
		<textarea rows="5" cols="40" name="fdesc" id="fdesc"></textarea>
		<br /> <input id="addButton" type="button" value="添加" />
	</form>
</body>
</html>
