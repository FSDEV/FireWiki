<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <div id="view_toolbar"><a href="<c:url value="edit"/>?page=<%= request.getParameter("page")%>">edit</a></div>
    <c:if test="${view_change==true}"><center><font color="red">page changed</font></center><p/></c:if>
    <div id="wiki_code">
        <%= request.getAttribute("wikiOutput") %>
    </div>