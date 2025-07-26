package ru.job4j.ood.ocp.weapon;

public class Ak47 extends Weapon implements AttackDescribable {
    public Ak47() {
        super("ak-47");
    }

    @Override
    public void describeAttack() {
        System.out.println("Reliable, automatic, legendary");
    }
}
