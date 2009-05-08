<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

Please enter your user activation code, which was emailed to the address
you provided during registration.

<c:if test="${failed==true}">
    <p/>

    <font color="red">Activation failed.  Please try again.</font>
</c:if>
<p/>

<form action="<c:url value="/activate"/>" method="POST">
    Activation Code: <input type="text" name="activation_code" size="30"/>

    <p/>

    <input type="submit" name="activate" value="activate"/>
</form>