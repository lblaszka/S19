package com.github.lblaszka.s19.sobjectenvironment;

import com.github.lblaszka.s19.sobjectcontainer.SobjectContainer;

import java.time.LocalDateTime;

public interface SobjectEnvironmentRuntimeStrategy
{
    void setFrequence( int mS );
    void run( SobjectContainer sobjectContainer );
    long getDeltaTime();
}
