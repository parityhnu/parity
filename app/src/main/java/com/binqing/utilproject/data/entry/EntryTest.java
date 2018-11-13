package com.binqing.utilproject.data.entry;

import com.binqing.utilproject.data.annotation.Column;
import com.binqing.utilproject.data.annotation.Table;

@Table(name = "test")
public class EntryTest {

    @Column(name = "t_id")
    public long id;
}
