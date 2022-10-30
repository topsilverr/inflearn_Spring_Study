package hello.itemservice.domain.item;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class item {

    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity; // Null 값을 넣기 위해

    public item() {
    }

    public item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
