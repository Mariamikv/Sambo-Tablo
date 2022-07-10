package com.example.sambo_tablo.timer;

import javafx.animation.Animation;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;

public class SetTimer {
    public StringBinding timeLestAsString(Animation animation){
        return Bindings.createStringBinding(
                () -> {
                    double currentTime = animation.getCurrentTime().toMillis();
                    double totalTime = animation.getCycleDuration().toMillis();
                    long remainingTime = Math.round(totalTime-currentTime);
                    java.time.Duration dur = java.time.Duration.ofMillis(remainingTime);
                    return String.format(
                            "%02d:%02d", dur.toMinutes(), dur.toSecondsPart()
                    );
                },
                animation.currentTimeProperty(),
                animation.cycleDurationProperty()
        );
    }
}
