#############################################
#
# 一、对于一些基本指令的添加
#
#############################################

#############################################
#
# 二、Android开发中一些需要保留的公共部分
#
#############################################

#############################################
#
# 三、自身项目相关处理（必须的，否则出问题-包括：实体类）。
# 在开发的时候我们可以将所有的实体类放在一个包内，这样我们写一次混淆就行了。
#
#############################################

#############################################
#
# 四、处理第三方依赖库
#
#############################################
# RxJava RxAndroid
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}