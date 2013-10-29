package com.sonic.mynetwork.model;

import java.io.IOException;
import java.util.Scanner;

import org.apache.xerces.parsers.DOMParser;
import org.scribe.builder.*;
import org.scribe.builder.api.*;
import org.scribe.model.*;
import org.scribe.oauth.*;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class LinkedInExample
{
  private static final String PROTECTED_RESOURCE_URL = "http://api.linkedin.com/v1/people/~/connections:("
  		+ "id,first-name,last-name,headline,industry)";
  
  public static void main(String[] args)
  {
    OAuthService service = new ServiceBuilder()
                                .provider(LinkedInApi.class)
                                .apiKey("p1k7dzy3a01s")
                                .apiSecret("MKYJljuHdy2HFn60")
                                .build();
    Scanner in = new Scanner(System.in);
    
    Token requestToken = service.getRequestToken();
    System.out.println(service.getAuthorizationUrl(requestToken));
    System.out.print(">>");
    Verifier verifier = new Verifier(in.nextLine());
    System.out.println();

    Token accessToken = service.getAccessToken(requestToken, verifier);
   
    OAuthRequest request = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL);
    service.signRequest(accessToken, request);
    Response response = request.send();
    System.out.println(response.getBody());
    /*DOMParser parser = new DOMParser();
    try {
        parser.parse(new InputSource(new java.io.StringReader(response.getBody())));
        Document doc = parser.getDocument();
        String message = doc.getChildNodes().item(0).getChildNodes().item(1).getTextContent();
        //String message = doc.getDocumentElement().getTextContent();
        System.out.println(message);
    } catch (SAXException e) {
        // handle SAXException 
    } catch (IOException e) {
        // handle IOException 
    }*/
  }
}
