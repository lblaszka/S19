package com.github.lblaszka.s19.sobjectenvironment;

import com.github.lblaszka.s19.sobjectcontainer.SobjectContainerStrategy;

public interface SobjectEnvironment
{
    void start();
    void stop();

    void changeContainer( Class strategyClass );
}
