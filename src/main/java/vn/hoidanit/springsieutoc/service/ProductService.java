package vn.hoidanit.springsieutoc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.servlet.http.HttpSession;
import vn.hoidanit.springsieutoc.domain.Cart;
import vn.hoidanit.springsieutoc.domain.CartDetail;
import vn.hoidanit.springsieutoc.domain.Order;
import vn.hoidanit.springsieutoc.domain.OrderDetail;
import vn.hoidanit.springsieutoc.domain.Product;
import vn.hoidanit.springsieutoc.domain.User;
import vn.hoidanit.springsieutoc.repository.CartDetailRepository;
import vn.hoidanit.springsieutoc.repository.CartRepository;
import vn.hoidanit.springsieutoc.repository.OrderDetailRepository;
import vn.hoidanit.springsieutoc.repository.OrderRepository;
import vn.hoidanit.springsieutoc.repository.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;
    private final UserService userService;
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;

    public ProductService(ProductRepository productRepository, CartRepository cartRepository,
            CartDetailRepository cartDetailRepository, UserService userService, OrderRepository orderRepository,
            OrderDetailRepository orderDetailRepository) {
        this.productRepository = productRepository;
        this.cartDetailRepository = cartDetailRepository;
        this.cartRepository = cartRepository;
        this.userService = userService;
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    public Product getProductById(long id) {
        return this.productRepository.findById(id).orElse(null);
    }

    public Product handleSaveProduct(Product product) {
        return this.productRepository.save(product);
    }

    public void deleteAProduct(long id) {
        this.productRepository.deleteById(id);
    }

    public List<Product> getALlProducts() {
        return this.productRepository.findAll();
    }

    public Cart findByUser(User user) {
        return this.cartRepository.findByUser(user);
    }

    public void handleAddProductToCard(String email, long productId, HttpSession session) {
        User user = this.userService.getUserByEmail(email);
        if (user != null) {
            Cart cart = this.cartRepository.findByUser(user);
            if (cart == null) {
                // create new cart
                Cart otherCart = new Cart();
                otherCart.setUser(user);
                otherCart.setSum(0);

                cart = this.cartRepository.save(otherCart);
            }
            // save cart_detail
            // find product by id
            Optional<Product> productOptional = this.productRepository.findById(productId);
            if (productOptional.isPresent()) {
                Product realProduct = productOptional.get();

                // check product have existed in cart before
                CartDetail oldDetail = this.cartDetailRepository.findByCartAndProduct(cart, realProduct);

                if (oldDetail == null) {
                    CartDetail cd = new CartDetail();
                    cd.setCart(cart);
                    cd.setProduct(realProduct);
                    cd.setPrice(realProduct.getPrice());
                    cd.setQuantity(1);
                    this.cartDetailRepository.save(cd);

                    // update sum
                    int s = cart.getSum() + 1;
                    cart.setSum(s);
                    this.cartRepository.save(cart);
                    session.setAttribute("sum", s);
                } else {
                    oldDetail.setQuantity(oldDetail.getQuantity() + 1);
                    this.cartDetailRepository.save(oldDetail);
                }

            }
        }

    }

    public void handleRemoveCartDetail(long cartDetailId, HttpSession session) {
        Optional<CartDetail> cdOptional = this.cartDetailRepository.findById(cartDetailId);
        if (cdOptional.isPresent()) {
            CartDetail cartDetail = cdOptional.get();
            Cart currentCart = cartDetail.getCart();

            // delete cart detail
            this.cartDetailRepository.deleteById(cartDetailId);

            // update cart
            if (currentCart.getSum() > 1) {
                // update current cart
                int s = currentCart.getSum() - 1;
                currentCart.setSum(s);
                session.setAttribute("sum", s);
                this.cartRepository.save(currentCart);
            } else {
                // delete cart (sum = 0)
                this.cartRepository.deleteById(currentCart.getId());
                session.setAttribute("sum", 0);
            }

        }
    }

    public void handleUpdateCartBeforeCheckout(List<CartDetail> cartDetails) {
        for (CartDetail cartDetail : cartDetails) {
            Optional<CartDetail> cdOptional = this.cartDetailRepository.findById(cartDetail.getId());
            if (cdOptional.isPresent()) {
                CartDetail currentCartDetail = cdOptional.get();
                currentCartDetail.setQuantity(cartDetail.getQuantity());
                this.cartDetailRepository.save(currentCartDetail);
            }
        }
    }

    public void handlePlaceOrder(User user, HttpSession session,
            String receiverName, String receiverAddress, String receiverPhone) {

        Cart cart = this.cartRepository.findByUser(user);
        if (cart != null) {
            List<CartDetail> cartDetails = cart.getCartDetails();
            if (cartDetails != null) {
                Order order = new Order();
                order.setUser(user);
                order.setReceiverPhone(receiverPhone);
                order.setReceiverName(receiverName);
                order.setReceiverAddress(receiverAddress);
                order.setStatus("PENDING");
                double sum = 0;
                for (CartDetail cd : cartDetails) {
                    sum += cd.getPrice();
                }
                order.setTotalPrice(sum);
                order = this.orderRepository.save(order);

                for (CartDetail cd : cartDetails) {
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setOrder(order);
                    orderDetail.setProduct(cd.getProduct());
                    orderDetail.setPrice(cd.getPrice());
                    orderDetail.setQuantity(cd.getQuantity());
                    this.orderDetailRepository.save(orderDetail);
                }
                for (CartDetail cd : cartDetails) {
                    this.cartDetailRepository.deleteById(cd.getId());
                }
                this.cartRepository.deleteById(cart.getId());
                session.setAttribute("sum", 0);
            }
        }
    }

}
