import { createStore } from 'vuex'
import login from './modules/login'
import todo from './modules/todo'
import myteam from './modules/myteam'

export default createStore({
  state: {
  },
  mutations: {
  },
  actions: {
  },
  modules: {
    login,
    todo,
    myteam
  }
})
