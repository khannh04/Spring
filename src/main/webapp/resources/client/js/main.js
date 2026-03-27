(function ($) {
    "use strict";

    // Spinner
    var spinner = function () {
        setTimeout(function () {
            if ($('#spinner').length > 0) {
                $('#spinner').removeClass('show');
            }
        }, 1);
    };
    spinner(0);


    // Fixed Navbar
    $(window).scroll(function () {
        if ($(window).width() < 992) {
            if ($(this).scrollTop() > 55) {
                $('.fixed-top').addClass('shadow');
            } else {
                $('.fixed-top').removeClass('shadow');
            }
        } else {
            if ($(this).scrollTop() > 55) {
                $('.fixed-top').addClass('shadow').css('top', 0);
            } else {
                $('.fixed-top').removeClass('shadow').css('top', 0);
            }
        }
    });


    // Back to top button
    $(window).scroll(function () {
        if ($(this).scrollTop() > 300) {
            $('.back-to-top').fadeIn('slow');
        } else {
            $('.back-to-top').fadeOut('slow');
        }
    });
    $('.back-to-top').click(function () {
        $('html, body').animate({ scrollTop: 0 }, 1500, 'easeInOutExpo');
        return false;
    });


    // Testimonial carousel
    $(".testimonial-carousel").owlCarousel({
        autoplay: true,
        smartSpeed: 2000,
        center: false,
        dots: true,
        loop: true,
        margin: 25,
        nav: true,
        navText: [
            '<i class="bi bi-arrow-left"></i>',
            '<i class="bi bi-arrow-right"></i>'
        ],
        responsiveClass: true,
        responsive: {
            0: {
                items: 1
            },
            576: {
                items: 1
            },
            768: {
                items: 1
            },
            992: {
                items: 2
            },
            1200: {
                items: 2
            }
        }
    });


    // vegetable carousel
    $(".vegetable-carousel").owlCarousel({
        autoplay: true,
        smartSpeed: 1500,
        center: false,
        dots: true,
        loop: true,
        margin: 25,
        nav: true,
        navText: [
            '<i class="bi bi-arrow-left"></i>',
            '<i class="bi bi-arrow-right"></i>'
        ],
        responsiveClass: true,
        responsive: {
            0: {
                items: 1
            },
            576: {
                items: 1
            },
            768: {
                items: 2
            },
            992: {
                items: 3
            },
            1200: {
                items: 4
            }
        }
    });


    // Modal Video
    $(document).ready(function () {
        var $videoSrc;
        $('.btn-play').click(function () {
            $videoSrc = $(this).data("src");
        });
        console.log($videoSrc);

        $('#videoModal').on('shown.bs.modal', function (e) {
            $("#video").attr('src', $videoSrc + "?autoplay=1&amp;modestbranding=1&amp;showinfo=0");
        })

        $('#videoModal').on('hide.bs.modal', function (e) {
            $("#video").attr('src', $videoSrc);
        })
    });

    const navElement = $("#navbarCollapse");
    const currentUrl = window.location.pathname;
    navElement.find('a.nav-link').each(function () {
        const link = $(this); // Get the current link in the loop
        const href = link.attr('href'); // Get the href attribute of the link

        if (href === currentUrl) {
            link.addClass('active'); // Add 'active' class if the href matches the current URL
        } else {
            link.removeClass('active'); // Remove 'active' class if the href does not match
        }
    });


    $('.quantity button').on('click', function () {
        var button = $(this);
        var input = button.parent().parent().find('input');
        var oldValue = parseFloat(input.val()) || 1;
        var newVal;

        // Tính toán số lượng mới
        if (button.hasClass('btn-plus')) {
            newVal = oldValue + 1;
        } else {
            newVal = (oldValue > 1) ? oldValue - 1 : 1;
        }
        input.val(newVal);

        // --- BƯỚC 1: ĐỒNG BỘ SANG FORM ẨN ĐỂ LƯU DATABASE ---
        const index = input.attr("data-cart-detail-index");
        // Sử dụng attribute selector để tránh lỗi dấu chấm trong ID của Spring
        $(`input[id="cartDetails${index}.quantity"]`).val(newVal);


        // --- BƯỚC 2: CẬP NHẬT HIỂN THỊ THÀNH TIỀN TỪNG SẢN PHẨM ---
        const pricePerUnit = input.attr("data-cart-detail-price");
        const id = input.attr("data-cart-detail-id");
        const priceElement = $(`p[data-cart-detail-id='${id}']`);

        if (priceElement.length > 0) {
            const newPrice = parseFloat(pricePerUnit) * newVal;
            priceElement.text(formatCurrency(newPrice) + " đ");
            // Cập nhật luôn data attribute để hàm tính tổng dùng được số mới
            priceElement.attr("data-cart-detail-subtotal", newPrice);
        }

        // --- BƯỚC 3: CẬP NHẬT TỔNG TIỀN TOÀN GIỎ HÀNG ---
        updateCartTotal();
    });

    function updateCartTotal() {
        let total = 0;
        // Quét tất cả các dòng sản phẩm
        $('p[data-cart-detail-id]').each(function () {
            // Lấy giá trị đã tính toán ở Bước 2
            let subtotal = parseFloat($(this).attr("data-cart-detail-subtotal")) || 0;
            total += subtotal;
        });

        let formattedTotal = formatCurrency(total) + " đ";

        // Cập nhật vào tất cả các thẻ hiển thị tổng tiền (Subtotal, Total, v.v.)
        $('#cart-subtotal, #cart-total, [data-cart-total-price]').text(formattedTotal);
        // Nếu bạn dùng data-attribute để lưu tổng, hãy cập nhật nó
        $('[data-cart-total-price]').attr("data-cart-total-price", total);
    }

    $('#btnFilter').click(function (event) {
        event.preventDefault();

        let factoryArr = [];
        let targetArr = [];
        let priceArr = [];
        //factory filter
        $("#factoryFilter .form-check-input:checked").each(function () {
            factoryArr.push($(this).val());
        });

        //target filter
        $("#targetFilter .form-check-input:checked").each(function () {
            targetArr.push($(this).val());
        });

        //price filter
        $("#priceFilter .form-check-input:checked").each(function () {
            priceArr.push($(this).val());
        });

        //sort order
        let sortValue = $('input[name="radio-sort"]:checked').val();

        const currentUrl = new URL(window.location.href);
        const searchParams = currentUrl.searchParams;

        // Add or update query parameters
        searchParams.set('page', '1');
        searchParams.set('sort', sortValue);

        searchParams.delete('factory');
        searchParams.delete('target');
        searchParams.delete('price');

        if (factoryArr.length > 0) {
            searchParams.set('factory', factoryArr.join(','));
        }
        if (targetArr.length > 0) {
            searchParams.set('target', targetArr.join(','));
        }
        if (priceArr.length > 0) {
            searchParams.set('price', priceArr.join(','));
        }

        // Update the URL and reload the page
        window.location.href = currentUrl.toString();
    });

    function formatCurrency(value) {
        // Use the 'vi-VN' locale to format the number according to Vietnamese currency format
        // and 'VND' as the currency type for Vietnamese đồng
        const formatter = new Intl.NumberFormat('vi-VN', {
            style: 'decimal',
            minimumFractionDigits: 0, // No decimal part for whole numbers
        });

        let formatted = formatter.format(value);
        // Replace dots with commas for thousands separator
        formatted = formatted.replace(/\./g, ',');
        return formatted;
    }

})(jQuery);