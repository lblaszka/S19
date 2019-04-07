package com.github.lblaszka.s19.sobjectenvironment.impl;

import com.github.lblaszka.s19.sobjectcontainer.SobjectContainer;
import com.github.lblaszka.s19.sobjectcontainer.SobjectContainerRepresentative;
import com.github.lblaszka.s19.sobjectcontainer.impl.SobjectContainerImpl;
import com.github.lblaszka.s19.sobjectenvironment.SobjectEnvironment;
import com.github.lblaszka.s19.sobjectenvironment.SobjectEnvironmentRunetimeStrategy;

public class SobjectEnvironmentImpl implements SobjectEnvironment
{
    SobjectEnvironmentRunetimeStrategy runtimeStrategy;
    SobjectContainer sobjectContainer;

    public static SobjectEnvironment newInstance( Class containerstrategyClass, Class runeTimeStrategyClass, int frequency ) throws IllegalAccessException, InstantiationException
    {
        SobjectEnvironmentRunetimeStrategy runtimeStrategy = (SobjectEnvironmentRunetimeStrategy) runeTimeStrategyClass.newInstance();

        return new SobjectEnvironmentImpl( containerstrategyClass, runtimeStrategy, frequency );
    }


    @Override
    public boolean isRunning()
    {
        return this.runtimeStrategy.isRunning();
    }


    private SobjectEnvironmentImpl( Class strategyClass, SobjectEnvironmentRunetimeStrategy runtimeStrategy, int frequency )
    {
        this.runtimeStrategy = runtimeStrategy;
        this.runtimeStrategy.setFrequency( frequency );
        this.changeContainer( strategyClass );
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
