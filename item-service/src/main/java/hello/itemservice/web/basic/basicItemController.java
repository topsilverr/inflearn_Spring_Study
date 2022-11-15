package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class basicItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model){
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items",items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId,Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item",item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm(){
        return "basic/addForm";
    }

    //@PostMapping("/add")
    public String addItemV1(@RequestParam String itemName,
                       @RequestParam int price,
                       @RequestParam Integer quantity,
                       Model model)
    {
        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        itemRepository.save(item);

        model.addAttribute("item",item);

        return "basic/item";
    }

    //@PostMapping("/add")
    // @ModelAttribute : model 객체 생성해주기 & 괄호 안에 있는 이름으로 모델에 넣어주는 두 가지 역할을 행함
    public String addItemV2(@ModelAttribute("item") Item item, Model model)
    {
        itemRepository.save(item);

//        model.addAttribute("item",item); // 자동 추가가 되기 때문에 생략 가능

        return "basic/item";
    }

    //@PostMapping("/add")
    // @ModelAttribute : model 객체 생성해주기 & 괄호 안에 있는 이름으로 모델에 넣어주는 두 가지 역할을 행함
    // 괄호 안 이름 생략하면 현재 class명 즉 Item 의 첫 글자만 소문자로 바꾼 이름을 modelattribute 에 넣어줌
    public String addItemV3(@ModelAttribute Item item, Model model)
    {
        itemRepository.save(item);

//        model.addAttribute("item",item); // 자동 추가가 되기 때문에 생략 가능

        return "basic/item";
    }

    //@PostMapping("/add")
    // @ModelAttribute : model 객체 생성해주기 & 괄호 안에 있는 이름으로 모델에 넣어주는 두 가지 역할을 행함
    // 괄호 안 이름 생략하면 현재 class명 즉 Item 의 첫 글자만 소문자로 바꾼 이름을 modelattribute 에 넣어줌
    // 임의의 객체일 경우 @ModelAttribute 생략 가능
    public String addItemV4(Item item, Model model)
    {
        itemRepository.save(item);

//        model.addAttribute("item",item); // 자동 추가가 되기 때문에 생략 가능

        return "basic/item";
    }
    @PostMapping("/add")
    public String addItemV5(Item item, Model model)
    {
        itemRepository.save(item);
        return "redirect:/basic/items/"+item.getId();
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId,Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item",item);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId,@ModelAttribute Item item){
        itemRepository.update(itemId,item);
        return "redirect:/basic/items/{itemId}";
    }
    /**
     *  테스트용 데이터 추가
     */
    @PostConstruct
    public void init(){
        itemRepository.save(new Item("itemA",10000,10));
        itemRepository.save(new Item("itemB",20000,20));
    }
}
