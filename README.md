# SuperShadow

   SuperShadow 是一个专门为 View 添加阴影效果的库。 SuperShadow 支持自定义阴影实现方式、阴影颜色、阴影经度大小、阴影附加方位。
#Example

![Example](http://upload-images.jianshu.io/upload_images/3874191-f746908bbeb2664b.gif?imageMogr2/auto-orient/strip)

# Import

### Gradle

Step 1. Add the JitPack repository to your build file

	allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}
   
Step 2. Add the dependency

	dependencies {
	        compile 'com.github.Hitomis:CrazyShadow:v1.0.1'
	}

# Usage
    ​
        new SuperShadow.Builder()
                .setContext(this)
                .setDirection(CrazyShadowDirection.ALL)
                .setShadowRadius(dip2Px(3))
                .setCorner(dip2Px(5))
                .setBackground(Color.parseColor("#96a993"))
                .setImpl(CrazyShadow.IMPL_DRAW)
                .action(findViewById(R.id.relay_draw1));
    ​

# Attribute

| 属性 | 说明 |
| :--: |  :--: |
| impl | 以何种方式添加阴影，支持 wrap、float、drawable 三种方式 |
| baseShadowColor | 阴影的基本颜色，即最深的颜色，与 shadowColors 表示为同一个作用， 如果baseShadowColor 与 shadowColors 都不设置，阴影会使用默认颜色|
| background | 修改 View 的背景色，如果使用 drawable 方式添加阴影，那么该属性必须添加 |
| shadowColors | 绘制阴影时需要的一个颜色由深到浅且长度为3的数组, 该属性与 baseShadowColor 起同一个作用，如果单单只设置 baseShadowColor 也会自动转换成为 shadowColors  |
| corner | 阴影顶点的内侧弧度。以适配被设置的 View 是圆角的情况， 对使用 drawable 方式设置阴影时，该属性表示为圆角矩形背景的圆角角度 |
| shadowRadius | 阴影大小 |
| direction | 设定阴影在 View 上显示的方位， 支持的方位详情可以查看 [CrazyShadowDirection](https://github.com/Hitomis/CrazyShadow/blob/master/cslibrary/src/main/java/com/hitomi/cslibrary/base/CrazyShadowDirection.java) |

# Method

| 方法 | 说明 |
| :--: | :--: |
| make | 为 View 添加阴影效果， 使用 Builder 的 action 方法时会自动调用 |
| remove | 移除阴影|
| hide | 隐藏阴影，与 remove 不同的是，hide 只是隐藏了 View 周围的阴影效果，并没有移除 |
| show | 如果调用了 hide ，可以再使用 show 将阴影效果显示出来 | 

#Licence

      Copyright 2017 MagicMashRoom, Inc.
 


