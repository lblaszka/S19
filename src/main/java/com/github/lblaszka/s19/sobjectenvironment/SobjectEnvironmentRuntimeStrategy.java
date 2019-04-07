package com.github.lblaszka.s19.sobjectenvironment;

import com.github.lblaszka.s19.sobjectcontainer.SobjectContainer;

public interface SobjectEnvironmentRuntimeStrategy
{
    void setFrequence( int mS );
    void run( SobjectContainer sobjectContainer );
}
