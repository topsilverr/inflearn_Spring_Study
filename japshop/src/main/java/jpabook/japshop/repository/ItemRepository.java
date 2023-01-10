package jpabook.japshop.repository;

import jakarta.persistence.EntityManager;
import jpabook.japshop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item){
        if(item.getId() == null){ // 새로 생성한 객체 -> 신규등록
            em.persist(item);
        } else {
            em.merge(item); // 원래 있던 아이템 정보 업데이트?
        }
    }

    public Item findOne(Long id){
        return em.find(Item.class,id);
    }

    public List<Item> findAll(){
        return em.createQuery("select i from Item i",Item.class)
                .getResultList();
    }
}
