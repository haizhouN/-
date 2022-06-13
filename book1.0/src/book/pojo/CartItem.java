package book.pojo;

public class CartItem {
    private Integer id;
    private Book book;
    private Integer buyCount;
    private User userBean;

    public CartItem(Book book, Integer buyCount, User user) {
        this.book = book;
        this.buyCount = buyCount;
        this.userBean = user;
    }

    public CartItem(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(Integer buyCount) {
        this.buyCount = buyCount;
    }

    public User getUser() {
        return userBean;
    }

    public void setUser(User userBean) {
        this.userBean = userBean;
    }

    public CartItem() {

    }

}
