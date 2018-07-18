/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battlesimulator;

import java.util.Random;

/**
 *
 * @author jhthx9
 */
public class Warrior implements WarriorInterface {
    
    public String name;
    public double health;
    public double attackMax;
    public double blockMax;
    
    public String getName() {
        return this.name;
    }
    
    public void setName() {
        this.name = "Warrior";
    }
    
    public double getHealth() {
        return this.health;
    }
    
    public void setHealth() {
        this.health = 0.0;
    }
    
    public double getAttackMax() {
        return this.attackMax;
    }
    
    public void setAttackMax() {
        this.attackMax = 0.0;
    }
    
    public double getBlockMax() {
        return this.blockMax;
    }
    
    public void setBlockMax() {
        this.blockMax = 0.0;
    }
    
    Random rnd = new Random();
    
    
    public Warrior(String warriorName, double warriorHealth, double warriorAttackMax, double warriorBlockMax) { 
        name = warriorName;
        health = warriorHealth;
        attackMax = warriorAttackMax;
        blockMax = warriorBlockMax;
    }
    
    public double Attack() {
        return rnd.nextInt((int)attackMax);
    }
    
    public double Block() {
        return rnd.nextInt((int)blockMax);
    }   

}
