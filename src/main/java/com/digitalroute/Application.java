// Copyright 2000-2017 Digital Route AB. All rights reserved.
// DIGITAL ROUTE AB PROPRIETARY/CONFIDENTIAL.
// Use is subject to license terms.
//

package com.digitalroute;

public class Application {

    public static final String IN_FILE = "INFILE_ascii_big";

    public static void main(String[] args) {
        /*
        //Create an CallRecordsProcessor an feed it with an anonymous class, to debug its activity
        CallRecordsProcessor processor = new YourImplementation(new BillingGateway() {
            @Override
            public void beginBatch() {
            }
            @Override
            public void consume(String callId, int seqNum, String aNum, String bNum, byte causeForOutput, int duration) {
                System.out.println("consume: " + callId +", "+  seqNum +", "+ aNum +", "+ bNum +", "+ causeForOutput +", "+ duration);
            }
            @Override
            public void endBatch(long totalDuration) {
                System.out.println("endBatch: totalDuration " + totalDuration);
            }
            @Override
            public void logError(ErrorCause errorCause, String callId, int seqNum, String aNum, String bNum) {
                System.out.println("logError " +errorCause  +", "+ callId +", "+  seqNum +", "+ aNum +", "+ bNum);
            }
        });
        ...
        //perform processing
        processor.processBatch(Application.class.getClassLoader().getResourceAsStream(IN_FILE));
        */
    }

}
