/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battlesimulator;

/**
 *
 * @author jhthx9
 */
public class Battle {
    
    //public boolean winningWarrior;

    public static StringBuilder battleText = new StringBuilder();
    
    public static void StartFight(Warrior warrior1, Warrior warrior2) {
        while(true) {
            if("Game Over".equals(GetAttackResult(warrior1, warrior2))) {
                battleText.append("Game Over\n");
                break;
            }
            
            if("Game Over".equals(GetAttackResult(warrior2, warrior1))) {
                battleText.append("Game Over\n");
                break;
            }
        }
    }
    
    public static String GetAttackResult(Warrior warriorA, Warrior warriorB) {
        double warriorAAttack = warriorA.Attack();
        double warriorBBlock = warriorB.Block();
        
        double damageToWarriorB = warriorAAttack - warriorBBlock;
        
        if(damageToWarriorB > 0) {
            warriorB.health = warriorB.health - damageToWarriorB;
        } else damageToWarriorB = 0;
        
        battleText.append(warriorA.name + " Attacks " +  warriorB.name + " and deals " + (int)damageToWarriorB + " damage." + "\n");
        
        battleText.append(warriorB.name + " has " + (int)warriorB.health + " health.\n" + "\n");
               
        if(warriorB.health <= 0) {
            battleText.append(warriorB.name + " has died and " + warriorA.name + " is victorious" + "\n");
            return "Game Over";
        } else return "Fight Again!";        
    }
}
