package com.github.lblaszka.s19.sobjectenvironment.impl;

import com.github.lblaszka.s19.sobject.Sobject;
import com.github.lblaszka.s19.sobjectcontainer.SobjectCollection;
import com.github.lblaszka.s19.sobjectcontainer.SobjectContainer;
import com.github.lblaszka.s19.sobjectenvironment.SobjectEnvironmentRuntimeStrategy;

public class SobjectEnvironmentRuntimeStrategyImplNonThread implements SobjectEnvironmentRuntimeStrategy
{

    private int fequence=0;

    @Override
    public void setFrequence( int mS )
    {
        this.fequence = mS;
    }


    @Override
    public void run( SobjectContainer sobjectContainer )
    {
        SobjectCollection sobjectCollection = sobjectContainer.getSobjectCollection();
        for( Sobject sobject : sobjectCollection )
            sobject.update();
        try
        {
            wait( this.fequence );
        } catch ( InterruptedException e )
        {
            e.printStackTrace();
        }
    }
}
