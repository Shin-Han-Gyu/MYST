import axios from "axios"
import SERVER from "@/api/api.js"

const state = {
  userToken: null,
  myteamlist: null,
  myTeamColor: []
}

const actions = {
  getTeamList: function(context, token) {
    context.commit("GET_JWT", token)
    axios({
      method: "get",
      url: `${SERVER.URL}/group/`,
      headers: {
        jwt: state.userToken
      },
    })
      .then((res) => {
        console.log(res.data)
        context.commit("GET_MY_TEAM_LIST", res.data)
      })
      .catch((err) => {
        console.log(err)
      })
  },
  getTeamColor: function(context, token) {
    axios({
      method: "get",
      url: `${SERVER.URL}/group/colors`,
      headers: {
        jwt: token
      },
    })
      .then((res) => {
        console.log(res.data)
        context.commit("GET_TEAM_COLOR", res.data)
      })
      .catch((err) => {
        console.log(err)
      })
  },
  colorChoose: function(context, color, token) {
    console.log(token)
    axios({
      method: "post",
      url: `${SERVER.URL}/group/colors`,
      headers: {
        jwt: state.userToken
      },
      data: color

    })
      .then((res) => {
        console.log(res)
      })
      .catch((err)=> {
        console.log(err)
      })
  }
}

const mutations = {
  GET_JWT: function (state, data) {
    state.userToken = data
  },
  GET_MY_TEAM_LIST: function (state, data) {
    state.myteamlist = data

    console.log(state)
  },
  GET_TEAM_COLOR: function (state, data) {
    state.myTeamColor = data
  }
}


export default {
  namespaced: true,
  state,
  actions,
  mutations,
}