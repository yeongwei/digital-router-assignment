package com.digitalroute;

import com.digitalroute.input.CallRecordsProcessor;
import com.digitalroute.input.common.TextInputSchema;
import com.digitalroute.output.BillingGateway;

import java.io.InputStream;

public abstract class TextRecordsProcessor implements CallRecordsProcessor {

    private BillingGateway billingGateway;

    public TextRecordsProcessor(BillingGateway billingGateway) {
        this.billingGateway = billingGateway;
    }

    @Override
    public void processBatch(InputStream in) {
    }

    public abstract void start();
    public abstract void end();
}
