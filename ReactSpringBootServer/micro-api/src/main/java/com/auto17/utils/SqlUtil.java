package com.auto17.utils;


import org.apache.commons.lang3.StringUtils;

/**
 * sql Util
 *
 * @author
 */
public class SqlUtil
{

    public static String SQL_PATTERN = "[a-zA-Z0-9_\\ \\,\\.]+";


    public static String escapeOrderBySql(String value) {
        if (StringUtils.isNotEmpty(value) && !isValidOrderBySql(value))
        {
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return value;
    }

    public static boolean isValidOrderBySql(String value)
    {
        return value.matches(SQL_PATTERN);
    }
}
