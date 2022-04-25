package at.htl.maturademo.entity;

import javax.persistence.Embeddable;

@Embeddable
public class Motor2 {

    private int ccm;
    private int hp;

    public Motor2() {
    }

    public Motor2(int ccm, int hp) {
        this.ccm = ccm;
        this.hp = hp;
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

}
