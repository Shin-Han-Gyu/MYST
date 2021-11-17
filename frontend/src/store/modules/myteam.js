import axios from "axios"
import SERVER from "@/api/api.js"

const state = {
  myteamlist: [{id:1, teamname:"a"},{id:2, teamname:"b"},{id:3, teamname: "c"}]
}

const actions = {
  getMyTeamList: function(context, token) {
    axios({
      method: "GET",
      url: `${SERVER.URL}/group/`,
      headers: {
        "Access-Control-Allow-Origin": "*",
        jwt: token
      }
    })
      .then((res) => {
        console.log(res.data)
        context.commit("GET_MYTEAM_LIST", res.data)
      })
      .catch((err) => {
        console.log(err)
      })
  }
}

const mutations = {
  GET_MYTEAM_LIST: function (state, data) {
    state.myteamlist = data
    console.log(state.myteamlist)
  }
}


export default {
  namespaced: true,
  state,
  actions,
  mutations,
}