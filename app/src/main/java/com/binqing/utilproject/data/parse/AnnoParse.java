package com.binqing.utilproject.data.parse;

import android.content.ContentValues;
import android.text.TextUtils;

import com.binqing.utilproject.data.annotation.Column;
import com.binqing.utilproject.data.annotation.Table;
import com.binqing.utilproject.data.entry.interfaceEntry.AbsEntry;
import com.binqing.utilproject.data.model.ColumnInfo;
import com.binqing.utilproject.data.model.DBValues;
import com.binqing.utilproject.data.model.DataType;
import com.binqing.utilproject.data.model.TableInfo;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;


/**
 * 解析注解
 */
public class AnnoParse {
    private static final String TAG = "AnnoParse";

    /**
     * 初始化table 的信息
     *
     * @param clazz
     */
    public static TableInfo initTableInfo(Class<?> clazz) {
        Table table = (Table) clazz.getAnnotation(Table.class);
        if (table == null) {
            return null;
        }
        TableInfo tableInfo = new TableInfo();
        tableInfo.clazzName = clazz.getName();
        if (TextUtils.isEmpty(table.name())) {
            tableInfo.tableName = clazz.getName();
        } else {
            tableInfo.tableName = table.name();
        }

        LinkedHashMap<String, ColumnInfo> columnMaps = null;
        Field[] fields = clazz.getDeclaredFields();
        List<Field> fieldList = getOrderedField(fields);
        for (Field field : fieldList) {
            field.setAccessible(true);
            Column column = field.getAnnotation(Column.class);
            if (column != null) {
                // 列信息
                ColumnInfo columnInfo = new ColumnInfo();
                columnInfo.fieldName = column.name();
                columnInfo.fieldtype = field.getType().getName();

                columnInfo.columLength = column.length();

                //数据库 对应的列类型
                columnInfo.dbtype = getType(field.getType(), columnInfo.fieldtype);

                if (columnMaps == null) {
                    columnMaps = new LinkedHashMap<String, ColumnInfo>();
                }
                columnMaps.put(columnInfo.fieldName, columnInfo);
            }
        }
        tableInfo.colunmMap = columnMaps;
        return tableInfo;
    }

    public static <T> String getTableName(Class<?> clazz) {
        Table table = (Table) clazz.getAnnotation(Table.class);
        if (table == null) {
            return null;
        }
        String tableName = null;
        if (TextUtils.isEmpty(table.name())) {
            tableName = clazz.getName();
        } else {
            tableName = table.name();
        }
        return tableName;
    }

    /**
     * 通过field的type类型名字，得到存到数据库里对应的数据类型
     *
     * @param typeName
     * @return
     */
    private static DataType getType(Class<?> clazz, String typeName) {
        if (clazz.isEnum()) {
            return DataType.ENUM;
        } else if (long.class.getName().equals(typeName) || Long.class.getName().equals(typeName)) {
            return DataType.LONG;
        } else if (Integer.class.getName().equals(typeName) || int.class.getName().equals(typeName)) {
            return DataType.INTEGER;
        } else if (String.class.getName().equals(typeName)) {
            return DataType.STRING;
        } else if (float.class.getName().equals(typeName) || Float.class.getName().equals(typeName)) {
            return DataType.FLOAT;
        } else if (boolean.class.getName().equals(typeName) || Boolean.class.getName().equals(typeName)) {
            return DataType.BOOLEAN;
        } else if (short.class.getName().equals(typeName) || Short.class.getName().equals(typeName)) {
            return DataType.SHORT;
        } else {
            return DataType.UNKOWN;
        }
    }

    public static DBValues getDBValues(List<AbsEntry> entryList) {
        if (entryList == null || entryList.isEmpty()) {
            return null;
        }
        AbsEntry entry = entryList.get(0);
        if (entry == null) {
            return null;
        }
        Class<? extends AbsEntry> clazz = entry.getClass();
        TableInfo tableInfo = initTableInfo(clazz);
        if (tableInfo == null) {
            return null;
        }
        String tableName = tableInfo.tableName;
        List<ContentValues> contentValuesList = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        List<Field> fieldList = getOrderedField(fields);
        for (AbsEntry absEntry : entryList) {
            ContentValues values = null;
            for (Field field : fieldList) {
                field.setAccessible(true);
                Column column = field.getAnnotation(Column.class);
                if (column != null) {
                    try {
                        if (values == null) {
                            values = new ContentValues();
                        }
                        ColumnInfo columnInfo = tableInfo.getColumnByColunmName(column.name());
                        if (columnInfo == null) {
                            continue;
                        }
                        Object object = field.get(absEntry);
                        switch (columnInfo.dbtype) {
                            case INTEGER:
                                if (object == null) {
                                    values.put(columnInfo.fieldName, "");
                                } else {
                                    values.put(columnInfo.fieldName, (Integer) object);
                                }
                                break;

                            case STRING:
                                if (object == null) {
                                    values.put(columnInfo.fieldName, "");
                                } else {
                                    values.put(columnInfo.fieldName, (String) object);
                                }
                                break;
                            case SHORT:
                                if (object == null) {
                                    values.put(columnInfo.fieldName, "");
                                } else {
                                    values.put(columnInfo.fieldName, (Short) object);
                                }
                                break;
                            case DOUBLE:
                            case FLOAT:
                            case LONG:
                            case ENUM:
                            case BOOLEAN:
                                if (object == null) {
                                    values.put(columnInfo.fieldName, "");
                                } else {
                                    values.put(columnInfo.fieldName, object.toString());
                                }
                                break;
                            default:
                                break;
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    }
                }
            }
            contentValuesList.add(values);
        }
        return new DBValues(tableName, contentValuesList);
    }

    private static List<Field> getOrderedField(Field[] fields) {
        List<Field> fieldList = new ArrayList<>();
        for (Field f : fields) {
            if(f.getAnnotation(Column.class) != null) {
                int index = 0;
                for (Field field : fieldList) {
                    if (field.getAnnotation(Column.class).order() < f.getAnnotation(Column.class).order()) {
                        index++;
                        continue;
                    }
                    break;
                }
                fieldList.add(index, f);
            }
        }
        return fieldList;
    }

}
