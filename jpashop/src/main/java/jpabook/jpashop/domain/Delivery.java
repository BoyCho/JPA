package jpabook.jpashop.domain;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
public class Delivery {

    @Id @GeneratedValue
    private Long id;

    private DeliveryStatus status;

    private Address address;

    @OneToOne(mappedBy = "delivery", fetch = LAZY)
    private Order order;
}
