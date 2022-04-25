package at.htl.maturademo.entity;

import javax.persistence.*;

@Entity
@Table(name = "X_VEHICLE")
public class Vehicle2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private
    Long id;

    private String brand;
    private String model;

    @Embedded
    private Motor2 motor;

    public Vehicle2() {
    }

    public Vehicle2(String brand, String model, int ccm, int hp) {
        this.brand = brand;
        this.model = model;
        this.motor = new Motor2(ccm, hp);
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



    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", ccm=" + motor.getCcm() +
                ", hp=" + motor.getHp() +
                '}';
    }

    public Motor2 getMotor() {
        return motor;
    }

    public void setMotor(Motor2 motor) {
        this.motor = motor;
    }
}
