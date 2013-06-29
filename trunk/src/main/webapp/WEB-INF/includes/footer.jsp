<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	String backgroundImg = request.getParameter("backgroundImg") != null ? request.getParameter("backgroundImg") : "fondoHome.jpg";
	String logo = request.getParameter("logo") != null ? request.getParameter("logo") : "logo.png";
%>
	
	
				<div id="logo2">
					<img src='images/<%= logo %>' width='235' height='79'/>
				</div> 
			</div>  
                                 
            <div class="bx-container">
                <img src="images/<%= backgroundImg %>" alt="image01" title="fondo"/>
            </div>
		</div>
	</div>
		
	</body>
</html>
