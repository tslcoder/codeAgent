<script setup lang="ts">
import { computed } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { type MenuProps, message } from 'ant-design-vue'
import { useLoginUserStore } from '@/stores/loginUser.ts'
import { LogoutOutlined } from '@ant-design/icons-vue'
import { userLogout } from '@/api/userController.ts'
//获取用户登陆状态
const loginUser = useLoginUserStore()

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

const visibleMenuItems = computed(() => {
  const isAdmin = loginUser.loginUser?.userRole === 'admin'
  return props.menuItems.filter((item) => {
    if (item.path?.startsWith('/admin')) {
      return isAdmin
    }
    return true
  })
})

// 根据当前路由匹配高亮的菜单项
const selectedKeys = computed(() => {
  const matched = visibleMenuItems.value.find((item) => item.path === route.path)
  return matched ? [matched.key] : []
})

// 点击菜单时跳转到对应路由或外部链接
const handleMenuClick: MenuProps['onClick'] = ({ key }) => {
  const target = visibleMenuItems.value.find((item) => item.key === key)
  if (target?.path) {
    router.push(target.path)
  } else if (target?.href) {
    window.open(target.href, '_blank')
  }
}
//用户注销
const doLogOut = async () => {
  const res = await userLogout()
  if (res.data.code === 0) {
    loginUser.setLoginUser({
      userName: '未登录',
    })
    message.success('退出登陆成功')
    await router.push({
      path: '/user/login',
    })
  } else {
    message.error('退出登陆失败 ' + res.data.message)
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
      <a-menu-item v-for="item in visibleMenuItems" :key="item.key">
        {{ item.label }}
      </a-menu-item>
    </a-menu>
    <div class="global-header__right">
      <!-- 接入实际登陆逻辑 -->
      <div v-if="loginUser.loginUser.id">
        <a-dropdown>
          <a-space>
            <a-avatar :src="loginUser.loginUser.userAvatar" />
            {{ loginUser.loginUser.userName ?? '无名' }}
          </a-space>
          <template #overlay>
            <a-menu>
              <a-menu-item @click="doLogOut">
                <LogoutOutlined />
                退出登陆
              </a-menu-item>
            </a-menu>
          </template>
        </a-dropdown>
      </div>
      <div v-else>
        <a-button type="primary" href="/user/login">登录</a-button>
      </div>
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
