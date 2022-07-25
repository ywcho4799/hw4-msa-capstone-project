package sirenorder.domain;

import java.util.Date;
import lombok.Data;
import sirenorder.infra.AbstractEvent;

@Data
public class Ordered extends AbstractEvent {

    private Long id;
    private String menu;
    private String customerId;
    private String customerName;
    private String customerTel;
    private Double price;

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getMenu(){
        return menu;
    }
    public void setMenu(String menu){
        this.menu = menu;
    }
    public String getCustomerId(){
        return customerId;
    }
    public void setCustomerId(String customerId){
        this.customerId = customerId;
    }
    public String getCustomerName(){
        return customerName;
    }
    public void setCustomerName(String customerName){
        this.customerName = customerName;
    }
    public String getCustomerTel(){
        return customerTel;
    }
    public void setCustomerTel(String customerTel){
        this.customerTel = customerTel;
    }
    public Double getPrice(){
        return price;
    }
    public void setPrice(Double price){
        this.price = price;
    }


    
}
