<%@page  import="db.User,java.util.List" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="search-title">Search</div>
<div align="center" id="search-box"><form name="userlist" action="<c:url value="/userlist"/>" method="GET">
<table align="center">
    <tr>
        <td colspan="2" align="right">Name: <input type="text" size="30" name="userlist_username"<% if(request.getParameter("userlist_username")!=null) { %> value="<%= request.getParameter("userlist_username")%>"<% } %>/></td>
        <td rowspan="2">
            <form action="<c:url value="/userlist"/>" method="GET">
                <input type="submit"/>
            </form>
        </td>
    </tr>
    <tr>
        <td colspan="2" align="right">Email: <input type="text" size="30" name="userlist_email"<% if(request.getParameter("userlist_email")!=null) { %> value="<%= request.getParameter("userlist_email")%>"<% } %>/></td>
    </tr>
    <tr>
        <td colspan="3" id="search-results">
            <table align="center">
                <tr>
                    <td align="center">User ID</td>
                    <td align="center">User Name</td>
                    <td align="center">Email</td>
                    <td align="center">Active</td>
                    <td align="center">Banned</td>
                    <td align="center">Admin</td>
                </tr>
                <%  for(User u:(List<User>)request.getAttribute("users")) {    %>
                  <tr>
                      <td><a href="<c:url value="/useredit"/>?id=<%= u.getId()%>"><%= u.getId()%></a></td>
                      <td><a href="<c:url value="/useredit"/>?id=<%= u.getId()%>"><%= u.getName()%></a></td>
                      <td><a href="<c:url value="/useredit"/>?id=<%= u.getId()%>"><%= u.getEmail()%></a></td>
                      <td><%= u.isActive()%></td>
                      <td><%= u.isBanned()%></td>
                      <td><%= u.isAdmin()%></td>
                  </tr>
                <%  }   %>
                <% if(((List<User>)request.getAttribute("users")).isEmpty()) { %>
                <tr>
                    <td colspan="6"><font color="red">no users found</font></td>
                </tr>
                <% } %>
            </table>
        </td>
    </tr>
    <tr>
        <td align="center">
            <form action="<c:url value="/userlist"/>" method="GET">
                <input type="submit" value="Previous"<c:if test="${start-num<=0}"> disabled="true"</c:if>/><input type="hidden" name="start" value="${start-num}"/>
            </form>
        </td>
        <td align="center">
            <select name="num" onchange="document.forms[1].submit()"<c:if test="${totalUsers<11}"> disabled="true"</c:if>>
                <c:forTokens items="10,25,50,100" delims="," var="iter">
                    <option <c:if test="${iter==num}"> selected="true"</c:if>>${iter}</option>
                </c:forTokens>
            </select> results per page
        </td>
        <td align="center">
            <form action="<c:url value="/userlist"/>" method="GET">
                <input type="submit" value="Next"<c:if test="${totalUsers<start+num}"> disabled="true"</c:if>><input type="hidden" name="start" value="${start+num}"/></input>
            </form>
        </td>
    </tr>
</table>
</form>
</div>