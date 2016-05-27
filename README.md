# jls8
语言规范里面貌似挺多东西...看下来貌似就是无限的引用其他章节的东西,略跳跃

The null reference can always be assigned or cast to any reference type (§5.2, §5.3,
§5.5)
null可以随便强转...

A class method that is declared synchronized (§8.4.3.6) synchronizes on the
monitor associated with the Class object of the class.
synchronized方法相当于普通方法里面加个synchronized(this.getClass())

The Class String
A String object has a constant (unchanging) value.
String literals (§3.10.5) are references to instances of class String
不变的String,"xxx"文字是String的一个实例

At run time, several reference types with the same binary name may be loaded
simultaneously by different class loaders. These types may or may not represent
the same type declaration. Even if two such types do represent the same type
declaration, they are considered distinct.
不同的classLoader加载的同一个class文件也是被认作不同的,==号,instanceof返回false吧..cast就不用说会报错了..

Object >1 Object[]
Cloneable >1 Object[]
java.io.Serializable >1 Object[]
似乎数组都是可以进行clone,进行序列化的,所以...Cloneable c = new Object[10];

Collections.reverse(List<?> list)
size < REVERSE_THRESHOLD || list instanceof RandomAccess
这个是看泛型通配符顺路发现的....jdk实现倒转一个集合,如果集合并不是RandomAccess的,那么get需要对调的元素会非常耗费时间,
所以默认定义了个阈值18,超过18并且不能随机访问的进行倒转会直接用迭代器来get需要对调的元素...考虑略充分...

8新增了个ElementType.TYPE_USE,这样的注解可以@C int @A [] @B [] f;注解在[]前..
具体在9.7.4,从4.11当中那段引用过去的...
并且getDeclaredAnnotations这个方法及类似的方法是获取不到的,这个方法只能获取到ElementType = 当前实例类型的注解
比如Field.getDeclaredAnnotations似乎只能获取到ElementType = ElementType.FIELD的注解数量,
其他的可以通过getAnnotatedType进一步操作来获取
