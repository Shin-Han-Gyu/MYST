import { createStore } from 'vuex'
import createPersistedState from 'vuex-persistedstate'
import login from './modules/login'
import todo from './modules/todo'
import myteam from './modules/myteam'

export default createStore({
  plugins: [
    createPersistedState({
      paths: ['login'],
    }),
  ],
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
