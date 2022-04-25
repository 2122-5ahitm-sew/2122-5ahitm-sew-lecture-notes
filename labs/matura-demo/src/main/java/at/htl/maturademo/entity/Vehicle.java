package at.htl.maturademo.entity;

import javax.persistence.*;

@Entity
@Table(name = "X_VEHICLE")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private
    Long id;

    private String brand;
    private String model;
    private int ccm;
    private int hp;

    public Vehicle() {
    }

    public Vehicle(String brand, String model, int ccm, int hp) {
        this.brand = brand;
        this.model = model;
        this.ccm = ccm;
        this.hp = hp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCcm() {
        return ccm;
    }

    public void setCcm(int ccm) {
        this.ccm = ccm;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", ccm=" + ccm +
                ", hp=" + hp +
                '}';
    }
}
