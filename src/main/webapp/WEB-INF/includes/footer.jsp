<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	String backgroundImg = request.getParameter("backgroundImg") != null ? request.getParameter("backgroundImg") : "fondoHome.jpg";
	String logo = request.getParameter("logo") != null ? request.getParameter("logo") : "logo.png";
	boolean includeOverBox = request.getParameter("includeOverBox") != null;
	String overBoxContent = request.getParameter("overBoxContent");
%>
	
<c:set var="backgroundImgUrl" value="<%=backgroundImg %>" />
<c:url value="/images/${backgroundImgUrl}" var="urlImg" />

<c:set var="logoImgUrl" value="<%= logo %>" />
<c:url value="/images/${logoImgUrl}" var="urlLogo" />
	
				<div id="logo2">
					<img src="${urlLogo}" width='235' height='79'/>
				</div> 
				
				<c:if test="<%= includeOverBox %>">
					<div id="over" class="overbox">
						<c:if test="<%= overBoxContent != null%>">
							<jsp:include page="<%= overBoxContent %>"/>
						</c:if>
					</div>
					<div id="fade" class="fadebox"></div>
				</c:if>
				
			</div>  
			
            <div class="bx-container">
                <img src="${urlImg}" alt="image01" title="fondo"/>
            </div>

		</div>
	
	</div>
	
	
<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-42534211-1', 'almadejazmin.com.ar');
  ga('send', 'pageview');

</script>

		
	</body>
</html>
