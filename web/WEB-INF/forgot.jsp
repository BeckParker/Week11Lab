<%-- 
    Document   : forgot
    Created on : Nov 27, 2017, 8:44:18 AM
    Author     : 679810
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>NotesKeepr</title>
    </head>
    <body>
        <h1>Forgot Password</h1>
        <p>Please enter your email address to reset your password.</p>
        <br>
        <form action="forgot" method="POST">
            Email Address: <input type="email" name="address"/><br>
            <input type="submit" value="Submit"/>
        </form>
        ${success}
        ${error}
    </body>
</html>
