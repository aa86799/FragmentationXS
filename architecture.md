# module 结构
fragmentation_core 这是基础核心

fragmentation 依赖fragmentation_core，增加了两个实现类： SupportFragment、SupportActivity

eventbus_activity_scope EventBus的辅助类，以 Activity 为作用域

fragmentation_swipeback  依赖了fragmentation。滑动Activity/Fragment边缘即可类似IOS一样，拖动返回 


****