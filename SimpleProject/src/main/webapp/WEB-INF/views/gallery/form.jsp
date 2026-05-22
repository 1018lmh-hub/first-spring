<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body> 

    <jsp:include page="../include/header.jsp" />

    <div class="outer">

        <h2 align="center">사진게시글 작성하기</h2>
        <br><br>

        <form action="" 
            enctype="multipart/form-data"
            method="post" id="insert-form" style="width:800px;margin:auto;">
        
            <div class="form-group">
                <label for="usr">제목</label>
                <input type="text" class="form-control" id="usr" name="">
            </div>

            <div class="form-group">
                <label for="comment">내용</label>
                <textarea class="form-control" name="" rows="15" id="comment" style="resize:none;"></textarea>
            </div>

            <div class="form-group" align="center" >
                <label>대표이미지</label><br>
                <img width="300" id="title-img" height="180" src="https://kh-academy.co.kr/resources/images/main/logo.svg" alt="대표이미지">
            </div>
            <div class="form-group" align="center" style="display:inline-block">
                <label>상세이미지-1</label><br>
                <img width="250" id="sub-img1" height="180" src="https://kh-academy.co.kr/resources/images/main/logo.svg" alt="상세이미지1">
            </div>
            <div class="form-group" align="center" style="display:inline-block; margin-left:19px;">
                <label>상세이미지-2</label><br>
                <img width="250" id="sub-img2" height="180" src="https://kh-academy.co.kr/resources/images/main/logo.svg" alt="상세이미지2">
            </div>
            <div class="form-group" align="center" style="display:inline-block; margin-left:19px;">
                <label>상세이미지-3</label><br>
                <img width="250" id="sub-img3" height="180" src="https://kh-academy.co.kr/resources/images/main/logo.svg" alt="상세이미지3">
            </div>

            <div id="file-area">
                <input type="file" name="file1" id="file1" required >
                <input type="file" name="file2" id="file2" >
                <input type="file" name="file3" id="file3" >
                <input type="file" name="file4" id="file4" >
            </div>
            

            <div align="center" style="margin-top:20px">
                <button type="submit" class="btn btn-sm btn-info">등록하기</button>
                <button type="button" class="btn btn-sm btn-secondary"
                onclick="">뒤로가기</button>
            </div>

        </form>
        
    </div>
    
	<jsp:include page="../include/footer.jsp" />

</body>
</html>