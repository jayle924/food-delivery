package fooddelivery.domain;

import fooddelivery.domain.Paid;
import fooddelivery.FrontApplication;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import java.util.Date;


@Entity
@Table(name="Payment_table")
@Data

public class Payment  {


    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
    
    
    
    
    private Long id;
    
    
    
    
    
    private String orderId;
    
    
    
    
    
    private String status;
    
    
    
    
    
    private String foodId;
    
    
    
    
    
    private String address;

    @PostPersist
    public void onPostPersist(){


        Paid paid = new Paid(this);
        paid.setStatus("결제됨");
        paid.publishAfterCommit();

    }

    public static PaymentRepository repository(){
        PaymentRepository paymentRepository = FrontApplication.applicationContext.getBean(PaymentRepository.class);
        return paymentRepository;
    }




    public static void pay(OrderPlaced orderPlaced){

        /** Example 1:  new item */
        Payment payment = new Payment();
        payment.setOrderId(String.valueOf(orderPlaced.getId()));
        payment.setFoodId(orderPlaced.getFoodId());
        payment.setAddress(orderPlaced.getAddress());
        payment.setStatus(orderPlaced.getStatus());
        repository().save(payment);
        /** Example 2:  finding and process
        
        repository().findById(orderPlaced.get???()).ifPresent(payment->{
            
            payment // do something
            repository().save(payment);


         });
        */

        
    }


}
