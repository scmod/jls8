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


A variable of type Object[] can hold a reference to an array of any reference type
Object[]只能放any reference type,不包括primitive type
所以上次谁跟我说的在新的jdk里可以支持Integer[] i = new int[10];..坑..

还发现了effectively final的解释.~匿名内部类引用外部数据只要是effectively final的可以不用将外部数据定义成final
虽然感觉定义不定义没什么意义,具体看看后面会不会提到

第六章讲了些命名的规范,如何让命名有意义~以及一些可见性的东西,好像不是很有看头,主要是外部类私有的东西的内部类可见之类的,然后是像
The fully qualified name of the type "array of array of array of array of String" is
"java.lang.String[][][][]".这样一些定义, Fully Qualified Names and Canonical Names的差别什么的


顺路发现编译器会把new XXX().method()拆成new XXX();method();这样调用如果method()是static的话,
虽然一般不会通过new调用静态方法