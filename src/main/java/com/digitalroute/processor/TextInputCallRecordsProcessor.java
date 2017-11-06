package com.digitalroute.processor;

import com.digitalroute.Application;
import com.digitalroute.input.CallRecordsProcessor;
import com.digitalroute.input.schema.TextInputSchema;
import com.digitalroute.output.BillingGateway;
import com.digitalroute.common.record.Record;

import java.io.InputStream;

public abstract class TextInputCallRecordsProcessor implements CallRecordsProcessor {

    private BillingGateway billingGateway;
    private TextInputSchema textInputSchema;

    public TextInputCallRecordsProcessor(BillingGateway billingGateway, TextInputSchema textInputSchema) {
        this.billingGateway = billingGateway;
        this.textInputSchema = textInputSchema;
    }

    /**
     * Should be used to process CDR(Call Data Records) batches from an {@link InputStream}.
     * Look at {@link Application#main(String[])} for an example
     *
     * @param in InputStream that should contain Call Data Records
     */
    @Override
    public void processBatch(InputStream in) {
        begin();
        if (textInputSchema.onOpen(in))
            while(textInputSchema.next())
                process(textInputSchema.get());
            postProcess();
        end();
    }

    protected BillingGateway billingGateway() {
        return billingGateway;
    }

    public abstract void begin();
    public abstract void process(Record record);
    public abstract void postProcess();
    public abstract void end();
}
