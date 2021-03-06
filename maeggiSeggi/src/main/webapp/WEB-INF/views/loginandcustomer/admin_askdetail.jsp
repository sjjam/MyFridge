<%@page import="maeggi.seggi.loginandcustomer.memberVO"%>
<%@page import="maeggi.seggi.mypage.BoardVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="description" content="">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

<!-- Title -->
<title> 문의 글 상세보기 (관리자) </title>

<!-- Favicon -->
<link rel="icon" href="/maeggiSeggi/images/core-img/favicon.ico">

<!-- font -->
<link href="/maeggiSeggi/common/css/maeggiFonts.css" rel="stylesheet">

<!-- Core Stylesheet -->
<link href="/maeggiSeggi/common/css/style.css" rel="stylesheet">
<link href="/maeggiSeggi/common/css/k_admin_askdetail.css" rel="stylesheet">


<!-- Responsive CSS -->
<link href="/maeggiSeggi/common/css/responsive/responsive.css" rel="stylesheet">


</head>

<body>
<%
		
		BoardVO admin_askdetail = (BoardVO) request.getAttribute("admin_askdetail");
	%>
	<div id=outter>
	<!-- ****** Breadcumb Area Start ****** -->
	<div class="breadcumb-area"
		>
		<div class="container h-100">
			<div class="row h-100 align-items-center">
				<div class="col-12">
					<div class="bradcumb-title text-center">
						<h2>문의 리스트</h2>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="breadcumb-nav">
		<div class="container">
			<div class="row">
				<div class="col-12">
					<nav aria-label="breadcrumb">
						<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="#"><i
									class="fa fa-home" aria-hidden="true"></i> 직접 문의하기</a></li>
							<li class="breadcrumb-item active" aria-current="page">문의
								리스트</li>
						</ol>
					</nav>
				</div>
			</div>

			<div class="row">
				<!-- row해야 그 집모양 아이콘 있는 곳부터 글자가 시작됨 -->
				<div class="col-12" id="asklist_customview">
					<h2>[관리자 전용 페이지] 문의 글 상세보기</h2>
				</div>
			</div>

			<div class="row">
				<div class="col-12" id="asklist_customview">

					<div id="AskUserForm">
						<table class="askboard" id="askboard_detail" border="1">
				
								<tr>
									<td>작성자</td>
									<td><%=admin_askdetail.getMember_id()%></td>
								</tr>

								<tr>
									<td>작성일</td>
									<td><%=admin_askdetail.getAsk_regdate()%></td>
								</tr>

								<tr>
									<td>제목</td>
									<td><%=admin_askdetail.getAsk_title()%></td>
								</tr>

								<tr>
									<td>content</td>
									<td><%=admin_askdetail.getAsk_content()%></td>
								</tr>
							</table>
							
						
						
					</div>
				</div>
			</div>


			<div class="container" id="AskMangerForm">
				<div class="row" id="asklist_customview">

					<form>
						<div class="col-xs-3" id="askdetail_reply_managername">
						<%=session.getAttribute("id")%></div>
						<div class="col-xs-9" id="feedback_box">
							<textarea id="text_feedback"></textarea>
						</div>
					</form>
				</div>

				<div class="row">
					<div class="col-3" id="reply_buttons">
						<form>
							<input type="submit" id="ask_reply_send" value="답변  달기"
								onclick="alert('답변이 저장되었습니다.')"> <input type="submit"
								id="ask_reply_pause" value="답변지우기" onclick="alert('등록취소.')">
						</form>

					</div>
				</div>
			</div>
			
			
		</div>
		<!-- ****** Breadcumb Area End ****** -->



	
</div>


</body>