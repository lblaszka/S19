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
    private SobjectContainer sobjectContainer;
    private boolean working;


    public SobjectEnvironmentRuntimeStrategyImplNonThread()
    {
    }


    @Override
    public void setFrequence( int mS )
    {
        this.fequence = mS;
    }


    @Override
    public void setSobjectContainer( SobjectContainer sobjectContainer )
    {
        lastUpdateDateTime = LocalDateTime.now();
        this.sobjectContainer = sobjectContainer;
    }


    @Override
    public void start()
    {
        this.working = true;
        loop();
    }


    @Override
    public void stop()
    {
        this.working = false;
    }


    @Override
    public long getDeltaTime()
    {
        return this.deltaTime;
    }

    private void loop()
    {
        SobjectCollection sobjectCollection = this.sobjectContainer.getSobjectCollection();
        while( this.working )
        {
            updateDeltaTime();

            for( Sobject sobject : sobjectCollection )
            {
                sobject.update();
            }

            killDyingSobject();
        }
    }

    private void updateDeltaTime()
    {
        LocalDateTime nowTime = LocalDateTime.now();
        this.deltaTime = Duration.between( this.lastUpdateDateTime, nowTime).toMillis();
        this.lastUpdateDateTime = nowTime;
    }

    private void killDyingSobject()
    {
        SobjectCollection sobjectCollection = this.sobjectContainer.getSobjectCollection();
        for( Sobject sobject : sobjectCollection )
        {
            if( sobject.isDying() )
            {
                this.sobjectContainer.deleteSobject( sobject );
            }
        }
    }
}
