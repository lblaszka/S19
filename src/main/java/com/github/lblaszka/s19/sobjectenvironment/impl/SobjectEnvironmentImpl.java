package com.github.lblaszka.s19.sobjectenvironment.impl;

import com.github.lblaszka.s19.sobject.Sobject;
import com.github.lblaszka.s19.sobjectcontainer.SobjectCollection;
import com.github.lblaszka.s19.sobjectcontainer.SobjectContainer;
import com.github.lblaszka.s19.sobjectcontainer.SobjectContainerStrategy;
import com.github.lblaszka.s19.sobjectcontainer.impl.SobjectContainerImpl;
import com.github.lblaszka.s19.sobjectenvironment.SobjectEnvironment;

public class SobjectEnvironmentImpl implements SobjectEnvironment
{
    SobjectContainer sobjectContainer;

    public SobjectEnvironmentImpl( Class strategyClass )
    {
        this.changeContainer( strategyClass );
    }

    @Override
    public void start()
    {
        SobjectCollection sobjectCollection = this.sobjectContainer.getSobjectCollection();

        for( Sobject sobject : sobjectCollection )
        {
            sobject.update();
        }
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
            newSobjectContainer = SobjectContainerImpl.newInstance( strategyClass );
        }
        catch ( Exception e )
        {
            e.printStackTrace();
            return;
        }

        this.sobjectContainer.stop();
        this.sobjectContainer = newSobjectContainer;
        this.sobjectContainer.start();
    }
}
