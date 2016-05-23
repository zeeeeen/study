package com.yang;

import java.io.ByteArrayOutputStream;

public class BufferNotCopiedByteArrayOutputStream extends ByteArrayOutputStream {
    public synchronized byte[] toByteArray() {
        return buf;
    }
}
