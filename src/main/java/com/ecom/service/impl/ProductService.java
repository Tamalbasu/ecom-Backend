package com.ecom.service.impl;

import com.ecom.api.ApiContext;
import com.ecom.api.Instamojo;
import com.ecom.api.InstamojoImpl;
import com.ecom.exception.ConnectionException;
import com.ecom.exception.HTTPException;
import com.ecom.model.PaymentOrder;
import com.ecom.model.PaymentOrderResponse;
import com.ecom.util.Constants;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.UUID;

@Service
public class ProductService {

    public PaymentOrderResponse getPaymentOrderResponse(PaymentOrder paymentOrder) {

        // Getting decoder
        Base64.Decoder decoder = Base64.getDecoder();
        // Decoding string
        String dStr = new String(decoder.decode(Constants.CLIENT_SECRET));
        System.out.println("Decoded string: "+dStr);
        ApiContext context = ApiContext.create("VJ9m95N4HNcIDELc6akPX7rTKI1EhZoiXLKQHLU5",dStr, ApiContext.Mode.LIVE);
        Instamojo api = new InstamojoImpl(context);
        PaymentOrder order = paymentOrder;
        order.setName("John Smith");
        order.setEmail("john.smith@gmail.com");
        order.setPhone("8420995452");
        order.setCurrency("INR");
        order.setDescription("This is a test transaction.");
        order.setRedirectUrl("http://localhost:4200/home");
        order.setWebhookUrl("http://www.someurl.com/");
        order.setTransactionId(UUID.randomUUID().toString());
        PaymentOrderResponse paymentOrderResponse = null;

        try {
            System.out.println(order);
            paymentOrderResponse = api.createPaymentOrder(order);
            System.out.println(paymentOrderResponse);

        } catch (HTTPException e) {
            System.out.println(e.getStatusCode());
            System.out.println(e.getMessage());
            System.out.println(e.getJsonPayload());

        } catch (ConnectionException e) {
            System.out.println(e.getMessage());
        }
        return paymentOrderResponse;
    }

}
