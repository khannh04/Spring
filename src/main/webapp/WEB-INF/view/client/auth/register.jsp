<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
            <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
                <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
                    <html lang="en">

                    <head>
                        <meta charset="utf-8" />
                        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
                        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
                        <meta name="description" content="" />
                        <meta name="author" content="" />
                        <title>Register - LaptopShop</title>
                        <!-- Google Web Fonts -->
                        <link rel="preconnect" href="https://fonts.googleapis.com">
                        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
                        <link
                            href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Raleway:wght@600;800&display=swap"
                            rel="stylesheet">

                        <!-- Icon Font Stylesheet -->
                        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" />
                        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css"
                            rel="stylesheet">

                        <!-- Libraries Stylesheet -->
                        <link href="/client/lib/lightbox/css/lightbox.min.css" rel="stylesheet">
                        <link href="/client/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">


                        <!-- Customized Bootstrap Stylesheet -->
                        <link href="/client/css/bootstrap.min.css" rel="stylesheet">

                        <!-- Template Stylesheet -->
                        <link href="/client/css/style.css" rel="stylesheet">
                    </head>

                    <body class="bg-primary">
                        <div id="layoutAuthentication">
                            <div id="layoutAuthentication_content">
                                <main>
                                    <div class="container">
                                        <div class="row justify-content-center">
                                            <div class="col-lg-7">
                                                <div class="card shadow-lg border-0 rounded-lg mt-5">
                                                    <div class="card-header">
                                                        <h3 class="text-center font-weight-light my-4">Create Account
                                                        </h3>
                                                    </div>
                                                    <div class="card-body">
                                                        <form:form method="post" action="/register"
                                                            modelAttribute="registerUser">
                                                            <div class="row mb-3">
                                                                <div class="col-md-6">
                                                                    <div class="form-floating mb-3 mb-md-0">
                                                                        <form:input path="firstName"
                                                                            class="form-control" id="inputFirstName"
                                                                            type="text"
                                                                            placeholder="Enter your first name" />
                                                                        <label for="inputFirstName">First name</label>
                                                                    </div>
                                                                </div>
                                                                <div class="col-md-6">
                                                                    <div class="form-floating">
                                                                        <form:input path="lastName" class="form-control"
                                                                            id="inputLastName" type="text"
                                                                            placeholder="Enter your last name" />
                                                                        <label for="inputLastName">Last name</label>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <spring:bind path="email">
                                                                <div class="form-floating mb-3">
                                                                    <form:input path="email" id="inputEmail"
                                                                        type="email" placeholder="name@example.com"
                                                                        cssClass="form-control ${status.error ? 'is-invalid' : ''}" />

                                                                    <label for="inputEmail">Email address</label>

                                                                    <form:errors path="email"
                                                                        cssClass="invalid-feedback" />
                                                                </div>
                                                            </spring:bind>
                                                            <div class="row mb-3">
                                                                <div class="col-md-6">
                                                                    <spring:bind path="password">
                                                                        <div class="form-floating mb-3 mb-md-0">
                                                                            <form:input path="password"
                                                                                id="inputPassword" type="password"
                                                                                placeholder="Create a password"
                                                                                cssClass="form-control ${status.error ? 'is-invalid' : ''}" />
                                                                            <label for="inputPassword">Password</label>
                                                                            <form:errors path="password"
                                                                                cssClass="invalid-feedback" />
                                                                        </div>
                                                                    </spring:bind>
                                                                </div>
                                                                <div class="col-md-6">
                                                                    <spring:bind path="confirmPassword">
                                                                        <div class="form-floating">
                                                                            <form:input path="confirmPassword"
                                                                                id="inputPasswordConfirm"
                                                                                type="password"
                                                                                placeholder="Confirm password"
                                                                                cssClass="form-control ${status.error ? 'is-invalid' : ''}" />
                                                                            <label for="inputPasswordConfirm">Confirm
                                                                                Password</label>
                                                                        </div>
                                                                    </spring:bind>
                                                                </div>
                                                            </div>
                                                            <div class="mt-4 mb-0">
                                                                <div class="d-grid">
                                                                    <button type="submit"
                                                                        class="btn btn-primary btn-block">
                                                                        Create Account
                                                                    </button>
                                                                </div>
                                                            </div>
                                                        </form:form>
                                                    </div>
                                                    <div class="card-footer text-center py-3">
                                                        <div class="small"><a href="login.html">Have an account? Go to
                                                                login</a></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </main>
                            </div>
                        </div>
                        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
                            crossorigin="anonymous"></script>
                        <script src="/js/scripts.js"></script>
                    </body>

                    </html>