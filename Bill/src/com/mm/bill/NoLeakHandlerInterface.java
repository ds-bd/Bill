package com.mm.bill;

import android.os.Message;

public interface NoLeakHandlerInterface {

    public boolean isValid();

    public void handleMessage(Message msg);

}
