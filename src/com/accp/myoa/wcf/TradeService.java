
package com.accp.myoa.wcf;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "TradeService", targetNamespace = "http://dota2chukucun.com/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface TradeService {


    /**
     * 
     * @param assestid
     * @param randomstr
     * @param appid
     * @param contextid
     * @param token
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "http://dota2chukucun.com/TradeService/acceptOffer")
    @WebResult(name = "acceptOfferResult", targetNamespace = "http://dota2chukucun.com/")
    @RequestWrapper(localName = "acceptOffer", targetNamespace = "http://dota2chukucun.com/", className = "a.AcceptOffer")
    @ResponseWrapper(localName = "acceptOfferResponse", targetNamespace = "http://dota2chukucun.com/", className = "a.AcceptOfferResponse")
    public String acceptOffer(
        @WebParam(name = "appid", targetNamespace = "http://dota2chukucun.com/")
        String appid,
        @WebParam(name = "contextid", targetNamespace = "http://dota2chukucun.com/")
        String contextid,
        @WebParam(name = "assestid", targetNamespace = "http://dota2chukucun.com/")
        String assestid,
        @WebParam(name = "token", targetNamespace = "http://dota2chukucun.com/")
        String token,
        @WebParam(name = "randomstr", targetNamespace = "http://dota2chukucun.com/")
        String randomstr);

}
