package com.github.lblaszka.s19.sobjectenvironment.impl;

import com.github.lblaszka.s19.sobject.Sobject;
import com.github.lblaszka.s19.sobjectcontainer.SobjectCollection;
import com.github.lblaszka.s19.sobjectcontainer.SobjectContainer;
import com.github.lblaszka.s19.sobjectenvironment.SobjectEnvironmentRunetimeStrategy;

import java.time.Duration;
import java.time.LocalDateTime;

public class SobjectEnvironmentRunetimeStrategyImplNonThread implements SobjectEnvironmentRunetimeStrategy
{

    private int fequency=0;
    private LocalDateTime lastUpdateDateTime;
    private long deltaTime=0;
    private SobjectContainer sobjectContainer;
    protected boolean running;


    @Override
    public void setFrequency( int mS )
    {
        this.fequency = mS;
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
        this.running = true;
        try
        {
            loop();
        } catch ( InterruptedException e )
        {
            e.printStackTrace();
            this.running = false;
        }
    }


    @Override
    public void pause()
    {
        this.running = false;
    }


    @Override
    public long getDeltaTime()
    {
        return this.deltaTime;
    }


    @Override
    public boolean isRunning()
    {
        return this.running;
    }


    protected void loop() throws InterruptedException
    {
        SobjectCollection sobjectCollection = this.sobjectContainer.getSobjectCollection();
        while( this.running )
        {
            updateDeltaTime();

            for( Sobject sobject : sobjectCollection )
            {
                sobject.update();
            }

            killDyingSobject();

            Thread.sleep( this.fequency );
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
