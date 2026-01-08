import {createRouter, createWebHistory} from 'vue-router'
import HomePage from "@/pages/HomePage.vue";
import UserLoginPage from "@/pages/user/UserLoginPage.vue";
import UserManagePage from "@/pages/admin/UserManagePage.vue";
import UserRegsiterPage from "@/pages/user/UserRegsiterPage.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomePage,
    },
    {
      path: '/user/register',
      name: '用户注册',
      component: UserRegsiterPage,
    },
    {
      path: '/user/login',
      name: '用户登陆',
      component: UserLoginPage,
    },
    {
      path: '/admin/userManage',
      name: '用户管理',
      component: UserManagePage,
    }
  ],
})

export default router
