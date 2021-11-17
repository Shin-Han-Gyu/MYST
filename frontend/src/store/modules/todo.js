import axios from "axios"
import SERVER from "@/api/api.js"

const state = {
  userToken: null,
  todolist: [{id:1, todotitle:"1"},{id:2, todotitle:"2"},{id:3, todotitle: "3"}]
}

const actions = {
  getMyTodo: function(context, token) {
    // context.commit("GET_JWT", token)
    axios({
      method: "get",
      url: `${SERVER.URL}/task/`,
      headers: {
        "Access-Control-Allow-Origin": "*",
        jwt: token
      },
    })
      .then((res) => {
        console.log(res.data)
        context.commit("GET_MY_TODO", res.data)
      })
      .catch((err) => {
        console.log(err)
      })
  },
  createTodo: function(context, todo, token) {
    axios({
      method: "post",
      url: `${SERVER.URL}/task/`,
      headers: {
        "Access-Control-Allow-Origin": "*",
        jwt: token,
      },
      data: todo,
    })
      .then((res) => {
        console.log(res.data)
      })
      .catch((err) => {
        console.log(err)
      })
  }
}

const mutations = {
  // GET_JWT: function (state, data) {
  //   state.userToken = data
  // },
  GET_MY_TODO: function (state, data) {
    state.todolist2 = data
    console.log(state)
  }
}


export default {
  namespaced: true,
  state,
  actions,
  mutations,
}