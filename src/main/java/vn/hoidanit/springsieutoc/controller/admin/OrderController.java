package vn.hoidanit.springsieutoc.controller.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import vn.hoidanit.springsieutoc.domain.Order;
import vn.hoidanit.springsieutoc.service.OrderService;
import vn.hoidanit.springsieutoc.service.ProductService;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class OrderController {
    private final OrderService orderService;
    private final ProductService productService;

    public OrderController(OrderService orderService, ProductService productService) {
        this.orderService = orderService;
        this.productService = productService;
    }

    @GetMapping("/admin/order")
    public String getOrderPage(Model model) {
        List<Order> orders = this.orderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "admin/order/show";
    }

    @GetMapping("/admin/order/{id}")
    public String getOrderDetailPage(Model model, @PathVariable long id) {
        Order order = this.orderService.getOrderById(id).get();
        model.addAttribute("id", id);
        model.addAttribute("order", order);
        model.addAttribute("orderDetails", order.getOrderDetails());

        return "admin/order/detail";
    }

    @GetMapping("/admin/order/update/{id}")
    public String getOrderUpdatePage(Model model, @PathVariable long id) {
        Optional<Order> currentOrder = this.orderService.getOrderById(id);
        model.addAttribute("newOrder", currentOrder.get());
        return "admin/order/update";
    }

    @PostMapping("/admin/order/update")
    public String postOrderPage(@ModelAttribute("newOrder") Order order) {
        this.orderService.updateOrder(order);
        return "redirect:/admin/order";
    }

    @GetMapping("/admin/order/delete/{id}")
    public String getOrderDeletePage(Model model, @PathVariable long id) {
        Order order = new Order();
        order.setId(id);
        model.addAttribute("newOrder", order);
        model.addAttribute("id", id);
        return "admin/order/delete";
    }

    @PostMapping("/admin/order/delete")
    public String postOrderDelete(@ModelAttribute("newOrder") Order order) {
        this.orderService.deleteOrderById(order.getId());
        return "redirect:/admin/order";
    }

}
