<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'upload.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
    <h2>上传单一文件 实例</h2>  
    
	<form action="<%=basePath%>upload/uploadFile.do" method="post" enctype="multipart/form-data">
		<input type="file" name="file" /> <input type="submit" value="Submit" />
	</form>
	
	
	<h2>上传多个文件 实例</h2>  
  
    <form action="<%=basePath%>upload/uploadFiles.do" method="post"  
        enctype="multipart/form-data">  
        <p>  
            选择文件:<input type="file" name="files">  
        <p>  
            选择文件:<input type="file" name="files">  
        <p>  
            选择文件:<input type="file" name="files">  
        <p>  
            <input type="submit" value="提交">  
    </form>  
</body>
</html>
