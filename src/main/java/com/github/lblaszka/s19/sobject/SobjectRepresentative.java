package com.github.lblaszka.s19.sobject;

import com.github.lblaszka.s19.sobject.impl.SobjectStrategy;
import com.github.lblaszka.s19.sobjectenvironment.SobjectEnvironment;
import com.github.lblaszka.s19.sobjectenvironment.SobjectEnvironmentRepresentative;

public interface SobjectRepresentative
{
    long getId();
    String getName();
    SobjectStrategy getSobjectStrategy();
    SobjectEnvironmentRepresentative getSobjectEnvironmentRepresentative();
}
