<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="${pageContext.request.contextPath }/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/css/gallery.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/bootstrap/js/bootstrap.js"></script>

</head>


<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<!-- //header -->
		<!-- //nav -->

		<c:import url="/WEB-INF/views/include/galleryAside.jsp"></c:import>
		<!-- //aside -->


		<div id="content">

			<div id="content-head">
				<h3>갤러리</h3>
				<div id="location">
					<ul>
						<li>홈</li>
						<li>갤러리</li>
						<li class="last">갤러리</li>
					</ul>
				</div>
				<div class="clear"></div>
			</div>
			<!-- //content-head -->


			<div id="gallery">
				<div id="list">
			
						<c:if test="${authUser.no != null}">
						<button id="btnImgUpload">이미지올리기</button>
						</c:if>
						<div class="clear"></div>

			
					<ul id="viewArea">
						
						<!-- 이미지반복영역 -->
							<li>
								<c:forEach items="${pMap}" var="galvo" varStatus="status">
									<div class="view" data-no ="${galvo.no}">
										<img class="imgItem" src="${pageContext.request.contextPath}/upload/${galvo.saveName}">
										<div class="imgWriter">작성자: <strong>${galvo.name}</strong></div>
										<input id="formodalNo" type="hidden" name="no" value="${galvo.no}">
									</div>
								</c:forEach>
							</li>
						<!-- 이미지반복영역 -->
						
						
					</ul>
				</div>
				<!-- //list -->
			</div>
			<!-- //board -->
				<div id="paging">
						<ul>
							<c:if test="${pMap.prev == true}">
								<li><a href="${pageContext.request.contextPath}/gallery/list?crtPage=${pMap.startPageBtnNo-1}">◀</a></li>
							</c:if>
							
							<c:forEach begin="${pMap.startPageBtnNo}" end="${pMap.endPageBtnNo}" step="1" var="page">
								<li><a href="${pageContext.request.contextPath}/gallery/list?crtPage=${page}&keyword=">${page}</a></li>
							</c:forEach>
							
							<c:if test="${pMap.next == true}">
								<li><a href="${pageContext.request.contextPath}/gallery/list?crtPage=${pMap.endPageBtnNo+1}">▶</a></li>
							</c:if>
						</ul>


						<div class="clear"></div>
					</div>
					
			<div class="form-group text-right">
							<input type="text" name="keyword">
							<button type="submit" id="btn_search">검색</button>
						</div>
		</div>
		<!-- //content  -->
		<div class="clear"></div>

		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		<!-- //footer -->

	</div>
	<!-- //wrap -->

	
		
	<!-- 이미지등록 팝업(모달)창 -->
	<div class="modal fade" id="addModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">이미지등록</h4>
				</div>
				
				<form method="post" action="${pageContext.request.contextPath}/gallery/upload" enctype="multipart/form-data">
					<div class="modal-body">
						<div class="form-group">
							<label class="form-text">글작성</label>
							<input id="addModalContent" type="text" name="content" value="" >
						</div>
						<div class="form-group">
							<label class="form-text">이미지선택</label>
							<input id="file" type="file" name="file" value="" >
						</div>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn" id="btnUpload">등록</button>
					</div>
				</form>
				
				
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	


	<!-- 이미지보기 팝업(모달)창 -->
	<div class="modal fade" id="viewModal">
		<div class="modal-dialog" >
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">이미지보기</h4>
				</div>
				<div class="modal-body">
					
					<div class="formgroup"  id="imgArea">
						<!-- ajax로 처리 : 이미지출력 위치-->
					</div>
					
					<div class="formgroup">
						<p id="viewModelContent"></p>
					</div>
					
					<!-- no 히든처리 -->
	      			<input id="modalNo" type="hidden" name="no" value="">


				</div>
				<form method="post" action="${pageContext.request.contextPath}/gallery/delete">
					<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
						<c:forEach items="${galleryList}" var="galvo" varStatus="status">
						
							<c:if test="${authUser.no == galvo.user_no && authUser.no != null}">
								<button type="button" class="btn btn-danger" id="btnDel">삭제</button>
							</c:if>
						</c:forEach>
				</div>
				
				
				</form>
				
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->	


</body>

<script type="text/javascript">
	//이미지 올리기 클릭_ 모달창 호출
    $("#btnImgUpload").on("click",function(){
    	$("#addModalContent").val("");
    	$("file").val("");
    	
        console.log("모달 창 호출");
        $("#addModal").modal();
    });
    
    //이미지 클릭_모달창 호출
    $("#viewArea").on("click", ".view", function(){

    	
    	var no = $(this).data("no");
    	console.log(no);
		$("#viewModal #modalNo").val(no);
				   
		 console.log("모달 창 호출");
		 $("#viewModal").modal();

		 $.ajax({
				
				url : "${pageContext.request.contextPath}/gallery/select",		
				type : "post",
				//contentType : "application/json",
				data : {no : no},
				dataType : "json",
				success : function(galleryVo){
					console.log(galleryVo);
					
					$(".formgroup #viewModelContent").text(galleryVo.content);
					
				
					//이미지 불러오기  
					var str = "";
					str += '<img id="viewModelImg" src=${pageContext.request.contextPath}/upload/'+galleryVo.saveName+'>'
					  
					$("#imgArea").html(str);
					
				},
				error : function(XHR, status, error) {
					console.error(status + " : " + error);
				}
			});
    });
    
   //모달창 삭제버튼 클릭할때 
   $("#btnDel").on("click", function(){
	   console.log("모달창 삭제 버튼 클릭");
	   
	   var no = $("#modalNo").val();
		   
	   $.ajax({
			
			url : "${pageContext.request.contextPath}/gallery/delete",		
			type : "post",
			//contentType : "application/json",
			data : {no : no},
			//dataType : "json",
			success : function(){				
					$("#viewModal").modal("hide");  //모달창닫기

			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});

	   
   });
   
   
	


		

</script>




</html>

