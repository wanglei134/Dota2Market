
package com.accp.myoa.wcf;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="appid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contextid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="assestid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="token" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="randomstr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "appid",
    "contextid",
    "assestid",
    "token",
    "randomstr"
})
@XmlRootElement(name = "acceptOffer")
public class AcceptOffer {

    @XmlElementRef(name = "appid", namespace = "http://dota2chukucun.com/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> appid;
    @XmlElementRef(name = "contextid", namespace = "http://dota2chukucun.com/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> contextid;
    @XmlElementRef(name = "assestid", namespace = "http://dota2chukucun.com/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> assestid;
    @XmlElementRef(name = "token", namespace = "http://dota2chukucun.com/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> token;
    @XmlElementRef(name = "randomstr", namespace = "http://dota2chukucun.com/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> randomstr;

    /**
     * ��ȡappid���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAppid() {
        return appid;
    }

    /**
     * ����appid���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAppid(JAXBElement<String> value) {
        this.appid = value;
    }

    /**
     * ��ȡcontextid���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getContextid() {
        return contextid;
    }

    /**
     * ����contextid���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setContextid(JAXBElement<String> value) {
        this.contextid = value;
    }

    /**
     * ��ȡassestid���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAssestid() {
        return assestid;
    }

    /**
     * ����assestid���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAssestid(JAXBElement<String> value) {
        this.assestid = value;
    }

    /**
     * ��ȡtoken���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getToken() {
        return token;
    }

    /**
     * ����token���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setToken(JAXBElement<String> value) {
        this.token = value;
    }

    /**
     * ��ȡrandomstr���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getRandomstr() {
        return randomstr;
    }

    /**
     * ����randomstr���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setRandomstr(JAXBElement<String> value) {
        this.randomstr = value;
    }

}
