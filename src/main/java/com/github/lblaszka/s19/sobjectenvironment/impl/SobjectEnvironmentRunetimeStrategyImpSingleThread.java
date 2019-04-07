package com.github.lblaszka.s19.sobjectenvironment.impl;

import com.github.lblaszka.s19.sobjectenvironment.SobjectEnvironmentRuntimeStrategy;

public class SobjectEnvironmentRunetimeStrategyImpSingleThread extends SobjectEnvironmentRuntimeStrategyImplNonThread
{
    private Thread thread;


    public SobjectEnvironmentRunetimeStrategyImpSingleThread()
    {
        thread = new Thread( new Loop( this ) );
    }


    @Override
    public void start()
    {
        if( this.running == false )
            this.thread.start();
    }


    @Override
    public void pause()
    {
        if( this.running == true )
            this.thread.stop();
    }

    private class Loop implements Runnable
    {
        SobjectEnvironmentRunetimeStrategyImpSingleThread sobjectEnvironmentRunetimeStrategyImpSingleThread;


        public Loop( SobjectEnvironmentRunetimeStrategyImpSingleThread sobjectEnvironmentRunetimeStrategyImpSingleThread )
        {
            this.sobjectEnvironmentRunetimeStrategyImpSingleThread = sobjectEnvironmentRunetimeStrategyImpSingleThread;
        }


        @Override
        public void run()
        {
            try
            {
                this.sobjectEnvironmentRunetimeStrategyImpSingleThread.loop();
            } catch ( InterruptedException e )
            {
                e.printStackTrace();
                this.sobjectEnvironmentRunetimeStrategyImpSingleThread.running=false;
            }
        }
    }
}
