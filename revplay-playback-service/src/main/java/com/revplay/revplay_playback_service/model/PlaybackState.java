package com.revplay.revplay_playback_service.model;

import com.revplay.revplay_playback_service.Enum.RepeatMode;
import jakarta.persistence.*;

@Entity
@Table(name = "playback_state")
public class PlaybackState {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private Long userId;

    private Long currentSongId;

    private boolean playing;

    private boolean shuffleEnabled;

    @Enumerated(EnumType.STRING)
    private RepeatMode repeatMode;

    public PlaybackState() {}

    public PlaybackState(Long id, Long userId, Long currentSongId, boolean playing, boolean shuffleEnabled, RepeatMode repeatMode) {
        this.id = id;
        this.userId = userId;
        this.currentSongId = currentSongId;
        this.playing = playing;
        this.shuffleEnabled = shuffleEnabled;
        this.repeatMode = repeatMode;
    }

    public static PlaybackStateBuilder builder() {
        return new PlaybackStateBuilder();
    }

    public static class PlaybackStateBuilder {
        private Long id;
        private Long userId;
        private Long currentSongId;
        private boolean playing;
        private boolean shuffleEnabled;
        private RepeatMode repeatMode;

        public PlaybackStateBuilder id(Long id) { this.id = id; return this; }
        public PlaybackStateBuilder userId(Long userId) { this.userId = userId; return this; }
        public PlaybackStateBuilder currentSongId(Long currentSongId) { this.currentSongId = currentSongId; return this; }
        public PlaybackStateBuilder playing(boolean playing) { this.playing = playing; return this; }
        public PlaybackStateBuilder shuffleEnabled(boolean shuffleEnabled) { this.shuffleEnabled = shuffleEnabled; return this; }
        public PlaybackStateBuilder repeatMode(RepeatMode repeatMode) { this.repeatMode = repeatMode; return this; }

        public PlaybackState build() {
            return new PlaybackState(id, userId, currentSongId, playing, shuffleEnabled, repeatMode);
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public Long getCurrentSongId() { return currentSongId; }
    public void setCurrentSongId(Long currentSongId) { this.currentSongId = currentSongId; }
    public boolean isPlaying() { return playing; }
    public void setPlaying(boolean playing) { this.playing = playing; }
    public boolean isShuffleEnabled() { return shuffleEnabled; }
    public void setShuffleEnabled(boolean shuffleEnabled) { this.shuffleEnabled = shuffleEnabled; }
    public RepeatMode getRepeatMode() { return repeatMode; }
    public void setRepeatMode(RepeatMode repeatMode) { this.repeatMode = repeatMode; }
}