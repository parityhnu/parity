package com.binqing.utilproject.data.object;

public class TestObject {
    private long mId;
    private String mName;
    private String mTestC;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getTestC() {
        return mTestC;
    }

    public void setTestC(String testC) {
        mTestC = testC;
    }

    public TestObject(long id, String name, String testC) {
        mId = id;
        mName = name;
        mTestC = testC;
    }

    @Override
    public String toString() {
        return "TestObject{" +
                "mName='" + mName + '\'' +
                ", mId=" + mId +
                ", mTestC='" + mTestC + '\'' +
                '}';
    }
}
