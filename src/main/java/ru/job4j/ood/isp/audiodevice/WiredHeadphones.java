package ru.job4j.ood.isp.audiodevice;

public class WiredHeadphones implements MusicPlayable, MicrophoneUsable {
    @Override
    public void useMicrophone() {
        System.out.println("Умеют проигрывать музыку");
    }

    @Override
    public void playMusic() {
        System.out.println("Есть микрофон");
    }
}
