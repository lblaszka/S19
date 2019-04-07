package com.github.lblaszka.s19.sobjectenvironment;

import com.github.lblaszka.s19.sobjectcontainer.SobjectContainerRepresentative;

public interface SobjectEnvironmentRepresentative
{
    SobjectContainerRepresentative getSobjectContainerRepresentative();
    long getDeltaTime();
    void changeContainer( Class strategyClass );
}
