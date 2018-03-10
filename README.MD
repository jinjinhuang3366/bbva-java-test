**Run the application**

The application is developed and tested under JDK version 1.8.0_161 and can be run 
by the following methods

- The application can be run from Application.java class

- It is also a maven project which can also be run by maven command
mvn clean install

**Framework and Design Decisions**

**_RxJava 2_**
 
 RxJava 2.x is used throughout the test, which has the following benefits
 - Reactive and non-blocking implementation
 - Simplify threading utilisation
 - Flowrable interface has Backpressure support in which developer can 
 easily specify strategy to handle overwhelming incoming messages
 
 **_OrderBookSimulator_**
 
 OrderBookSimulator delegate to rxJava background threads to generate
 market depth updates, subscriber blocks the main thread and notify updates
 to MarketDataListener
 
 **_MarketDataListener_**
 
 MarketDataListener uses ConcurrentMap<String, BlockingQueue<OrderBookEntry>> to buffer
 the incoming OrderBookEntry updates, the key is the instrument, eg. "EURUSD". 
 
 This listener delegates the VWAP calculation to RxJava background threads (Schedulers.computation() 
 means spinning up threads according to number of processors), i.e each VWAP calculation is handled 
 by a separate thread to enhance performance.
 
 VWAP result will be printed out in standard output by the responsible worker thread.
 
 **Assumptions**
 
 It is assumed all the instruments emitted from the simulator are interested and collected
 for calculation, so there is no currency filtering logic implemented in the test.
 
 **Testing**
 
 Unit testing and integration testing should be covered to get the test closed to a 
 professional grade, most of them are omitted due to the time constraint to this exercise. 
 