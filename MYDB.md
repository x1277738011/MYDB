# JAVA IO

## RandomAccessFile

RandomAccessFile支持对文件的读取和写入随机访问（其他的输入流或者输出流只能进行一种操作，要么输入，要么输出）。RandomAccessFile把随机访问的文件对象看作存储在文件系统中的一个大型 byte 数组，然后通过指向该 byte 数组的光标或索引（即：文件指针 FilePointer）在该数组任意位置读取或写入任意数据。**输入操作从文件指针开始读取字节（以字节为单位进行读取），并随着对字节的读取而前移此文件指针。如果**RandomAccessFile**访问文件以读取/写入模式创建，则输出操作也可用；输出操作从文件指针开始写入字节，并随着对字节的写入而前移此文件指针。**

### 构造方法

**1、RandomAccessFile(File file, String mode)**

**2、RandomAccessFile(String name, String mode)**

**两个构造方法的第一个参数不做介绍，**

**mode ：**第二个参数是指以什么模式创建读写流，此参数有固定的输入值，必须为"r"/"rw"/"rws"/"rwd"其中一个。

**r**：以只读方式打开指定文件。如果试图对该RandomAccessFile指定的文件执行写入方法则会抛出IOException
**rw**：以读取、写入方式打开指定文件。如果该文件不存在，则尝试创建文件
**rws**：以读取、写入方式打开指定文件。相对于rw模式，还要求对文件的内容或元数据的每个更新都同步写入到底层存储设备，默认情形下(rw模式下),是使用buffer的,只有cache满的或者使用RandomAccessFile.close()关闭流的时候儿才真正的写到文件
**rwd**：与rws类似，只是仅对文件的内容同步更新到磁盘，而不修改文件的元数据

### 指针相关方法

RandomAccessFile包含三个方法来操作文件记录指针

- **`long getFilePointer()`**：返回文件记录指针的当前位置
- **`void seek(long pos)`**：将文件记录指针定位到pos位置
- **`skipBytes(int n)`**

```
  该方法用于尝试跳过输入的n个字节以丢弃跳过的字节（跳过的字节不读取）：int skipBytes(int n)
  该方法可能跳过一些较少数量的字节（可能包括0），这可能由任意数量的条件引起，在跳过n个字节之前已经到大文件的末尾只是其中的一种可能
  该方法不抛出EOFException，返回跳过的实际字节数，如果n为负数，则不跳过任何字节
```

- FileDescriptor getFD() : 可以返回这个文件的文件描述符
- native long length() : 可以返回文件的长度

- native void setLength(long newLength) : 还可以设置文件的长度
- close() ： RandomAccessFile在对文件访问操作全部结束后，要调用close()方法来释放与其关联的所有系统资源

# JAVA NIO

**NIO**中有三大组件：**Channel**，**Buffer**和**Selector**。那么**Buffer**的作用就是提供一个缓冲区，用于用户程序和**Channel**之间进行数据读写，也就是用户程序中可以使用**Buffer**向**Channel**写入数据，也可以使用**Buffer**从**Channel**读取数据。

## ByteBuffer

### 创建方法

#### allocate(int capacity)

在堆上分配一个新的字节缓冲区。说明如下：
\1. 创建出来后，**position**为0，并且**limit**会取值为**capacity**；
\2. 创建出来的实际为**HeapByteBuffer**，其内部使用一个字节数组**hb**存储元素；
\3. 初始时**hb**中所有元素为0

#### allocateDirect(int capacity)

在直接内存中分配一个新的字节缓冲区。说明如下：
\1. 创建出来后，**position**为0，并且**limit**会取值为**capacity**；
\2. 创建出来的实际为**DirectByteBuffer**，是基于操作系统创建的内存区域作为缓冲区；
\3. 初始时所有元素为0

#### wrap(byte[] array)

将字节数组包装到字节缓冲区中。说明如下：
1. 创建出来的是HeapByteBuffer，其内部的hb字节数组就会使用传入的array；

2. 改变HeapByteBuffer会影响array，改变array会影响HeapByteBuffer；

3. 得到的HeapByteBuffer的limit和capacity均取值为array.length；

4. position此时都为0

  

## FileChannel

### FileChannel.truncate()

通过调用`FileChannel.truncate()`方法，你可以truncate一个文件。当你truncate一个文件，你会把其截断为指定的长度。

### FileChannel.force()

`Channel`里面还未写入的数据全部刷新到磁盘。操作系统可能会将数据缓存在内存里以提升性能，因此我们无法保证你写入`Channel`的数据都被写到了磁盘，直到你调用`force()`方法。

`force()`方法有一个`boolean`类型的参数，代表是否将文件元数据（比如权限等）也刷新到磁盘。

### position()

当读取或写入`FileChannel`时，需要在特定`position`执行。你可以通过调用`position()`方法来获得`FileChannel`的当前`position`。

你还可以通过调用`position(long pos)`来设置`FileChannel`的`position`。

如果你设置的position超过了文件的大小，并且尝试从Channel读取数据，则会返回-1代表文件结尾。

如果你设置的position超过了文件的大小，并且尝试往Channel写入数据，文件会自动扩张至能放下position以及写入的数据。这个可能导致"file hole"，即磁盘上的物理文件在写入的数据中存在漏洞（即中间有一段完全没有任何数据）


