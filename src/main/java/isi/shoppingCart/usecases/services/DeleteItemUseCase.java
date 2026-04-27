package isi.shoppingCart.usecases.services;

import isi.shoppingCart.entities.Cart;
import isi.shoppingCart.entities.CartItem;
import isi.shoppingCart.entities.Product;
import isi.shoppingCart.usecases.dto.OperationResult;
import isi.shoppingCart.usecases.ports.CartRepository;
import isi.shoppingCart.usecases.ports.ProductRepository;

import java.util.List;

public class DeleteItemUseCase {
    private CartRepository cartRepository;
    private ProductRepository productRepository;

    public DeleteItemUseCase(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }


    public OperationResult execute(int itemId) {
        Cart cart = cartRepository.getCart();
        Product product = productRepository.findById(itemId);

        if(product == null) {
            return OperationResult.fail("Product not found");
        }
        cart.removeProduct(product);
        cartRepository.save(cart);
        return OperationResult.ok("item removed");



    }
}
