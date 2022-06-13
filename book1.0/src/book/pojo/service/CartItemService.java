package book.pojo.service;

import book.pojo.Cart;
import book.pojo.CartItem;
import book.pojo.User;

import java.util.List;

public interface CartItemService {
    void addCartItem(CartItem cartItem);

    void updateCartItem(CartItem cartItem);

    void addOrUpdateCartItem(CartItem cartItem, Cart cart);

    //获取指定用户的所有购物车项列表（此方法内部查询的时候，将book的详细信息设置进去）
    List<CartItem> getCartItemList(User user);

    //加载特定用户的购物车信息
    Cart getCart(User user);
}
