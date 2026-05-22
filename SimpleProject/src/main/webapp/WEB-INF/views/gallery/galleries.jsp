<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<style> 
	.list-area{
		text-align : center;
	}

	.thumbnail{
		box-shadow : 1px 1px 2px #0000002e;
		width : 300px;
		padding : 12px;
		margin : 25px;
		display : inline-block;
		background-color: #ffffffb0;
		color:#000000b0;
		font-weight: bold;
		border-radius: 12px;
	}

	.thumbnail > img{
		width : 270px;
		height : 180px;
		margin-bottom : 8px;
		border : 1px solid rgb(172 205 255 / 57%);
		border-radius: 12px;
	}

	.thumbnail:hover{
		cursor:pointer;
		opacity:0.8;
	}


</style>
</head>
<body>

	<jsp:include page="../include/header.jsp" />
	
	<div class="outer">
		
		<div style="margin-top : 15px; width : 400px; height : 280px; margin:auto;">
			<img width="100%" height="100%" src="https://kh-academy.co.kr/resources/images/main/logo.svg" alt="">
		</div>
		
			<div style="align:right;" >
				<a class="btn btn-sm btn-primary"
				   style="background:rgb(193 229 255); border:none; width:100%"
				   href="galleries/form"
				>글작성</a>
			</div>

		<div class="list-area">
			
			<br>
				

			<div id="1" class="thumbnail" align="center" onclick="">
				<img src="..." alt="대표이미지">
				<p>
					<label>No. 1</label> / <span>제목이다옹</span> <br>
					<label>조회수</label> : <span>0</span>
				</p>
			</div>
			

		</div>
		
	
	
	</div>
	
	<jsp:include page="../include/footer.jsp" />


</body>
