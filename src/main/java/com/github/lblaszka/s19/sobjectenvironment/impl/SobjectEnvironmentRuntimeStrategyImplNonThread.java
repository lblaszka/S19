package com.github.lblaszka.s19.sobjectenvironment.impl;

import com.github.lblaszka.s19.sobject.Sobject;
import com.github.lblaszka.s19.sobjectcontainer.SobjectCollection;
import com.github.lblaszka.s19.sobjectcontainer.SobjectContainer;
import com.github.lblaszka.s19.sobjectenvironment.SobjectEnvironmentRuntimeStrategy;

import java.time.Duration;
import java.time.LocalDateTime;

public class SobjectEnvironmentRuntimeStrategyImplNonThread implements SobjectEnvironmentRuntimeStrategy
{

    private int fequence=0;
    private LocalDateTime lastUpdateDateTime;
    private long deltaTime=0;


    public SobjectEnvironmentRuntimeStrategyImplNonThread()
    {
    }


    @Override
    public void setFrequence( int mS )
    {
        this.fequence = mS;
    }


    @Override
    public void run( SobjectContainer sobjectContainer )
    {
        lastUpdateDateTime = LocalDateTime.now();
        this.loop( sobjectContainer.getSobjectCollection() );
    }


    @Override
    public long getDeltaTime()
    {
        return this.deltaTime;
    }

    private void loop( SobjectCollection sobjectCollection )
    {
        while( true )
        {
            LocalDateTime nowTime = LocalDateTime.now();
            this.deltaTime = Duration.between( this.lastUpdateDateTime, nowTime).toMillis();
            this.lastUpdateDateTime = nowTime;

            for( Sobject sobject : sobjectCollection )
            {
                sobject.update();
            }
        }
    }
}
