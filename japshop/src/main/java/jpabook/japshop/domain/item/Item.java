package jpabook.japshop.domain.item;

import jakarta.persistence.*;
import jpabook.japshop.domain.Category;
import jpabook.japshop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    //==비지니스 로직==//

    /**
     * stock 증가
     */
    public void addStock(int quantity){
        this.stockQuantity+=quantity;
    }

    /**
     * stock 감소
     * stock 의 수량이 0 보다 작은 경우 예외 발생
     */
    public void removeStock(int quantity){
        int restStock = this.stockQuantity - quantity;
        if(restStock<0){
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }
}
