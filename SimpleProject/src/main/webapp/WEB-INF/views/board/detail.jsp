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
        .content {
            background-color:rgb(247, 245, 245);
            width:80%;
            margin:auto;
        }
        .innerOuter {
            border:1px solid lightgray;
            width:80%;
            margin:auto;
            padding:5% 10%;
            background-color:white;
        }

        table * {margin:5px;}
        table {width:100%;}
    </style>
</head>
<body>

	<!-- 
		5월 22일 ~ 5월 25일까지 공부 계획 세우기
		
		단위를 두시간씩으로 나눠서
		
		스프링 기능 구현단위로
		
		일단 라이브러리, 빈파일, view(jsp), controller, model(service, dao, dto), mapper, table
		4시간이면 만들지 않으려나 
		
		자
		board, member
		
		에러 exception처리, 필터, 인터셉터
		
		회원가입, 로그인, 회원탈퇴, 
		게시판 페이징 처리
		게시글 작성, 게시글 수정, 게시글 삭제, 게시글 조회, 게시글 상세조회, 게시글 검색
		조회수
		댓글 작성, 댓글 수정, 댓글 삭제
		
		처음부터 안하고 일단
		사진게시판이랑 글게시판 따로 만들어보기
		
		4시간에서 6시간 정도하고
		
		페이징 처리  -- 2
		게시글 작성, -- 2
		게시글 수정, -- 2
		
		게시글 삭제, -- 2 
		게시글 조회, -- 2
		게시글 상세조회 -- 2
		
		----------------
		
		안해보긴 했는데 뭐 시간 남으면 ㄱ
		
		댓글 작성, -- 2
		댓글 수정, -- 2
		댓글 삭제  -- 2
		-----------------
		
		
		일단 이정도
		
		
		
		
		
		
		
		
		
		
		
		
		
		





		
		
		
		정처기 일요일 2시에 시험 보니까
		적어도 금 토 6시간 정도는 하는 게
		
		기능단위로 하는 게 아니라 메서드 단위로 구분해서 시행한다라
		오호
		
		하긴 그렇게 하면 초보자일 때 어떤 것들을 수행해야할 지 좀 더 명확하긴 하겠다.
		실행계획에 대해서도 그렇고 기획을 한다고 생각하면 뭐
		그대로 따라가면 되니까
		
		어려우면 단순화 
		
		CRUD 에 충실한 기본적인 그런.
		
		어차피 여기에 살을 덧붙이는 거니까 그르긴하네
		
		계획 시간을 세울 때 너무 여유롭게 잡지 말고
		타이트하게 잡고 하고 쉬어라잉
		당장 실행할 수 있는 계획을 세우면 좋긴한데
		쉽지 않긴 할듯
		이건 계속 피드백해야지 해봐야 알듯
		 
		그러네 계획을 개인의 능력을 봤을 때 한두개 정도 못할 정도로 세우는 게 	
		
		그렇다면 개념(이해 ,논리적, 정의) 흐름(연결, 머릿 속 이미지, 과정) 기본용어(암기, 소통하기 위한 정의를 표현하는 단어)만 알면 나머지는 찾아서 해도 되는 거 아닐까
		말하는 능력이나 쓰는 능력도 필요하겠네. 정보 전달, 설명, 정확하고 명확한 디테일한 가이드
		해석이랑 설명
		
	 -->
	 
	 
        
    <jsp:include page="../include/header.jsp" />

    <div class="content">
        <br><br>
        <div class="innerOuter">
            <h2>게시글 상세보기</h2>
            <br>

            <a class="btn btn-secondary" style="float:right;" href="">목록으로</a>
            <br><br>

            <table id="contentArea" algin="center" class="table">
                <tr>
                    <th width="100">제목</th>
                    <td colspan="3">${ board.boardTitle }</td>
                </tr>
                <tr>
                    <th>작성자</th>
                    <td>${ board.boardWriter }</td>
                    <th>작성일</th>
                    <td>${ board.createDate }</td>
                </tr>
                <tr>
                    <th>첨부파일</th>
                    <c:choose>
	                    <c:when test="${ not empty board.changeName }">
		                    <td colspan="3">
		                        <a href="${ board.changeName }" download="${ board.originName }">${ board.originName }</a>
		                    </td>
	                    </c:when>
	                    <c:otherwise>
	                    	<td colspan="3">
	                    		첨부파일이 존재하지 않습니다.
	                    	</td>
	                    </c:otherwise>
                    </c:choose>
                </tr>
                <tr>
                    <th>내용</th>
                    <td colspan="3"></td>
                </tr>
                <tr>
                    <td colspan="4"><p style="height:150px;">${ board.boardContent }</p></td>
                </tr>
            </table>
            <br>

            <div align="center">
                <!-- 수정하기, 삭제하기 버튼은 이 글이 본인이 작성한 글일 경우에만 보여져야 함 -->
                <a class="btn btn-primary" href="">수정하기</a>
                <a class="btn btn-danger" href="">취소하기</a>
            </div>
            <br><br>

            <table id="replyArea" class="table" align="center">
                <thead>
                    <tr>
                        <th colspan="2">
                            <textarea class="form-control" name="" id="content" cols="55" rows="2" style="resize:none; width:100%;"></textarea>
                        </th>
                        <th style="vertical-align:middle"><button class="btn btn-secondary">등록하기</button></th> 
                    </tr>
                    <tr>
                        <td colspan="3">댓글(<span id="rcount">3</span>)</td>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <th>user02</th>
                        <td>ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ</td>
                        <td>2026-05-12</td>
                    </tr>
                    <tr>
                        <th>user01</th>
                        <td>재밌어요</td>
                        <td>2026-05-11</td>
                    </tr>
                    <tr>
                        <th>admin</th>
                        <td>댓글입니다!!</td>
                        <td>2026-05-10</td>
                    </tr>
                </tbody>
            </table>
        </div>
        <br><br>

    </div>
    
    <jsp:include page="../include/footer.jsp" />
    
</body>
</html>