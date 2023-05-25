package com.pred.build;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;

@Entity
@Table(name = "pred_items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int ap;

    @Column(nullable = false)
    private int ad;

    @Column(nullable = false)
    private int armor;

    @Column(nullable = false)
    private int mr;

    @Column(nullable = false)
    private int hp;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAp() {
        return ap;
    }

    public int getAd() {
        return ad;
    }

    public int getArmor() {
        return armor;
    }

    public int getMr() {
        return mr;
    }

    public int getHp() {
        return hp;
    }
    public Item() {
    }
    public Item(String name, int ap, int ad, int armor, int hp) {
        this.name = name;
        this.ap = ap;
        this.ad = ad;
        this.armor = armor;
        this.hp = hp;
    }

    @Override
    public String toString() {
        return "Item [id=" + id + ", name=" + name + ", ap=" + ap + ", ad=" + ad + ", armor=" + armor + ", mr=" + mr
                + ", hp=" + hp + "]";
    }

    
}
