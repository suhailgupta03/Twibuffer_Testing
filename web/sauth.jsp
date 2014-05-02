<%-- 
    Document   : sauth
    Created on : Apr 16, 2014, 10:57:31 PM
    Author     : user
--%>

<%@page import="org.suhail.twitter.InitializeU"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Twibuffer</title>
    </head>
    <body>
        <%! 
            String authURL;
        %>
        
        <% 
            InitializeU user = new InitializeU();
            user.setApplicationKeys();
            authURL = user.getAuthURL();
            session.setAttribute("AuthUrl", authURL);
        %>
        <p style="font:'Trebuchet MS', Arial, Helvetica, sans-serif; font-size:36px; font-weight:300; padding-left:10%; padding-right:10%; padding-top:10%;">
            Before you begin using Twibuffer, please authorize the application by clicking on the link given     :
            <a href=<%= authURL %>>Authorize link</a>
        </p>
    </body>
</html>
