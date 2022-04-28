# module 结构
fragmentation_core 这是基础核心

fragmentation 依赖fragmentation_core，增加了两个实现类： SupportFragment、SupportActivity

eventbus_activity_scope EventBus的辅助类，以 Activity 为作用域

fragmentation_swipeback  依赖了fragmentation。滑动Activity/Fragment边缘即可类似IOS一样，拖动返回 

# androidx中 ViewPager 和 ViewPager2 中加载 Fragment
vp2, 不会走 setUserVisibleHint()；比较 onSupportVisible和onResume，onResume 触发的次数正常。
而 androidx的 vp1，对应 adapter 的构造参数，可以传递一个 behavior， `@IntDef({BEHAVIOR_SET_USER_VISIBLE_HINT, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT}) private @interface Behavior { }`。
默认枚举值 BEHAVIOR_SET_USER_VISIBLE_HINT，其表现上，onSupportVisible 触发次数正常。 当传递 BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT，onResume 触发次数正常。

vp2，推荐把代码 写在 onResume 里。
vp1，使用默认 behavior 时，推荐 onSupportVisible；使用 BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT，推荐 onResume。

有3个 fragment的 DiscoverFragment 、DiscoverVp2Fragment，测试数据：
```
DiscoverFragment 用 vp1：
进入左侧 frag，触发 onResume:0， onResume:1， onSupportVisible:0；
进入中间 frag，触发 onResume:2， onSupportVisible:1；
进入右侧 frag，触发 onSupportVisible:2
前后台切换：
左侧  onResume:0， onResume:1， onSupportVisible:0；
在中间时，后台切到前台，onResume:0， onResume:1， onResume:2， onSupportVisible:1；
在右侧， onResume:1， onResume:2， onSupportVisible:2；

DiscoverVp2Fragment 用 vp2：
左右滑动时：
进入左侧 frag，触发 onResume:0， onSupportVisible:0；
进入中间 frag，触发 onSupportVisible:1， onResume:1；
进入右侧 frag，触发 onSupportVisible:2， onResume:2;
之后，再滑动切换， 执行顺序 onResume、onSupportVisible 

首次进入后，点击tab切换：
进入左侧 frag，触发 onResume:0， onSupportVisible:0；
tab1, 进入中间 frag，触发 onSupportVisible:1，onSupportVisible:2, onResume:1；
tab2, 进入右侧 frag，触发 onResume:2
之后，再点 tab 切换，又都正常了。
前后台切换：
左侧 onResume:0， onSupportVisible:0；
中间 onResume:1， onSupportVisible:1；
右侧 onResume:2， onSupportVisible:2；
```


****