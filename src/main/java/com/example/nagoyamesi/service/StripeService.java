package com.example.nagoyamesi.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.nagoyamesi.form.PaidRegistForm;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

import jakarta.servlet.http.HttpServletRequest;


@Service
public class StripeService {
	@Value("${stripe.api-key}")
    private String stripeApiKey;
	
    // セッションを作成し、Stripeに必要な情報を返す
    public String createStripeSession(PaidRegistForm paidRegistForm, HttpServletRequest httpServletRequest) {
    	 Stripe.apiKey = stripeApiKey;
         
         String requestUrl = new String(httpServletRequest.getRequestURL());
         String priceId = "price_1Pa6d6P4bt7o7owHK1snJL2k";  // 正しいprice IDを設定
         
         SessionCreateParams params = new SessionCreateParams.Builder()
             .setSuccessUrl(requestUrl.replaceAll("/user/upgradeConfirm", "") + "/user")
             .setCancelUrl(requestUrl.replace("/user/paidconfirm", ""))
             .setMode(SessionCreateParams.Mode.SUBSCRIPTION)
             .setSubscriptionData(
                 new SessionCreateParams.SubscriptionData.Builder()
                     .putMetadata("userId", paidRegistForm.getUserId().toString())
                     .putMetadata("nominee", paidRegistForm.getNominee())
                     .putMetadata("cardNumber", paidRegistForm.getCard_number())
                     .putMetadata("secNumber", paidRegistForm.getSec_number())
                     .putMetadata("cardType", paidRegistForm.getCard_type())
                     .putMetadata("periodYear", paidRegistForm.getPeriod_year())
                     .putMetadata("periodMonth", paidRegistForm.getPeriod_month())
                     .build()
             )
             .addLineItem(new SessionCreateParams.LineItem.Builder()
                 .setQuantity(1L)
                 .setPrice(priceId)
                 .build()
             )
             .build();

         try {
             Session session = Session.create(params);
             return session.getUrl();
         } catch (StripeException e) {
             e.printStackTrace();
             return "";
         }
     }
 }