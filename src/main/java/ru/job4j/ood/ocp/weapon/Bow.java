package ru.job4j.ood.ocp.weapon;

public class Bow extends Weapon implements AttackDescribable {
    public Bow() {
        super("Bow");
    }

    @Override
    public void describeAttack() {
        System.out.println("For the Amazons");
    }
}
