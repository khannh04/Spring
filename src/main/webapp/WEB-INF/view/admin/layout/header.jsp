<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">

            <!-- Logo -->
            <a class="navbar-brand ps-3" href="/admin">LaptopShop</a>

            <!-- Toggle sidebar -->
            <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle">
                <i class="fas fa-bars"></i>
            </button>

            <!-- Đẩy sang phải -->
            <div class="ms-auto d-flex align-items-center">

                <span class="text-white me-3">Welcome, khanhnguyxn</span>

                <!-- User dropdown -->
                <ul class="navbar-nav">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button"
                            data-bs-toggle="dropdown">
                            <i class="fas fa-user fa-fw"></i>
                        </a>

                        <ul class="dropdown-menu dropdown-menu-end">
                            <li><a class="dropdown-item" href="#">Settings</a></li>
                            <li><a class="dropdown-item" href="#">Activity Log</a></li>
                            <li>
                                <hr class="dropdown-divider">
                            </li>
                            <li>
                                <form method="post" action="/logout">
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                    <button class="dropdown-item">Logout</button>
                                </form>
                            </li>
                        </ul>

                    </li>
                </ul>

            </div>

        </nav>