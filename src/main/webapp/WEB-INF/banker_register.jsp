<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!doctype html>
<html lang="en">

<head>

    <title>Register</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Reference Bootstrap files -->
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>

    <script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>

<body>

<div>



    <div id="loginbox2" style="margin-top: 50px;"
         class="mainbox col-md-6 col-md-offset-2 col-sm-6 col-sm-offset-2">

        <div class="panel panel-info">

            <div class="panel-heading">
                <div class="panel-title">Register</div>
            </div>

            <div style="padding-top: 30px" class="panel-body">

                <!-- Register Form -->
                <form:form action="${pageContext.request.contextPath}/registerBanker" method="POST" commandName="bankerRegisterForm" class="form-horizontal">

                    <form:errors/>
                    <!-- User name -->
                    <div style="margin-bottom: 25px" class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                        <form:label path="username">Username</form:label>
                        <form:input path="username" required="required" />
                        <form:errors path="username"/>
                            <%--<input type="text" name="username" placeholder="username" class="form-control">--%>
                    </div>

                    <!-- Email -->
                    <div style="margin-bottom: 25px" class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                        <form:label path="email">Email</form:label>
                        <form:input path="email" type="email" required="required"/>
                        <form:errors path="email"/>
                            <%--<input type="text" name="email" placeholder="email" class="form-control">--%>
                    </div>

                    <!-- Password -->
                    <div style="margin-bottom: 25px" class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                        <form:label path="password">Password</form:label>
                        <form:password path="password" required="required"/>
                        <form:errors path="password"/>
                            <%--<input type="password" name="password" placeholder="password" class="form-control" >--%>
                    </div>

                    <!-- Confirm Password -->
                    <div style="margin-bottom: 25px" class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                        <form:label path="confirmPassword">Confirm Password</form:label>
                        <form:password path="confirmPassword" required="required"/>
                        <form:errors path="confirmPassword"/>
                            <%--<input type="password" name="confirmPassword" placeholder="confirm password" class="form-control" >--%>
                    </div>

                    <!-- Register Button -->
                    <div style="margin-top: 10px" class="form-group">
                        <div class="col-sm-6 controls">
                            <button type="submit" class="btn btn-success">Register</button>
                        </div>
                    </div>

                </form:form>

            </div>

        </div>

    </div>

</div>



</body>
</html>