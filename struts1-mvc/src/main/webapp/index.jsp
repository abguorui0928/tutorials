<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>struts-mvc</title>
<script type="text/javascript" src="jquery-1.10.2.js"></script>
</head>
<body>
	<%=request.getAttribute("welcome")%>

	<br />
	<button id="test">test</button>
</body>
<script type="text/javascript">
	$(function() {
		$("#test").on("click",
			function() {
				$.ajax({
					url : "http://toutiao.com/api/article/recent/?source=2&count=20&category=news_society&utm_source=toutiao&offset=0&callback=?",
					dataType : 'jsonp',
					type : 'get',
					success : function(data) {
						alert(data.message);
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						alert(XMLHttpRequest.status);
					}
				});
			});
	});
</script>
</html>