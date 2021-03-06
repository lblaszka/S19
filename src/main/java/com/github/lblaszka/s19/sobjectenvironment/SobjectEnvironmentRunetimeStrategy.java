package com.github.lblaszka.s19.sobjectenvironment;

import com.github.lblaszka.s19.sobjectcontainer.SobjectContainer;

import java.time.LocalDateTime;

public interface SobjectEnvironmentRunetimeStrategy
{
    void setFrequency( int mS );
    void setSobjectContainer( SobjectContainer sobjectContainer );

    void start();
    void pause();

    long getDeltaTime();

    boolean isRunning();
}
