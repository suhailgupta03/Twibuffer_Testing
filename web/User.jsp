<%-- 
    Document   : User
    Created on : Apr 13, 2014, 7:22:16 PM
    Author     : user
--%>

<%@page import="org.suhail.bean.twb.Sholder"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="./js/htmlDatePicker.js" type="text/javascript"></script>
        <script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
        <script src="./js/jquery.timepicker.js" type="text/javascript"></script>
        <link href="./css/htmlDatePicker.css" rel="stylesheet" />
        <link href="./css/scheduler.css" rel="stylesheet" />
        <title>Twibuffer</title>
    </head>
    <body>
        <%! 
            HttpSession uSession;
            String username;
        %>
        
        <%
            uSession = Sholder.getSession();
            username = (String)Sholder.getSession().getAttribute("username");
            if(request.getAttribute("scheduled") != null) {
                if((Boolean)request.getAttribute("scheduled") & !(Boolean)request.getAttribute("Incorrect data")) {
        %>
        <script type="text/javascript"> alert("Scheduled !"); </script>
        <% } else { %>
        <script type="text/javascript"> alert("Unable to schedule !"); </script>
        <% } } %>        
        
        <div id="logout">
            <span> You are logged in as <%= username %> <a href="#">logout</a> </span>
        </div>
        
	<div id="wrapper">
        
    	<div id="header">
        	<span id="logo"> <img src="images/twibuffer.gif" alt="twibuffer"  /> </span>
            <span id="latest_tweet"> Latest Tweet:</span>
        </div>
        
    	<div id="scheduler">
        	<form method="post" action="stweet">
            	<span id="type_tweet">Type your tweet</span>
                <span>
                	<textarea rows="4" cols="80" id="tweet_entered" name="tweet_entered"> </textarea>
                </span>
                <hr />
                <span>When to tweet</span>
                
                <span id="date"> <!-- Date -->
                	<input type="text" name="SelectedDate" id="SelectedDate" readonly onClick="GetDate(this);">
                </span> 
                
                <span id="at">@</span>
                
                <span id="time"> <!-- Time-->
                	<input type="text" class="timepicker" name="timepicker" id="timepicker" />
                </span>
                <span>
                	<input type="submit" value="submit"  id="schedule_button" />
                </span>
            </form>
            
            <table id="table" width="100%" border="+2">
            	<th id="heading">scheduled tweets</th>
                <th id="heading">edit</th>
                <th id="heading">delete</th>
                
                <tr id="stweet"></tr> <!-- tweet -->
                <tr id="echkbox"></tr> <!-- edit checkbox -->
                <tr id="dchkbox"></tr> <!-- delete checkbox -->
            </table>
        </div>
    </div>
        
    </body>
</html>
