package ru.job4j.gc.ref;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class SoftWeakReferenceDemo {

    private static void safeSoftRef() {
        SoftReference<String> softReference = new SoftReference<>(new String("SoftData"));
        System.gc();
        String str = softReference.get();
        if (str != null) {
            System.out.println("GC hasn't removed the instance yet");
        } else {
            System.out.println("GC has cleared the instance");
        }
        System.out.println(str);
    }

    private static void safeWeakRef() {
        WeakReference<String> weakReference = new WeakReference<>(new String("WeakData"));
        String str = weakReference.get();
        if (str != null) {
            System.out.println("GC hasn't removed the instance yet");
        } else {
            System.out.println("GC has cleared the instance");
        }
        System.out.println(str);
    }

    public static void main(String[] args) {
        safeSoftRef();
        safeWeakRef();
    }
}
