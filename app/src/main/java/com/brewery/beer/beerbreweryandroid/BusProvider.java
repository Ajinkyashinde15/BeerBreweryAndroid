package com.brewery.beer.beerbreweryandroid;

import com.squareup.otto.Bus;

/**
 * Created by Ajinkya
 */

public class BusProvider
{
        private static final Bus BUS = new Bus();

        public static Bus getInstance(){
            return BUS;
        }

        public BusProvider(){}

}
