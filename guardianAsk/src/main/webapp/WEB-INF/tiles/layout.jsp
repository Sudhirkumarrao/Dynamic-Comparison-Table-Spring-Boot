<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><tiles:insertAttribute name="title" ignore="true" /></title>
</head>
<body>
	<div class="mainpanel">
		<!-- Header -->
		<tiles:insertAttribute name="header" />
		
		<!-- page Content start -->
		<div id="wrapper" class="leftpanel-collapsed "> 
			<div id="page-content-wrapper">
				<div class="container-fluid"><!-- class xyz removed -->
					<div class="row hideMenuCls">
						<div> <!--The content will be displayed here..<div class="col-lg-12"> commented--> 
							<!-- tab start -->
							<tiles:insertAttribute name="body" />
							<!-- tab end --> 
						</div>
					</div>
				</div>
				<tiles:insertAttribute name="footer" />
			</div>
		</div>
		<!-- page Content end -->
		<!-- Footer --> 
			
	</div>
</body>
</html>