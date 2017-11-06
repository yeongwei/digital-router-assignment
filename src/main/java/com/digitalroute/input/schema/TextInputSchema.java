package com.digitalroute.input.schema;

import com.digitalroute.common.record.Record;
import com.digitalroute.common.record.TextRecordSchema;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public abstract class TextInputSchema {
    private TextRecordSchema textRecordSchema;

    private InputStreamReader inReader = null;
    private BufferedReader buffReader = null;

    private String line;

    public TextInputSchema(TextRecordSchema textRecordSchema) {
        this.textRecordSchema = textRecordSchema;
    }

    public boolean onOpen(InputStream in) {
        try {
            inReader = new InputStreamReader(in); // TODO: What about encoding
            buffReader = new BufferedReader(inReader);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean onClose() {
        try {
            if (buffReader != null) buffReader.close();
            if (inReader != null) inReader.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean next() {
        try {
            line = buffReader.readLine();
            if (line == null) return false;
            else if (line.isEmpty()) return false;
            else return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Record get() {
        return textRecordSchema.onRead(line);
    }
}
