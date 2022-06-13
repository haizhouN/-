package book.pojo.controller;

import book.pojo.Cart;
import book.pojo.User;
import book.pojo.service.CartItemService;
import book.pojo.service.UserService;

import javax.servlet.http.HttpSession;

public class UserController {
    private UserService userService;
    private CartItemService cartItemService;

    public String login(String uname, String pwd, HttpSession session) {
        User user = userService.login(uname, pwd);
        if (user != null) {
            Cart cart = cartItemService.getCart(user);
            user.setCart(cart);
            session.setAttribute("currUser", user);
            return "redirect:book.do";
        }

//        System.out.println("user" + user);
        return "user/login";
    }


}
