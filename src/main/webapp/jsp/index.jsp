<%@ include file="/WEB-INF/views/includes.jsp"%>

<%-- Redirected because we can't set the welcome page to a virtual URL. --%>
<%-- <%response.sendRedirect("homePage.htm");%> --%>

<c:redirect url="/homePage.htm"/>