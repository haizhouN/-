package book.pojo.service.Impl;

import book.pojo.Book;
import book.pojo.Cart;
import book.pojo.CartItem;
import book.pojo.User;
import book.pojo.dao.CartItemDAO;
import book.pojo.service.BookService;
import book.pojo.service.CartItemService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartItemServiceImpl implements CartItemService {
    private CartItemDAO cartItemDAO;
    private BookService bookService;

    @Override
    public void addCartItem(CartItem cartItem) {
        cartItemDAO.addCartItem(cartItem);
    }

    @Override
    public void updateCartItem(CartItem cartItem) {
        cartItemDAO.updateCartItem(cartItem);
    }

    @Override
    public void addOrUpdateCartItem(CartItem cartItem, Cart cart) {
        //1.如果当前用户的购物车中已经存在这个图书，那么将购物车中这本图书的数量+1
        //否则在我的购物车中新增一个这本图书的CartItem项，数量是1

        //判断当前用户的购物车中是否有这本书
        if (cart != null) {
            Map<Integer, CartItem> cartItemMap = cart.getCartItemMap();
            if (cartItemMap == null) {
                cartItemMap = new HashMap<>();
            }
            if (cartItemMap.containsKey(cartItem.getBook().getId())) {
                CartItem cartItemTemp = cartItemMap.get(cartItem.getBook().getId());
                cartItemTemp.setBuyCount(cartItemTemp.getBuyCount() + 1);
                updateCartItem(cartItemTemp);
            } else {
                addCartItem(cartItem);
            }
        } else {//连购物车都没有的情况
            addCartItem(cartItem);
        }

    }

    @Override
    public List<CartItem> getCartItemList(User user) {
        List<CartItem> cartItemList = cartItemDAO.getCartItemList(user);
        for (CartItem cartItem : cartItemList) {
            Book book = bookService.getBook(cartItem.getBook().getId());
            cartItem.setBook(book);
        }

        return cartItemList;
    }

    @Override
    public Cart getCart(User user) {
        List<CartItem> cartItemList = getCartItemList(user);
        Map<Integer, CartItem> cartItemMap = new HashMap<>();
        for (CartItem cartItem : cartItemList) {
            cartItemMap.put(cartItem.getBook().getId(), cartItem);
        }
        Cart cart = new Cart();
        cart.setCartItemMap(cartItemMap);
        return cart;
    }
}
