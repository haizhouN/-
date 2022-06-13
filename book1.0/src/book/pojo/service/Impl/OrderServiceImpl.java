package book.pojo.service.Impl;

import book.pojo.CartItem;
import book.pojo.OrderBean;
import book.pojo.OrderItem;
import book.pojo.User;
import book.pojo.dao.CartItemDAO;
import book.pojo.dao.OrderDAO;
import book.pojo.dao.OrderItemDAO;
import book.pojo.service.OrderService;

import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    private OrderDAO orderDAO;
    private OrderItemDAO orderItemDAO;
    private CartItemDAO cartItemDAO;

    private Integer getOrderTotalBookCount;

    @Override
    public void addOrderBean(OrderBean orderBean) {
        //添加订单记录
        orderDAO.addOrderBean(orderBean);//orderBean中的id是有值的
        //订单详情表
        //orderBean中的orderItemList是空的，我们应该根据用户的购物车中的购物车项去转换成订单项
        User orderUser = orderBean.getOrderUser();
        Map<Integer, CartItem> cartItemMap = orderUser.getCart().getCartItemMap();
        for (CartItem cartItem : cartItemMap.values()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setBook(cartItem.getBook());
            orderItem.setBuyCount(cartItem.getBuyCount());
            orderItem.setOrderBean(orderBean);
            orderItemDAO.addOrderItem(orderItem);
        }


        //购物车删除对应的记录
        for (CartItem cartItem : cartItemMap.values()) {
            cartItemDAO.delCartItem(cartItem);

        }
    }

    @Override
    public List<OrderBean> getOrderList(User user) {
        List<OrderBean> orderBeanList = orderDAO.getOrderList(user);
        for (OrderBean orderBean : orderBeanList) {
            Integer totalBookCount = orderDAO.getOrderTotalBookCount(orderBean);
            orderBean.setTotalBookCount(totalBookCount);
        }

        return orderBeanList;
    }
}
