<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
Howdy, thanks for activating your account.  Use the activation code you recieved in the email we sent you.
<form action="<c:url value="/activate"/>" method="POST">
    <c:if test="${activate_failure==true}">Sorry, that's not the key we emailed you.
    <p/></c:if>
    Activation Key: <input type="text" name="activate_key" size="40" value="${activate_key}"/>
    <p/>
    <input type="submit" name="submit" value="submit"/>
</form>