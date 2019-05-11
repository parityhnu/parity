package com.lufan.parityproject.data.entry;

import com.lufan.parityproject.data.annotation.Column;
import com.lufan.parityproject.data.annotation.Table;
import com.lufan.parityproject.data.entry.interfaceEntry.AbsEntry;
import com.lufan.parityproject.data.object.TestObject;

@Table(name = "test1")
public class EntryTest implements AbsEntry {
    @Column(name = "t_name", order = 2)
    public String name;

    @Column(name = "t_id", order = 1)
    public long id;

    @Column(name = "t_testC", order = 3)
    public String testC;

    @Override
    public void fromObject(Object object) {
        if (object instanceof TestObject) {
            id = ((TestObject) object).getId();
            name = ((TestObject) object).getName();
            testC = ((TestObject) object).getTestC();
        }
    }

    @Override
    public TestObject fromEntry() {
        TestObject object = new TestObject(id, name, testC);
        return object;
    }
}
