package com.github.lblaszka.s19.sobjectcontainer;

import com.github.lblaszka.s19.sobject.SobjectStrategy;
import com.github.lblaszka.s19.sobjectenvironment.SobjectEnvironment;

public abstract class SobjectContainerStrategy
{
    private SobjectEnvironment environment;

    protected abstract void start();
    protected abstract void stop();

    protected final SobjectEnvironment getSobject()
    {
        return this.environment;
    }

    final void setSobject( SobjectEnvironment environment )
    {
        this.environment = environment;
    }

    final void kill()
    {
        this.environment = null;
    }
}
