<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div align="center" style="vertical-align:middle">
    Howdy ${login_userName}!
    <p/>
    <form action="<%= request.getRequestURL() %>" method="POST"><input type="hidden" name="logout" value="yesplease"/><input type="submit" value="logout"/></form>
</div>