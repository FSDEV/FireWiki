<%@tag description="AJAX-ified page listing for the sidebar area" pageEncoding="UTF-8"
    import="java.util.List,controller.WikiPageController,db.WikiPage"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
    <ul>
<%  String startsWith = "";
    int start=-1;
    int num=-1;

    startsWith=request.getParameter("startsWith");
    start = Integer.parseInt(request.getParameter("start"));
    num = Integer.parseInt(request.getParameter("num"));

    WikiPageController wpc = new WikiPageController();

    List<WikiPage> pages;

    if(start>0&&num>0)
        pages = wpc.getWikiPages(start, num);
    else if(startsWith!=null&&!startsWith.equalsIgnoreCase(""))
        pages = wpc.getWikiPages(startsWith);
    else
        pages = wpc.getWikiPages();

    for(WikiPage wp:pages) {    %>
    <li><a href="<c:url value="/wiki"/>?page=<%= wp.getPath()%>"><%= wp.getPath()%></a></li>
<%  }   %>
</ul>
</div>