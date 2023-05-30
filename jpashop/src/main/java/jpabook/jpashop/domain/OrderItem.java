package jpabook.jpashop.domain;

import jpabook.jpashop.domain.items.Item;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "ORDER_ITEM_ID")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    private int orderPrice;
    private int count;

    public void setOrder(Order order) {
        this.order = order;
    }

}
