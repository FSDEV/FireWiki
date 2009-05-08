<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form action="<c:url value="/edit"/>?page=<%= request.getParameter("page") %>" method="POST">
    <table align="center" width="80%">
        <tr align="center">
            <td colspan="2"><textarea cols="90" rows="15" name="editText"><%= request.getAttribute("editText")%></textarea></td>
        </tr>
        <tr align="center">
            <td><input type="submit" name="preview" value="preview"/></td>
            <td><input type="submit" value="save" name="save"/></td>
        </tr>
    </table>
</form>