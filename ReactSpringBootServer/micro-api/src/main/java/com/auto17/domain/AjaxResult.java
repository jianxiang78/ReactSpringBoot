package com.auto17.domain;

import org.springframework.util.StringUtils;

import java.util.HashMap;

/**
 * Ajax Result
 *
 * @author
 */
public class AjaxResult extends HashMap<String, Object>
{
    private static final long serialVersionUID = 1L;

    /** status code */
    public static final String CODE_TAG = "code";
    public static final String MSG_TAG = "msg";
    public static final String DATA_TAG = "data";


    /**
     * enum Type
     */
    public enum Type
    {
        /** SUCCESS */
        SUCCESS(1),
        /** WARN */
        WARN(301),
        /** ERROR */
        ERROR(0);
        private final int value;

        Type(int value)
        {
            this.value = value;
        }

        public int value()
        {
            return this.value;
        }
    }


    public AjaxResult()
    {
    }

    public AjaxResult(Type type, String msg)
    {
        super.put(CODE_TAG, type.value);
        super.put(MSG_TAG, msg);
    }

    public AjaxResult(Type type, String msg, Object data)
    {
        super.put(CODE_TAG, type.value);
        super.put(MSG_TAG, msg);
        if (!StringUtils.isEmpty(data))
        {
            super.put(DATA_TAG, data);
        }
    }

    public static AjaxResult success()
    {
        return AjaxResult.success("operation succeeded");
    }

    public static AjaxResult success(Object data)
    {
        return AjaxResult.success("operation succeeded", data);
    }


    public static AjaxResult success(String msg)
    {
        return AjaxResult.success(msg, null);
    }


    public static AjaxResult success(String msg, Object data)
    {
        return new AjaxResult(Type.SUCCESS, msg, data);
    }

    public static AjaxResult warn(String msg)
    {
        return AjaxResult.warn(msg, null);
    }

    public static AjaxResult warn(String msg, Object data)
    {
        return new AjaxResult(Type.WARN, msg, data);
    }


    public static AjaxResult error()
    {
        return AjaxResult.error("operation failed");
    }


    public static AjaxResult error(String msg)
    {
        return AjaxResult.error(msg, null);
    }


    public static AjaxResult error(String msg, Object data)
    {
        return new AjaxResult(Type.ERROR, msg, data);
    }
}
