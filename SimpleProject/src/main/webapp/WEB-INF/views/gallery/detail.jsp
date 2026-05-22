<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html> 
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<style>
    image{
        border-radius : 40px;
        border : 1px solid rgb(255, 172, 248);
    }
</style>
</head>
<body>
<br/><br/>
    <jsp:include page="../include/header.jsp" />
    
    <div class="outer">
        <div class="container">
        
        <div class="row">
          <div class="offset-lg-2 col-lg-8">
            <div class="card">
              <div class="card-header text-white" style="background-color: #52b1ff;">0번 게시물 내용</div>
              <div class="card-body">      
               
                  <div class="form-group">
                    <label>제목</label>
                    <input type="text" class="form-control" name='title' value="" readonly>
                  </div>
        
                  <div class="form-group">
                    <label>내용</label>
                    <textarea class="form-control" rows="5" name='content' readonly style="resize:none;"></textarea>
                  </div>
        
                  <div class="form-group" align="center">
                    <label style="color:#52b1ff; font-weight:bold;">대표 이미지</label>
                    <img width="100%" src="..."/>
                  </div>
                  

                  <div class="form-group" align="center">
                    <label style="color:#52b1ff;">상세 이미지-1번</label>
                    <img width="100%" src="..."/>
                  </div>

                  <a class="btn" href="게시글목록매핑값"
                     style="background-color: #52b1ff; height: 40px; color: white; border: 0px solid #388E3C; opacity: 0.8"
                  >목록</a>&nbsp;&nbsp;
                  
              </div>
            </div>
          </div>
        </div>
        </div>
    </div>
    
    <jsp:include page="../include/footer.jsp" />
</body>
</html>