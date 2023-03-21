package fooddelivery.domain;

import fooddelivery.domain.OrderAccepted;
import fooddelivery.domain.OrderRejected;
import fooddelivery.domain.CookStarted;
import fooddelivery.domain.CookFinisied;
import fooddelivery.StoreApplication;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import java.util.Date;


@Entity
@Table(name="Cooking_table")
@Data

public class Cooking  {


    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
    
    
    
    
    private Long id;
    
    
    
    
    
    private String status;
    
    
    
    
    
    private String foodId;
    
    
    
    
    
    private String orderId;
    
    
    
    @ElementCollection
    
    private List<String> options;
    
    
    
    
    
    private String address;

    @PostPersist
    public void onPostPersist(){


        OrderAccepted orderAccepted = new OrderAccepted(this);
        orderAccepted.setStatus("주문수락됨");
        orderAccepted.publishAfterCommit();



        OrderRejected orderRejected = new OrderRejected(this);
        orderRejected.setStatus("주문거절됨");
        orderRejected.publishAfterCommit();



        CookStarted cookStarted = new CookStarted(this);
        cookStarted.setStatus("요리시작됨");
        cookStarted.publishAfterCommit();



        CookFinisied cookFinisied = new CookFinisied(this);
        cookFinisied.setStatus("요리완료됨");
        cookFinisied.publishAfterCommit();

    }
    @PreRemove
    public void onPreRemove(){
    }

    public static CookingRepository repository(){
        CookingRepository cookingRepository = StoreApplication.applicationContext.getBean(CookingRepository.class);
        return cookingRepository;
    }



    public void start(){
    }

    public static void sendOrderInfo(Paid paid){

        /** Example 1:  new item */ 
        Cooking cooking = new Cooking();
        cooking.setOrderId(paid.getOrderId());
        cooking.setFoodId(paid.getFoodId());
        cooking.setAddress(paid.getAddress());
        cooking.setStatus(paid.getStatus());
        repository().save(cooking);

        /** Example 2:  finding and process
        
        repository().findById(paid.get???()).ifPresent(cooking->{
            
            cooking // do something
            repository().save(cooking);


         });
        */

        
    }
    public static void refund(OrderCanceled orderCanceled){

        /** Example 1:  new item 
        Cooking cooking = new Cooking();
        repository().save(cooking);

        */

        /** Example 2:  finding and process */
        
        repository().findByOrderId(orderCanceled.getId()).ifPresent(cooking->{
            cooking.setStatus("주문취소됨");
            repository().save(cooking);
         });
        
    }


}
