package com.omar.controller;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class URLchecker {
  private final String SITE_IS_UP = "Site is Up!";
  private final String SITE_IS_DOWN = "Site is Down!";
  private final String INCORRECT_URL = "Incorrect URL!";


  @GetMapping("/check")
  public String getURLstatusMsg(@RequestParam String url) {
    String returnMsg = "";
    try {
      URL urlObj = new URL(url);
      HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
      conn.setRequestMethod("GET");
      conn.connect();
      int responseCodeCategory = conn.getResponseCode() / 100;
      System.out.println(responseCodeCategory); 
      if (responseCodeCategory != 2 || responseCodeCategory != 3) {
        returnMsg = SITE_IS_UP; 
      } else {
        returnMsg = SITE_IS_DOWN;
      }
    } catch (MalformedURLException e) {
      returnMsg = INCORRECT_URL;
    } catch (IOException e) {
      returnMsg = SITE_IS_DOWN;
    }
    return returnMsg;
  }
}
