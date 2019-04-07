package com.github.lblaszka.s19.sobjectenvironment.impl;

import com.github.lblaszka.s19.sobject.Sobject;
import com.github.lblaszka.s19.sobjectcontainer.SobjectCollection;
import com.github.lblaszka.s19.sobjectcontainer.SobjectContainer;
import com.github.lblaszka.s19.sobjectcontainer.SobjectContainerRepresentative;
import com.github.lblaszka.s19.sobjectcontainer.impl.SobjectContainerImpl;
import com.github.lblaszka.s19.sobjectenvironment.SobjectEnvironment;
import com.github.lblaszka.s19.sobjectenvironment.SobjectEnvironmentRuntimeStrategy;

public class SobjectEnvironmentImpl implements SobjectEnvironment
{
    SobjectEnvironmentRuntimeStrategy runtimeStrategy;
    SobjectContainer sobjectContainer;


    public SobjectEnvironmentImpl( Class strategyClass )
    {
        this.changeContainer( strategyClass );
        this.runtimeStrategy = new SobjectEnvironmentRuntimeStrategyImplNonThread();
        this.runtimeStrategy.setFrequence( 100 );
    }


    @Override
    public SobjectContainerRepresentative getSobjectContainerRepresentative()
    {
        return this.sobjectContainer;
    }


    @Override
    public void start()
    {
        this.runtimeStrategy.run( this.sobjectContainer );
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
    }
}
