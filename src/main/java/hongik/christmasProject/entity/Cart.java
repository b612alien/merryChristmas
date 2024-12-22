package hongik.christmasProject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//id

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; //유저

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product; //상품

    private int quantity; //상품수량
}