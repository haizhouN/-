package book.pojo.dao.impl;

import book.pojo.OrderItem;
import book.pojo.dao.OrderItemDAO;
import myssm.basedao.BaseDAO;

public class OrderItemDAOImpl extends BaseDAO<OrderItem> implements OrderItemDAO {
    @Override
    public void addOrderItem(OrderItem orderItem) {
        super.executeUpdate("insert into t_order_item values(0,?,?,?)", orderItem.getBook().getId(), orderItem.getBuyCount(), orderItem.getOrderBean().getId());
    }
}
