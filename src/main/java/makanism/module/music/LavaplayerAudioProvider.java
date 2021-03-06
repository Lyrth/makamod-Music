package makanism.module.music;

import com.sedmelluq.discord.lavaplayer.format.StandardAudioDataFormats;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.playback.MutableAudioFrame;
import discord4j.voice.AudioProvider;

import java.nio.ByteBuffer;

public class LavaplayerAudioProvider extends AudioProvider {

    private final AudioPlayer player;
    private final MutableAudioFrame frame = new MutableAudioFrame();

    protected LavaplayerAudioProvider(AudioPlayer player) {
        super(ByteBuffer.allocate(StandardAudioDataFormats.DISCORD_OPUS.maximumChunkSize()));
        this.player = player;
        this.frame.setBuffer(getBuffer());
    }

    @Override
    public boolean provide() {
        boolean didProvide = player.provide(frame);
        if (didProvide) getBuffer().flip();
        return didProvide;
    }
}
