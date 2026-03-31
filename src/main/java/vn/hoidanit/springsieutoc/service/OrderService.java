package vn.hoidanit.springsieutoc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import vn.hoidanit.springsieutoc.domain.Order;
import vn.hoidanit.springsieutoc.repository.OrderDetailRepository;
import vn.hoidanit.springsieutoc.repository.OrderRepository;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;

    public OrderService(OrderRepository orderRepository, OrderDetailRepository orderDetailRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    public List<Order> getAllOrders() {
        return this.orderRepository.findAll();
    }

    public Optional<Order> getOrderById(long id) {
        return this.orderRepository.findById(id);
    }

    public void updateOrder(Order order) {
        Optional<Order> orderOptional = this.getOrderById(order.getId());
        if (orderOptional.isPresent()) {
            Order currentOrder = orderOptional.get();
            currentOrder.setStatus(order.getStatus());
            this.orderRepository.save(currentOrder);
        }
    }

    public void deleteOrderById(long id) {
        this.orderDetailRepository.deleteById(id);
        this.orderRepository.deleteById(id);
    }
}
