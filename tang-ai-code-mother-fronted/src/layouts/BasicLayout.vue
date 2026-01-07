<script setup lang="ts">
import { RouterView } from 'vue-router'
import GlobalHeader, { type HeaderMenuItem } from '@/components/GlobalHeader.vue'
import GlobalFooter from '@/components/GlobalFooter.vue'

// 顶部导航菜单配置，可按需扩展
const menuItems: HeaderMenuItem[] = [
  { key: 'home', label: '首页', path: '/' },
  { key: 'about', label: '关于我们', path: '/about' },
  { key: 'code', label: '编程导航', href: 'https://www.codefather.cn' }
]
</script>

<template>
  <a-layout class="basic-layout">
    <!-- 顶部区域：展示导航栏 -->
    <a-layout-header class="basic-layout__header">
      <GlobalHeader title="唐林应用生成" :menu-items="menuItems" />
    </a-layout-header>
    <!-- 中间区域：根据路由渲染内容 -->
    <a-layout-content class="basic-layout__content">
      <RouterView />
    </a-layout-content>
    <!-- 底部区域：全局版权信息 -->
    <a-layout-footer class="basic-layout__footer">
      <GlobalFooter />
    </a-layout-footer>
  </a-layout>
</template>

<style scoped>
.basic-layout {
  min-height: 100vh;
  background: #f5f5f5;
  display: flex;
  flex-direction: column;
}

.basic-layout__header {
  padding: 0;
  background: transparent;
}

.basic-layout__content {
  flex: 1;
  padding: 24px;
}

.basic-layout__footer {
  background: #fff;
  border-top: 1px solid #f0f0f0;
}

@media (max-width: 768px) {
  .basic-layout__content {
    padding: 16px;
  }
}
</style>
