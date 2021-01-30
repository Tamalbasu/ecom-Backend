package com.ecom.controller;

import com.ecom.api.ApiContext;
import com.ecom.api.Instamojo;
import com.ecom.api.InstamojoImpl;
import com.ecom.exception.ConnectionException;
import com.ecom.exception.HTTPException;
import com.ecom.model.Login;
import com.ecom.model.PaymentOrder;
import com.ecom.model.PaymentOrderResponse;
import com.ecom.model.Registration;
import com.ecom.service.impl.ProductService;
import com.ecom.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.UUID;


@RestController
public class HelloController {
 @Autowired
    UserService userService;
 @Autowired
 ProductService productService;




    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ResponseEntity<Object> registration(@RequestBody Registration registration) {
        System.out.println(registration);

        userService.saveOrUpdate(registration);
        registration.setStatus("Success");
        return ResponseEntity.ok(registration);
    }

    @RequestMapping(value = "/placeOrder", method = RequestMethod.POST)
    public ResponseEntity<Object> listOfImages(@RequestBody PaymentOrder paymentOrder ) throws UnsupportedEncodingException {
        PaymentOrderResponse paymentOrderResponse = productService.getPaymentOrderResponse(paymentOrder);


        return ResponseEntity.ok(paymentOrderResponse); }



}
