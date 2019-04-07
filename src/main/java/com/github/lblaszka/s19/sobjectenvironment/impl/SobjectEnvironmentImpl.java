package com.github.lblaszka.s19.sobjectenvironment.impl;

import com.github.lblaszka.s19.sobject.Sobject;
import com.github.lblaszka.s19.sobjectcontainer.SobjectCollection;
import com.github.lblaszka.s19.sobjectcontainer.SobjectContainer;
import com.github.lblaszka.s19.sobjectcontainer.SobjectContainerRepresentative;
import com.github.lblaszka.s19.sobjectcontainer.impl.SobjectContainerImpl;
import com.github.lblaszka.s19.sobjectcontainer.impl.SobjectContainerStrategy;
import com.github.lblaszka.s19.sobjectenvironment.SobjectEnvironment;
import com.github.lblaszka.s19.sobjectenvironment.SobjectEnvironmentRuntimeStrategy;

public class SobjectEnvironmentImpl implements SobjectEnvironment
{
    SobjectEnvironmentRuntimeStrategy runtimeStrategy;
    SobjectContainer sobjectContainer;

    public static SobjectEnvironment newInstance( Class containerstrategyClass, Class runeTimeStrategyClass, int frequency ) throws IllegalAccessException, InstantiationException
    {
        SobjectEnvironmentRuntimeStrategy runtimeStrategy = (SobjectEnvironmentRuntimeStrategy) runeTimeStrategyClass.newInstance();

        return new SobjectEnvironmentImpl( containerstrategyClass, runtimeStrategy, frequency );
    }


    @Override
    public boolean isRunning()
    {
        return this.runtimeStrategy.isRunning();
    }


    private SobjectEnvironmentImpl( Class strategyClass, SobjectEnvironmentRuntimeStrategy runtimeStrategy, int frequency )
    {
        this.changeContainer( strategyClass );
        this.runtimeStrategy = runtimeStrategy;
        this.runtimeStrategy.setFrequency( frequency );
    }


    @Override
    public SobjectContainerRepresentative getSobjectContainerRepresentative()
    {
        return this.sobjectContainer;
    }


    @Override
    public void start()
    {
        this.runtimeStrategy.start();
    }

    @Override
    public void pause()
    {
        this.runtimeStrategy.pause();
    }


    @Override
    public void stop()
    {
        this.sobjectContainer.stop();
    }


    @Override
    public void changeContainer( Class strategyClass )
    {
        SobjectContainer newSobjectContainer;
        try
        {
            newSobjectContainer = SobjectContainerImpl.newInstance( strategyClass, this );
        }
        catch ( Exception e )
        {
            e.printStackTrace();
            return;
        }
        if( this.sobjectContainer != null )
            this.sobjectContainer.stop();

        this.sobjectContainer = newSobjectContainer;
        this.sobjectContainer.start();
        this.runtimeStrategy.setSobjectContainer( sobjectContainer );
    }


    @Override
    public long getDeltaTime()
    {
        return this.runtimeStrategy.getDeltaTime();
    }
}
