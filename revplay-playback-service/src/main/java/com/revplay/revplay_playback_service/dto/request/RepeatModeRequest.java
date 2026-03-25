package com.revplay.revplay_playback_service.dto.request;

import com.revplay.revplay_playback_service.Enum.RepeatMode;

public class RepeatModeRequest {

    private RepeatMode repeatMode;

    public RepeatMode getRepeatMode() { return repeatMode; }
    public void setRepeatMode(RepeatMode repeatMode) { this.repeatMode = repeatMode; }
}