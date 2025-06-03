package ru.job4j.gc.ref;

import java.lang.ref.*;

public class ReferenceDemo {
    public static void main(String[] args) {

        /* StrongReference */
        Object obj = new Object();

        SoftReference<Object> softRef = new SoftReference<>(obj);
        WeakReference<Object> weakRef = new WeakReference<>(obj);
        ReferenceQueue<Object> refQueue = new ReferenceQueue<>();
        PhantomReference<Object> phantomRef = new PhantomReference<>(obj, refQueue);

        /* Проверяем значение через get() */
        System.out.println("Strong: " + (obj   != null ? "существует" : "null"));
        System.out.println("Soft:   " + (softRef.get()   != null ? "существует" : "null"));
        System.out.println("Weak:   " + (weakRef.get()   != null ? "существует" : "null"));
        System.out.println("Phantom:" + (phantomRef.get() != null ? "существует" : "null"));

        /* Убираем сильную ссылку и инициируем сборку мусора */
        obj = null;
        System.gc();

        /* После GC */
        System.out.println("\nПосле GC:");
        System.out.println("Strong: " + (obj   != null ? "существует" : "null"));
        System.out.println("Soft:   " + (softRef.get()   != null ? "существует" : "null"));
        System.out.println("Weak:   " + (weakRef.get()   != null ? "существует" : "null"));
        System.out.println("Phantom:" + (phantomRef.get() != null ? "существует" : "null"));

        /* Проверяем очередь фантомных ссылок */
        Reference<?> refFromQueue = refQueue.poll();
        if (refFromQueue != null) {
            System.out.println("Объект попадёт в очередь PhantomReference");
        }

    }
}