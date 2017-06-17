# SuperShadow

   SuperShadow 是一个专门为 View 添加阴影效果的库。 SuperShadow 支持自定义阴影实现方式、阴影颜色、阴影大小，圆角大小、阴影附加方向。
   
# Example

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
	        compile 'com.github.MagicMashRoom:SuperShadow:v1.0.0'
	}


# Usage
    ​
        superWrapShadowDirectionExample = new SuperShadow.Builder()
                            .setContext(this)
                            .setDirection(ShadowDirection.LEFT)
                            .setShadowSize(dip2Px(8))
                            .setCorner(dip2Px(4))
                            .setBaseShadowColor(Color.parseColor("#99cc00"))
                            .setImpl(SuperShadow.WRAP)
                            .action(wrapShadowLeftExample);
    ​

# Attribute

| 属性 | 说明 |
| :--: |  :--: |
| impl | 以何种方式添加阴影，支持 wrap、drawable 两种方式 （SuperShadow.WRAP SuperShadow.DRAW） |
| baseShadowColor | 阴影的基本颜色，即最深的颜色，如果不主动设置colors的话，将会自动生成由baseShadowColor为最深颜色渐变到完全透明的一个长度为三的数组|
| background | 修改 View 的背景色，如果使用 drawable 方式添加阴影，那么该属性必须添加 |
| shadowColors | 绘制阴影时需要的一个颜色数组,该数组的长度为三, 通过设置该数组, 会将你放在数组的颜色转化为你阴影的颜色 |
| corner | 阴影顶点的内侧弧度。以适配被设置的 View 是圆角的情况， 对使用 drawable 方式设置阴影时，该属性表示为圆角矩形背景的圆角角度 |
| shadowSize | 阴影大小 |
| direction | 设定阴影在 View 上显示的方位 |

# Method

| 方法 | 说明 |
| :--: | :--: |
| make | 为 View 添加阴影效果， 使用 Builder 的 action 方法时会自动调用 |
| remove | 移除阴影|
| hide | 隐藏阴影，与 remove 不同的是，hide 只是隐藏了 View 周围的阴影效果，并没有移除 |
| show | 如果调用了 hide ，可以再使用 show 将阴影效果显示出来 | 

# Licence

      Copyright 2017 MagicMashRoom, Inc.
 


