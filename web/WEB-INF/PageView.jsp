<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="db.User"%>
    <div id="view_toolbar">
        <%
            User u = (User)request.getSession().getAttribute("user");
            if(u!=null&&u.isActive()) {
        %>
            <a href="<c:url value="edit"/>?page=<%= request.getParameter("page")%>">edit</a>
        <%
            }
            if(u==null||!u.isActive()) {
        %>
                you must log in to edit wiki pages
        <%
            }
        %>
    </div>
    <c:if test="${view_change==true}"><center><font color="red">page changed</font></center><p/></c:if>
    <div id="wiki_code">
        <%= request.getAttribute("wikiOutput") %>
    </div>