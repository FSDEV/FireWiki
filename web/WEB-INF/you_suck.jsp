<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/JavaScript">
    function logoutFu() {
        $("div#user_box").fadeOut("slow", function(){
            $("div#user_box").text("See you later ${login_userName}!");
            setTimeout(function() {
                $("div#user_box").fadeOut("slow", function(){
                    $("div#user_box").load("<c:url value="/login"/>", {
                        logout: 'yesplease'
                    });
                });
                $("div#user_box").fadeIn("slow");
            }, 7500);
        })
    }
</script>
Go away ${login_userName}, for you have been banned!
<p/>
<a href="<c:url value="/login"/>" onclick="logoutFu()">logout</a>