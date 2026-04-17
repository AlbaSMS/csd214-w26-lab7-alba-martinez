package csd214.app.entities;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Objects;

@Entity
@DiscriminatorValue("MAGAZINE")
public class MagazineEntity extends PublicationEntity {

    @Column(name = "order_qty")
    private int orderQty;

    @Column(name = "issue_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE) // Stores as DATE in MySQL (no time component)
    private Date currentIssue;
    private String publisher;

    public MagazineEntity() {
        super();
    }

    public MagazineEntity(String title, double price, int copies, int orderQty, Date currentIssue, String publisher) {
        this.setTitle(title);
        this.setPrice(price);
        this.setCopies(copies);
        this.orderQty = orderQty;
        this.currentIssue = currentIssue;
        this.publisher = publisher;
    }

    public int getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(int orderQty) {
        this.orderQty = orderQty;
    }

    public Date getCurrentIssue() {
        return currentIssue;
    }

    public void setCurrentIssue(Date currentIssue) {
        this.currentIssue = currentIssue;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MagazineEntity that = (MagazineEntity) o;
        return getOrderQty() == that.getOrderQty() && Objects.equals(getCurrentIssue(), that.getCurrentIssue()) && Objects.equals(getPublisher(), that.getPublisher());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getOrderQty(), getCurrentIssue(), getPublisher());
    }

    @Override
    public String toString() {
        return "MagazineEntity{" +
                "orderQty=" + orderQty +
                ", currentIssue=" + currentIssue +
                ", publisher='" + publisher + '\'' +
                '}';
    }
}