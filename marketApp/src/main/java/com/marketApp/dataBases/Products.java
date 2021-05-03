
package com.marketApp.dataBases;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Products")
// ÜRÜNLER
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int ID;

    @Column(name = "NAME")
    private String pName;

    @Column(name = "PRICE")
    private float pPrice;

    @Column(name = "ORIGIN")
    private String pOrigin;

    @Column(name = "count")
    private int count;

    public Products() {
    }
//constructor
    public Products( String pName, float pPrice, String pOrigin, int count) {
        this.pName = pName;
        this.pPrice = pPrice;
        this.pOrigin = pOrigin;
        this.count = count;

    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public float getpPrice() {
        return pPrice;
    }

    public void setpPrice(float pPrice) {
        this.pPrice = pPrice;
    }

    public String getpOrigin() {
        return pOrigin;
    }

    public void setpOrigin(String pOrigin) {
        this.pOrigin = pOrigin;
    }

}
