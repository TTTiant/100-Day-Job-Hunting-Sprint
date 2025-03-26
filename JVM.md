# 八股文 

## JVM

### 1. JDK、JRE、JVM

JVM包含JRE包含JDK

JDK：Java标准**开发**包，提供了编译、运行，包含Java编译器、Java运行时的环境、
常用的Java类库等。

JRE：Java**运行**时的环境，普通用户只需安装JRE来运行，开发者必须安装JDK
来编译，调试程序。

JVM：Java虚拟机，java实现**跨平台**最核心的部分，**运行字节码文件**。
JVM运行字节码时，会把字节码解释为机器指令，不同OS，机器指令可能不同，所以要根据OS
安装JDK

### 2. hashCode()与equals（）的关系
对于两个对象：
hashCode不相同，那么一定不同
hashCode相同，也有**可能**不同
equals（），那hashCode一定相同

总结：equals（） > hashCode

### 3.String,StringBuffer, StringBuilder的区别

String：线程安全，不可变
StringBuffer：**线程安全**，可变，适合多线程
StringBuilder：**线程不安全**，可变，适合单线程

### 4.泛型中extends和super的区别
<extends T>表示包括T在哪的任何T的**子类**
<super T>表示包括T在内的任何T的**父类**

### == 和equals方法的区别
==：_基本数据类型_，比较的是**值**。
   _引用类型_（如String、Array、类等），比较的是**引用地址**
equals：各个类重写equals的比较逻辑不同，例如：String，比较的是字符串的哥哥字符是否全部相等

### 重载overload和重写override的区别
重载：一个类，同名的方法有不同的参数列表（包含参数类型和个数）

重写：子类继承父类的方法
     名字**可以**不同
     参数列表必须**相同**
     返回类型必须兼容（子类方法的返回类型可以是父类方法返**回类型的子类**）
     访问修饰符不能更严格（可以**更宽松**但不能更严格）
     不能抛出更宽泛的检查异常（可以抛出**更具体**的异常或不抛出）

### List和Set的区别

List:有序，可重复，允许多Null，用Iterator取出元素，然后遍历，用get（index）获取制定下标元素

Set：无序，不可重复，最多**允许一个Null**元素。
    *取*元素时，**只能**用Iterator。
例如： 
``` Java 
    System.out.println("--- Iterator ---");
        Iterator<String> it = fruits.iterator();
        while (it.hasNext()) {
            String fruit = it.next();
            if (fruit.equals("Banana")) {
                it.remove();  // 安全移除
            }
            System.out.println(fruit);
        }
```

### ArrayList和LinkedList的区别
1. 数据结构：ArrayList是数组；LinkedList是链表
2. 根据数据结构不同，所以应用场景不同：ArrayList适合**随机查找**，
   LinkedList更适合**删除和添加**。
3. 他们**都实现了List接口**，但是**LinkedList**格外实现了Deque，所以用作**队列**使用。



### HashMap的扩容机制
1.7版本：先生成新的数组
        遍历老数组中的每个位置上的链表上的每个元素
        取每个元素的key，并基于新数组长度，计算出每个元素在新数组中的下标
        将元素添加到新数组中
        转移完后，将新数组赋值给HashMap的table属性（table是map的底层数据结构）
总结：新数组- 遍历老数组 - 取key，计算下标-添加到新数组-赋值table

1.8版本：先生成新的数组
        遍历老数组每个位置上的链表和红黑树
        如果是链表，则直接将链表中的每个元素重新计算下标，并添加到新数组中去
        如果是红黑树，先遍历红黑树，再计算红黑树对应新数组的下标
            如果元素个数超过8，生成新的红黑树，在将根基诶单添加到新数组中
            如果没有超过8，生成一个链表，将链表头节点添加到新数组



### ConcurrentHashMap的扩容机制
1.7版本：是基于Segment分段实现的
        每个Segment相当于一个小型HashMap
        每个Segment内部会扩容和HashMap的扩容逻辑类似
        过程：先生成新的数组，再转移元素到新数组中
             扩容判断也是每个Segment内部单独判断的，是否超过阈值。

1.8版本：不基于Segment
1. 当某个线程put时，
     如果 ConcurrentHashMap正在进行扩容，那么该线程一起扩容；
     如果 没有，那么将Key-Value添加到ConcurrentHashMap中，判断是否超过阈值。
   
2. 支持多个线程同时扩容
   扩容之前也声称新的数组，
   转移元素到数组中时候，将原数组分组，不同的线程来进行元素的转移，
   每个线程负责一组或多组元素转移工作。






