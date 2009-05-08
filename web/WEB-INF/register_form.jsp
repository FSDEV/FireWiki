<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<form action="<c:url value="/register"/>" method="POST">
    <table align="center">
        <tr>
            <td width="35px">Name</td>
            <td width="125px"><input type="text" name="reg_name" size="15" value="<%= request.getAttribute("reg_name")%>"/></td>
            <c:if test="${reg_invalid==true}">
                <c:choose>
                    <c:when test="${reg_name_invalid==true}">
                        <td><font color="red">you must enter a name</font></td>
                    </c:when>
                    <c:otherwise>
                        <td/>
                    </c:otherwise>
                </c:choose>
            </c:if>
        </tr>
        <tr>
            <td>Email</td>
            <td><input type="text" name="reg_email" size="15"value="<%= request.getAttribute("reg_email")%>"/></td>
            <c:if test="${reg_invalid==true}">
                <c:choose>
                    <c:when test="${reg_email_invalid==true}">
                        <td><font color="red">this may be a dumb computer, but that's definitely not an email address</font></td>
                    </c:when>
                    <c:when test="${reg_email_already_exists==true}">
                        <td><font color="red">sorry, that email has already been taken</font></td>
                    </c:when>
                    <c:otherwise>
                        <td/>
                    </c:otherwise>
                </c:choose>
            </c:if>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="reg_pass" size="15"value="<%= request.getAttribute("reg_pass")%>"/></td>
            <c:if test="${reg_invalid==true}">
                <c:choose>
                    <c:when test="${reg_pass_empty==true}">
                        <td><font color="red">you must enter a password</font></td>
                    </c:when>
                    <c:when test="${reg_passwords_dont_match==true}">
                        <td rowspan="2"><font color="red">passwords don't match</font></td>
                    </c:when>
                    <c:otherwise>
                        <td/>
                    </c:otherwise>
                </c:choose>
            </c:if>
        </tr>
        <tr>
            <td>Confirm Password</td>
            <td><input type="password" name="reg_pass_confirm" size="15"value="<%= request.getAttribute("reg_pass_confirm")%>"/></td>
            <c:if test="${reg_invalid==true}">
                <c:choose>
                    <c:when test="${reg_pass_conf_empty==true}">
                        <td><font color="red">you must confirm your password</font></td>
                    </c:when>
                    <c:when test="${reg_passwords_dont_match==false}"></c:when>
                    <c:otherwise>
                        <td/>
                    </c:otherwise>
                </c:choose>
            </c:if>
        </tr>
        <tr>
            <td align="center"><input type="reset" name="panic" value="panic"/></td>
            <c:if test="${reg_invalid==true}"><td/></c:if>
            <td align="center"><input type="submit" name="register" value="register"/></td>
        </tr>
    </table>
            <c:if test="${reg_email_failed==true}"><font color="red">sending of email failed</font></c:if>
</form>