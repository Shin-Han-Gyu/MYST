import axios from "axios"
import SERVER from "@/api/api.js"

const state = {
  userToken: null,
  myteamlist: null
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
  }
}

const mutations = {
  GET_JWT: function (state, data) {
    state.userToken = data
  },
  GET_MY_TEAM_LIST: function (state, data) {
    state.myteamlist = data
    console.log(state)
  }
}


export default {
  namespaced: true,
  state,
  actions,
  mutations,
}