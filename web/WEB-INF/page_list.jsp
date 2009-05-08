<%@page  import="db.WikiPage,java.util.List" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="search-title">Search</div>
<div align="center" id="search-box"><form name="search" action="<c:url value="/search"/>" method="GET">
<table align="center">
    <tr>
        <td colspan="2" align="right"><input type="text" size="30" name="searchTerm"<% if(request.getParameter("searchTerm")!=null) { %> value="<%= request.getParameter("searchTerm")%>"<% } %>/></td>
        <td>
            <form action="<c:url value="/search"/>" method="GET">
                <input type="submit"/>
            </form>
        </td>
    </tr>
    <tr>
        <td colspan="3" id="search-results">
            <ul>
            <%  for(WikiPage wp:(List<WikiPage>)request.getAttribute("pages")) {    %>
                  <li><a href="<c:url value="/wiki"/>?page=<%= wp.getPath()%>"><%= wp.getPath()%></a></li>
            <%  }   %>
            </ul>
        </td>
    </tr>
    <tr>
        <td align="center">
            <form action="<c:url value="/search"/>" method="GET">
                <input type="submit" value="Previous"<c:if test="${start-num<=0}"> disabled="true"</c:if>><input type="hidden" name="start" value="${start-num}"/></input>
            </form>
        </td>
        <td align="center">
            <select name="num" onchange="document.forms[1].submit()">
                <c:forTokens items="10,25,50,100" delims="," var="iter">
                    <option<c:if test="${iter==num}"> selected="true"</c:if>>${iter}</option>
                </c:forTokens>
            </select> results per page
        </td>
        <td align="center">
            <form action="<c:url value="/search"/>" method="GET">
                <input type="submit" value="Next"<c:if test="${totalPages<start+num}"> disabled="true"</c:if>><input type="hidden" name="start" value="${start+num}"/></input>
            </form>
        </td>
    </tr>
</table>    
</form>
</div>