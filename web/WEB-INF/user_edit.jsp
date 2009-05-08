<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="useredit">
    <div id="useredit_header">${user_name}</div>
    <table align="center" id="useredit_boolean_box">
        <tr>
            <td width="0%" align="center">
                <form action="<c:url value="/useredit"/>?id=${user_id}">
                    <input type="hidden" name="useredit_deactivateUser" value="yes"/>
                    <input type="submit" value="<c:choose><c:when test="${user_active==true}">deactivate</c:when><c:otherwise>activate</c:otherwise></c:choose>" size="10"/>
                </form>
            </td>
            <td width="50%"/>
            <td width="0%">
                <form action="<c:url value="/useredit"/>?id=${user_id}">
                    <input type="hidden" name="useredit_adminUser" value="yes"/>
                    <input type="submit" value="<c:choose><c:when test="${user_admin==true}">remove admin</c:when><c:otherwise>make admin</c:otherwise></c:choose>" size="10"/>
                </form>
            </td>
            <td width="50%"/>
            <td width="0%">
                <form action="<c:url value="/useredit"/>?id=${user_id}">
                    <input type="hidden" name="useredit_banUser" value="yes"/>
                    <input type="submit" value="<c:choose><c:when test="${user_banned==true}">unban</c:when><c:otherwise>ban</c:otherwise></c:choose>" size="10"/>
                </form>
            </td>
        </tr>
    </table>
    <div id="useredit_delete_user_box" align="center">
        <form action="<c:url value="/useredit"/>?id=${user_id}" method="POST">
            <input type="hidden" name="useredit_deleteUser" value="yes"/>
            <input type="submit" value="delete user"/>
        </form>
    </div>
</div>


<table align="center">
    <tr>
        <td id="useredit_nameHeader" colspan="2" align="center">${user_name}</td>
    </tr>
    <c:if test="${useredit_message!=null}">
        <tr>
            <td colspan="2" align="center">
                <font color="red">${useredit_message}</font>
            </td>
        </tr>
    </c:if>
    <tr>
        <td align="center">user id</td>
        <td align="center">${user_id}</td>
    </tr>
    <tr>
        <td align="center">user email</td>
        <td align="center">${user_email}</td>
    </tr>
    <tr>
        <td colspan="2" align="center">
            <form action="<c:url value="/useredit"/>?id=${user_id}" method="POST">
                <input type="text" name="useredit_changeEmail" value="${user_email}"/>
                <input type="submit" value="change email"/>
            </form>
        </td>
    </tr>
    <tr>
        <td align="center">user name</td>
        <td align="center">${user_name}</td>
    </tr>
    <tr>
        <td colspan="2" align="center">
            <form action="<c:url value="/useredit"/>?id=${user_id}" method="POST">
                <input type="text" name="useredit_changeName" value="${user_name}"/>
                <input type="submit" value="change name"/>
            </form>
        </td>
    </tr>
    <tr>
        <td align="center">is user active?</td>
        <td align="center">${user_active}</td>
    </tr>
</table>