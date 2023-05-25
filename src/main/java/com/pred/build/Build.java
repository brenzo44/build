package com.pred.build;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;

import java.util.ArrayList;

@Entity
public class Build {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int buildID;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Item> items;
    @Column(nullable = false)
    private String buildName;

    public Build(String buildName) {
        this.buildName = buildName;
        this.items = new ArrayList<Item>();
    }
    public Build(){}
    
    public void addItem(Item item) {
        this.items.add(item);
    }
    public boolean removeItem(Item item) {
        for (Item i : items) {
            if (i.getName().equals(item.getName())) {
                items.remove(i);
                return true;
            }
        }
        return false;
    }
    public Map<String, Integer> getTotals(){
        Map<String, Integer> totals = new HashMap<String, Integer>();
        int totalAp = 0;
        int totalAd = 0;
        int totalMr = 0;
        int totalArmor = 0;
        int totalHp = 0;
        for (Item i : items) {
            totalAp += i.getAp();
            totalAd += i.getAd();
            totalMr += i.getMr();
            totalArmor += i.getArmor();
            totalHp += i.getHp();
        }
        totals.put("ap", totalAp);
        totals.put("ad", totalAd);
        totals.put("mr", totalMr);
        totals.put("armor", totalArmor);
        totals.put("hp", totalHp);
        return totals;
    }
    public List<Item> getItems() {
        return items;
    }
    public int getNumItems() {
        return items.size();
    }
    public String getBuildName() {
        return buildName;
    }
    public int getBuildID() {
        return buildID;
    }
    @Override
    public String toString() {
        return "Build [items=" + items + ", buildName=" + buildName + ", buildID=" + buildID + "]";
    }

    


    
}
