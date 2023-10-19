package com.projetjee.projetjee.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class jdbcUtils {
    public static Map<String, String>[] resultToArray(ResultSet res) throws SQLException {
        int colNum = res.getMetaData().getColumnCount();
        int rowCount = 0;
        int rowNum = res.last() ? res.getRow() : 0;
        res.first();
        Map<String, String>[] ret = new Map[rowNum];
        do{
            ret[rowCount] = new HashMap();
            for(int colCount=0;colCount<colNum;colCount++){
                ret[rowCount].put(res.getMetaData().getColumnName(colCount+1),res.getString(colCount+1));
            }
            rowCount++;
        }
        while(res.next());
        return ret;
    }
}
