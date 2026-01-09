import { createApp } from 'vue'
import { createPinia } from 'pinia'

// @ts-ignore: Vue SFC type missing declaration; create src/shims-vue.d.ts to declare '*.vue' modules for a proper fix
import App from './App.vue'
import router from './router'

import Antd from 'ant-design-vue'
import 'ant-design-vue/dist/reset.css'
import '@/access.ts'

const app = createApp(App)

app.use(createPinia())
app.use(router)
app.use(Antd)

app.mount('#app')
