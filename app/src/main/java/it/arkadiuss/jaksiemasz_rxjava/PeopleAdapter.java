package it.arkadiuss.jaksiemasz_rxjava;

import java.util.ArrayList;

/**
 * Created by arkadius on 08.01.18.
 */

interface PeopleAdapter {
    void receiveEvent(MessageEvent event);
    void registerEventBus();
    void unregisterEventBus();
}
