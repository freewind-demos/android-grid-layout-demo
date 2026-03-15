# Android GridLayout 网格布局演示

## 简介

本 Demo 演示 Android GridLayout 的基本用法。

## 基本原理

GridLayout 是网格布局，使用行列定位子元素。

## 教程

```kotlin
val params = GridLayout.LayoutParams().apply {
    rowSpec = GridLayout.spec(row)
    columnSpec = GridLayout.spec(col)
}
```
