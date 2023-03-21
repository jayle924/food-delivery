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


        Delivered delivered = new Delivered(this);
        delivered.publishAfterCommit();



        Picked picked = new Picked(this);
        picked.publishAfterCommit();

    }

    public static DeliveryRepository repository(){
        DeliveryRepository deliveryRepository = RiderApplication.applicationContext.getBean(DeliveryRepository.class);
        return deliveryRepository;
    }




    public static void addPickableList(CookFinisied cookFinisied){

        /** Example 1:  new item */
        Delivery delivery = new Delivery();
        delivery.setOrderId(cookFinisied.getOrderId());
        delivery.setFoodId(cookFinisied.getFoodId());
        delivery.setAddress(cookFinisied.getAddress());
        delivery.setStatus("요리완료됨");
        repository().save(delivery);

        /** Example 2:  finding and process
        
        repository().findById(cookFinisied.get???()).ifPresent(delivery->{
            
            delivery // do something
            repository().save(delivery);


         });
        */

        
    }


}
