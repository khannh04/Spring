<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
            <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
                <html lang="en">

                <head>
                    <meta charset="utf-8" />
                    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
                    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
                    <meta name="description" content="khanhnguyxn - Demo LaptopShop" />
                    <meta name="author" content="khanhnguyxn" />
                    <title>Update Product - Khannh</title>
                    <link href="/css/styles.css" rel="stylesheet" />
                    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
                    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js"
                        crossorigin="anonymous"></script>
                    <script>
                        $(document).ready(() => {
                            const avatarFile = $("#khanhFile");
                            const orgImage = "${newProduct.image}";
                            if (orgImage) {
                                const urlImage = "/images/product/" + orgImage;
                                $("#productPreview").attr("src", urlImage);
                                $("#productPreview").css({ "display": "block" });
                            }
                            avatarFile.change(function (e) {
                                const imgURL = URL.createObjectURL(e.target.files[0]);
                                $("#productPreview").attr("src", imgURL);
                                $("#productPreview").css({ "display": "block" });
                            });
                        });
                    </script>
                </head>

                <body class="sb-nav-fixed">
                    <jsp:include page="../layout/header.jsp" />
                    <div id="layoutSidenav">
                        <jsp:include page="../layout/sidebar.jsp" />
                        <div id="layoutSidenav_content">
                            <main>
                                <div class="container-fluid px-4">
                                    <h1 class="mt-4">Manage Product</h1>
                                    <ol class="breadcrumb mb-4">
                                        <li class="breadcrumb-item"><a href="/admin">Dashboard</a></li>
                                        <li class="breadcrumb-item"><a href="/admin/product">Products</a></li>
                                        <li class="breadcrumb-item active">Update</li>
                                    </ol>
                                    <div class=" mt-5">
                                        <div class="row">
                                            <div class="col-md-6 col-12 mx-auto">
                                                <h3>Update a Product</h3>
                                                <hr />
                                                <form:form class="row g-3" method="post" action="/admin/product/update"
                                                    modelAttribute="newProduct" enctype="multipart/form-data">
                                                    <form:input type="hidden" path="id" />
                                                    <spring:bind path="name">
                                                        <div class="col-md-6">
                                                            <label class="form-label">Name:</label>

                                                            <form:input type="text" path="name"
                                                                cssClass="form-control ${status.error ? 'is-invalid' : ''}" />

                                                            <form:errors path="name" cssClass="invalid-feedback" />
                                                        </div>
                                                    </spring:bind>
                                                    <spring:bind path="price">
                                                        <div class="col-md-6">
                                                            <label class="form-label">Price:</label>

                                                            <form:input type="number" path="price"
                                                                cssClass="form-control ${status.error ? 'is-invalid' : ''}" />

                                                            <form:errors path="price" cssClass="invalid-feedback" />
                                                        </div>
                                                    </spring:bind>
                                                    <spring:bind path="detailDesc">
                                                        <div class="mb-3 col-12">
                                                            <label class="form-label">Detail description:</label>

                                                            <form:input type="text" path="detailDesc"
                                                                cssClass="form-control ${status.error ? 'is-invalid' : ''}" />

                                                            <form:errors path="detailDesc"
                                                                cssClass="invalid-feedback" />
                                                        </div>
                                                    </spring:bind>
                                                    <spring:bind path="shortDesc">
                                                        <div class="col-md-6">
                                                            <label class="form-label">Short description:</label>

                                                            <form:input type="text" path="shortDesc"
                                                                cssClass="form-control ${status.error ? 'is-invalid' : ''}" />

                                                            <form:errors path="shortDesc" cssClass="invalid-feedback" />
                                                        </div>
                                                    </spring:bind>
                                                    <spring:bind path="quantity">
                                                        <div class="col-md-6">
                                                            <label class="form-label">Quantity:</label>

                                                            <form:input type="text" path="quantity"
                                                                cssClass="form-control ${status.error ? 'is-invalid' : ''}" />

                                                            <form:errors path="quantity" cssClass="invalid-feedback" />
                                                        </div>
                                                    </spring:bind>
                                                    <div class="col-md-6">
                                                        <label class="form-label">Factory:</label>
                                                        <form:select class="form-select" path="factory">
                                                            <form:option value="APPLE">Apple (MacBook)</form:option>
                                                            <form:option value="ASUS">Asus</form:option>
                                                            <form:option value="LENOVO">Lenovo</form:option>
                                                            <form:option value="DELL">Dell</form:option>
                                                            <form:option value="LG">LG</form:option>
                                                            <form:option value="ACER">Acer</form:option>
                                                        </form:select>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <label class="form-label">Target:</label>
                                                        <form:select class="form-select" path="target">
                                                            <form:option value="GAMING">Gaming</form:option>
                                                            <form:option value="OFFICE">Student - Office</form:option>
                                                            <form:option value="DESIGN">Graphic design</form:option>
                                                            <form:option value="THIN_LIGHT">Thin & Light</form:option>
                                                            <form:option value="BUSINESS">Entrepreneur</form:option>
                                                        </form:select>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <label for="productFile" class="form-label">Image:</label>
                                                        <input class="form-control" type="file" id="khanhFile"
                                                            accept=".png, .jpg, .jpeg" name="khanhFile" />
                                                    </div>
                                                    <div class="col-md-6">
                                                        <img style="max-height: 250px; display: none;"
                                                            alt="product preview" id="productPreview">
                                                    </div>
                                                    <div class="col-12 mb-5">
                                                        <button type="submit" class="btn btn-primary">Update</button>
                                                    </div>
                                                </form:form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </main>
                            <jsp:include page="../layout/footer.jsp" />
                        </div>
                    </div>
                    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
                        crossorigin="anonymous"></script>
                    <script src="/js/scripts.js"></script>
                </body>

                </html>