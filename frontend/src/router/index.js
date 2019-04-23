import Vue from 'vue'
import Router from 'vue-router'
import Index from '@/components/Index'
import Sub from '@/components/Sub'
import Regist from '@/components/Regist'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Index',
      component: Index
    },
    {
      path: '/sub',
      name: 'Sub',
      component: Sub
    },
    {
      path: '/regist',
      name: 'Regist',
      component: Regist
    }
  ]
})
