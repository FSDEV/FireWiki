<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="useredit">
    <div id="useredit_header">${user_name}</div>
    <form action="<c:url value="/useredit"/>?id=${user_id}" method="POST">
        <table align="center" id="useredit_stringBox">
            <tr>
                <td align="center" colspan="4">
                    <input type="text" name="useredit_changeEmail" value="${user_email}"  size="35"/>
                </td>
                <td align="center">
                    <input type="submit" value="change email"/>
                </td>
            </tr>
        </table>
    </form>
    <form action="<c:url value="/useredit"/>?id=${user_id}" method="POST">
        <table align="center" id="useredit_stringBox">
            <tr>
                <td align="center" colspan="4">
                    <input type="text" name="useredit_changeName" value="${user_name}" size="35"/>
                </td>
                <td align="center">
                    <input type="submit" value="change name"/>
                </td>
            </tr>
        </table>
    </form>
    <table align="center" id="useredit_boolean_box">
        <tr>
            <td width="33%">
                <form action="<c:url value="/useredit"/>?id=${user_id}">
                    <input type="hidden" name="useredit_deactivateUser" value="yes"/>
                    <input type="submit" value="<c:choose><c:when test="${user_active==true}">deactivate</c:when><c:otherwise>activate</c:otherwise></c:choose>" size="10"/>
                </form>
            </td>
            <td width="33%">
                <form action="<c:url value="/useredit"/>?id=${user_id}">
                    <input type="hidden" name="useredit_adminUser" value="yes"/>
                    <input type="submit" value="<c:choose><c:when test="${user_admin==true}">remove admin</c:when><c:otherwise>make admin</c:otherwise></c:choose>" size="10"/>
                </form>
            </td>
            <td width="33%">
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