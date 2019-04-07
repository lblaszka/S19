package com.github.lblaszka;

import com.github.lblaszka.s19.sobject.*;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws InterruptedException
    {
        LocalDateTime t1 = LocalDateTime.now();
        for( int i = 0; i < 1000; i++ )
        {
            LocalDateTime now = LocalDateTime.now();
            System.out.println(  );
            Thread.sleep( 1 );
            t1 = now;
        }
    }
}