<script setup lang="ts">
import { computed } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import type { MenuProps } from 'ant-design-vue'

export type HeaderMenuItem = {
  key: string
  label: string
  path?: string
  href?: string
}

// 通过 props 注入标题与菜单配置
const props = defineProps<{
  title: string
  menuItems: HeaderMenuItem[]
}>()

const router = useRouter()
const route = useRoute()

// 根据当前路由匹配高亮的菜单项
const selectedKeys = computed(() => {
  const matched = props.menuItems.find((item) => item.path === route.path)
  return matched ? [matched.key] : []
})

// 点击菜单时跳转到对应路由或外部链接
const handleMenuClick: MenuProps['onClick'] = ({ key }) => {
  const target = props.menuItems.find((item) => item.key === key)
  if (target?.path) {
    router.push(target.path)
  } else if (target?.href) {
    window.open(target.href, '_blank')
  }
}
</script>

<template>
  <div class="global-header">
    <div class="global-header__left">
      <img class="global-header__logo" src="@/assets/logo.png" alt="项目 Logo" />
      <span class="global-header__title">{{ title }}</span>
    </div>
    <a-menu
      class="global-header__menu"
      mode="horizontal"
      :selectedKeys="selectedKeys"
      @click="handleMenuClick"
    >
      <a-menu-item v-for="item in menuItems" :key="item.key">
        {{ item.label }}
      </a-menu-item>
    </a-menu>
    <div class="global-header__right">
      <!-- 暂时代替用户信息的登录按钮，可接入实际登录逻辑 -->
      <a-button type="primary">登录</a-button>
    </div>
  </div>
</template>

<style scoped>
.global-header {
  display: flex;
  align-items: center;
  gap: 16px;
  height: 64px;
  padding: 0 24px;
  background: #fff;
  border-bottom: 1px solid #f0f0f0;
}

.global-header__left {
  display: flex;
  align-items: center;
  flex-shrink: 0;
  gap: 8px;
}

.global-header__logo {
  width: 32px;
  height: 32px;
  object-fit: contain;
}

.global-header__title {
  font-size: 18px;
  font-weight: 600;
  color: #1f1f1f;
  white-space: nowrap;
}

.global-header__menu {
  flex: 1;
  background: transparent;
  min-width: 0;
}

.global-header__right {
  display: flex;
  justify-content: flex-end;
  flex-shrink: 0;
}

@media (max-width: 768px) {
  .global-header {
    flex-wrap: wrap;
    height: auto;
    gap: 12px;
    padding: 12px 16px;
  }

  .global-header__menu {
    order: 3;
    width: 100%;
  }

  .global-header__right {
    order: 2;
    width: 100%;
    justify-content: flex-start;
  }
}
</style>
