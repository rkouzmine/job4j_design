package ru.job4j.clone;

import java.util.Objects;

public class CloneByConstr {
    int x;
    int y;

    public CloneByConstr(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public CloneByConstr(CloneByConstr obj) {
        this(obj.getX(), obj.getY());
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CloneByConstr that = (CloneByConstr) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "CloneByConstr{"
                + "x=" + x
                + ", y=" + y
                + '}';
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        CloneByConstr obj = new CloneByConstr(5, 10);
        CloneByConstr clonedObj = new CloneByConstr(obj);
        clonedObj.setX(15);
        clonedObj.setY(20);
        System.out.println("Исходный объект. Х: " + obj.getX() + ", Y: " + obj.getY());
        System.out.println("Клонированный объект. Х: " + clonedObj.getX() + ", Y: " + clonedObj.getY());
    }
}
