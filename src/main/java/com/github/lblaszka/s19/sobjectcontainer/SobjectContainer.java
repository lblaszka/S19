package com.github.lblaszka.s19.sobjectcontainer;

import com.github.lblaszka.s19.sobject.Sobject;

public interface SobjectContainer extends SobjectContainerRepresentative
{
    SobjectCollection getSobjectCollection();

    void start();
    void stop();

    void deleteSobject( Sobject sobject );
}
