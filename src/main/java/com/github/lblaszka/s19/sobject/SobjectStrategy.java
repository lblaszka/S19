package com.github.lblaszka.s19.sobject;

public abstract class SobjectStrategy
{
    protected final String name = "Sobject";
    private SobjectRepresentative sobject;

    protected abstract void start();
    protected abstract void update();
    protected abstract void stop();

    protected final SobjectRepresentative getSobject()
    {
        return this.sobject;
    }

    final void setSobject( SobjectRepresentative sobject )
    {
        this.sobject = sobject;
    }

    final void kill()
    {
        this.sobject = null;
    }
}
