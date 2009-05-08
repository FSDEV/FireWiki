<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form action="<%= request.getRequestURL() %>" method="POST">
    <table align="center">
        <tr>
            <td>Email</td>
            <td colspan="<c:choose><c:when test="${login_failed==true}">1</c:when><c:otherwise>2</c:otherwise></c:choose>"><input type="text" size="15" id="login_email" name="login_email" value="${login_email}"/></td>
            <c:if test="${login_failed==true}"><td>email and/or password incorrect</td></c:if>
        </tr>
        <tr>
            <td>Password</td>
            <td colspan="<c:choose><c:when test="${login_failed==true}">1</c:when><c:otherwise>2</c:otherwise></c:choose>"><input type="password" size="15" id="login_password" name="login_password" value="${login_password}"/></td>
        </tr>
        <tr align="center">
            <td><input type="reset" title="panic!"/></td>
            <td><input type="submit" title="login"/></td>
            <td><a href="<c:url value="/register"/>">register</a></td>
        </tr>
    </table>
</form>