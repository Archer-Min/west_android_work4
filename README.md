# 仿稀土掘金考核作业

还没全部完成 还差写文章和个人设置部分 明天再补（

评论功能卡了好久。。只实现了二级评论

## token存储 mmkv使用

在请求头里添加token拦截器

mmkv工具类封装

## `suspend`

`suspend` 是用于声明挂起函数的关键字。挂起函数是一种可以暂停执行并在稍后恢复的函数。在协程中执行异步操作

## ViewModel

### 观察数据更新

```kotlin
viewModel.essayList.observe(viewLifecycleOwner, Observer { essays ->
            essayAdapter.submitList(essays)
        })
```

- `viewModel.essayList.observe(...)`

  设置了一个观察者，当 ViewModel 中的数据发生变化时，会触发观察者的回调函数。

- `Observer { essays -> essayAdapter.submitList(essays) }`

  观察者的回调函数，当文章列表数据发生变化时，会将新数据 `essays` 提交给 RecyclerView 的 Adapter，从而更新列表的显示。

## 下拉列表Spinner

1. 在res/values/strings.xml文件中定义sorting_options字符串数组

2. ```kotlin
   arrayAdapter = ArrayAdapter(requireContext(),R.layout.item_spinner,resources.getStringArray(R.array.sorting_options))
   ```

3. ```kotlin
   spinner.onItemSelectedListener= object : AdapterView.OnItemSelectedListener {
               override fun onItemSelected(
                   parent: AdapterView<*>?,
                   view: View?,
                   position: Int,
                   id: Long
               ) {
                   if (position == 1){
                       
                   }else{
                       
                   }
               }
               override fun onNothingSelected(parent: AdapterView<*>?) {
                   TODO("not implemented")
               }
           }
   ```

## 输入弹窗

## 评论列表

## 出现的bug

1. ScrollerView里嵌套RecyclerView滑动冲突问题

   解决：RecyclerView外包一层RelativeLayout并添加`android:nestedScrollingEnabled="false"`

   但是出现数据显示不完全问题

2. 评论完后数据刷新会回到首页
