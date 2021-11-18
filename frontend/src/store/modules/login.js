import axios from "axios"
import SERVER from '@/api/api.js'

const state = {
  userinfo: {
    userToken: null,
    username: null,
  }
};

const actions = {
  getJWT: async function(context, credentials) {
    console.log(state)
    await axios({
      method: "post",
      url: `${SERVER.URL}/user/sign-in`,
      headers: {
        "Access-Control-Allow-Origin": "*",
      },
      data: credentials,
    })
      .then((res) => {
        console.log(res.data)
        context.commit("saveJWT", res.data)
      })
      .catch((err) => {
        console.log(err);
      });
  },
  deleteJWT: function(context) {
    context.commit("deleteJWT");
  },
};

const mutations = {
  saveJWT: function(state, data) {
    state.userinfo.userToken = data.jwt
    state.userinfo.username = data.name
    console.log(state)
  },
  deleteJWT: function(state) {
    state.userinfo.userToken = null
    state.userinfo.username = null
},
};

const getters = {
  // decodedToken: function (state) {
  //   if (state.userToken) {
  //     console.log(state.userToken);
  //     return jwt_decode(state.userToken);
  //   } else {
  //     return null;
  //   }
  // },
};

export default {
  namespaced: true,
  state,
  actions,
  mutations,
  getters,
};
