package com.github.lblaszka.s19.sobjectcontainer;

import com.github.lblaszka.s19.sobjectcontainer.impl.SobjectCollectionImpl;

public interface SobjectContainer extends SobjectContainerRepresentative
{
    SobjectCollection getSobjectCollection();
}
