package book.pojo.controller;

import book.pojo.Book;
import book.pojo.Cart;
import book.pojo.CartItem;
import book.pojo.User;
import book.pojo.service.CartItemService;

import javax.servlet.http.HttpSession;

public class CartController {
    private CartItemService cartItemService;

    //加载当前购物车的信息
    public String index(HttpSession session) {
        User user = (User) session.getAttribute("currUser");
        Cart cart = cartItemService.getCart(user);
        user.setCart(cart);
        session.setAttribute("currUser", user);
        return "cart/cart";

    }

    public String addCart(Integer bookId, HttpSession session) {
        User user = (User) session.getAttribute("currUser");
        CartItem cartItem = new CartItem(new Book(bookId), 1, user);
        //将指定的图书添加到当前用户的购物车中
        cartItemService.addOrUpdateCartItem(cartItem, user.getCart());

        return "redirect:cart.do";
    }
}
