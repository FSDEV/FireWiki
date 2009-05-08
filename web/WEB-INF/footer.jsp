<%@page import="db.User"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% User u = (request.getSession(false)==null)?null:(User)request.getSession(false).getAttribute("user"); %>
        </div></div>
        <div id="sidebar">
            <ul>
                <li><a href="<c:url value="/wiki"/>">Home</a></li>
                <li><a href="<c:url value="/search"/>">Search</a></li>
                <% if(u!=null) { if(u.isAdmin()) { %><li><a href="<c:url value="/userlist"/>">Users</a></li><% } } %>
            </ul>
        </div>
        <br clear="all"/>
        <p/><p/>
        <hr width="35%"/>
        <div id="footer" align="center">
            Powered by FireWiki &copy; 2009 <a href="http://www.fsdev.net/">Firestorm Development Group</a>.  All Rights Reserved.
        </div>
    </body>
</html>
