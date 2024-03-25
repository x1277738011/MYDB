package com.xcc.mydb.backend.utils;
import java.nio.ByteBuffer;
import java.util.Arrays;

import com.google.common.primitives.Bytes;

/**
 * 数据解析器
 */
public class Parser {
    public static byte[] short2Byte(short value) {
        return ByteBuffer.allocate(Short.SIZE / Byte.SIZE).putShort(value).array();
    }

    public static short parseShort(byte[] buf) {
        ByteBuffer buffer = ByteBuffer.wrap(buf, 0, 2);
        return buffer.getShort();
    }

    public static byte[] int2Byte(int value) {
        return ByteBuffer.allocate(Integer.SIZE / Byte.SIZE).putInt(value).array();
    }

    public static int parseInt(byte[] buf) {
        ByteBuffer buffer = ByteBuffer.wrap(buf, 0, 4);
        return buffer.getInt();
    }

    public static long parseLong(byte[] buf) {
        ByteBuffer buffer = ByteBuffer.wrap(buf, 0, 8);
        return buffer.getLong();
    }

    public static byte[] long2Byte(long value) {
        return ByteBuffer.allocate(Long.SIZE / Byte.SIZE).putLong(value).array();
    }

    public static ParseStringRes parseString(byte[] raw) {
        int length = parseInt(Arrays.copyOf(raw, 4));
        String str = new String(Arrays.copyOfRange(raw, 4, 4+length));
        return new ParseStringRes(str, length+4);
    }

    public static byte[] string2Byte(String str) {
        byte[] l = int2Byte(str.length());
        return Bytes.concat(l, str.getBytes());
    }

    public static long str2Uid(String key) {
        long seed = 13331;
        long res = 0;
        for(byte b : key.getBytes()) {
            res = res * seed + (long)b;
        }
        return res;
    }

}
/**
 * 这段代码是一个简单的数据解析器，包括了将基本数据类型转换为字节数组以及将字节数组解析为基本数据类型的方法。让我来详细解释一下各个方法的作用：
 *
 * 1. `short2Byte(short value)`：将一个 short 类型的数据转换为字节数组。首先，使用 `ByteBuffer.allocate(Short.SIZE / Byte.SIZE)` 创建一个长度为 2 字节的 ByteBuffer，然后使用 `putShort(value)` 将 short 值放入 ByteBuffer 中，并使用 `array()` 方法将 ByteBuffer 转换为字节数组并返回。
 *
 * 2. `parseShort(byte[] buf)`：将一个字节数组解析为 short 类型的数据。使用 `ByteBuffer.wrap(buf, 0, 2)` 创建一个长度为 2 字节的 ByteBuffer，并使用 `getShort()` 方法从 ByteBuffer 中获取 short 值并返回。
 *
 * 3. `int2Byte(int value)`：将一个 int 类型的数据转换为字节数组。与 `short2Byte` 方法类似，首先创建一个长度为 4 字节的 ByteBuffer，然后将 int 值放入 ByteBuffer 中，并转换为字节数组返回。
 *
 * 4. `parseInt(byte[] buf)`：将一个字节数组解析为 int 类型的数据。使用 `ByteBuffer.wrap(buf, 0, 4)` 创建一个长度为 4 字节的 ByteBuffer，并使用 `getInt()` 方法从 ByteBuffer 中获取 int 值并返回。
 *
 * 5. `long2Byte(long value)`：将一个 long 类型的数据转换为字节数组。与前面两个方法类似，首先创建一个长度为 8 字节的 ByteBuffer，然后将 long 值放入 ByteBuffer 中，并转换为字节数组返回。
 *
 * 6. `parseLong(byte[] buf)`：将一个字节数组解析为 long 类型的数据。使用 `ByteBuffer.wrap(buf, 0, 8)` 创建一个长度为 8 字节的 ByteBuffer，并使用 `getLong()` 方法从 ByteBuffer 中获取 long 值并返回。
 *
 * 7. `parseString(byte[] raw)`：将一个包含字符串长度和字符串内容的字节数组解析为字符串。首先从字节数组中解析出字符串的长度（前 4 个字节），然后使用 `Arrays.copyOfRange` 方法获取字符串内容的字节数组，并通过 `new String` 构造函数将其转换为字符串，最后返回一个包含解析出的字符串和长度的 `ParseStringRes` 对象。
 *
 * 8. `string2Byte(String str)`：将一个字符串转换为字节数组。首先将字符串的长度转换为字节数组，然后使用 `Bytes.concat` 方法将长度字节数组和字符串内容的字节数组拼接起来并返回。
 *
 * 9. `str2Uid(String key)`：将一个字符串转换为唯一标识符（uid）。该方法使用了简单的哈希算法，通过将字符串的每个字符转换为字节，并以某个固定的 seed（这里是 13331）作为基数，计算得到一个长整型的唯一标识符并返回。
 *
 * 需要注意的是，`ParseStringRes` 是一个自定义的类，用于存储解析字符串时的结果，包括解析出的字符串和字符串的长度。
 */