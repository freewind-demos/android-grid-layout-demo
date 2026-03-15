# Android GridLayout 网格布局演示

## 简介

本 Demo 演示 Android GridLayout 的基本用法，展示如何创建网格化的 UI 布局。

## 基本原理

GridLayout 是 Android 提供的网格布局管理器，采用行列定位方式排列子元素。它是 Android 4.0 引入的轻量级布局，比 TableLayout 更灵活，比 RelativeLayout 更简单。

GridLayout 的特点：
- 使用行列坐标定位元素（从0开始计数）
- 支持设置跨行（rowSpan）和跨列（columnSpan）
- 子元素按添加顺序从左到右、从上到下排列
- 支持设置对齐方式

GridLayout 的优势：
- 布局层级更少，性能更好
- 可以精确控制每个元素的位置
- 支持单元格的合并（跨行跨列）

## 启动和使用

### 环境要求
- Android Studio
- JDK 17
- Gradle 8.x

### 安装和运行

1. 用 Android Studio 打开项目
2. 连接 Android 设备或模拟器
3. 点击 Run 运行

### 使用方法
- 运行后将看到一个 3x3 的按钮网格布局

## 教程

### 什么是 GridLayout？

GridLayout 是一个二维网格布局，将容器划分为行和列的网格，每个子元素可以占据一个或多个网格单元。它特别适合需要规则排列的场景，如计算器键盘、表格数据、图片网格等。

GridLayout 与其他布局的区别：
- **LinearLayout**：只能单行或单列排列
- **RelativeLayout**：相对定位，位置关系较复杂
- **GridLayout**：规则网格，定位直观

### 基本属性

在 XML 中设置 GridLayout 的行列数：

```xml
<GridLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:columnCount="3"
    android:rowCount="3" />
```

- `columnCount`：列数
- `rowCount`：行数

### 在代码中动态添加子元素

可以通过代码动态创建子元素并设置网格位置：

```kotlin
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gridLayout = findViewById<GridLayout>(R.id.gridLayout)

        // 创建 9 个按钮，排成 3x3 网格
        for (i in 1..9) {
            val btn = Button(this)
            btn.text = "按钮$i"

            // 设置网格位置参数
            val params = GridLayout.LayoutParams().apply {
                // 设置行位置：(i-1) / 3 = 0,1,2
                rowSpec = GridLayout.spec((i-1) / 3)
                // 设置列位置：(i-1) % 3 = 0,1,2
                columnSpec = GridLayout.spec((i-1) % 3)
                // 宽度设为0，配合 columnSpec 的权重实现均分
                width = 0
                // 高度自适应
                height = GridLayout.LayoutParams.WRAP_CONTENT
            }

            gridLayout.addView(btn, params)
        }
    }
}
```

### 设置跨行跨列

GridLayout 支持一个元素占据多个单元格：

```kotlin
// 跨两行
rowSpec = GridLayout.spec(row, 2)

// 跨两列
columnSpec = GridLayout.spec(column, 2)

// 同时跨行跨列
rowSpec = GridLayout.spec(row, 2)
columnSpec = GridLayout.spec(column, 2)
```

### 对齐方式

可以设置子元素在网格中的对齐方式：

```kotlin
val params = GridLayout.LayoutParams().apply {
    // 设置对齐方式
    setGravity(Gravity.CENTER)  // 居中
    // 或使用特定对齐
    setGravity(Gravity.LEFT or Gravity.TOP)  // 左上
}
```

### 注意事项

1. **行高列宽**：GridLayout 默认根据子元素自动调整行高列宽
2. **权重分配**：将宽度/高度设为0，然后设置 gravity 的权重比可以实现均分
3. **索引越界**：确保 rowSpec 和 columnSpec 的索引在有效范围内
4. **性能**：GridLayout 层级少，比嵌套的 LinearLayout 性能更好

## 关键代码详解

### activity_main.xml

```xml
<!-- 根布局：垂直线性布局 -->
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="16dp">

    <!-- 标题 -->
    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="GridLayout 网格布局演示"
        android:textSize="20sp"
        android:textStyle="bold"
        android:gravity="center"
        android:paddingBottom="16dp" />

    <!-- GridLayout：3列3行 -->
    <GridLayout
        android:id="@+id/gridLayout"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:columnCount="3"
        android:rowCount="3" />
</LinearLayout>
```

### MainActivity.kt

```kotlin
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. 获取 GridLayout 实例
        val gridLayout = findViewById<GridLayout>(R.id.gridLayout)

        // 2. 循环创建 9 个按钮
        for (i in 1..9) {
            val btn = Button(this)
            btn.text = "按钮$i"

            // 3. 创建布局参数，指定在网格中的位置
            val params = GridLayout.LayoutParams().apply {
                // 计算当前行号：(i-1) / 3 -> i=1时0行，i=4时1行，i=7时2行
                rowSpec = GridLayout.spec((i-1) / 3)
                // 计算当前列号：(i-1) % 3 -> i=1时0列，i=2时1列，i=3时2列
                columnSpec = GridLayout.spec((i-1) % 3)
                // 宽度设为0，配合 GridLayout 的自动分配
                width = 0
                // 高度自适应
                height = GridLayout.LayoutParams.WRAP_CONTENT
            }

            // 4. 将按钮添加到 GridLayout
            gridLayout.addView(btn, params)
        }
    }
}
```
