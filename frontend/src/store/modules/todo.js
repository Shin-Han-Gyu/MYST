import axios from "axios"
import SERVER from "@/api/api.js"

const state = {
  userToken: null,
  todolist: []
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
  createTodo: function(context, ele) {
    
    axios({
      method: "post",
      url: `${SERVER.URL}/task/${ele.createTeam}`,
      headers: {
        "Access-Control-Allow-Origin": "*",
        jwt: ele.userToken,
      },
      data: ele.newtodo,
    })
      .then((res) => {
        console.log(res.data)
        actions.getMyTodo(context, ele.userToken)
      })
      .catch((err) => {
        console.log(err)
      })
  },
  checkTodo: function(context, data) {
    if (data.teamId == -1) {
      data.teamId = ""
    }
    axios({
      method: 'patch',
      url: `${SERVER.URL}/task/${data.teamId}`,
      headers: {
        jwt: data.token
      },
      data: data.taskId
    })
      .then((res) => {
        console.log(res)
        actions.getMyTodo(context, data.token)
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
    state.todolist = data
    console.log(state)
  }
}


export default {
  namespaced: true,
  state,
  actions,
  mutations,
}