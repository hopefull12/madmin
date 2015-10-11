<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>

<!doctype html>
<html class="fixed">
	<head>

		<!-- Basic -->
		<meta charset="UTF-8">

		<title>Form Layouts | Porto Admin - Responsive HTML5 Template 1.4.0</title>
		<meta name="keywords" content="HTML5 Admin Template" />
		<meta name="description" content="Porto Admin - Responsive HTML5 Template">
		<meta name="author" content="okler.net">

		<!-- Mobile Metas -->
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />

		<!-- Web Fonts  -->
		<link href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800|Shadows+Into+Light" rel="stylesheet" type="text/css">

		<!-- Vendor CSS -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendor/bootstrap/css/bootstrap.css" />

		<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendor/font-awesome/css/font-awesome.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendor/magnific-popup/magnific-popup.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendor/bootstrap-datepicker/css/datepicker3.css" />
		
		<!-- Specific Page Vendor CSS -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendor/bootstrap-fileupload/bootstrap-fileupload.min.css" />		

		<!-- Theme CSS -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/stylesheets/theme.css" />

		<!-- Skin CSS -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/stylesheets/skins/default.css" />

		<!-- Theme Custom CSS -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/stylesheets/theme-custom.css">

		<!-- Head Libs -->
		<script src="${pageContext.request.contextPath}/assets/vendor/modernizr/modernizr.js"></script>
	</head>
	<body>
		<section class="body">

			<!-- start: header -->
			<t:insertAttribute name="header" />
			<!-- end: header -->

			<div class="inner-wrapper">
				<!-- start: sidebar -->
				<t:insertAttribute name="left-aside" />
				<!-- end: sidebar -->

		        <!-- Body
		        ============================================= -->        
				<t:insertAttribute name="body" />


			</div>
			<t:insertAttribute name="right-aside" />
		</section>

		<!-- Vendor -->
		<script src="${pageContext.request.contextPath}/assets/vendor/jquery/jquery.js"></script>
		<script src="${pageContext.request.contextPath}/assets/vendor/jquery-browser-mobile/jquery.browser.mobile.js"></script>
		<script src="${pageContext.request.contextPath}/assets/vendor/bootstrap/js/bootstrap.js"></script>
		<script src="${pageContext.request.contextPath}/assets/vendor/nanoscroller/nanoscroller.js"></script>
		<script src="${pageContext.request.contextPath}/assets/vendor/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
		<script src="${pageContext.request.contextPath}/assets/vendor/magnific-popup/magnific-popup.js"></script>
		<script src="${pageContext.request.contextPath}/assets/vendor/jquery-placeholder/jquery.placeholder.js"></script>
		
		<!-- Specific Page Vendor -->
		<script src="${pageContext.request.contextPath}/assets/vendor/jquery-autosize/jquery.autosize.js"></script>
		<script src="${pageContext.request.contextPath}/assets/vendor/bootstrap-fileupload/bootstrap-fileupload.min.js"></script>		
		
		<!-- Theme Base, Components and Settings -->
		<script src="${pageContext.request.contextPath}/assets/javascripts/theme.js"></script>
		
		<!-- Theme Custom -->
		<script src="${pageContext.request.contextPath}/assets/javascripts/theme.custom.js"></script>
		
		<!-- Theme Initialization Files -->
		<script src="${pageContext.request.contextPath}/assets/javascripts/theme.init.js"></script>
		
		<script type="text/javascript">
		
		$(function(){
			
			$(".pt-field").each(function(){ $(this).hide()});
			
			$("#fileCategory").change(function(){
				
				console.log($(this).val());
				if($(this).val() === "PrayerTimes"){
					$(".pt-field").each(function(){ $(this).show()})
				} else {
					$(".pt-field").each(function(){ $(this).hide()});
				}
				
			})
			
		})
		
		</script>			

	</body>
</html>