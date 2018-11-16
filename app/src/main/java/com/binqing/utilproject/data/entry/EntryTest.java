package com.binqing.utilproject.data.entry;

import com.binqing.utilproject.data.annotation.Column;
import com.binqing.utilproject.data.annotation.Table;
import com.binqing.utilproject.data.entry.interfaceEntry.AbsEntry;
import com.binqing.utilproject.data.object.TestObject;

@Table(name = "test")
public class EntryTest implements AbsEntry {
    @Column(name = "t_name")
    public String name;

    @Column(name = "t_id")
    public long id;

    @Override
    public void fromObject(Object object) {
        if (object instanceof TestObject) {
            id = ((TestObject) object).getId();
            name = ((TestObject) object).getName();
        }
    }

    @Override
    public TestObject fromEntry() {
        TestObject object = new TestObject();
        object.setId(id);
        object.setName(name);
        return object;
    }
}
