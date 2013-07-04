<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	String backgroundImg = request.getParameter("backgroundImg") != null ? request.getParameter("backgroundImg") : "fondoHome.jpg";
	String logo = request.getParameter("logo") != null ? request.getParameter("logo") : "logo.png";
	boolean includeOverBox = request.getParameter("includeOverBox") != null;
%>
	
<c:set var="backgroundImgUrl" value="<%=backgroundImg %>" />
<c:url value="/images/${backgroundImgUrl}" var="urlImg" />

<c:set var="logoImgUrl" value="<%= logo %>" />
<c:url value="/images/${logoImgUrl}" var="urlLogo" />
	
				<div id="logo2">
					<img src="${urlLogo}" width='235' height='79'/>
				</div> 
				
				<c:if test="<%= includeOverBox %>">
					<div id="over" class="overbox"></div>
					<div id="fade" class="fadebox"></div>
				</c:if>
				
			</div>  
			
            <div class="bx-container">
                <img src="${urlImg}" alt="image01" title="fondo"/>
            </div>

		</div>
	
	</div>
	
		
	</body>
</html>
