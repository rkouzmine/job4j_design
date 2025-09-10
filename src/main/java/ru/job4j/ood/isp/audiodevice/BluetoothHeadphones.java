package ru.job4j.ood.isp.audiodevice;

public class BluetoothHeadphones implements MusicPlayable, MicrophoneUsable, BluetoothConnectable {
    @Override
    public void connectBluetooth() {
        System.out.println("Беспроводные наушники работаеют по Bluetooth");
    }

    @Override
    public void useMicrophone() {
        System.out.println("Есть микрофон");
    }

    @Override
    public void playMusic() {
        System.out.println("Проигрывают музыку");
    }
}
