package ru.job4j.ood.isp.audiodevice;

public class WiredHeadphones implements AudioDevice {
    @Override
    public void playMusic() {
        System.out.println("Умеют проигрывать музыку");

    }

    @Override
    public void useMicrophone() {
        System.out.println("Есть микрофон");

    }

    public void connectBluetooth() {
        throw new UnsupportedOperationException("Проводные наушники не поддерживают Bluetooth");
    }
}
