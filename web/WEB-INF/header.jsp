<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FireWiki</title>
        <link href="<c:url value="/site.css"/>" rel="stylesheet" type="text/css" media="all"/>
        <script src="<c:url value="/resources/jquery_1.2.6/jquery-1.2.6.js"/>" type="text/JavaScript"/>
    </head>
    <body>
        <div id="user_box"><jsp:include flush="false" page="/login"></jsp:include></div><div id="title_box">
        <span id="wiki_title">FireWiki</span>
        <hr id="post_title_break"/></div><br clear="right"/>
        <div id="content"><div id="subcontent">
