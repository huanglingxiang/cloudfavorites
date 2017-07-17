<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">

<title>�Ѳ�</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<link rel="stylesheet" type="text/css" href="styles.css">
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>

<script type="text/javascript">
	function add() {
		//js�������ֶ���:  js���ö���  (Date, Array,eval, ),   dom(�ĵ�����ģ��)    BOM( ���������ģ��)
		var r = window
				.showModalDialog(
						'favoriteAdd.jsp',
						'newwindow',
						'height=300, width=400, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no');

		if (r && r.code == 1) {
			init();
		}
	}

	function showTags(type) {
		$.ajax({
			type : "post",
			url : "favorite.action",
			data: "op=findAllTag",
			dataType : "json",
			success : function(data) {
				if (type == 1) { //��ʾ���б�ǩ�������
					document.getElementById("taglink").innerHTML = '';
					$.each(data.obj, function(i, entity) {
						$("#taglink").append(
								"<tr ><td class='selected_label'><a href=javascript:findAllFavorite('"
										+ entity.tname + "')>" + entity.tname
										+ "</a></td></tr>");
					});
				} else {
					document.getElementById("content_links").innerHTML = '';
					$.each(data.obj, function(i, entity) {
						var fontsize = entity.tcount / 2 + 3;   //�����С
						$("#content_links").append(
								"<font size='"+fontsize+"'><a href=javascript:findAllFavorite('"
										+ entity.tname + "')>" + entity.tname
										+ "</a></font>&nbsp;&nbsp;");
					});
				}

			}
		});
	}

	function init() {
		showTags(1);  //�����ʾ��ǩ
		findAllFavorite('ȫ��');   //��ʾȫ������
	}

	function findAllFavorite(type) {
		var typeval = 'op=findFavorite&tname=' + encodeURI(type);     //    encodeURI   ���ı���
		var urlstring = "favorite.action";

		$
				.ajax({
					type : "post",
					url : urlstring,
					data : typeval,
					dataType : "json",
					success : function(data) {
						document.getElementById("content_links").innerHTML = '';
						$
								.each(
										data.obj,
										function(i, entity) {
											var result = "<div style='padding:6px 10px;'>";
											result += "<div><a href="+entity.furl+" style='color:blue;font-size:18px;' target='_blank'>"
													+ entity.flabel
													+ "</a></div><div style='color:black;font-size:16px;'>"
													+ entity.flabel + "</div>";
											result += "<div style='color:green;font-size:14px;'>"
													+ entity.furl
													+ "</div></div>";

											$("#content_links").append(result);

										});
					}
				});
	}

	$(function() {
		init();
	});
</script>
</head>
<body>
	<form name="favForm" method="post"
		action="/cang/fav.do;jsessionid=FF8C0487A99814B144B07E5633524A0F">
		<input type="hidden" name="op" value="toList" />

		<div class="banner" valign="top">�Ѳ�</div>

		<table cellspacing="0" cellpadding="0" border="0">
			<tr>
				<td style="text-align: right;" valign="top">
					<!-- ���Tag�б� -->
					<div class="left_labels">


						<table class="labels_table" cellspacing="0" cellpadding="0"
							border="0">
							<tr>
								<td><a href="#" onclick="add();" style="font-weight: bold;">�����ǩ</a>
								</td>
							</tr>
							<tr>
								<td class="selected_label"><a
									href="javascript:findAllFavorite('ȫ��')">ȫ��</a></td>
							</tr>
							<tr>
								<td><a href="javascript:findAllFavorite('δ����')">δ����</a></td>
							</tr>

							<tr>
								<td>
									<table>
										<tbody id="taglink">

										</tbody>
									</table>
								</td>
							</tr>

							<tr>
								<td><a style="font-weight: bold;"
									href="javascript:showTags(2)">��ͼ</a></td>
							</tr>
						</table>
					</div>
				</td>
				<td>
					<!-- �ұ�fav���� -->
					<div class="content_links" id="content_links"></div>
				</td>
			</tr>
		</table>

	</form>
</body>
</html>