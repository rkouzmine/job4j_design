package ru.job4j.ood.ocp.weapon;

public class Sword extends Weapon implements AttackDescribable {
    public Sword() {
        super("Sword");
    }

    @Override
    public void describeAttack() {
        System.out.println("He cuts, slices and thrashes");
    }
}
