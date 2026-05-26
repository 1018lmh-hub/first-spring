<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>블로그 검색</title>
</head>
<body>

	<jsp:include page="../include/header.jsp"/>
	
	<div class="innerOuter">
		<div class="form-control">
			<input type="text" id="query" style="width:100%; height:100%; box-sizing:border-box"/>
		</div>
	
		<button class="btn btn-lg btn-danger" onclick="search();">블로그 검색</button>
		
		<hr style="margin:25px;"/>
		
		<select>
			<option>경부선</option>
			<option>중부선</option>
			<option>제2중부선</option>
			<option>영동선</option>
			<option>서해안선</option>
			<option>서울~양양선</option>			
		</select>
		
		<button class="btn btn-lg btn-primary" onclick="findServiceAreaFoods();">휴게소 음식 검색하기</button>
		
		<hr style="margin:25px;"/>
		
		<div class="form-control">
			<input type="text" id="service" style="width:100%; height:100%; box-sizing:border-box"/>
		</div>
	
		<button class="btn btn-lg btn-danger" onclick="findServiceArea();">휴게소 검색</button>
		
		
		<div id="result">
		
		</div>
	
	</div>
	
	
	<script>
		function findServiceArea(){
			const service = document.querySelector('#service').value;
			
			$.ajax({
				url : `api/service?service=\${service}`,
				success : result =>{
					console.log(result);
					const el = result.list.map(e => `
													  <div style="padding : 20px;">
													  	<h5>휴게소 명 : \${e.stdRestNm}</h5>
													  	<p style = "color : \${e.recommendyn =='Y' ? 'gold' : 'black'}"
													  	<p>음식이름 : \${e.foodNm} | 가격 : \${e.foodCost}원</P>
													  	<p>
													  		\${e.etc != null ? e.etc : '설명이 없습니다.'}
													  	</p>
													  </div>
													`).join('');
				document.querySelector('#result').innerHTML = el;
				
					
				}
			})
		};
	
	
		var page = 1;
		function findServiceAreaFoods(){
			//const line = document.querySelector(":selected").innerText;
			const $line = $('option:selected').text();
			//console.log($line);
			
			$.ajax({
				url : `api/foods?line=\${$line}&page=\${page}`,
				success : result => {
					//console.log(result);
					
					const el = result.list.map(e => `
													  <div style="padding : 20px;">
													  	<h5>휴게소 명 : \${e.stdRestNm}</h5>
													  	<p style = "color : \${e.recommendyn =='Y' ? 'gold' : 'black'}"
													  	<p>음식이름 : \${e.foodNm} | 가격 : \${e.foodCost}원</P>
													  	<p>
													  		\${e.etc != null ? e.etc : '설명이 없습니다.'}
													  	</p>
													  </div>
													`).join('');
					document.querySelector('#result').innerHTML = el;
					page++;
				}
			});
		}
	
	
	
		function search(){
			const keyword = document.querySelector('#query').value;
			
			$.ajax({
				url : `api/blog?query=\${keyword}`,
				success : result => {
					//console.log(result);
					
				
					
					/*
					const items = result.items;
					
					const el = items.map(e => `
												<div>
													<h4>\${e.title}</h4><br>
													<p>\${e.description}</p><br>
													<a href="\${e.link}" target="_blank">이동하기</a><br>
													<hr>
												</div>
											  `).join('');
					document.querySelector('#result').innerHTML = el;
					*/
					
					const items = result.items;
					
					const el = items.map(e => `
												<div style="width : 250px; height : 350px; padding : 14px; display:inline-block;">
													<h5>\${e.title}</h5>
													<br>
													<img src="\${e.image}" width="200" height="140"/>
													<p>\${e.lprice}원</p>
													<a href="\${e.link}" target="_blank">사러가기</a>
												</div>
											  `).join('');
					document.querySelector('#result').innerHTML = el;
				}
			});
		}
	</script>
	<jsp:include page="../include/footer.jsp"/>


</body>
</html>