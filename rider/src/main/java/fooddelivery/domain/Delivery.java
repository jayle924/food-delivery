package fooddelivery.domain;

import fooddelivery.domain.Delivered;
import fooddelivery.domain.Picked;
import fooddelivery.RiderApplication;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import java.util.Date;


@Entity
@Table(name="Delivery_table")
@Data

public class Delivery  {


    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
    
    
    
    
    private Long id;
    
    
    
    
    
    private String status;
    
    
    
    
    
    private String orderId;
    
    
    
    
    
    private String address;
    
    
    
    
    
    private String foodId;

    @PostPersist
    public void onPostPersist(){

        // Picked picked = new Picked(this);
        // picked.setStatus("요리픽됨");
        // picked.publishAfterCommit();


        // Delivered delivered = new Delivered(this);
        // delivered.setStatus("배송됨");
        // delivered.publishAfterCommit();
    }

    public static DeliveryRepository repository(){
        DeliveryRepository deliveryRepository = RiderApplication.applicationContext.getBean(DeliveryRepository.class);
        return deliveryRepository;
    }

    public void pick(){
        this.setStatus("요리픽됨");
        Picked picked = new Picked(this);
        picked.publishAfterCommit();
    }

    public void confirm(){
        this.setStatus("배송완료됨");
        Delivered delivered = new Delivered(this);
        delivered.publishAfterCommit();
    }

    public static void addPickableList(CookFinisied cookFinisied){

        /** Example 1:  new item */
        Delivery delivery = new Delivery();
        delivery.setOrderId(cookFinisied.getOrderId());
        delivery.setFoodId(cookFinisied.getFoodId());
        delivery.setAddress(cookFinisied.getAddress());
        delivery.setStatus(cookFinisied.getStatus());
        repository().save(delivery);

        /** Example 2:  finding and process
        
        repository().findById(cookFinisied.get???()).ifPresent(delivery->{
            
            delivery // do something
            repository().save(delivery);


         });
        */

        
    }


}
