package book.pojo.dao.impl;

import book.pojo.OrderBean;
import book.pojo.User;
import book.pojo.dao.OrderDAO;
import myssm.basedao.BaseDAO;

import java.math.BigDecimal;
import java.util.List;

public class OrderDAOImpl extends BaseDAO<OrderBean> implements OrderDAO {
    @Override
    public void addOrderBean(OrderBean orderBean) {
        int orderBeanId = super.executeUpdate("insert into t_order values(0,?,?,?,?,?)", orderBean.getOrderNo(), orderBean.getOrderDate(), orderBean.getOrderUser().getId(), orderBean.getOrderMoney(), orderBean.getOrderStatus());
        orderBean.setId(orderBeanId);
    }

    @Override
    public List<OrderBean> getOrderList(User user) {
        return executeQuery("select * from t_order where orderUser=?", user.getId());
    }

    @Override
    public Integer getOrderTotalBookCount(OrderBean orderBean) {
        String sql = "select sum(t3.buyCount) as totalBookCount,t3.orderBean from( " +
                "select t1.id,t2.buyCount ,t2.orderBean from t_order t1 inner join t_order_item t2 " +
                "on t1.id=t2.orderBean where t1.orderUser= ? ) t3 where t3.orderBean= ? group by t3.orderBean;";
        System.out.println(orderBean.getId());
        return ((BigDecimal) executeComplexQuery(sql, orderBean.getOrderUser().getId(), orderBean.getId())[0]).intValue();
    }
}
