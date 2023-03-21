package fooddelivery.infra;

import fooddelivery.domain.*;
import fooddelivery.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class MyPageViewHandler {

    @Autowired
    private MyPageRepository myPageRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrderPlaced_then_CREATE_1 (@Payload OrderPlaced orderPlaced) {
        try {

            if (!orderPlaced.validate()) return;

            // view 객체 생성
            MyPage myPage = new MyPage();
            // view 객체에 이벤트의 Value 를 set 함
            myPage.setId(orderPlaced.getId());
            myPage.setStatus(orderPlaced.getStatus());
            // view 레파지 토리에 save
            myPageRepository.save(myPage);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenPaid_then_UPDATE_1(@Payload Paid paid) {
        try {
            if (!paid.validate()) return;
                // view 객체 조회
            List<MyPage> myPageList = myPageRepository.findAllById(Long.valueOf(paid.getOrderId()));
            MyPage myPage = new MyPage();
            myPage.setId(Long.valueOf(paid.getOrderId()));
            myPage.setStatus(paid.getStatus());
            myPageList.add(myPage);
            myPageRepository.saveAll(myPageList);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrderAccepted_then_UPDATE_2(@Payload OrderAccepted orderAccepted) {
        try {
            if (!orderAccepted.validate()) return;
                // view 객체 조회
            List<MyPage> myPageList = myPageRepository.findAllById(Long.valueOf(orderAccepted.getOrderId()));
            MyPage myPage = new MyPage();
            myPage.setId(Long.valueOf(orderAccepted.getOrderId()));
            myPage.setStatus(orderAccepted.getStatus());
            myPageList.add(myPage);
            myPageRepository.saveAll(myPageList);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrderRejected_then_UPDATE_3(@Payload OrderRejected orderRejected) {
        try {
            if (!orderRejected.validate()) return;
                // view 객체 조회
            List<MyPage> myPageList = myPageRepository.findAllById(Long.valueOf(orderRejected.getOrderId()));
            MyPage myPage = new MyPage();
            myPage.setId(Long.valueOf(orderRejected.getOrderId()));
            myPage.setStatus(orderRejected.getStatus());
            myPageList.add(myPage);
            myPageRepository.saveAll(myPageList);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrderCanceled_then_UPDATE_4(@Payload OrderCanceled orderCanceled) {
        try {
            if (!orderCanceled.validate()) return;
                // view 객체 조회
            List<MyPage> myPageList = myPageRepository.findAllById(orderCanceled.getId());
            MyPage myPage = new MyPage();
            myPage.setId(orderCanceled.getId());
            myPage.setStatus(orderCanceled.getStatus());
            myPageList.add(myPage);
            myPageRepository.saveAll(myPageList);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenPicked_then_UPDATE_5(@Payload Picked picked) {
        try {
            if (!picked.validate()) return;
                // view 객체 조회
            List<MyPage> myPageList = myPageRepository.findAllById(Long.valueOf(picked.getOrderId()));
            MyPage myPage = new MyPage();
            myPage.setId(Long.valueOf(picked.getOrderId()));
            myPage.setStatus(picked.getStatus());
            myPageList.add(myPage);
            myPageRepository.saveAll(myPageList);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenDelivered_then_UPDATE_6(@Payload Delivered delivered) {
        try {
            if (!delivered.validate()) return;
                // view 객체 조회
            List<MyPage> myPageList = myPageRepository.findAllById(Long.valueOf(delivered.getOrderId()));
            MyPage myPage = new MyPage();
            myPage.setId(Long.valueOf(delivered.getOrderId()));
            myPage.setStatus(delivered.getStatus());
            myPageList.add(myPage);
            myPageRepository.saveAll(myPageList);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}

