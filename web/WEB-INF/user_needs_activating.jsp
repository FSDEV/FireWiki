<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div align="center" style="vertical-align:middle">
    Howdy ${login_userName}!  You can't use your account yet, because you still need to <a href="<c:url value="/activate"/>">activate</a> it.
    <p/>
    <form action="<%= request.getRequestURL() %>" method="POST"><input type="hidden" name="logout" value="yesplease"/><input type="submit" value="logout"/></form>
</div>