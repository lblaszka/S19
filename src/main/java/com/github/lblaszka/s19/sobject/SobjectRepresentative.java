package com.github.lblaszka.s19.sobject;

import com.github.lblaszka.s19.sobject.impl.SobjectStrategy;

public interface SobjectRepresentative
{
    long getId();
    String getName();
    SobjectStrategy getSobjectStrategy();
}
